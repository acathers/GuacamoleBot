package dev.kemikals.guacamole.commands.hello;
import dev.kemikals.guacamole.command.Context;
import dev.kemikals.guacamole.command.commandloader.annotations.ChannelCommand;
import dev.kemikals.guacamole.command.commandloader.annotations.MentionCommand;
import dev.kemikals.guacamole.commands.GuacamoleCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.TextChannel;

@MentionCommand
@ChannelCommand
public class Hello implements GuacamoleCommand {

    private final String commandName = "hello";
    private Permission permission = Permission.MESSAGE_WRITE;

    public String getCommandName() {
        return commandName;
    }

    @Override
    public void execute(Context context, String arguments) {
      TextChannel channel = context.getChannel();
      channel.sendMessage("Hi, " + context.getUser().getName()).queue();
    }

    @Override
    public Permission getPermissionRequired() {
      return permission;
    }
}