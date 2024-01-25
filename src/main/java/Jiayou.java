import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Jiayou {
    private static final String LINE = "____________________________________________________________";
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private int counter = 0;

    private void printList() {
        for (Task task : this.taskList) {
            System.out.println(task.toString());
        }
    }

    public static void main(String[] args) {
        Jiayou jiayou = new Jiayou();
        Scanner sc = new Scanner(System.in);
        System.out.println(Jiayou.LINE);
        System.out.println("Hello! I'm Jiayou.");
        System.out.println("What can I do for you?");
        System.out.println(Jiayou.LINE);

        while (true) {
            String input = sc.nextLine();
            System.out.println(Jiayou.LINE);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(Jiayou.LINE);
                break;
            } else if (input.equals("list")) {
                jiayou.printList();
            } else {
                jiayou.counter += 1;
                Task newTask = new Task(jiayou.counter, input);
                jiayou.taskList.add(newTask);

                System.out.println("added: " + input);
            }
            System.out.println(Jiayou.LINE);
        }
        sc.close();
    }
}
