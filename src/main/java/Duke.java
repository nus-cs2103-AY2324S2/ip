import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String input;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        List<Task> taskList;
        File f = new File("./tasks.txt");

        // Checking save file
        try {
            if (f.createNewFile()) { // if file doesn't exist
                System.out.println("New save created: " + f.getName()); // first time user
            } else {
                System.out.println("Loading from save file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        taskList = load(f);

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

                case save:
                    save(f, taskList);
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
            } catch (IOException e) {
                throw new RuntimeException(e);
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
            case "save":
                return Options.save;
            default:
                return Options.error;
        }
    }

    public static void save(File f, List<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(f, false); // create a new file
        String text = "";

        for (Task t : taskList) {
            text += t.toString() + "\n";
        }

        fw.write(text);
        fw.close();

        String output = "\t____________________________________________________________\n"
                + "\tSuccessfully saved!\n"
                + "\t____________________________________________________________\n";

        System.out.println(output);
    }

    public static List<Task> load(File f) throws FileNotFoundException {
        List<Task> taskList = new ArrayList<Task>();
        Scanner s = new Scanner(f);
        String curr, taskName, taskType, isMarked, start, finish;
        int index;
        Task t;

        while (s.hasNext()) {
            curr = s.nextLine();
            taskType = curr.substring(1, 2);
            isMarked = curr.substring(4, 5);

            index = curr.indexOf("(", curr.lastIndexOf("]"));
            if (index == -1) {
                index = curr.length();
            }

            taskName = curr.substring(7, index);

            if (taskType.equals("T")) { // To Do
                t = new ToDo(taskName);
            } else if (taskType.equals("D")) { // Deadline
                finish = curr.substring(curr.indexOf("by: ") + 4, curr.lastIndexOf(")"));
                t = new Deadline(taskName, finish);
            } else { // Event, assuming input file is always correct format
                start = curr.substring(curr.indexOf("from: ") + 6, curr.lastIndexOf("to:") - 1);
                finish = curr.substring(curr.indexOf("to: ") + 4, curr.lastIndexOf(")"));
                t = new Event(taskName, start, finish);
            }
            
            if (isMarked.equals("X")) {
                t.mark();
            }

            taskList.add(t);
        }

        return taskList;
    }
}



enum Options {
    bye, list, delete, mark, unmark, todo, deadline, event, error, save
}
