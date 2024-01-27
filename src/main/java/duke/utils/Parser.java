package duke.utils;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.GenerateTaskCommand;
import duke.commands.ListTaskCommand;
import duke.commands.ModifyTaskCommand;
import duke.exceptions.NoSuchCommandException;

public class Parser {
    public static final DateTimeFormatter inputdtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
    public static final DateTimeFormatter outputdtFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mma", Locale.ENGLISH);
    
    public static Command parse(String input) throws NoSuchCommandException {

        if (input.equals("bye")) {
            return new ExitCommand();
        }

        String action = input.split(" ")[0].toLowerCase();

        switch (action) {
            case "list":
                return new ListTaskCommand();
            case "todo":
                return new GenerateTaskCommand(GenerateTaskCommand.TaskType.TODO, input);
            case "event":
                return new GenerateTaskCommand(GenerateTaskCommand.TaskType.EVENT, input);
            case "deadline":
                return new GenerateTaskCommand(GenerateTaskCommand.TaskType.DEADLINE, input);
            case "mark":
                return new ModifyTaskCommand(ModifyTaskCommand.ModificationTypes.MARK, input);
            case "unmark":
                return new ModifyTaskCommand(ModifyTaskCommand.ModificationTypes.UNMARK, input);
            case "delete":
                return new ModifyTaskCommand(ModifyTaskCommand.ModificationTypes.DELETE, input);
            default:
                throw new NoSuchCommandException(input);
            }
    }
    
}
