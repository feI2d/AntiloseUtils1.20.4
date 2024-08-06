package xyz.antilose.settings;

import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import xyz.antilose.AntiloseUtils;

import java.util.function.Consumer;

public class ModKeybinds {

    public static KeyBinding tapeMouseBind;
    public static void initKeyBinds() {
        tapeMouseBind = new KeyBinding("key.antilose.tapemouse", 0, "category.antilose.keybinding");
        KeyBindingRegistryImpl.registerKeyBinding(tapeMouseBind);
    }


}
