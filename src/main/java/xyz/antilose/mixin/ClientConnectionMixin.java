package xyz.antilose.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.antilose.AntiloseUtils;
import xyz.antilose.MinecraftWrapper;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"))
    private void onPacketSend(Packet<?> packet, CallbackInfo ci) {
        final MinecraftClient mc = MinecraftClient.getInstance();

        if (AntiloseUtils.INSTANCE.getModuleManager().getNoCrystalDelay().isEnabled()) {
            if (packet instanceof PlayerInteractEntityC2SPacket) {
                PlayerInteractEntityC2SPacket interactPacket = (PlayerInteractEntityC2SPacket) packet;
                interactPacket.handle(new PlayerInteractEntityC2SPacket.Handler() {
                    @Override
                    public void interact(Hand hand) {
                        // N/A
                    }

                    @Override
                    public void interactAt(Hand hand, Vec3d pos) {
                        // N/A
                    }

                    @Override
                    public void attack() {
                        HitResult hitResult = mc.crosshairTarget;
                        if (hitResult == null) {
                            return;
                        }
                        if (hitResult.getType() == HitResult.Type.ENTITY) {
                            EntityHitResult entityHitResult = (EntityHitResult) hitResult;
                            Entity entity = entityHitResult.getEntity();
                            if (entity instanceof EndCrystalEntity) {
                                StatusEffectInstance weakness = mc.player.getStatusEffect(StatusEffects.WEAKNESS);
                                StatusEffectInstance strength = mc.player.getStatusEffect(StatusEffects.STRENGTH);
                                if (!(weakness == null || (strength != null && strength.getAmplifier() > weakness.getAmplifier()) || isTool(mc.player.getMainHandStack()))) {
                                    return;
                                }
                                entity.kill();
                            }
                        }
                    }
                });
            }
        }

    }

    private boolean isTool(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof ToolItem) || itemStack.getItem() instanceof HoeItem) {
            return false;
        }
        ToolMaterial material = ((ToolItem) itemStack.getItem()).getMaterial();
        return material == ToolMaterials.DIAMOND || material == ToolMaterials.NETHERITE;
    }

}
