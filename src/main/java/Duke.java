import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String input;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        List<Task> taskList = new ArrayList<Task>();

        greet();

        // Active
        while (true){
            try {
                // Take user input
                input = scanner.nextLine();  // Read user input
                String firstWord;
                String trail = "";
                int space = input.indexOf(" ");
                if (space == -1) {
                    firstWord = input;
                } else {
                    firstWord = input.substring(0, space);
                    trail = input.substring(space + 1);
                }
                Task add;
                String taskName;

                Options choice = optionType(firstWord);

                switch (choice) {
                    case bye:
                        exit();
                        return;

                    case list:
                        list(taskList);
                        break;

                    case delete:
                        int no = Integer.parseInt(trail);
                        delete(taskList, no);
                        break;

                    case mark:
                        int markNo = Integer.parseInt(trail);
                        mark(taskList, markNo);
                        break;

                    case unmark:
                        int unmarkNo = Integer.parseInt(trail);
                        unmark(taskList, unmarkNo);
                        break;

                    case todo:
                        if (trail.isEmpty()) {throw new DukeException("Description of a " + firstWord + " cannot be empty!");}
                        add = new ToDo(trail);
                        add(taskList, add);
                        break;

                    case deadline:
                        if (!trail.contains(" /by ")) {
                            throw new DukeException("Description of a " + firstWord + " must contain \" /by \"!");
                        }
                        taskName = trail.substring(0, trail.indexOf(" /by "));
                        if (taskName.isEmpty()) {throw new DukeException("Description of a " + firstWord + " cannot be empty!");}
                        String by = trail.substring(trail.indexOf(" /by ") + 5);
                        // depending on whether by can be empty or not
                        // if (by.isEmpty()) {throw new DukeException("Deadline cannot be empty!");}
                        add = new Deadline(taskName, by);
                        add(taskList, add);
                        break;

                    case event:
                        if (!trail.contains(" /from ") || !trail.contains(" /to ")) {
                            throw new DukeException("Description of a " + firstWord + " must contain \" /from \" and \" /to \"!");
                        }
                        taskName = trail.substring(0, trail.indexOf(" /from "));
                        if (taskName.isEmpty()) {throw new DukeException("Description of a " + firstWord + " cannot be empty!");}
                        int a = trail.indexOf(" /from ") + 7;
                        int b = trail.indexOf(" /to ");
                        if (a > b) {throw new DukeException("From cannot be empty!");}
                        String from = trail.substring(a, b);
                        // depending on whether from can be empty or not
                        // if (from.isEmpty()) {throw new DukeException("From cannot be empty!");}
                        String to = trail.substring(trail.indexOf(" /to ") + 5);
                        // depending on whether to can be empty or not
                        // if (to.isEmpty()) {throw new DukeException("To cannot be empty!");}
                        add = new Event(taskName, from, to);
                        add(taskList, add);
                        break;

                    case error:
                        throw new DukeException("Command not found! Please try again.");
                }
            } catch (DukeException de) {
                String text = "\t____________________________________________________________\n"
                        + "\t" + de.toString() +"\n"
                        + "\t____________________________________________________________\n";
                System.out.println(text);
            } catch (NumberFormatException nfe) {
                String text = "\t____________________________________________________________\n"
                        + "\tInvalid number, please enter a valid number.\n"
                        + "\t____________________________________________________________\n";
                System.out.println(text);
            } catch (IndexOutOfBoundsException oobe) {
                String text;
                if (taskList.size() == 0) {
                    text = "\t____________________________________________________________\n"
                            + "\tNo tasks found. Please add a task first!\n"
                            + "\t____________________________________________________________\n";
                } else {
                    text = "\t____________________________________________________________\n"
                            + "\tInvalid number, please enter a valid number between 1 and " + taskList.size() + ".\n"
                            + "\t____________________________________________________________\n";
                }
                System.out.println(text);
            }
        }
    }

    public static void greet() {
        String text = "\t____________________________________________________________\n"
                + "\tHello! I'm Teemo!\n"
                + "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public static void exit() {
        String text = "\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }


    public static void list(List<Task> list) {
        String text;
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            text = "\t" + (i+1) + "." + list.get(i).toString();
            System.out.println(text);
        }
        System.out.println("\t____________________________________________________________\n");
    }

    public static void mark(List<Task> list, int num) {
        Task curr = list.get(num - 1);
        curr.mark();

        String text = "\t____________________________________________________________\n"
                + "\tNice! I've marked this task as done:\n"
                + "\t" + curr.toString() + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public static void unmark(List<Task> list, int num) {
        Task curr = list.get(num - 1);
        curr.unmark();

        String text = "\t____________________________________________________________\n"
                + "\tOK, I've marked this task as not done yet:\n"
                + "\t" + curr.toString() + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public static void add(List<Task> list, Task add) {
        list.add(add);
        String word = " task";
        if (list.size() != 1) {word += "s";}

        String text = "\t____________________________________________________________\n"
                + "\tGot it. I've added this task:\n"
                + "\t  " + add.toString() + "\n"
                + "\tNow you have " + list.size() + word + " in the list.\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public static void delete(List<Task> list, int num) {
        Task curr = list.get(num - 1);
        list.remove(curr);

        String text = "\t____________________________________________________________\n"
                + "\tOK, I've marked this task as not done yet:\n"
                + "\t  " + curr.toString() + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public static Options optionType(String option) {
        switch (option) {
            case "bye":
                return Options.bye;
            case "list":
                return Options.list;
            case "delete":
                return Options.delete;
            case "mark":
                return Options.mark;
            case "unmark":
                return Options.unmark;
            case "todo":
                return Options.todo;
            case "deadline":
                return Options.deadline;
            case "event":
                return Options.event;
            default:
                return Options.error;
        }
    }
}

enum Options {
    bye, list, delete, mark, unmark, todo, deadline, event, error
}
