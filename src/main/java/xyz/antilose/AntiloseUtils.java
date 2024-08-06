package xyz.antilose;

import com.google.common.eventbus.EventBus;
import lombok.Getter;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.antilose.commands.FriendCommand;
import xyz.antilose.features.ModuleManager;
import xyz.antilose.friends.FriendManager;
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
		FriendManager.loadFriendsList();
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			FriendCommand.register(dispatcher);
		});

		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (hand == Hand.MAIN_HAND && player != null && entity instanceof PlayerEntity) {
				PlayerEntity targetPlayer = (PlayerEntity) entity;
				if (FriendManager.isFriend(targetPlayer) && !FriendManager.allowAttacksOnFriends && moduleManager.getFriendProtect().isEnabled()) {
					return ActionResult.FAIL;
				}
			}
			return ActionResult.PASS;
		});
	}

	public void sendClientMessage(String message) {
		if (MinecraftClient.getInstance().world != null && MinecraftClient.getInstance().player != null) {
			MinecraftClient.getInstance().player.sendMessage(Text.literal(Formatting.GRAY + "(" + Formatting.LIGHT_PURPLE + "Antilose" + Formatting.GRAY + ")" +  " -> " + Formatting.RESET + message));
		}
	}

}