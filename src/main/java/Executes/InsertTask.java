package Executes;

import Actions.Action;
import Actions.Deadline;
import Actions.Event;
import Actions.Todo;
import Exceptions.DukeException;

import java.util.List;

public class InsertTask {
    /**
     * No constructor needed
     */
    private InsertTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Execute inserting the tasks
     * @param input: User input
     * @param actionList
     * @throws DukeException
     */
    public static void exec(String input, List<Action> actionList) throws DukeException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isBlank()) {
            throw new DukeException("The description of the task is empty.");
        }
        String content = parts[1];
        Type type = Type.valueOf(parts[0].toUpperCase());

        switch (type) {
            case TODO:
                todoTask(content, actionList);
                break;
            case DEADLINE:
                deadlineTask(content, actionList);
                break;
            case EVENT:
                eventTask(content, actionList);
                break;
        }
    }

    /**
     * Add todo
     * @param content : what to do?
     * @param actionList
     */
    public static void todoTask(String content, List<Action> actionList) {
        System.out.println("Got it. I've added this task:");
        Todo task = new Todo(content);
        actionList.add(task);
        System.out.println(task);
        System.out.println("Now you have " + actionList.size() + " tasks in the list.");
    }

    /**
     * Add deadline
     * @param content : what to do? by when?
     * @param actionList
     * @throws DukeException
     */
    public static void deadlineTask(String content, List<Action> actionList) throws DukeException {
        try {
            String[] splitter = content.split("/by");

            if (splitter.length < 2 || splitter[0].trim().isBlank() || splitter[1].trim().isBlank()) {
                throw new DukeException("Invalid format. Please type in the following format: " +
                        "deadline <action> /by <time>");
            }

            System.out.println("Got it. I've added this task:");
            Deadline dl = new Deadline(splitter[0], splitter[1]);
            actionList.add(dl);
            System.out.println(dl);
            System.out.println("Now you have " + actionList.size() + " tasks in the list.");

        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("Invalid format. Please type in the following format: deadline <action> /by <time>");
        }
    }

    /**
     * Add event
     * @param content : what to do? From when to when?
     * @param actionList
     * @throws DukeException
     */
    public static void eventTask(String content, List<Action> actionList) throws DukeException{
        try {
            String[] splitter = content.split("/from");

            if (splitter.length < 2 || splitter[0].trim().isBlank() || splitter[1].trim().isBlank()) {
                throw new DukeException("Invalid format. Please type in the following format: " +
                        "event <action> /from <time> /to <time>");
            }

            try {
                String[] splitter1 = splitter[1].split("/to");

                if (splitter1.length < 2 || splitter1[0].trim().isBlank() || splitter1[1].trim().isBlank()) {
                    throw new DukeException("Invalid format. Please type in the following format: " +
                            "event <action> /from <time> /to <time>");
                }
                Event eve = new Event(splitter[0], splitter1[0], splitter1[1]);
                actionList.add(eve);

                System.out.println(eve);
                System.out.println("Now you have " + actionList.size() + " tasks in the list.");
            } catch (ArrayIndexOutOfBoundsException err) {
                throw new DukeException("Invalid format. Please type in the following format: " +
                        "event <action> /from <time> /to <time>");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("Invalid format. Please type in the following format: " +
                    "event <action> /from <time> /to <time>");
        }
    }

}
