package xyz.antilose.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.antilose.AntiloseUtils;
import xyz.antilose.settings.ModKeybinds;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(method = "onKey", at = @At("HEAD"))
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo callbackInfo) {
        if (MinecraftClient.getInstance().currentScreen != null) return;
        if (action == 0)
        {
            if (key == 344)
            {
                MinecraftClient.getInstance().setScreen(AntiloseUtils.INSTANCE.getMenuScreen());
            }
            if (ModKeybinds.tapeMouseBind.isPressed()) {
                if (MinecraftClient.getInstance().currentScreen == null) {
                    AntiloseUtils.INSTANCE.getModuleManager().getTapeMouse().toggle();
                }
            }
        }
    }

}
