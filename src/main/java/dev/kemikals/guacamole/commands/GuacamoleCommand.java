package dev.kemikals.guacamole.commands;

import dev.kemikals.guacamole.command.Context;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public interface GuacamoleCommand {
    void execute(Context context);
    String getCommandName();
}