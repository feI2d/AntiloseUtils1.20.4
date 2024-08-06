package xyz.antilose.features.impl;

import com.google.common.eventbus.Subscribe;
import xyz.antilose.events.impl.TickEvent;
import xyz.antilose.features.Module;
import xyz.antilose.mixin.LivingEntityAcessor;

@Module.registerModule(name = "No Jump Delay", desc = "Убирает задержку на прыжок")
public class NoJumpDelay extends Module {

    @Subscribe
    public void onTick(TickEvent tickEvent) {
        ((LivingEntityAcessor)mc.player).setLastJumpCooldown(0);
    }

}
