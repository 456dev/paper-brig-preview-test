package dev.the456gamer.paperbrigtestplugin.fancyliteral;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;

public class FancyLiteralArgumentBuilder {

  public FancyLiteralArgumentBuilder() {
  }

  public static ArgumentBuilder<CommandSourceStack, ?> literal(String literal, Component tooltip) {
    FancyLiteral fancyLiteral = FancyLiteral.fliteral(literal, tooltip);
    return RequiredArgumentBuilder.argument(literal, new FancyLiteralArgument(fancyLiteral));
  }
}
