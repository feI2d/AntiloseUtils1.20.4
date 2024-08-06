package xyz.antilose.features.impl;

import com.google.common.eventbus.Subscribe;
import xyz.antilose.events.impl.TickEvent;
import xyz.antilose.features.Module;
import xyz.antilose.mixin.MinecraftClientMixin;

@Module.registerModule(name = "TapeMouse", desc = "Автокликер")
public class TapeMouse extends Module {
    private long lastClickTime = 0;
    @Subscribe
    public void onTick(TickEvent tickEvent) {
        if (mc.player.getAttackCooldownProgress(0.0f) >= 0.99) {
            ((MinecraftClientMixin) mc).mouseClick();
        }
    }

}
