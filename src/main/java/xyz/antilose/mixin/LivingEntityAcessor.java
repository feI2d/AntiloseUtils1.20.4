package xyz.antilose.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface LivingEntityAcessor {
    @Accessor("jumpingCooldown")
    void setLastJumpCooldown(int value);
}
