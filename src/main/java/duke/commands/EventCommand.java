package duke.commands;

import java.util.List;

import duke.ChatSession;
import duke.Pair;
import duke.SubcommandParser;
import duke.tasks.Event;

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
    public void execute(ChatSession session, String commandArgs) {
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
            default:
                // add exception handling later
                break;
            }
        }

        session.getTaskList().add(t);
        session.printMessage(String.format("Got it. I've added the following task: \n %s", t.getName()));
    }
}
