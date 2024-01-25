import java.util.Scanner;

public class Quacky {
    private static TaskList tasks = new TaskList();

    private static String format(String text){
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder("\t____________________________________________________________\n");

        for (String line : lines) {
            sb.append("\t").append(line).append("\n");
        }

        sb.append("\t____________________________________________________________");
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(format("Quack! how u doing, Im Quacky. How can I help you?"));
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    System.out.println(format(tasks.toString()));
                } else if (command.startsWith("mark ")) {
                    int space = command.indexOf(' ');
                    int taskNumber = Integer.parseInt(command.substring(space + 1)) - 1;
                    tasks.markCompleteTask(taskNumber);
                    String message = "Quack! I marked this task as done \n\t" + tasks.printTask(taskNumber);
                    System.out.println(format(message));
                } else if (command.startsWith("unmark ")) {
                    int space = command.indexOf(' ');
                    int taskNumber = Integer.parseInt(command.substring(space + 1)) - 1;
                    tasks.unmarkCompleteTask(taskNumber);
                    String message = "Quack! I marked this task as not done\n\t" + tasks.printTask(taskNumber);
                    System.out.println(format(message));
                } else if (command.startsWith("delete ")) {
                    int space = command.indexOf(' ');
                    int taskNumber = Integer.parseInt(command.substring(space + 1)) - 1;
                    tasks.deleteTask(taskNumber);
                    String message = "Quack! I removed this task:  \n\t" + tasks.printTask(taskNumber)
                                            + "\nNow you have " + tasks.taskNumber() + " tasks in the list.";
                    System.out.println(format(message));
                }
                else if (command.startsWith("todo ")) {
                    if (command.trim().equals("todo")) {
                        int space = command.indexOf(' ');

                    }
                    String taskDescription = command.substring(5);
                    Task newTask = new Todo(taskDescription);
                    tasks.addTask(newTask);
                    System.out.println(format("Quack! I've added this task:\n\t" + newTask +
                            "\nNow you have " + tasks.taskNumber() + " tasks in the list."));
                } else if (command.startsWith("deadline ")) {
                    String[] parts = command.substring(9).split(" /by ");
                    Task newTask = new Deadline(parts[0], parts[1]);
                    tasks.addTask(newTask);
                    System.out.println(format("Got it. I've added this task:\n\t" + newTask +
                            "\nNow you have " + tasks.taskNumber() + " tasks in the list."));
                } else if (command.startsWith("event ")) {
                    String[] parts = command.substring(6).split(" /from | /to ");
                    Task newTask = new Event(parts[0], parts[1], parts[2]);
                    tasks.addTask(newTask);
                    System.out.println(format("Got it. I've added this task:\n\t" + newTask +
                            "\nNow you have " + tasks.taskNumber() + " tasks in the list."));
                } else {
                    throw new QuackyException("Quack? (In confusion)");
                }
            } catch (QuackyException e) {
                System.out.println(format(e.getMessage()));
            } finally {
                command = scanner.nextLine();
            }
    }
        System.out.println(format("Quack Quack"));
        scanner.close();
    }
}
