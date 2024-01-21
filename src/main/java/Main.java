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

        outer: while (sc.hasNextLine()) {
            line();
            String command = sc.next();
            String data = sc.nextLine();
            switch (command) {
                case "bye":
                    bye();
                    break outer;
                case "list":
                    System.out.println(duke);
                    break;
                default:
                    Task task = new Task(data);
                    System.out.println("Added task " + task.describe());
                    duke.addTask(task);
            }
            line();
        }
    }

    public static void main(String[] args) {
        hello();
        repl();
    }
}
