package xyz.antilose.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import xyz.antilose.AntiloseUtils;
import xyz.antilose.MinecraftWrapper;
import xyz.antilose.api.render.RenderApi;
import xyz.antilose.features.Module;
import xyz.antilose.screens.impl.FeatureComponent;

import java.awt.*;
import java.util.ArrayList;

public class MenuScreen extends Screen implements MinecraftWrapper {

    private ArrayList<FeatureComponent> features = new ArrayList<>();

    public MenuScreen() {
        super(Text.literal("Menu"));
        for (Module module : AntiloseUtils.INSTANCE.getModuleManager().getModules()) {
            features.add(new FeatureComponent(module));
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        float width = 100.0f;
        float height = 20.0f;
        float x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2.0f - width / 2.0f;
        float y = MinecraftClient.getInstance().getWindow().getScaledHeight() / 2.0f - height / 2.0f;
        float yOff = 0;

        RenderApi.drawRect(context, x, y, width, height, new Color(21, 21, 21));
        context.drawCenteredTextWithShadow(mc.textRenderer, "Modules", (int) (x + width / 2.0f), (int) (y + 6f), -1);
        for (FeatureComponent featureComponent : features) {
            featureComponent.setX(x);
            featureComponent.setY(y + 20 + yOff);
            featureComponent.setWidth(width);
            featureComponent.setHeight(20.0f);
            featureComponent.onRender(context, mouseX, mouseY, delta);

            yOff += 20.0f;
            height += 20.0f;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        features.forEach(f -> f.onMouseClick(mouseX, mouseY, button));
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
