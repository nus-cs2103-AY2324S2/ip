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

        //handle mark || unmark
        if (inputParts.length == 2 && (inputParts[0].equals("mark") || inputParts[0].equals("unmark"))) {
            int inputInt = Integer.parseInt(inputParts[1]);
            return list.getTask(inputInt - 1).toggle();
        } else if (original.equals("list")) {
            //handle "list"
            return showList(list);
        } else if (inputParts[0].equals("todo")) {
            //handle "todoo"
            String description = original.replace("todo", "");
            if (description.isEmpty()) {
                throw new DukeException("oi todo what. todo WHATTTTTT!!!!!!!!");
            }
            Task task = new ToDo(description);
            list.addTask(task);
            return addMessage(task, list);
        } else if (inputParts[0].equals("deadline")) {
            //handle "deadline"
            String[] parts = original.replace("deadline", "").split(" /");
            Task task = new Deadline(parts[0], parts[1].replace("by ", ""));
            list.addTask(task);
            return addMessage(task, list);
        } else if (inputParts[0].equals("event")) {
            //handle event
            String[] parts = original.replace("event", "").split(" /");
            Task task = new Event(parts[0], parts[1].replace("from ", ""), parts[2].replace("to ", ""));
            list.addTask(task);
            return addMessage(task, list);
        } else if (inputParts[0].equals("delete")) {
            //handle delete
            int inputInt = Integer.parseInt(inputParts[1]);
            list.deleteTask(inputInt);
            return deleteMessage(inputInt, list);
        } else if (inputParts[0].equals("find")) {
            TaskList found = list.findTasks(inputParts[1]);
            return showList(found);
        } else if (original.equals("bye")) {
            Storage.writeToFile(list);
            return showOutro();
        } else {
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

    /**
     * Shows the list of duke tasks to the user.
     *
     * @param list The list of duke tasks to be shown.
     */
    public String showList(TaskList list) {
        String message = "Here are the tasks in your list";
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < list.getSize(); i++) {
            tasks.append(" ").append(i + 1).append(". ").append(list.getTask(i).toString()).append("\n");
        }
        message = message + "\n" + tasks;
        return message;
    }

    /**
     * Returns message string for "delete" action.
     *
     * @param i index of task to delete.
     */
    public String deleteMessage(int i, TaskList list) {
        String m1 = "I remove this one alrdy: \n";
        String m2 = "\n Now you have " + (list.getSize() - 1) + " duke.tasks in the list.\n";
        return m1 + list.getTask(i - 1).toString() + m2;
    }

    /**
     * Returns message string for "add" action.
     *
     * @param task new task to add to list.
     */
    public String addMessage(Task task, TaskList list) {
        String m1 = " Got it. I've added this task:\n";
        String m2 = "\n Now you have " + (list.getSize()) + " tasks in the list.\n";
        return m1 + task.toString() + m2;
    }
}


