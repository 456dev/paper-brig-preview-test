package dev.the456gamer.paperbrigtestplugin;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.DARK_AQUA;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;
import static net.kyori.adventure.text.format.NamedTextColor.LIGHT_PURPLE;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.the456gamer.paperbrigtestplugin.fancyliteral.FancyLiteralArgumentBuilder;
import dev.the456gamer.paperbrigtestplugin.util.DummyNamedPlugin;
import io.papermc.paper.command.brigadier.CommandBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.event.server.ServerResourcesLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Brigtest extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    // Plugin startup logic
    getServer().getPluginManager().registerEvents(this, this);
  }

  @EventHandler
  public void load(ServerResourcesLoadEvent event) {
    getLogger().info("resource load event fired");
    event.getCommands().register(
        CommandBuilder.newCommandBuilder(new DummyNamedPlugin("custom"), "testcommand")
            .then(FancyLiteralArgumentBuilder.literal("BLA",
                    text("COOL! TOOLTIP!", GREEN))
                .executes(context -> {
                  context.getSource().getSender()
                      .sendMessage(text("Brig subCommand", LIGHT_PURPLE));
                  return 1;
                }))
            .then(FancyLiteralArgumentBuilder.literal("FOO",
                    text("BAD! TOOLTIP!", RED))
                .executes(context -> {
                  context.getSource().getSender()
                      .sendMessage(text("FOO sub", DARK_AQUA));
                  return 1;
                }))
//            .then(FancyLiteralArgumentBuilder.literal("a", text("b"), "argb")
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
            }));
    event.getCommands()
        .register(CommandBuilder.newCommandBuilder(this, "test2")
            .then(LiteralArgumentBuilder.<CommandSourceStack>literal("aaa")
                .executes(context -> {
                  context.getSource().getSender().sendMessage(text("AAA"));
                  return 1;
                }))
            .then(LiteralArgumentBuilder.<CommandSourceStack>literal("bbb")
                .executes(context -> {
                  context.getSource().getSender().sendMessage(text("BBB"));
                  return 1;
                }))
            .executes((ct) -> {
              ct.getSource().getSender().sendMessage(text("Brig 2 Command", GREEN));
              return 1;
            }));
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
