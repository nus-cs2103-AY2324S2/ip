import java.util.ArrayList;
import java.util.Scanner;

public class Dino {
    private ArrayList<Task> taskList;
    private int taskNum;

    public Dino() {
        this.taskList = new ArrayList<>();
    }

    public void welcome() {
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

    public static void main(String[] args) {
        Dino mrDino = new Dino();
        Scanner sc = new Scanner(System.in);
        mrDino.welcome();

        String command = sc.next();

        while (true) {
            switch (command) {
                case "list":
                    mrDino.listTask();
                    break;

                case "bye":
                    mrDino.goodbye();
                    sc.close();
                    return;

                case "todo":
                    String todoDescription = sc.nextLine().trim();
                    mrDino.addTask(new ToDo(todoDescription));

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + mrDino.taskList.get(mrDino.taskList.size() - 1));
                    System.out.println("Now you have " + mrDino.taskList.size() + " tasks in the list.");
                    break;

                case "deadline":
                    String deadlineInput = sc.nextLine().trim();
                    String[] deadlineParts = deadlineInput.split("/by");
                    if (deadlineParts.length != 2) {
                        System.out.println("Invalid input format for deadline. Please use: deadline <deadline name> /by <time>");
                        break;
                    }
                    String deadlineName = deadlineParts[0].trim();
                    String deadlineTime = deadlineParts[1].trim();
                    mrDino.addTask(new Deadline(deadlineName, deadlineTime));

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + mrDino.taskList.get(mrDino.taskList.size() - 1));
                    System.out.println("Now you have " + mrDino.taskList.size() + " tasks in the list.");
                    break;

                case "event":
                    String eventInput = sc.nextLine().trim();
                    String[] eventParts = eventInput.split("/from|/to");
                    if (eventParts.length != 3) {
                        System.out.println("Invalid input format for event. Please use: event <event name> /from <time> /to <time>");
                        break;
                    }
                    String eventName = eventParts[0].trim();
                    String startTime = eventParts[1].trim();
                    String endTime = eventParts[2].trim();
                    mrDino.addTask(new Event(eventName, startTime, endTime));

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + mrDino.taskList.get(mrDino.taskList.size() - 1));
                    System.out.println("Now you have " + mrDino.taskList.size() + " tasks in the list.");
                    break;

                case "mark":
                    int taskNum = sc.nextInt();
                    if (taskNum > mrDino.taskList.size()) {
                        System.out.println("Uh oh, we do not have a task assigned to that number.");
                    } else {
                        System.out.println("Good job on completing the task! I have checked it off the list.");
                        Task completed = mrDino.taskList.get(taskNum - 1);
                        completed.markAsDone();
                    }
                    break;

                case "unmark":
                    int taskNumber = sc.nextInt();
                    if (taskNumber > mrDino.taskList.size()) {
                        System.out.println("Uh oh, we do not have a task assigned to that number.");
                    } else {
                        System.out.println("Ah, I will mark it as undone. Remember to do it asap!");
                        Task missing = mrDino.taskList.get(taskNumber - 1);
                        missing.markAsUndone();
                    }
                    break;

                default:
                    System.out.println("I don't understand ;;");
                    break;
            }
            command = sc.next();
        }

    }
}