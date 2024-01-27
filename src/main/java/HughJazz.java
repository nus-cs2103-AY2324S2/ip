import java.util.Scanner;
public class HughJazz {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    public static void main(String[] args) {
        greeting();
        Scanner scn = new Scanner(System.in);
        String userInput;

        while (true) {
            try {
                userInput = scn.nextLine().trim();
                if("bye".equalsIgnoreCase(userInput)) {
                    break;
                } else{
                    handleInput(userInput);
                }
            } catch (ChatbotException e) {
                System.out.println(e.getMessage());
            }



        }
        ending();
        scn.close();
    }

    private static void handleInput(String userInput) throws ChatbotException {
        if (userInput.toLowerCase().startsWith("todo")) {
            addTask(userInput, "todo");
        } else if (userInput.toLowerCase().startsWith("deadline")) {
            addTask(userInput, "deadline");
        } else if (userInput.toLowerCase().startsWith("event")) {
            addTask(userInput, "event");
        } else if ("list".equalsIgnoreCase(userInput)) {
            printTasks();
        } else {
            throw new ChatbotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void greeting() {
        System.out.println("Hello! I'm HughJazz");
        System.out.println("What can I do for you?");
    }
    public static void ending() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    private static void addTask(String userInput, String taskType) throws ChatbotException {
        String taskDetails = userInput.substring(taskType.length()).trim();
        if (taskDetails.isEmpty()) {
            throw new ChatbotException("OOPS!!! The description of a " + taskType + " cannot be empty.");
        }
        if (taskCount < MAX_TASKS) {
            Task newTask;
            if (taskType.equals("todo")) {
                newTask = new Todo(taskDetails);
            } else if (taskType.equals("deadline")) {
                String[] details = taskDetails.split(" /by ", 2);
                newTask = new Deadline(details[0], details[1]);
            } else if (taskType.equals("event")) {
                String[] details = taskDetails.split(" /from ", 2);
                String[] times = details[1].split(" /to ", 2);
                newTask = new Event(details[0], times[0], times[1]);
            } else {
                // Handle invalid task type
                System.out.println("Invalid task type");
                return;
            }
            tasks[taskCount] = newTask;
            taskCount++;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + newTask);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
    }

    private static void markTask(String userInput, boolean isDone) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskNumber >= 0 && taskNumber < taskCount) {
                if (isDone) {
                    tasks[taskNumber].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                }
                else {
                    tasks[taskNumber].unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                }
                System.out.println("  " + tasks[taskNumber].toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number");
        }
    }
    private static void printTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
    }
}

