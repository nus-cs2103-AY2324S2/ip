package duke;
import java.util.List;

import duke.commands.Bye;
import duke.commands.DeadlineCommand;
import duke.commands.Delete;
import duke.commands.EventCommand;
import duke.commands.ListTasks;
import duke.commands.Mark;
import duke.commands.NamedCommand;
import duke.commands.ToDoCommand;
import duke.commands.Unmark;
import duke.exceptions.InvalidParametersException;

public class Parser {
    private ChatSession session;
    public List<NamedCommand> commandList;
    
    Parser(ChatSession session) {
        this.initCommands();
        this.session = session;
    }

    public void initCommands() {
        this.commandList = List.of(
            new Bye(),
            new ListTasks(), 
            new Mark(), 
            new Unmark(), 
            new ToDoCommand(), 
            new EventCommand(), 
            new DeadlineCommand(),
            new Delete()
            );
    }

    public void handleMessage(String message) {
        String[] result = message.split(" ", 2);
        String command = result[0];
        String commandArgs;

        if (result.length > 1) {
            commandArgs = result[1];
        } else {
            commandArgs = "";
        }

        for (NamedCommand cmd : commandList) {
            if (command.equals(cmd.getName())) {
                try {
                    cmd.execute(session, commandArgs);
                    return;
                } catch (InvalidParametersException e) {
                    session.printMessage(e.getMessage());
                    return;
                }
            }
        }

        this.unmatchedCommand(message);
    }

    public void unmatchedCommand(String message) {
        session.printMessage("unrecognized command");
    }


}
