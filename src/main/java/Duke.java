import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {
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
        while (!userInput.equals("bye")) {

            System.out.println("    ____________________________________________________________");
            String[] splitInput = userInput.split(" ");
            if (splitInput[0].equals("list")) {
                System.out.println("      Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i) instanceof ToDo) {
                        System.out.println("      " + (i + 1) + ".[T]["+ tasks.get(i).getStatusIcon() +"] " + tasks.get(i).getDescription());
                    } else if (tasks.get(i) instanceof Deadline) {
                        System.out.println("      " + (i + 1) + ".[D]["+ tasks.get(i).getStatusIcon() +"] " + tasks.get(i).getDescription() + " (by: " + ((Deadline) tasks.get(i)).getBy() + ")");
                    } else {
                        System.out.println("      " + (i + 1) + ".[E]["+ tasks.get(i).getStatusIcon() +"] " + tasks.get(i).getDescription() + " (from: " + ((Event) tasks.get(i)).getStart() + " to: " + ((Event) tasks.get(i)).getEnd() + ")");
                    }
                }
            } else if (splitInput[0].equals("mark")) {
                try {
                    markTask(userInput);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            } else if (splitInput[0].equals("unmark")) {
                try {
                    unmarkTask(userInput);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            } else if (splitInput[0].equals("todo")) {
                try {
                    addToDo(userInput);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            } else if (splitInput[0].equals("deadline")) {
                try {
                    addDeadline(userInput);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            } else if (splitInput[0].equals("event")) {
                try {
                    addEvent(userInput);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            } else {

                System.out.println("      I'm sorry, I do not understand that.");

            }
            System.out.println("    ____________________________________________________________\n");
            userInput = sc.nextLine().trim();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
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
        tasks.add(new ToDo(name.trim()));
        System.out.println("      Got it. I've added this task: ");
        System.out.println(String.format("      [T][ ] " + name.trim()));
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
        tasks.add(new Deadline(name, by));
        System.out.println("      Got it. I've added this task: ");
        System.out.println(String.format("      [D][ ] " + name + " (by: " + by + ")"));
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
        tasks.add(new Event(name, start, end));
        System.out.println("      Got it. I've added this task: ");
        System.out.println(String.format("      [E][ ] " + name + " (from: " + start + " to: " + end + ")"));
        System.out.println("      Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void markTask(String input) throws DukeException {
        String splitInput[] = input.split(" ");
        if (tasks.size() == 0) {
            throw new DukeException("      No task at the moment.");
        } else if (splitInput.length < 2) {
            throw new DukeException("      Please select the task.");
        }

        int choiceMark = Integer.parseInt(splitInput[1]);
        if (choiceMark <= tasks.size()) {
            tasks.get(choiceMark - 1).markAsDone();
            System.out.println("      Nice! I've marked this task as done: ");
            System.out.println("        " + "[X] " + tasks.get(choiceMark - 1).getDescription());
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
        int choiceMark = Integer.parseInt(splitInput[1]);
        if (choiceMark <= tasks.size()) {
            tasks.get(choiceMark - 1).markAsUndone();
            System.out.println("      OK, I've marked this task as not done yet: ");
            System.out.println("        " + "[ ] " + tasks.get(choiceMark - 1).getDescription());
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
}

class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }
}