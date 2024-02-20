package goldbot.commands;

import java.util.List;

import goldbot.ChatSession;
import goldbot.Pair;
import goldbot.SubcommandParser;
import goldbot.exceptions.InvalidParametersException;
import goldbot.tasks.Event;

/**
 * EventCommand class to handle the execution of event command
 */
public class EventCommand implements NamedCommand {
    public String getName() {
        return "event";
    }

    /**
     * Adds an event task to the task list
     *
     * @param session     Chat session
     * @param commandArgs Command arguments
     */
    public void execute(ChatSession session, String commandArgs) throws InvalidParametersException {
        Pair<String, List<Pair<String, String>>> data = SubcommandParser.parseSubcommands(commandArgs, "/");
        String name = data.getFirst();
        List<Pair<String, String>> subcommPairs = data.getSecond();
        Event t = new Event(name);

        for (Pair<String, String> subcommPair : subcommPairs) {
            String subcommString = subcommPair.getFirst();
            switch (subcommString) {
            case "/from":
                String fromDate = subcommPair.getSecond();
                t.setFromDate(fromDate);
                break;
            case "/to":
                String toDate = subcommPair.getSecond();
                t.setToDate(toDate);
                break;
            default:
                // add exception handling
                throw new InvalidParametersException("Invalid subcommand");
            }
        }

        session.getTaskList().add(t);
        session.printMessage(String.format("Got it. I've added the following task: \n %s", t.getName()));
    }
}
