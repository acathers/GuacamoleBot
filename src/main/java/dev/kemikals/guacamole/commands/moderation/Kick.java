package dev.kemikals.guacamole.commands.moderation;

import java.util.List;
import dev.kemikals.guacamole.command.Context;
import dev.kemikals.guacamole.command.commandloader.annotations.ChannelCommand;
import dev.kemikals.guacamole.commands.GuacamoleCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.exceptions.HierarchyException;
import net.dv8tion.jda.core.exceptions.PermissionException;

@ChannelCommand
public class Kick implements GuacamoleCommand {
  private final String commandName = "kick";
  Permission permission = Permission.KICK_MEMBERS;
  

  @Override
  public void execute(Context context, String arguments) {
    List<Member> members = context.getMessage().getMentionedMembers();
    for (Member member : members) {
      if (member.isOwner() || member.getUser().isBot()) {
        context.getChannel().sendMessage("Not today batman!").queue();
        return;
      }
      TextChannel channel = context.getChannel();
      try {
        context.getGuild().getController().kick(member)
            .queue(success -> channel
                .sendMessage("Kicked ").append(member.getEffectiveName()).append("! Cya!").queue(),
                error -> {
                  // The failure consumer provides a throwable. In this case we want to check for a
                  // PermissionException.
                  if (error instanceof PermissionException) {
                    PermissionException pe = (PermissionException) error;
                    Permission missingPermission = pe.getPermission(); // If you want to know
                                                                       // exactly what permission is
                                                                       // missing, this is how.
                                                                       // Note: some
                                                                       // PermissionExceptions have
                                                                       // no permission provided,
                                                                       // only an error message!

                    channel.sendMessage("PermissionError kicking [")
                        .append(member.getEffectiveName()).append("]: ").append(error.getMessage())
                        .queue();
                  } else {
                    channel.sendMessage("Unknown error while kicking [")
                        .append(member.getEffectiveName()).append("]: <")
                        .append(error.getClass().getSimpleName()).append(">: ")
                        .append(error.getMessage()).queue();
                  }
                });


      } catch (HierarchyException e) {
        channel.sendMessage("Sorry dave, I can't do that...").queue();
      }
    }

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
