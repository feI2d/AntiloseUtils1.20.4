package xyz.antilose.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import xyz.antilose.friends.FriendManager;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class FriendCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("friend").then(ClientCommandManager.literal("add").then(ClientCommandManager.argument("name", StringArgumentType.word())
                .executes((p) -> {
                    FriendManager.addFriend(StringArgumentType.getString(p, "name"));
                    return 1;
                }))).then(ClientCommandManager.literal("remove")
                .then(ClientCommandManager.argument("name", FriendArgumentType.create()).executes(context -> {
                    FriendManager.removeFriend(context.getArgument("name", String.class));
                    return SINGLE_SUCCESS;
                })))

        );
    }

}
