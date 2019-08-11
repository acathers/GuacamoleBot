package dev.kemikals.guacamole.command.commandloader;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.reflections.Reflections;
import dev.kemikals.guacamole.command.commandloader.annotations.ChannelCommand;
import dev.kemikals.guacamole.command.commandloader.annotations.MentionCommand;
import dev.kemikals.guacamole.command.commandloader.annotations.PrivateCommand;
import dev.kemikals.guacamole.commands.GuacamoleCommand;

public class CommandLoader {

  private HashMap<String, GuacamoleCommand> mentionCommands;
  private HashMap<String, GuacamoleCommand> channelCommands;
  private HashMap<String, GuacamoleCommand> privateCommands;

  public CommandLoader() {
    privateCommands = loadCommand(PrivateCommand.class);
    mentionCommands = loadCommand(MentionCommand.class);
    channelCommands = loadCommand(ChannelCommand.class);
  }

  public HashMap<String, GuacamoleCommand> loadCommand(Class<?> typeToLoad) {

    @SuppressWarnings("unchecked")
    List<GuacamoleCommand> commandsSet =
        (List<GuacamoleCommand>) getCommandsToLoad(typeToLoad).stream().map(e -> {
          try {
            return e.getConstructor().newInstance();

          } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
              | InvocationTargetException | NoSuchMethodException | SecurityException e1) {

            e1.printStackTrace();
          }
          return null;
        }).collect(Collectors.toList());

    HashMap<String, GuacamoleCommand> commands = new HashMap<>();
    for (GuacamoleCommand command : commandsSet) {
      commands.put(command.getCommandName(), command);
    }

    return commands;
  }

  @SuppressWarnings("unchecked")
  public Set<Class<?>> getCommandsToLoad(Class<?> classToLoad) {
    Reflections reflections = new Reflections("dev.kemikals.guacamole.commands");
    Set<Class<?>> typesSet =
        reflections.getTypesAnnotatedWith((Class<? extends Annotation>) classToLoad);
    for (Class<?> type : typesSet) {
      System.out.println(type);
    }
    return typesSet;
  }

  public HashMap<String, GuacamoleCommand> getMentionCommands() {
    return mentionCommands;
  }

  public HashMap<String, GuacamoleCommand> getChannelCommands() {
    return channelCommands;
  }

  public HashMap<String, GuacamoleCommand> getPrivateCommands() {
    return privateCommands;
  }
}
