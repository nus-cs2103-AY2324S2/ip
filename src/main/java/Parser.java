import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    enum Commands {
        bye, list, mark, unmark, todo, deadline, event, delete
    }

    public static String[] splitCommand(String command) {
        String words[] = command.split("\\s+");
        String currentString = "";
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                result.add(words[i]);
                continue;
            }
            if (words[i].charAt(0) == '/') {
                result.add(currentString);
                currentString = "";
                continue;
            }
            currentString += words[i] + " ";
        }
        if (!currentString.equals("")) {
            result.add(currentString);
        }
        return result.toArray(new String[0]);
    }

    public static Command parseCommand(String command) throws DukeException {
        Command cmd = new DefaultCommand();
        try {
            String[] commandList = splitCommand(command);
            switch (Commands.valueOf(commandList[0])) {
                case bye:
                    cmd =  new ByeCommand();
                    break;
                case list:
                    cmd = new ListCommand();
                    break;
                case mark:
                    cmd = new MarkCommand(commandList, "mark");
                    break;
                case unmark:
                    cmd = new MarkCommand(commandList, "unmark");
                    break;
                case todo:
                    cmd = new TodoCommand(commandList);
                    break;
                case deadline:
                    cmd = new DeadlineCommand(commandList);
                    break;
                case event:
                    cmd = new EventCommand(commandList);
                    break;
                case delete:
                    cmd = new DeleteCommand(commandList);
                    break;
            }
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
        return cmd;
    }


}
