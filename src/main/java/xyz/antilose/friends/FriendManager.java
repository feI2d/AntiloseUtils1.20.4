package xyz.antilose.friends;

import lombok.Getter;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import xyz.antilose.AntiloseUtils;
import xyz.antilose.commands.FriendCommand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FriendManager {

    public static final List<String> friendsList = new ArrayList<>();
    private static final Path friendsListFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "friend_list.json").toPath();
    public static boolean allowAttacksOnFriends = false;


    public static PlayerEntity getTargetedPlayer() {
        if (MinecraftClient.getInstance().crosshairTarget instanceof EntityHitResult) {
            Entity targetEntity = ((EntityHitResult) MinecraftClient.getInstance().crosshairTarget).getEntity();

            if (targetEntity instanceof PlayerEntity) {
                return (PlayerEntity) targetEntity;
            }
        }
        return null;
    }

    public static void addFriend(String playerName) {
        if (!friendsList.contains(playerName)) {
            friendsList.add(playerName);
            String message = "Игрок " + playerName + " был добавлен в список друзей.";
            AntiloseUtils.INSTANCE.sendClientMessage("§a" + message);
            saveFriendsList();
        } else {
            String message = "Игрок " + playerName + " уже есть в списке ваших друзей.";
            AntiloseUtils.INSTANCE.sendClientMessage("§e" + message);
        }
    }

    public static void removeFriend(String playerName) {
        if (friendsList.contains(playerName)) {
            friendsList.remove(playerName);
            String message = "Игрок " + playerName + " удалён из списка ваших друзей.";
            AntiloseUtils.INSTANCE.sendClientMessage("§c" + message);
            saveFriendsList();
        } else {
            String message = "Игрока " + playerName + " нет в списке друзей.";
            AntiloseUtils.INSTANCE.sendClientMessage("§e" + message);
        }
    }

    public static void removeAllFriends() {
        friendsList.clear();
        String message = "Removed all friends.";
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of("§c" + message));
        saveFriendsList();
    }

    public static boolean isFriend(PlayerEntity player) {
        return friendsList.contains(player.getName().getString());
    }

    public static void loadFriendsList() {
        try {
            if (Files.exists(friendsListFile)) {
                friendsList.addAll(Files.readAllLines(friendsListFile));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFriendsList() {
        try {
            Files.write(friendsListFile, friendsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
