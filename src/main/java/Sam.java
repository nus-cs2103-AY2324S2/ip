
import java.util.List;
import java.util.Scanner;

public class Sam {
    private Ui ui;
    private Storage storage;
    private static void displayList(List<Task> taskList) {
        System.out.println("____________________________________________________________");

        if (taskList.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println(((i + 1) + "." + task));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private void processCommand(String userInput, List<Task> taskList) {
        if (userInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty.");
        }
        String[] parts = userInput.split(" ");
        String commandStr = parts[0];

        CommandType command;
        try {
            command = CommandType.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("I'm sorry, but I don't understand what that means.");
        }

        switch (command) {
            case LIST:
                displayList(taskList);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                addTask(userInput, taskList);
                break;
            case MARK:
                markTask(userInput, taskList);
                break;
            case UNMARK:
                unmarkTask(userInput, taskList);
                break;
            case DELETE:
                deleteTask(userInput, taskList);
                break;
            default:
                throw new IllegalArgumentException("Hey, please choose from the following commands\n" +
                        "if you want to add task, please use todo, deadline or event\n" +
                        "if you want to mark or unmark task, please use mark or unmark\n" +
                        "if you want delete a task, please use delete\n" +
                        "if you want to view the existing task list, please enter list.");
        }
    }

    public void markTask(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide a task number.");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException("Please check how many tasks are there in your list.");
        }
        taskList.get(taskIndex).markAsDone();
        System.out.println("Nice, I've marked this task as done for you:");
        System.out.println((taskList.get(taskIndex)));
    }

    public void unmarkTask(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide a task number.");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException("Please check how many tasks are there in your list.");
        }
        taskList.get(taskIndex).markAsUndone();
        System.out.println("Nice, I've marked this task as undone for you:");
        System.out.println((taskList.get(taskIndex)));
    }
    public void addTask(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("The description of a task cannot be empty.");
        }
        String command = parts[0].toLowerCase();
        String taskInfo = parts[1];

        Task newTask;

        switch (command) {
            case "todo":
                newTask = new ToDo(taskInfo);
                break;
            case "deadline":
                String[] details = taskInfo.split("/by", 2);
                if (details.length < 2) {
                    throw new IllegalArgumentException("Invalid format for deadline, please provide a deadline using /by.");
                }
                String deadlineDescription = details[0].trim();
                String deadline = details[1].trim();
                newTask = new Deadline(deadlineDescription, deadline);
                break;
            case "event":
                String[] taskDetails = taskInfo.split("/from", 2);
                if (taskDetails.length < 2) {
                    throw new IllegalArgumentException("Invalid format for event, please provide event details by using /from and /to.");
                }
                String eventDescription = taskDetails[0].trim();
                String dateTimeDetails = taskDetails[1].trim();
                String[] dateTimeSplit = dateTimeDetails.split("/to", 2);
                if (dateTimeSplit.length < 2) {
                    throw new IllegalArgumentException("Invalid format for event, please provide event details by using /from and /to.");
                }
                String from = dateTimeSplit[0].trim();
                String until = dateTimeSplit[1].trim();
                newTask = new Event(eventDescription, from, until);
                break;
            default:
                throw new IllegalArgumentException("I'm sorry, but I don't understand what you intend to do.");
        }

        taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    public void deleteTask(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide the task number to delete.");
        }

        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException("Invalid task number. Please check how many tasks your have in the list.");
        }

        Task removedTask = taskList.remove(taskIndex);
        System.out.println("---------------------------");
        System.out.println("I've removed this task:");
        System.out.println(" " + removedTask);
        System.out.println("Now you have " + taskList.size() + " task(s) left in the list. ");
        System.out.println("---------------------------");
    }

    public Sam(String FILE_PATH) {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
    }
    public void run() {
        List<Task> taskList = storage.load();
        ui.greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                ui.bye();
                break;
            }
            try{
                processCommand(userInput, taskList);
                storage.save(taskList);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
    public static void main(String[] args) {
        new Sam("./data/Sam.txt").run();
    }
}
