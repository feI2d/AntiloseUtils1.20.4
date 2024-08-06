package xyz.antilose;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;

public interface MinecraftWrapper {

    MinecraftClient mc = MinecraftClient.getInstance();
    Window window = mc.getWindow();

}
