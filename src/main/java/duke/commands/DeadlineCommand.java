package duke.commands;

import java.util.List;

import duke.ChatSession;
import duke.Pair;
import duke.SubcommandParser;
import duke.tasks.Deadline;

/**
 * DeadlineCommand class to handle the execution of deadline command
 */
public class DeadlineCommand implements NamedCommand {
    public String getName() {
        return "deadline";
    }

    /**
     * Adds a deadline task to the task list
     *
     * @param session     Chat session
     * @param commandArgs Command arguments
     */
    public void execute(ChatSession session, String commandArgs) {
        Pair<String, List<Pair<String, String>>> data = SubcommandParser.parseSubcommands(commandArgs, "/");
        String name = data.getFirst();
        List<Pair<String, String>> subcommPairs = data.getSecond();
        Deadline t = new Deadline(name);

        for (Pair<String, String> subcommPair : subcommPairs) {
            String subcommString = subcommPair.getFirst();
            switch (subcommString) {
            case "/by":
                String date = subcommPair.getSecond();
                t.setByDate(date);
                break;
            default:
                // add exception handling later
                break;
            }
        }

        session.getTaskList().add(t);
        session.printMessage(String.format("Got it. I've added the following task: \n %s", t.getName()));
    }
}
