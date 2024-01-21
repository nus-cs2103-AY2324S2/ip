import java.util.Scanner;

public class Main {
    private static Duke duke = new Duke();

    private static void cat() {
        System.out.println(" |\\ /| ");
        System.out.println("=(O O)=");
        System.out.println(" /   \\ ");
    }

    private static void line() {
        for (int i = 0; i < 72; i++) {
            System.out.print('_');
        }
        System.out.print('\n');
    }

    private static void hello() {
        cat();
        System.out.println("Hello! I'm the cat that lives in your walls.");
        System.out.println("What do you need?");
        line();
    }

    private static void bye() {
        System.out.println("*The cat recedes into the wall with a bored look on its face*");
        line();
    }

    public static void repl() {
        Scanner sc = new Scanner(System.in);

        String input;
        while (sc.hasNextLine()) {
            line();
            input = sc.nextLine();
            if (!parseCommand(input)) {
                break;
            }
            line();
        }
    }

    public static boolean parseCommand(String command) {
        switch (command) {
            case "bye":
                return false;
            case "list":
                System.out.println(duke);
                break;
            default:
                Task task = new Task(command);
                System.out.println("Added task " + task.describe());
                duke.addTask(task);
        }
        return true;
    }

    public static void main(String[] args) {
        hello();
        repl();
        bye();
    }
}
