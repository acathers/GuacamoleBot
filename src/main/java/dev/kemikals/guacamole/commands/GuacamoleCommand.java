package dev.kemikals.guacamole.commands;

import dev.kemikals.guacamole.command.Context;
import net.dv8tion.jda.core.Permission;

public interface GuacamoleCommand {
    void execute(Context context, String arguments);
    String getCommandName();
    Permission getPermissionRequired();
}