package dev.kemikals.guacamole.commands.sassy;

import dev.kemikals.guacamole.command.Context;
import dev.kemikals.guacamole.command.commandloader.annotations.PrivateCommand;
import dev.kemikals.guacamole.commands.GuacamoleCommand;
import net.dv8tion.jda.core.Permission;

@PrivateCommand
public class sassy implements GuacamoleCommand {
  private String commandName = "hello";
  Permission permission = Permission.UNKNOWN;

  @Override
  public void execute(Context context, String arguments) {
    context.getUser().openPrivateChannel().queue((channel) -> {
      channel.sendMessage("Sup ho").queue();
    });

  }

  @Override
  public String getCommandName() {
    return commandName;
  }

  @Override
  public Permission getPermissionRequired() {

    return permission;
  }

}
