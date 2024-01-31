import java.util.Scanner;

public class Ui {

    private TaskList taskList;
    public Ui() {
        taskList = new TaskList();
        Storage.read(taskList);
        greet();
        listen();
    }
    public void greet() {
        fillerLine();
        System.out.println("    Hello! I am Greg.\n    What can I do for you?");
        fillerLine();
    }

    public void bye() {
        Storage.save(taskList);
        fillerLine();
        System.out.println("    Goodbye! Hope to see you again soon!");
        fillerLine();
    }

    public static void fillerLine() {
        System.out.println("    _______________________________________");
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String request = sc.nextLine();
            if (request.equals("bye")) {
                bye();
                break;
            } else {
                Parser.commands(taskList, request, false, false);
            }
        }
    }

}
