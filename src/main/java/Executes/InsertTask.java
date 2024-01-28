import Actions.Action;
import Exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class InsertTask {
    enum Type {
        TODO, DEADLINE, EVENT
    }
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

            Deadline dl = new Deadline(splitter[0], dateConversion(splitter[1].trim()));
            System.out.println("Got it. I've added this task:");
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
                Event eve = new Event(splitter[0],
                        dateConversion(splitter1[0].trim()), dateConversion(splitter1[1].trim()));
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

    public static LocalDateTime dateConversion(String inputDate) throws DukeException{
        try {
            LocalDateTime date = LocalDateTime.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH));
            if (date.isBefore(LocalDateTime.now())) {
                throw new DukeException("Date input is in the past");
            }

            return date;
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
