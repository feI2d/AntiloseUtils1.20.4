package xyz.antilose.features.impl;

import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import xyz.antilose.events.impl.TickEvent;
import xyz.antilose.features.Module;

@Module.registerModule(name = "FullBright", desc = "Даёт вам бесконечный еффект ночного зрения")
public class FullBright extends Module {

    @Subscribe
    public void onTick(TickEvent tickEvent) {
        assert mc.player != null;
        mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, Integer.MAX_VALUE, 1));
    }

    @Override
    public void onDisable() {
        assert mc.player != null;
        mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        super.onDisable();
    }
}
