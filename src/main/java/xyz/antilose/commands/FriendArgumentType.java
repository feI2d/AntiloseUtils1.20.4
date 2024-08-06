package xyz.antilose.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import xyz.antilose.friends.FriendManager;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class FriendArgumentType implements ArgumentType<String> {

    private static final Collection<String> EXAMPLES = FriendManager.friendsList.stream().limit(5).toList();

    public static FriendArgumentType create() {
        return new FriendArgumentType();
    }

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        String config = reader.readString();
        if (!FriendManager.friendsList.contains(config)) throw new DynamicCommandExceptionType(
                name -> Text.literal("Ника '" + name.toString() + "' нет в списке друзей.")
        ).create(config);

        return config;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(FriendManager.friendsList, builder);
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }

}
