package dev.the456gamer.paperbrigtestplugin;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.AQUA;
import static net.kyori.adventure.text.format.NamedTextColor.DARK_AQUA;
import static net.kyori.adventure.text.format.NamedTextColor.GOLD;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;
import static net.kyori.adventure.text.format.NamedTextColor.LIGHT_PURPLE;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

import com.destroystokyo.paper.event.brigadier.AsyncPlayerSendSuggestionsEvent;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.the456gamer.paperbrigtestplugin.argliteral.ArgLiteralArgumentBuilder;
import dev.the456gamer.paperbrigtestplugin.extliteral.ExtLiteralArgumentBuilder;
import dev.the456gamer.paperbrigtestplugin.util.DummyNamedPlugin;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import io.papermc.paper.event.server.ServerResourcesLoadEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Brigtest extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    // Plugin startup logic
    getServer().getPluginManager().registerEvents(this, this);

    getServer().getCommandMap().register("testpl", new Command("abc123test") {
      @Override
      public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel,
          @NotNull String[] args) {
        sender.sendMessage(text("bla command map command?"));
        return false;
      }
    });
  }

  // todo: more tests
  // custom inherited argument node (?) not sure if possible due to wrapping (yes it is, just add child of root / command node)
  // in particular, override is_valid to see if server changes or client side only / console only

  @EventHandler
  public void load(ServerResourcesLoadEvent event) {
    getLogger().info("sauce load event fired");

    event.getCommands().register(new DummyNamedPlugin("custom"),
        LiteralArgumentBuilder.<CommandSourceStack>literal("give")
            .then(ExtLiteralArgumentBuilder.<CommandSourceStack>literal("EXTBAR",
                    text("COOL! TOOLTIP!", NamedTextColor.YELLOW))
                .executes(context -> {
                  context.getSource().getSender()
                      .sendMessage(text("EXTBAR subCommand", GOLD));
                  return 1;
                }))
            .then(ExtLiteralArgumentBuilder.<CommandSourceStack>literal("BAZ",
                    text("BAD! TOOLTIP!", NamedTextColor.DARK_GREEN))
                .executes(context -> {
                  context.getSource().getSender()
                      .sendMessage(text("FOO subBAZ", AQUA));
                  return 1;
                }))
            .then(ExtLiteralArgumentBuilder.<CommandSourceStack>literal("BLA",
                    text("COOL! TOOLTIP!", GREEN))
                .executes(context -> {
                  context.getSource().getSender()
                      .sendMessage(text("Brig subCommand", LIGHT_PURPLE));
                  return 1;
                }))
            .then(ArgLiteralArgumentBuilder.literal("FOO",
                    text("BAD! TOOLTIP!", RED))
                .executes(context -> {
                  context.getSource().getSender()
                      .sendMessage(text("FOO sub", DARK_AQUA));
                  return 1;
                }))
//            .then(ArgLiteralArgumentBuilder.literal("a", text("b"), "argb")
//                .executes(context -> {
//                  context.getSource().getSender().sendMessage(text("custom argtype builder"));
//                  return 1;
//                }))
//            .then(RequiredArgumentBuilder.<CommandSourceStack, Double>argument("value",
//                    DoubleArgumentType.doubleArg()).requires(
//                    commandSourceStack -> commandSourceStack.getSender().hasPermission("custom.aaa"))
//                .executes(context -> {
//                  Double value = context.getArgument("value", Double.class);
//                  context.getSource().getSender()
//                      .sendMessage(text("value=%f".formatted(value), RED));
//                  return 1;
//                }))
            .executes((ct) -> {
              ct.getSource().getSender().sendMessage(text("Brig Command", RED));
              return 1;
            }).build());
    event.getCommands()
        .register(this, LiteralArgumentBuilder.<CommandSourceStack>literal("testabc")
            .then(LiteralArgumentBuilder.<CommandSourceStack>literal("aaa")
                .executes(context -> {
                  context.getSource().getSender().sendMessage(text("AAA"));
                  return 1;
                }))
            .then(ArgLiteralArgumentBuilder.literal("FOO",
                    text("BAD! TOOLTIP!", RED))
                .executes(context -> {
                  context.getSource().getSender()
                      .sendMessage(text("FOO sub", DARK_AQUA));
                  return 1;
                }))
            .executes((ct) -> {
              ct.getSource().getSender().sendMessage(text("Brig 2 Command", GREEN));
              return 1;
            }).build());
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }


  @EventHandler
  public void onPlayerSendSuggestionsEvent(
      AsyncPlayerSendSuggestionsEvent event) {
    System.out.println("player send suggestion event %s".formatted(event.getBuffer()));
    event.getSuggestions().getList().forEach(suggestion -> System.out.println(
        "suggestion: %s[%d:%d] (%s) ".formatted(suggestion.getText(),
            suggestion.getRange().getStart(),
            suggestion.getRange().getEnd(),
            MessageComponentSerializer.message().deserializeOrNull(suggestion.getTooltip()))));
  }
}
