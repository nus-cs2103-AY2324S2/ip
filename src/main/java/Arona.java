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
        tasks.addElements(task);
        System.out.println("added: " + task);
    }

    private void markDone(int id) {
        tasks.markIndexAsDone(id);
        System.out.println("Nice, I've marked this task as done!");
        System.out.println(tasks.getTask(id));
    }

    private void markUndone(int id) {
        tasks.markIndexAsUndone(id);
        System.out.println("Okay, I've marked this task as not done.");
        System.out.println(tasks.getTask(id));
    }

    public static void main(String[] args) {
        Arona arona = new Arona();
        Scanner scanner = new Scanner(System.in);
        String command;

        arona.greet();

        while (true) {
            command = scanner.nextLine();
            String[] commandSplit = command.split(" ");

            if (commandSplit[0].equals("bye")) {
                break;
            } else if (commandSplit[0].equals("list")) {
                arona.listTasks();
            } else if (commandSplit[0].equals("mark")) {
                int index = Integer.parseInt(commandSplit[1]) - 1;
                arona.markDone(index);
            } else if (commandSplit[0].equals("unmark")) {
                int index = Integer.parseInt(commandSplit[1]) - 1;
                arona.markUndone(index);
            } else {
                arona.addTask(command);
            }
        }

        arona.exit();
        scanner.close();
    }
}
