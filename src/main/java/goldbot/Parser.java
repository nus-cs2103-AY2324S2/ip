package goldbot;

import java.util.List;

import goldbot.commands.Bye;
import goldbot.commands.DeadlineCommand;
import goldbot.commands.Delete;
import goldbot.commands.EventCommand;
import goldbot.commands.Find;
import goldbot.commands.ListTasks;
import goldbot.commands.LoanCommand;
import goldbot.commands.Mark;
import goldbot.commands.NamedCommand;
import goldbot.commands.Pay;
import goldbot.commands.ToDoCommand;
import goldbot.commands.Unmark;
import goldbot.exceptions.InvalidParametersException;

/**
 * Parser class to handle parsing of commands
 */
public class Parser {
    private List<NamedCommand> commandList;
    private ChatSession session;

    Parser(ChatSession session) {
        this.initCommands();
        this.session = session;
    }

    private void initCommands() {
        this.commandList = List.of(
            new Bye(),
            new ListTasks(),
            new Mark(),
            new Unmark(),
            new ToDoCommand(),
            new EventCommand(),
            new DeadlineCommand(),
            new Delete(),
            new Find(),
            new LoanCommand(),
            new Pay()
        );
    }

    /**
     * Handles message from user
     *
     * @param message Message from user
     */
    public void handleMessage(String message) {
        String[] result = message.split(" ", 2);
        assert result.length > 0 : "result should not be empty";
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


    private void unmatchedCommand(String message) {
        session.printMessage("unrecognized command");
    }


}
