import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Fireraya {

    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void Start() {
        System.out.println("--------------------------------");
        System.out.println("Hello, my name is Fireraya!");
        System.out.println("What can I do for you today sir?");
        System.out.println("--------------------------------");
    }

    public static void AddTask(String a) {
        tasks.add(new Task(a));
        System.out.println("added: " + a);
    }

    public static void ListTasks() {
        System.out.println("Here are your tasks!");
    for (int i = 0; i < tasks.size(); i++) {
        System.out.println( i + ". " + tasks.get(i).getTask());
        }
    }

    public static void End() {

        System.out.println("Bye, hope to see you soon!");
    }

    public static void main(String[] args) {

        Start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                End();
                break;
            }

            if (input.equals("list")) {
                ListTasks();
                continue;
            }

            AddTask(input);
        }
        scanner.close();
    }
}
