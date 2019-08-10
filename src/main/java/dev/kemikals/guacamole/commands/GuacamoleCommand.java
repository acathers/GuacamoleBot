package dev.kemikals.guacamole.commands;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public interface GuacamoleCommand {
    void execute(TextChannel channel);
    void execute(User user);
    String getCommandName();
}