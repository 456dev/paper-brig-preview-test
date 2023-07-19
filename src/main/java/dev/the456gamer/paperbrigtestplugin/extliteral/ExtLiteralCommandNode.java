package dev.the456gamer.paperbrigtestplugin.extliteral;

import static net.kyori.adventure.text.Component.text;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import net.kyori.adventure.text.Component;

public class ExtLiteralCommandNode<S> extends LiteralCommandNode<S> {

  private final String literal;
  private final String literalLowerCase;

  private final Component tooltip;

  private static final Message defaultTooltip = MessageComponentSerializer.message()
      .serialize(text("Default"));

  public ExtLiteralCommandNode(final String literal, final Command<S> command,
      final Predicate<S> requirement, final CommandNode<S> redirect,
      final RedirectModifier<S> modifier, final boolean forks, final Component tooltip) {
    super(literal, command, requirement, redirect, modifier, forks);
    this.literal = literal;
    this.literalLowerCase = literal.toLowerCase(Locale.ROOT);
    this.tooltip = tooltip;
  }


  @Override
  public CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context,
      final SuggestionsBuilder builder) {
    if (literalLowerCase.startsWith(builder.getRemainingLowerCase())) {
      return builder.suggest(literal,
          MessageComponentSerializer.message().serializeOr(tooltip, defaultTooltip)).buildFuture();
    } else {
      return Suggestions.empty();
    }
  }


  @Override
  public LiteralArgumentBuilder<S> createBuilder() {
    final ExtLiteralArgumentBuilder<S> builder = ExtLiteralArgumentBuilder.<S>literal(this.literal,
        this.tooltip);
    builder.requires(getRequirement());
    builder.forward(getRedirect(), getRedirectModifier(), isFork());
    if (getCommand() != null) {
      builder.executes(getCommand());
    }
    return builder;
  }

  @Override
  public String toString() {
    return "<extliteral " + literal + ">";
  }

}
