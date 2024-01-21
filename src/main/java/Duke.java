import java.util.Scanner;

public class Duke {
    private static final TaskList taskList = new TaskList();

    private static void cat() {
        System.out.println(" |\\ /| ");
        System.out.println("=(O O)=");
        System.out.println(" /   \\ ");
    }

    private static void line() {
        for (int i = 0; i < 72; i++) {
            System.out.print('─');
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
            String data = sc.nextLine().trim();
            switch (command) {
                case "bye":
                    break label;
                case "list":
                    System.out.print(taskList);
                    break;
                case "mark":
                case "unmark":
                case "delete":
                    try {
                        int index = Integer.parseInt(data) - 1;
                        Task task = taskList.getTask(index);
                        if (command.equals("delete")) {
                            taskList.deleteTask(index);
                            System.out.println("Alright, I've deleted the task:\n  " + task);
                            break;
                        }
                        if (command.equals("mark")) {
                            task.setComplete();
                        } else {
                            task.setIncomplete();
                        }
                        System.out.println("Alright, I've set the task as " + task.status() + ":\n  " + task);
                    } catch (TaskList.TaskNotFound e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("\"" + data + "\" is not a number. Please try again.");
                    };
                    break;
                case "todo":
                case "deadline":
                case "event":
                    try {
                        Task task = Task.of(command, data);
                        taskList.addTask(task);
                        System.out.println("Added task " + task.describe());
                    } catch (Task.InvalidComponents | Task.InvalidType e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("I have no idea what you want.\n" +
                            "I can respond to \"list\", \"deadline\", \"event\", \"todo\", \"mark\" and \"unmark\"");
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
