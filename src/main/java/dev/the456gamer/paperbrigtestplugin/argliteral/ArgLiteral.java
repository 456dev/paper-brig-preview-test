package dev.the456gamer.paperbrigtestplugin.argliteral;

import net.kyori.adventure.text.Component;

public class ArgLiteral {

  private final String literal;

  private final Component tooltip;

  public ArgLiteral(String literal, Component tooltip) {
    this.literal = literal;
    this.tooltip = tooltip;
  }

  public static ArgLiteral fliteral(String literal, Component tooltip) {
    return new ArgLiteral(literal, tooltip);
  }

  public String getLiteral() {
    return literal;
  }

  public Component getTooltip() {
    return tooltip;
  }
}
