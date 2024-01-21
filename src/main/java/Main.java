import java.util.Scanner;

public class Main {
    private static final Duke duke = new Duke();

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

        label:
        while (sc.hasNextLine()) {
            line();
            String command = sc.next();
            String data = sc.nextLine();
            switch (command) {
                case "bye":
                    break label;
                case "list":
                    System.out.print(duke);
                    break;
                case "mark":
                case "unmark":
                    String trimmed = data.trim();
                    try {
                        int index = Integer.parseInt(trimmed) - 1;
                        Task task = duke.getTask(index);
                        if (command.equals("mark")) {
                            task.setComplete();
                        } else {
                            task.setIncomplete();
                        }
                        System.out.println("Alright, I've set the task as " + task.status() + ":\n  " + task);
                    } catch (Duke.TaskNotFound e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("\"" + trimmed + "\" is not a number. Please try again.");
                    };
                    break;
                default:
                    System.out.println("I have no idea what you want.\n" +
                            "I can respond to \"list\", \"deadline\", \"event\", \"todo\", \"mark\" and \"unmark\"");
                    break;
            }
            line();
        }
    }

    public static void main(String[] args) {
        hello();
        repl();
        bye();
    }
}
