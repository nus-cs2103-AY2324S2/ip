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
                    return;
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
                    Task task = new Task(command);
                    mrDino.addTask(task);
                    System.out.println("added: " + command);
                    break;
            }
            command = sc.next();
        }

    }
}
