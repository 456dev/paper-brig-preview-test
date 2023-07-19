package dev.the456gamer.paperbrigtestplugin.argliteral;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import io.papermc.paper.command.brigadier.argument.CustomArgumentType;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.NotNull;


// works for console, doesn't work for client > 1 arg (suggestions from server not displayed)
public class ArgLiteralArgument extends CustomArgumentType<ArgLiteral, String> {

  ArgLiteral argLiteral;
  String literal;
  String literalLowerCase;

  public ArgLiteralArgument(ArgLiteral argLiteral) {
    super(StringArgumentType.word());
    this.argLiteral = argLiteral;
    this.literal = argLiteral.getLiteral();
    this.literalLowerCase = argLiteral.getLiteral().toLowerCase(Locale.ROOT);
  }

  @Override
  public @NotNull ArgLiteral convert(@NotNull String baseType) throws CommandSyntaxException {
    if (baseType.equals(literal)) {
      return argLiteral;
    }
    throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.literalIncorrect().create(argLiteral.getLiteral());
  }

  @Override
  public boolean handleSuggestions() {
        return true;
    }

  @Override
  public <S> @NotNull CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context,
      SuggestionsBuilder builder) {
    if (literalLowerCase.startsWith(builder.getRemainingLowerCase()) || literalLowerCase.equalsIgnoreCase(
        builder.getRemainingLowerCase())) {
      return builder.suggest(literal, MessageComponentSerializer.message().serialize(argLiteral.getTooltip()))
          .buildFuture();
    } else {
      return Suggestions.empty();
    }
  }
}
