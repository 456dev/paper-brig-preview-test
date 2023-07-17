package dev.the456gamer.paperbrigtestplugin.fancyliteral;

import net.kyori.adventure.text.Component;

public class FancyLiteral {

  private final String literal;

  private final Component tooltip;

  public FancyLiteral(String literal, Component tooltip) {
    this.literal = literal;
    this.tooltip = tooltip;
  }

  public static FancyLiteral fliteral(String literal, Component tooltip) {
    return new FancyLiteral(literal, tooltip);
  }

  public String getLiteral() {
    return literal;
  }

  public Component getTooltip() {
    return tooltip;
  }
}
