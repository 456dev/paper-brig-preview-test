package dev.the456gamer.paperbrigtestplugin.fancyliteral;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import io.papermc.paper.command.brigadier.argument.CustomArgumentType;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;

public class FancyLiteralArgument extends CustomArgumentType<FancyLiteral, String> {

  FancyLiteral fancyLiteral;
  String literal;
  String literalLowerCase;

  public FancyLiteralArgument(FancyLiteral fancyLiteral) {
    super(StringArgumentType.word());
    this.fancyLiteral = fancyLiteral;
    this.literal = fancyLiteral.getLiteral();
    this.literalLowerCase = fancyLiteral.getLiteral().toLowerCase(Locale.ROOT);
  }

  @Override
  public @NotNull FancyLiteral convert(@NotNull String baseType) throws CommandSyntaxException {
    if (baseType.equals(literal)) {
      return fancyLiteral;
    }
    throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.literalIncorrect().create(fancyLiteral.getLiteral());
  }

  @Override
  public boolean handleSuggestions() {
        return true;
    }

  @Override
  public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context,
      SuggestionsBuilder builder) {
    if (literalLowerCase.startsWith(builder.getRemainingLowerCase()) || literalLowerCase.equalsIgnoreCase(
        builder.getRemainingLowerCase())) {
      return builder.suggest(literal, MessageComponentSerializer.message().serialize(fancyLiteral.getTooltip()))
          .buildFuture();
    } else {
      return Suggestions.empty();
    }
  }
}
