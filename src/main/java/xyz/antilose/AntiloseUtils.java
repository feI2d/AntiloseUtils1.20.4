package xyz.antilose;

import com.google.common.eventbus.EventBus;
import lombok.Getter;
import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.antilose.features.ModuleManager;
import xyz.antilose.screens.MenuScreen;
import xyz.antilose.settings.ModKeybinds;

@Getter
public class AntiloseUtils implements ModInitializer {
	public static AntiloseUtils INSTANCE;
    public static final Logger LOGGER = LoggerFactory.getLogger("antilose-utils");
	private EventBus eventBus = new EventBus();
	private ModuleManager moduleManager;
	private MenuScreen menuScreen;

	public AntiloseUtils() {
		INSTANCE = this;
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Antilose Utils was initialized");
		eventBus.register(this);
		moduleManager = new ModuleManager();
		moduleManager.registerModules();
		ModKeybinds.initKeyBinds();
		menuScreen = new MenuScreen();
	}

	public void sendClientMessage(String message) {
		if (MinecraftClient.getInstance().world != null && MinecraftClient.getInstance().player != null) {
			MinecraftClient.getInstance().player.sendMessage(Text.literal(Formatting.GRAY + "(" + Formatting.LIGHT_PURPLE + "Antilose" + Formatting.GRAY + ")" +  " -> " + Formatting.RESET + message));
		}
	}

}