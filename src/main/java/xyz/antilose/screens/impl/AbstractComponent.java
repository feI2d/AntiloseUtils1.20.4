package xyz.antilose.screens.impl;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.DrawContext;

@Getter
@Setter
public abstract class AbstractComponent {

    private float x, y, width, height;

    public abstract void onRender(DrawContext context, int mouseX, int mouseY, float delta);

    public abstract void onMouseClick(double mouseX, double mouseY, int button);

}
