package dev.the456gamer.paperbrigtestplugin.argliteral;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;

public class ArgLiteralArgumentBuilder {

  public ArgLiteralArgumentBuilder() {
  }

  public static ArgumentBuilder<CommandSourceStack, ?> literal(String literal, Component tooltip) {
    ArgLiteral argLiteral = ArgLiteral.fliteral(literal, tooltip);
//    LiteralArgumentBuilder.literal(literal)

    return RequiredArgumentBuilder.argument(literal, new ArgLiteralArgument(argLiteral));
  }
}
