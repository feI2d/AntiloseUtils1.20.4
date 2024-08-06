package xyz.antilose.features.impl;

import com.google.common.eventbus.Subscribe;
import xyz.antilose.events.impl.TickEvent;
import xyz.antilose.features.Module;

@Module.registerModule(name = "Auto Sprint", desc = "Спринтит за вас")
public class AutoSprint extends Module {

    @Subscribe
    public void onTick(TickEvent tickEvent) {
        assert mc.player != null;
        if (mc.player.forwardSpeed > 0.0f) {
            mc.player.setSprinting(true);
        }
    }

    @Override
    public void onDisable() {
        assert mc.player != null;
        mc.player.setSprinting(false);
        super.onDisable();
    }
}
