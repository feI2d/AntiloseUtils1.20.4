package xyz.antilose.api.math;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;

public class MathApi {

    public static boolean isHovered(float mouseX, float mouseY, float x, float y, float width, float height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public static boolean isHovered(double mouseX, double mouseY, float x, float y, float width, float height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public static double deltaTime() {
        return MinecraftClient.getInstance().getCurrentFps() > 0 ? 1.0 / MinecraftClient.getInstance().getCurrentFps() : 1.0;
    }

    public static float fast(float end, float start, float multiple) {
        return (1.0F - MathHelper.clamp((float)(deltaTime() * (double)multiple), 0.0F, 1.0F)) * end + MathHelper.clamp((float)(deltaTime() * (double)multiple), 0.0F, 1.0F) * start;
    }

}
