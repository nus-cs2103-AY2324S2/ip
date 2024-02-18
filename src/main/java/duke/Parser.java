package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Represents a parser that parses the user input.
 */
public class Parser {

    public static final String INTRO = "yoooooooo im sibehupzcoder9000\n"
            + "what you want sia";
    public static final String OUTRO = "toodledoo";


    /**
     * Constructor for the parser.
     */
    public Parser() {}

    /**
     * Processes the user input.
     * @param original The original user input.
     * @param list The list of tasks.
     * @throws DukeException If the user input is invalid.
     */
    public String processLine(String original, TaskList list) throws DukeException {
        String[] inputParts = original.split("\\s+");

        String command = inputParts[0];
        String details = original.replace(command, "").trim();

        switch(command) {
            case "mark":
                return list.markTask(details);
            case "unmark":
                return list.unmarkTask(details);
            case "list":
                return list.showList();
            case "todo":
                if (details.isEmpty()) {
                    throw new DukeException("oi todo what. todo WHATTTTTT!!!!!!!!");
                }
                return list.addTask(new ToDo(details));
            case "deadline":
                String[] parts1 = details.split(" /");
                Task deadlineTask = new Deadline(parts1[0], parts1[1].replace("by ", ""));
                return list.addTask(deadlineTask);
            case "event":
                String[] parts2 = details.split(" /");
                Task eventTask = new Event(parts2[0], parts2[1].replace("from ", ""), parts2[2].replace("to ", ""));
                return list.addTask(eventTask);
            case "delete":
                return list.deleteTask(Integer.parseInt(inputParts[1]));
            case "find":
                TaskList found = list.findTasks(inputParts[1]);
                return found.showList();
            case "bye":
                Storage.writeToFile(list);
                return showOutro();
            default:
                throw new DukeException("harh what u talking sia walao");
        }
    }

    /** Shows intro message to the user */
    public String showIntro() {
        return INTRO;
    }

    /** Shows outro message to the user */
    public String showOutro() {
        return OUTRO;
    }

    /** Shows loading error message to the user */
    public String showLoadingError(String e) {
        return e;
    }



}


