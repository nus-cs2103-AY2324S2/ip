import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final ArrayList<String> TASK_TYPES = new ArrayList<String>(Arrays.asList("todo", "deadline", "event"));
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    private TaskList tasks;

    enum Command {
        BYE,
        LIST,
        ADD,
        MARK,
        UNMARK,
        OTHER
    }

    Duke() {
        this.tasks = new TaskList();
    }

    private static Command getCommand(String input) {
        String firstWord;
        firstWord = input.split(" ", 2)[0];
        if (firstWord.equals("bye")) {
            return Command.BYE;
        } else if (firstWord.equals("list")) {
            return Command.LIST;
        } else if (Duke.TASK_TYPES.contains(firstWord)){
            return Command.ADD;
        } else if (firstWord.equals("mark")) {
            return Command.MARK;
        } else if (firstWord.equals("unmark")) {
            return Command.UNMARK;
        } else {
            return Command.OTHER;
        }
    }

    private static Task getTask(String input) {
        String[] cmds = input.split(" ", 2);
        String taskType = cmds[0];
        String taskDetails = cmds[1];
        if (taskType.equals("todo")) {
            return new ToDo(taskDetails);
        } else if (taskType.equals("deadline")) {
            String[] details = taskDetails.split(" /by ", 2);
            String name = details[0];
            String doBy = details[1];
            return new Deadline(details[0], details[1]);
        } else {
            String[] details = taskDetails.split(" /from ", 2);
            String name = details[0];
            String[] startAndEnd = details[1].split(" /to ", 2);
            String start = startAndEnd[0];
            String end = startAndEnd[1];
            return new Event(name, start, end);
        }
    }

    private void introduceSelf() {
        String logo = " ____              _    \n"
                + "|  _ \\  ___   ___ | | __      ╱|、\n"
                + "| | | |/ _ \\ / _ \\| |/ /    (˚ˎ 。7  \n"
                + "| |_| | |_| | |_| |   <      |、˜〵 \n"
                + "|____/ \\___/ \\___/|_|\\_\\     じしˍ,)ノ\n";
        Duke.printSeparator();
        System.out.println("Hello from Dook! :D meow\n" + logo);
        System.out.println("What can I do for you? uwu");
        Duke.printSeparator();
    }

    private static void printSeparator() {
        System.out.println(Duke.LINE_SEPARATOR);
    }

    private boolean executeCommand(String input) {
        Command command = Duke.getCommand(input);
        String[] cmds = input.split(" ", 2);
        switch(command) {
            case BYE:
                return true;
            case LIST:
                System.out.println("Here are your tasks!");
                System.out.println(this.tasks);
                return false;
            case ADD:
                Task toAdd = Duke.getTask(input);
                this.tasks.addTask(toAdd);
                System.out.println("Oki! I've added this task:");
                System.out.println(toAdd);
                this.tasks.printStatus();
                return false;
            case MARK:
                int positionToMark = Integer.valueOf(cmds[1]);
                Task toMark = this.tasks.get(positionToMark - 1);
                toMark.markAsDone();
                System.out.println("Oki! :D Good job! I've marked this task as done:");
                System.out.println(toMark);
                return false;
            case UNMARK:
                int positionToUnmark = Integer.valueOf(cmds[1]);
                Task toUnmark = this.tasks.get(positionToUnmark - 1);
                toUnmark.markAsNotDone();
                System.out.println("Lazy bum. >:( I've marked this task as done:");
                System.out.println(toUnmark);
                return false;
            default:
                System.out.println("I don't understand this command :( Try again!");
                return false;
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.introduceSelf();
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true) {
            input = sc.nextLine();
            Duke.printSeparator();
            boolean exitLoop = false;
            try {
                exitLoop = duke.executeCommand(input);
            } catch (Exception e) {
                System.out.println("Error detected, please try again :(");
            }
            if (exitLoop) {
                break;
            }
            Duke.printSeparator();
        }
        System.out.println("Bye :(. Hope to see you again soon! ≽^- ˕ -^≼");
        Duke.printSeparator();
    }
}
