package dev.kemikals.guacamole.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import dev.kemikals.guacamole.command.Source;

public interface GuacamoleCommand {
    LiteralCommandNode<Source> getCommand(CommandDispatcher<Source> dispatcher);

}