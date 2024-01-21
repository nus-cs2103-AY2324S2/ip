import java.util.ArrayList;
import java.util.Scanner;

public class Dino {
    private ArrayList<Task> taskList;
    private int taskNum;

    public Dino() {
        this.taskList = new ArrayList<>();
    }

    public static void welcome() {
        System.out.println("Hola! I'm Dino.\nWhat are you doing here?");
    }

    public void goodbye() {
        System.out.println("Goodbye! It was nice meeting you.");
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);
        String chat = sc.nextLine();
        while (!chat.equalsIgnoreCase("bye")) {
            System.out.println(chat);
            chat = sc.nextLine();
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void listTask() {
        System.out.println("Here are the tasks that you wanted to do:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            int index = i + 1;
            System.out.println(index + ".[" + task.getStatusIcon() + "] " + task);
        }
    }

    public void chatHere() {

        Scanner sc = new Scanner(System.in);

        String command = sc.next();

        while (true) {
            switch (command) {
                case "list":
                    listTask();
                    break;

                case "bye":
                    goodbye();
                    sc.close();
                    return;

                case "todo":
                    try {
                        String todoDescription = sc.nextLine().trim();
                        if (todoDescription.isEmpty()) {
                            throw new DinoException("Task description cannot be empty.");
                        }
                        addTask(new ToDo(todoDescription));

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + taskList.get(taskList.size() - 1));
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } catch (DinoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "deadline":
                    try {
                        String deadlineInput = sc.nextLine().trim();
                        String[] deadlineParts = deadlineInput.split("/by");
                        if (deadlineParts.length != 2) {
                            throw new DinoException("Invalid input format for deadline. Please use: deadline <deadline name> /by <time>");
                        }
                        String deadlineName = deadlineParts[0].trim();
                        String deadlineTime = deadlineParts[1].trim();
                        addTask(new Deadline(deadlineName, deadlineTime));

                        if (deadlineName.isEmpty() || deadlineTime.isEmpty()) {
                            throw new DinoException("Deadline name and time cannot be empty.");
                        }

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + taskList.get(taskList.size() - 1));
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } catch (DinoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "event":
                    try {
                        String eventInput = sc.nextLine().trim();
                        String[] eventParts = eventInput.split("/from|/to");
                        if (eventParts.length != 3) {
                            throw new DinoException("Invalid input format for event. Please use: event <event name> /from <time> /to <time>");
                        }
                        String eventName = eventParts[0].trim();
                        String startTime = eventParts[1].trim();
                        String endTime = eventParts[2].trim();
                        addTask(new Event(eventName, startTime, endTime));

                        if (eventName.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                            throw new DinoException("Event name, start time, and end time cannot be empty.");
                        }

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + taskList.get(taskList.size() - 1));
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } catch (DinoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "mark":
                    int taskNum = sc.nextInt();
                    if (taskNum > taskList.size()) {
                        System.out.println("Uh oh, we do not have a task assigned to that number.");
                    } else {
                        System.out.println("Good job on completing the task! I have checked it off the list.");
                        Task completed = taskList.get(taskNum - 1);
                        completed.markAsDone();
                    }
                    break;

                case "unmark":
                    int taskNumber = sc.nextInt();
                    if (taskNumber > taskList.size()) {
                        System.out.println("Uh oh, we do not have a task assigned to that number.");
                    } else {
                        System.out.println("Ah, I will mark it as undone. Remember to do it asap!");
                        Task missing = taskList.get(taskNumber - 1);
                        missing.markAsUndone();
                    }
                    break;

                default:
                    try {
                        throw new DinoException("I don't understand ;;");
                    } catch (DinoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
            }
            command = sc.next();
        }
    }


    public static void main(String[] args) {
        Dino mrDino = new Dino();
        welcome();
        mrDino.chatHere();
    }
}