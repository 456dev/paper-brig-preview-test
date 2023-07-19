package dev.the456gamer.paperbrigtestplugin.extliteral;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.kyori.adventure.text.Component;


// works for console, doesn't work for client (suggestions not sent to server for literal, client side issue)
// if other args get sent to server, you can send literals as well?
public class ExtLiteralArgumentBuilder<S> extends
    LiteralArgumentBuilder<S> {

  private final Component tooltip;

  protected ExtLiteralArgumentBuilder(final String literal, final Component tooltip) {
    super(literal);
    this.tooltip = tooltip;
  }

  public static <S> ExtLiteralArgumentBuilder<S> literal(
      final String name, final Component tooltip) {
    return new ExtLiteralArgumentBuilder<>(name, tooltip);
  }

  @Override
  public LiteralCommandNode<S> build() {
    final ExtLiteralCommandNode<S> result = new ExtLiteralCommandNode<>(getLiteral(), getCommand(),
        getRequirement(), getRedirect(), getRedirectModifier(), isFork(), getTooltip());

    for (final CommandNode<S> argument : getArguments()) {
      result.addChild(argument);
    }

    return result;
  }

  public Component getTooltip() {
    return tooltip;
  }
}
