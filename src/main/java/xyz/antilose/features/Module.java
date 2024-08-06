package xyz.antilose.features;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.util.Formatting;
import xyz.antilose.AntiloseUtils;
import xyz.antilose.MinecraftWrapper;
import xyz.antilose.api.SoundApi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Getter
@Setter
public class Module implements MinecraftWrapper {

    private String name;
    private String description;
    private boolean enabled;

    public Module() {
        this.name = getClass().getAnnotation(registerModule.class).name();
        this.description = getClass().getAnnotation(registerModule.class).desc();
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public void setEnabled(boolean value) {
        enabled = value;

        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {
        AntiloseUtils.INSTANCE.sendClientMessage("Модуль " + getName() + " был " + Formatting.GREEN + "включён!");
        SoundApi.playSound("module_enable", 70.0f, false);
        AntiloseUtils.INSTANCE.getEventBus().register(this);
    }

    public void onDisable() {
        AntiloseUtils.INSTANCE.sendClientMessage("Модуль " + getName() + " был " + Formatting.RED + "выключен!");
        SoundApi.playSound("module_disable", 70.0f, false);
        AntiloseUtils.INSTANCE.getEventBus().unregister(this);
    }

    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface registerModule {
        String name();
        String desc();
    }

}
