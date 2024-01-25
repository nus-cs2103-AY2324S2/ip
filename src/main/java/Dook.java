import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Dook {

    private static final ArrayList<String> TASK_TYPES = new ArrayList<String>(Arrays.asList("todo", "deadline", "event"));
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private TaskList tasks;

    enum Command {
        BYE,
        LIST,
        ADD,
        MARK,
        UNMARK,
        DELETE,
        OTHER
    }

    Dook() {
        this.tasks = new TaskList();
    }

    private static Command getCommand(String input) {
        String firstWord;
        firstWord = input.split(" ", 2)[0];
        if (firstWord.equals("bye")) {
            return Command.BYE;
        } else if (firstWord.equals("list")) {
            return Command.LIST;
        } else if (Dook.TASK_TYPES.contains(firstWord)){
            return Command.ADD;
        } else if (firstWord.equals("mark")) {
            return Command.MARK;
        } else if (firstWord.equals("unmark")) {
            return Command.UNMARK;
        } else if (firstWord.equals("delete")){
            return Command.DELETE;
        } else {
            return Command.OTHER;
        }
    }

    private static Task getTask(String input) throws DookException {
        String[] cmds = input.split(" ", 2);
        String taskType = cmds[0];
        String taskDetails;
        try {
            taskDetails = cmds[1];
            if (taskDetails.isBlank()) {
                throw new DookException(":( Task description cannot be empty!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DookException(":( Task description cannot be empty!");
        }
        try {
            if (taskType.equals("todo")) {
                return new ToDo(taskDetails);
            } else if (taskType.equals("deadline")) {
                String[] details = taskDetails.split(" /by ", 2);
                String name = details[0];
                String doBy = details[1];
                return new Deadline(name, doBy);
            } else {
                String[] details = taskDetails.split(" /from ", 2);
                String name = details[0];
                String[] startAndEnd = details[1].split(" /to ", 2);
                String start = startAndEnd[0];
                String end = startAndEnd[1];
                return new Event(name, start, end);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DookException("Oh nyo! Wrong format for " + taskType + " command!");
        }
    }

    private void introduceSelf() {
        String logo = " ____              _    \n"
                + "|  _ \\  ___   ___ | | __      ╱|、\n"
                + "| | | |/ _ \\ / _ \\| |/ /    (˚ˎ 。7  \n"
                + "| |_| | |_| | |_| |   <      |、˜〵 \n"
                + "|____/ \\___/ \\___/|_|\\_\\     じしˍ,)ノ\n"; Dook.printSeparator();
        System.out.println("Hello from Dook! :D meow\n" + logo);
        System.out.println("What can I do for you? uwu"); Dook.printSeparator();
    }

    private static void printSeparator() {
        System.out.println(Dook.LINE_SEPARATOR);
    }

    private boolean executeCommand(String input) throws DookException {
        Command command = Dook.getCommand(input);
        String[] cmds = input.split(" ", 2);
        switch(command) {
            case BYE:
                executeBYE();
                return true;
            case LIST:
                executeLIST();
                return false;
            case ADD:
                executeADD(input);
                return false;
            case MARK:
                executeMARK(cmds);
                return false;
            case UNMARK:
                executeUNMARK(cmds);
                return false;
            case DELETE:
                executeDELETE(cmds);
                return false;
            case OTHER:
            default:
                throw new DookException("I don't understand this command :( Try again!");
        }
    }

    private void executeBYE() {
        System.out.println("Bye :(. Hope to see you again soon! ≽^- ˕ -^≼");
        Dook.printSeparator();
    }

    private void executeLIST() {
        System.out.println("Here are your tasks!");
        System.out.println(this.tasks);
    }

    private void executeADD(String input) throws DookException {
        Task toAdd = Dook.getTask(input);
        this.tasks.addTask(toAdd);
        System.out.println("Oki! I've added this task:");
        System.out.println(toAdd);
        this.tasks.printStatus();
    }

    private void executeMARK(String[] cmds) throws DookException {
        Task toMark;
        try {
            int positionToMark = Integer.valueOf(cmds[1]);
            toMark = this.tasks.get(positionToMark - 1);
        } catch (IndexOutOfBoundsException e) {
            DookException err;
            if (this.tasks.size() == 0) {
                err = new DookException("Nooo! You don't have any tasks to mark :(");
            } else {
                err = new DookException(String.format("Nooo! " +
                                "You have %d tasks!" +
                                " Valid inputs for mark is in the range [0 - %d]",
                        this.tasks.size()));
            }
            throw err;
        } catch (NumberFormatException e) {
            throw new DookException("Ohnoo! Please enter a number after \"mark\"!");
        }
        toMark.markAsDone();
        System.out.println("Oki! :D Good job! I've marked this task as done:");
        System.out.println(toMark);
    }

    private void executeUNMARK(String[] cmds) throws DookException {
        Task toUnmark;
        try {
            int positionToUnmark = Integer.valueOf(cmds[1]);
            toUnmark = this.tasks.get(positionToUnmark - 1);
        } catch (IndexOutOfBoundsException e) {
            DookException err;
            if (this.tasks.size() == 0) {
                err = new DookException("Nooo! You don't have any tasks to unmark :(");
            } else {
                err = new DookException(String.format("Nooo! " +
                                "You have %d tasks!" +
                                " Valid inputs for unmark is in the range [0 - %d]",
                        this.tasks.size()));
            }
            throw err;
        } catch (NumberFormatException e) {
            throw new DookException("Ohnoo! Please enter a number after \"ummark\"!");
        }
        toUnmark.markAsNotDone();
        System.out.println("Lazy bum. >:( I've marked this task as done:");
        System.out.println(toUnmark);
    }

    private void executeDELETE(String[] cmds) throws DookException {
        Task toDelete;
        int positionToDelete;
        try {
            positionToDelete = Integer.valueOf(cmds[1]);
            toDelete = this.tasks.get(positionToDelete - 1);
        } catch (IndexOutOfBoundsException e) {
            DookException err;
            if (this.tasks.size() == 0) {
                err = new DookException("Nooo! You don't have any tasks to delete :(");
            } else {
                err = new DookException(String.format("Nooo! " +
                                "You have %d tasks!" +
                                " Valid inputs for delete is in the range [0 - %d]",
                        this.tasks.size()));
            }
            throw err;
        } catch (NumberFormatException e) {
            throw new DookException("Ohnoo! Please enter a number after \"delete\"!");
        }
        System.out.println("Oki! Bye Bye task!");
        this.tasks.remove(positionToDelete - 1);
        System.out.println("You deleted this task :(");
        System.out.println(toDelete);
    }
    public static void main(String[] args) {
        Dook dook = new Dook();
        dook.introduceSelf();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            Dook.printSeparator();
            boolean exitLoop = false;
            try {
                exitLoop = dook.executeCommand(input);
            } catch (DookException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error detected, please try again :(\n" + e);
            }
            if (exitLoop) {
                break;
            }
            Dook.printSeparator();
        }ele
                
    }
}
