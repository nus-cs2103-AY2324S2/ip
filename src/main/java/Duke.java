import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final int max_tasks = 100;
    private static final ArrayList<Task> tasks = new ArrayList<Task>(max_tasks);
    private static int taskCount = 0;

    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        EVENT,
        DEADLINE,
        TODO,
        DELETE,
        UNKNOWN
    }

    public static void main(String[] args) {
        String name = "BotChat";

        //Introduction message
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm " + name + "\n What can I do for you?\n" +
                "____________________________________________________________\n");

        userInput();
    }

    private static void userInput() {
        //Scanner to scan what the user is inputting
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String input = scanner.nextLine();
                try {
                    handleInput(input);
                } catch (DukeException e) {
                    System.out.println("____________________________________________________________\n" +
                            e.getMessage() + "\n" +
                            "____________________________________________________________\n");
                }
            }
        } finally {
                scanner.close();
        }
    }

    //Method to handle inputs
    private static void handleInput(String input) throws DukeException {
        Command command = getCommand(input.split(" ")[0]);
        switch (command) {
            //Bye input + close scanner + exit program
            case BYE:
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                System.exit(0);
                break;
            case LIST:
                //List tasks
                listTasks();
                break;
            case MARK:
                //mark tasks as complete
                markTask(input);
                break;
            case UNMARK:
                //unmark tasks
                unmarkTask(input);
                break;
            case EVENT:
                //Add event task to list
                addEventTask(input);
                break;
            case DEADLINE:
                //Add deadline task to list
                addDeadlineTask(input);
                break;
            case TODO:
                //Add tod0 task to list
                addTodoTask(input);
                break;
            case DELETE:
                //Delete tasks from list
                deleteTask(input);
                break;
            case UNKNOWN:
                throw new DukeException("Sorry, I do not understand that command. Please try again.");
        }
    }

    private static Command getCommand(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    //Method to list tasks
    private static void listTasks() {
        System.out.println("____________________________________________________________\n" +
                " Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    //Method to add tasks to list
    private static void addTask(Task task) throws DukeException {
        //Check that taskCount does not exceed maxtask
        if (taskCount < max_tasks) {
            tasks.add(task);
            System.out.println("____________________________________________________________\n" +
                    " Okay! Added to your list: \n"
                    + "   " + task
                    + "\n Now you have " + tasks.size() + " tasks in your list.\n" +
                    "____________________________________________________________\n");
        } else {
            throw new DukeException(" Ohno :( Your task list is full. Complete some tasks first.");
        }
    }

    //Method to mark tasks
    private static void markTask(String input) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(input.substring(5)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).mark();
                System.out.println("____________________________________________________________\n" +
                        " Nice! This task has been marked as done:\n" +
                        "   " + tasks.get(taskIndex) + "\n" +
                        "____________________________________________________________\n");
            } else {
                throw new DukeException(" Invalid task index inputted. Please try again.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" Please indicate the task number you want to mark complete.");
        }
    }

    //Method to unmark tasks
    private static void unmarkTask(String input) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).unmark();
                System.out.println("____________________________________________________________\n" +
                        " Okay. This task has been unmarked. \n" +
                        "   " + tasks.get(taskIndex) + "\n" +
                        "____________________________________________________________\n");
            } else {
                throw new DukeException(" Invalid task index inputted. Please try again.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" Please indicate the task number you want to unmark.");
        }
    }

    //Method to add event task
    private static void addEventTask(String input) throws DukeException {
        String[] parts = input.split("/", 3);
        if (parts.length == 3) {
            String description = parts[0].substring(5);
            String from = parts[1].substring(5);
            String to = parts[2].substring(3);

            if (!description.isEmpty()) {
                Task task = new Event(description, from, to);
                addTask(task);
            } else {
                throw new DukeException(" Please provide a valid description of the task.");
            }
        } else {
            throw new DukeException(" Invalid format of Event task. Please try again with the correct format.\n" +
                    " event (event name) /from (start) /to (end)");
        }
    }

    //Method to add deadline task
    private static void addDeadlineTask(String input) throws DukeException {
        String[] parts = input.split("/", 2);
        if (parts.length == 2) {
            String description = parts[0].substring(8);
            String by = parts[1].substring(3);

            if (!description.isEmpty()) {
                Task task = new Deadline(description, by);
                addTask(task);
            } else {
                throw new DukeException(" Please provide a valid description of the task.");
            }
        } else {
            throw new DukeException(" Invalid format of Deadline task. Please try again with the correct format.\n" +
                    " deadline (event name) /by (deadline)");
        }
    }

    //Method to add tod0 task
    private static void addTodoTask(String input) throws DukeException {
        if (!input.substring(4).isEmpty()) {
            Task task = new Todo(input.substring(4));
            addTask(task);
        } else {
            throw new DukeException(" Please provide a valid description of the task.");
        }
    }

    //Method to delete task
    private static void deleteTask(String input) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task removedTask = tasks.get(taskIndex);
                tasks.remove(taskIndex);
                System.out.println("____________________________________________________________\n" +
                        " Okay. This task has been removed: \n" +
                        "   " + removedTask + "\n" +
                        " Now you have " + tasks.size() + " tasks in your list.\n" +
                        "____________________________________________________________\n");
            } else {
                throw new DukeException(" Invalid task index inputted. Please try again.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" Please indicate the task number you want to delete.");
        }
    }
}
