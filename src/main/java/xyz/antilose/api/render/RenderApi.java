package xyz.antilose.api.render;

import lombok.experimental.UtilityClass;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

@UtilityClass
public class RenderApi {

    public void drawRect(DrawContext context, float x, float y, float width, float height, Color color) {
        context.fill((int) x,(int)y,(int)(x + width),(int)(y + height), color.getRGB());
    }

}
