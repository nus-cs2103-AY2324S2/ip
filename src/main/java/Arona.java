import java.util.Scanner;

public class Arona {
    private TaskList tasks;
    Arona() {
        tasks = new TaskList();
    }
    private void greet() {
        System.out.println("Welcome, sensei! Arona has been waiting for you.\n");
    }

    private void exit() {
        System.out.println("Thanks for the hard work, Sensei.\n");
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks.");
        } else {
            System.out.println("There's lots of work to do, but I know you can do it!");
        }
        tasks.printElements();
    }

    private void addTask(String task) {
        System.out.println("added: " + task);
        tasks.addElements(task);
    }

    private void echoCommand(String command) {
        System.out.println(command);
    }
    public static void main(String[] args) {
        Arona arona = new Arona();
        Scanner scanner = new Scanner(System.in);
        String command;

        arona.greet();

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                arona.listTasks();
            } else {
                arona.addTask(command);
            }
        }

        arona.exit();
        scanner.close();
    }
}
