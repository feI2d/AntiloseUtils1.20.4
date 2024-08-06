package xyz.antilose.settings;

import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import xyz.antilose.AntiloseUtils;

public class ModKeybinds {

    public static KeyBinding autoSprintBind;
    public static KeyBinding fullBrightBind;
    public static KeyBinding noCrystalDelayBind;
    public static KeyBinding noJumpDelayBind;
    public static KeyBinding tapeMouseBind;
    public static void initKeyBinds() {
        autoSprintBind = new KeyBinding("key.antilose.autosprint",  GLFW.GLFW_KEY_UNKNOWN, "category.antilose.keybinding");
        fullBrightBind = new KeyBinding("key.antilose.fullbright", GLFW.GLFW_KEY_UNKNOWN, "category.antilose.keybinding");
        noCrystalDelayBind = new KeyBinding("key.antilose.nocrystaldelay", GLFW.GLFW_KEY_UNKNOWN, "category.antilose.keybinding");
        noJumpDelayBind = new KeyBinding("key.antilose.nojumpdelay", GLFW.GLFW_KEY_UNKNOWN, "category.antilose.keybinding");
        tapeMouseBind = new KeyBinding("key.antilose.tapemouse", GLFW.GLFW_KEY_UNKNOWN, "category.antilose.keybinding");
        KeyBindingRegistryImpl.registerKeyBinding(tapeMouseBind);
    }

    public static void handleKeyBinds() {
        if (autoSprintBind.isPressed()) {
            if (MinecraftClient.getInstance().currentScreen == null) {
                AntiloseUtils.INSTANCE.getModuleManager().getAutoSprint().toggle();
            }
        }
        if (fullBrightBind.isPressed()) {
            if (MinecraftClient.getInstance().currentScreen == null) {
                AntiloseUtils.INSTANCE.getModuleManager().getFullBright().toggle();
            }
        }
        if (noCrystalDelayBind.isPressed()) {
            if (MinecraftClient.getInstance().currentScreen == null) {
                AntiloseUtils.INSTANCE.getModuleManager().getNoCrystalDelay().toggle();
            }
        }
        if (noJumpDelayBind.isPressed()) {
            if (MinecraftClient.getInstance().currentScreen == null) {
                AntiloseUtils.INSTANCE.getModuleManager().getNoJumpDelay().toggle();
            }
        }
        if (tapeMouseBind.isPressed()) {
            if (MinecraftClient.getInstance().currentScreen == null) {
                AntiloseUtils.INSTANCE.getModuleManager().getTapeMouse().toggle();
            }
        }
    }

}
