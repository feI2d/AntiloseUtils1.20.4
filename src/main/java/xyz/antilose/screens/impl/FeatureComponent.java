package xyz.antilose.screens.impl;

import net.minecraft.client.gui.DrawContext;
import xyz.antilose.MinecraftWrapper;
import xyz.antilose.api.math.MathApi;
import xyz.antilose.api.render.RenderApi;
import xyz.antilose.features.Module;

import java.awt.*;

public class FeatureComponent extends AbstractComponent implements MinecraftWrapper {

    private Module module;
    private float animX;

    public FeatureComponent(Module module) {
        this.module = module;
    }

    @Override
    public void onRender(DrawContext context, int mouseX, int mouseY, float delta) {
        animX = MathApi.fast(animX, MathApi.isHovered(mouseX, mouseY, getX(), getY(), getWidth(), getHeight()) ? 5.0f : 0.0f, 10);

        RenderApi.drawRect(context, getX(), getY(), getWidth(), getHeight(), new Color(42, 42, 42, 255));
        context.drawTextWithShadow(mc.textRenderer, module.getName(), (int) (getX() + 5 + animX), (int) (getY() + 7f), MathApi.isHovered(mouseX, mouseY, getX(), getY(), getWidth(), getHeight()) ? -1 : new Color(200, 200, 200).getRGB());

        if (MathApi.isHovered(mouseX, mouseY, getX(), getY(), getWidth(), getHeight())) {
            String status = module.isEnabled() ? "on" : "off";
            RenderApi.drawRect(context, getX() + getWidth() + 5.0f, getY() + 2.5f, mc.textRenderer.getWidth(module.getDescription() + " (Status: " + status + ")") + 10, getHeight() - 5.0f, new Color(42, 42, 42, 255));
            context.drawTextWithShadow(mc.textRenderer, module.getDescription() + " (Status: " + status + ")", (int) (getX() + getWidth() + 10f), (int) (getY() + 7f), -1);
        }

    }

    @Override
    public void onMouseClick(double mouseX, double mouseY, int button) {
        if (MathApi.isHovered(mouseX, mouseY, getX(), getY(), getWidth(), getHeight())) {
            module.toggle();
        }
    }
}
