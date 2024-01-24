import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

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

    public static void listMethod() {
        System.out.println("      Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {

            System.out.println("      " + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    public static void addToDo(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("      Missing the description!");
        }
        String name = "";
        for (int i = 1; i < splitInput.length; i++) {
            name += splitInput[i] + " ";
        }
        ToDo newToDo= new ToDo(name.trim());
        tasks.add(newToDo);
        System.out.println("      Got it. I've added this task: ");
        System.out.println("      " + newToDo.toString());
        System.out.println("      Now you have " + tasks.size() + " tasks in the list.");

    }

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
        Deadline newDeadline = new Deadline(name, by);
        tasks.add(newDeadline);
        System.out.println("      Got it. I've added this task: ");
        System.out.println("      " + newDeadline.toString());
        System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
    }

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
        Event newEvent = new Event(name, start, end);
        tasks.add(newEvent);
        System.out.println("      Got it. I've added this task: ");
        System.out.println("      " + newEvent.toString());
        System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
    }

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
        if (choiceMark <= tasks.size()) {
            tasks.get(choiceMark - 1).markAsDone();
            System.out.println("      Nice! I've marked this task as done: ");
//            System.out.println("        " + "[X] " + tasks.get(choiceMark - 1).toString());
            System.out.println("        " + tasks.get(choiceMark - 1).toString());
        } else {
            throw new DukeException("      Invalid choice.");
        }

    }

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
        if (choiceUnmark <= tasks.size()) {
            tasks.get(choiceUnmark - 1).markAsUndone();
            System.out.println("      OK, I've marked this task as not done yet: ");
//            System.out.println("        " + "[ ] " + tasks.get(choiceUnmark - 1).toString());
            System.out.println("        " + tasks.get(choiceUnmark - 1).toString());
        } else {
            System.out.println("      Invalid choice");
        }
    }

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
        if (choiceDelete <= tasks.size()) {
            Task deletedTask = tasks.get(choiceDelete - 1);
            tasks.remove(choiceDelete - 1);
            System.out.println("      Noted, I've removed this task: ");
            System.out.println("      " + deletedTask.toString());
            System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("      Invalid choice");
        }

    }
}

class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]["+ super.getStatusIcon() +"] " + super.getDescription();
    }
}

class Deadline extends Task {
    String by;
    public Deadline(String description, String by) {
        super(description);
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
    public Event(String description, String start, String end) {
        super(description);
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

class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }
}