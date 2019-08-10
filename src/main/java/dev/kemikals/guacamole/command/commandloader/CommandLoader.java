package dev.kemikals.guacamole.command.commandloader;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import dev.kemikals.guacamole.command.Source;
import dev.kemikals.guacamole.command.commands.GuacamoleCommand;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.RootCommandNode;

public class CommandLoader {
CommandDispatcher<Source> dispatcher = new CommandDispatcher<>();
RootCommandNode<Source> root = dispatcher.getRoot();

    public void loadCommands(){
     @SuppressWarnings("unchecked")
        List<GuacamoleCommand> commands = (List<GuacamoleCommand>) getCommandsToLoad().stream().map(e -> {
            try {
                return e.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList()); // e.getConstructor().newInstance()).collect(Collectors.toList());

        commands.stream()
        .map(guacCommand -> guacCommand.getCommand(dispatcher))
        .forEach(root::addChild);
       

    }

    public Set<Class<?>> getCommandsToLoad() {
        Reflections reflections = new Reflections("dev.kemikals.guacamole.command");
        Set<Class<?>> typesSet = reflections.getTypesAnnotatedWith(Command.class);
        return typesSet;
    }


}