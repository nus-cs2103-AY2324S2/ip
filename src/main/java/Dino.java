import java.util.ArrayList;
import java.util.Scanner;
public class Dino {
    private ArrayList<Task> taskList;
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
        for (int i = 0; i < taskList.size(); i++) {
            String task = taskList.get(i).toString();
            int index = i + 1;
            System.out.println(index + ". " + task);
        }
    }

    public static void main(String[] args) {
        Dino mrDino = new Dino();
        Scanner sc = new Scanner(System.in);
        mrDino.welcome();

        String newTask = sc.nextLine();

        while (true) {
            if (newTask.equals("list")) {
                mrDino.listTask();
            } else if (newTask.equals("bye")) {
                mrDino.goodbye();
                return;
            } else {
                Task task = new Task(newTask);
                mrDino.addTask(task);
                System.out.println("added: " + newTask);
            }
            newTask = sc.nextLine();
        }

    }
}
