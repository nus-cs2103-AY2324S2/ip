import java.util.ArrayList;
import java.util.Scanner;

public class Huyang {
    private String logo =
            "  ___ ___                                      \n" +
                    " /   |   \\ __ __ ___.__._____    ____    ____  \n" +
                    "/    ~    \\  |  <   |  |\\__  \\  /    \\  / ___\\ \n" +
                    "\\    Y    /  |  /\\___  | / __ \\|   |  \\/ /_/  >\n" +
                    " \\___|_  /|____/ / ____|(____  /___|  /\\___  / \n" +
                    "       \\/        \\/          \\/     \\//_____/  ";

    private ArrayList<String> tasks = new ArrayList<>();
    public void greet() {
        // Greeting message
        System.out.println("Hello! I'm Huyang");
        System.out.println(logo);
        System.out.println("What can I do for you?");
    }

    public void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listTasks();
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }
        scanner.close();
    }

    private void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    public void farewell() {
        // Farewell message
        System.out.print("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Huyang huyang = new Huyang();

        huyang.greet();
        huyang.echo();
        huyang.farewell();
    }
}


