import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Duke {

    public enum Action {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        BYE
    }



    static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("      Hello! I'm AndrewOng2066");
        System.out.println("      What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);

        String userInput = sc.nextLine().trim();
        while (!userInput.equalsIgnoreCase(Action.BYE.toString())) {

            System.out.println("    ____________________________________________________________");
            String[] splitInput = userInput.split(" ");
            try {
                if (splitInput[0].equalsIgnoreCase(Action.LIST.toString())) {
                    listMethod();

                } else if (splitInput[0].equalsIgnoreCase(Action.MARK.toString())) {
                    markTask(userInput);

                } else if (splitInput[0].equalsIgnoreCase(Action.UNMARK.toString())) {
                    unmarkTask(userInput);

                } else if (splitInput[0].equalsIgnoreCase(Action.TODO.toString())) {
                    addToDo(userInput);

                } else if (splitInput[0].equalsIgnoreCase(Action.DEADLINE.toString())) {
                    addDeadline(userInput);

                } else if (splitInput[0].equalsIgnoreCase(Action.EVENT.toString())) {
                    addEvent(userInput);

                } else if (splitInput[0].equalsIgnoreCase(Action.DELETE.toString())) {
                    deleteTask(userInput);

                } else {

                    System.out.println("      I'm sorry, I do not understand that.");

                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("    ____________________________________________________________\n");
            userInput = sc.nextLine().trim();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Lists down the list of Tasks.
     */
    public static void listMethod() {
        System.out.println("      Here are the tasks in your list:");
        ArrayList<Task> allTasksFromFile = getTasksFromFile("duke");
        tasks = allTasksFromFile;
        for (int i = 0; i < tasks.size(); i++) {

            System.out.println("      " + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Adds a new ToDo.
     * Adds the new ToDo inside the file.
     *
     * @param input the description of the ToDo.
     * @throws DukeException If missing the description.
     */
    public static void addToDo(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("      Missing the description!");
        }
        String name = "";
        for (int i = 1; i < splitInput.length; i++) {
            name += splitInput[i] + " ";
        }
        ToDo newToDo= new ToDo(name.trim(), false);
        tasks.add(newToDo);
        ArrayList<Task> newToDoList = new ArrayList<>();
        newToDoList.add(newToDo);
        storeArrayListToFile("duke", newToDoList, false);//TODO Add the file function here
        System.out.println("      Got it. I've added this task:");
        System.out.println("      " + newToDo.toString());
        System.out.println("      Now you have " + tasks.size() + " tasks in the list.");

    }

    /**
     * Adds a new Deadline.
     * Adds the new Deadline inside the file.
     *
     * @param input the description and 'by' of the Deadline.
     * @throws DukeException If missing the description or 'by'.
     */
    public static void addDeadline(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("      Missing the description!");
        }
        String[] deadlineSplit = input.split("/");

        if (deadlineSplit.length < 2) {
            throw new DukeException("      Invalid format for new Deadline!");
        }

        String name = deadlineSplit[0].substring(9).trim();
        String by = deadlineSplit[1].substring(3).trim();
        Deadline newDeadline = new Deadline(name,false, by);
        tasks.add(newDeadline);
        ArrayList<Task> newDeadlineList = new ArrayList<>();
        newDeadlineList.add(newDeadline);
        storeArrayListToFile("duke", newDeadlineList, false);//TODO Add the file function here
        System.out.println("      Got it. I've added this task:");
        System.out.println("      " + newDeadline.toString());
        System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds a new Event.
     * Adds the new Event inside the file.
     *
     * @param input the description, 'from', and 'to' of the Event.
     * @throws DukeException if missing the description, 'from' or 'to'.
     */
    public static void addEvent(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("      Missing the description!");
        }

        String[] eventSplit = input.split("/");
        if (eventSplit.length < 3) {
            throw new DukeException("      Invalid format for new Event!");
        }

        String name = eventSplit[0].substring(6).trim();
        String start = eventSplit[1].substring(5).trim();
        String end = eventSplit[2].substring(3).trim();
        Event newEvent = new Event(name, false, start, end);
        tasks.add(newEvent);
        ArrayList<Task> newEventList = new ArrayList<>();
        newEventList.add(newEvent);
        storeArrayListToFile("duke", newEventList, false);//TODO Add the file function here
        System.out.println("      Got it. I've added this task:");
        System.out.println("      " + newEvent.toString());
        System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks a task for being completed.
     *
     * @param input the index of the task.
     * @throws DukeException If the size of tasks is 0, or no task is being selected.
     */
    public static void markTask(String input) throws DukeException {
        String splitInput[] = input.split(" ");
        if (tasks.size() == 0) {
            throw new DukeException("      No task at the moment.");
        } else if (splitInput.length < 2) {
            throw new DukeException("      Please select the task.");
        }
        int choiceMark;
        try {
            choiceMark = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("      Please enter a valid integer value.");
        }
        if (choiceMark <= tasks.size() && choiceMark > 0) {
            tasks.get(choiceMark - 1).markAsDone();
            storeArrayListToFile("duke", tasks, true);
            System.out.println("      Nice! I've marked this task as done:");
            System.out.println("        " + tasks.get(choiceMark - 1).toString());
        } else {
            throw new DukeException("      Invalid choice.");
        }

    }

    /**
     * Unmarks a task for being incomplete.
     *
     * @param input the index of the task.
     * @throws DukeException If the size of tasks is 0, or no task is being selected.
     */
    public static void unmarkTask(String input) throws DukeException {
        String splitInput[] = input.split(" ");
        if (tasks.size() == 0) {
            throw new DukeException("      No task at the moment.");
        } else if (splitInput.length < 2) {
            throw new DukeException("      Please select the task.");
        }
        int choiceUnmark;
        try {
            choiceUnmark = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("      Please enter a valid integer value.");
        }
        if (choiceUnmark <= tasks.size() && choiceUnmark > 0) {
            tasks.get(choiceUnmark - 1).markAsUndone();
            storeArrayListToFile("duke", tasks, true);
            System.out.println("      OK, I've marked this task as not done yet:");
//            System.out.println("        " + "[ ] " + tasks.get(choiceUnmark - 1).toString());
            System.out.println("        " + tasks.get(choiceUnmark - 1).toString());
        } else {
            System.out.println("      Invalid choice");
        }
    }

    /**
     * Deletes a task.
     *
     * @param input the index of the task.
     * @throws DukeException If the size of tasks is 0, or no task is being selected.
     */
    public static void deleteTask(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        if (tasks.size() == 0) {
            throw new DukeException("      No task at the moment.");
        } else if (splitInput.length < 2) {
            throw new DukeException("      Please select the task.");
        }
        int choiceDelete;
        try {
            choiceDelete = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("      Please enter a valid integer value.");
        }
        if (choiceDelete <= tasks.size() && choiceDelete > 0) {
            Task deletedTask = tasks.get(choiceDelete - 1);
            tasks.remove(choiceDelete - 1);
            storeArrayListToFile("duke", tasks, true);
            System.out.println("      Noted, I've removed this task:");
            System.out.println("      " + deletedTask.toString());
            System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("      Invalid choice");
        }
    }

    /**
     * Load the data into a txt file
     *
     * @param fileName name of the txt file
     * @param tasks context to be stored in the txt file
     * @param isOverwrite check if we need to delete the current context in the txt file
     */
    static void storeArrayListToFile(String fileName, ArrayList<Task> tasks, boolean isOverwrite) {
        FileManager fileManager = new FileManager(fileName);
        fileManager.writeArrayListToFile(tasks, isOverwrite);
    }

    /**
     * Gets the list of Tasks from the file.
     *
     * @param filename the txt file.
     * @return an ArrayList of Tasks.
     */
    static ArrayList<Task> getTasksFromFile(String filename) {
        FileManager fileManager = new FileManager(filename);
        return fileManager.loadTasksFromFile();
    }

    /**
     * Coverts the String to LocalDateTime type.
     *
     * @param timeString The time in String.
     * @return LocalDateTime.
     * @throws DukeException If none of the timeString matches the formatter.
     */
    static LocalDateTime convertStringToLocalDateTime(String timeString) throws DukeException{
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-mm-dd")
        );

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(timeString, formatter);
            } catch (DateTimeParseException e) {
                // Continue next format
            }
        }

        throw new DukeException("      Invalid format for Date-Time. The format is \"yyyy-mm-dd\" or \"yyyy-mm-dd HH:mm\".");

    }


}

class Task {

    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}

class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]["+ super.getStatusIcon() +"] " + super.getDescription();
    }
}

class Deadline extends Task {
    String by;
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]["+ super.getStatusIcon() +"] " + super.getDescription() + " (by: " + this.by + ")";
    }
}

class Event extends Task {
    String start;
    String end;
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]["+ super.getStatusIcon() +"] " + super.getDescription() + " (from: " + this.start + " to: " + this.end + ")";
    }
}

