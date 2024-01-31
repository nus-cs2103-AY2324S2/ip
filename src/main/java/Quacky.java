import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
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
        tasks.loadFromFile("./data/data.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.println(format("Quack! how u doing, Im Quacky. How can I help you?"));
        String command = scanner.nextLine();
        String[] keywords = command.split(" ", 2);
        while (!command.equals("bye")) {
            try {
                if (keywords[0].equals("list")) {
                    System.out.println(format(tasks.toString()));
                } else if (keywords[0].equalsIgnoreCase("mark")) {
                    int taskNumber = Integer.parseInt(keywords[1]) - 1;
                    tasks.markCompleteTask(taskNumber);
                    String message = "Quack! I marked this task as done \n\t" + tasks.printTask(taskNumber);
                    System.out.println(format(message));
                } else if (keywords[0].equalsIgnoreCase("unmark")) {
                    int taskNumber = Integer.parseInt(keywords[1]) - 1;
                    tasks.unmarkCompleteTask(taskNumber);
                    String message = "Quack! I marked this task as not done\n\t" + tasks.printTask(taskNumber);
                    System.out.println(format(message));
                } else if (keywords[0].equalsIgnoreCase("delete")) {
                    int taskNumber = Integer.parseInt(keywords[1]) - 1;
                    tasks.deleteTask(taskNumber);
                    String message = "Quack! I removed this task:  \n\t" + tasks.printTask(taskNumber)
                                            + "\nNow you have " + tasks.taskNumber() + " tasks in the list.";
                    System.out.println(format(message));
                }
                else if (keywords[0].equalsIgnoreCase("todo")) {
                    if (command.trim().equals("todo")) {
                        throw new QuackyException("Quack? (Please provide a description for your task)");
                    }
                    Task newTask = new Todo(keywords[1]);
                    tasks.addTask(newTask);
                    System.out.println(format("Quack! I've added this task:\n\t" + newTask +
                            "\nNow you have " + tasks.taskNumber() + " tasks in the list."));
                } else if (keywords[0].equalsIgnoreCase("deadline")) {
                    if (command.trim().equals("deadline")) {
                        throw new QuackyException("Quack? (Please provide a description for your task)");
                    }
                    String[] parts = command.substring(9).split(" /by ");
                    Task newTask = new Deadline(parts[0], LocalDate.parse(parts[1]));
                    tasks.addTask(newTask);
                    System.out.println(format("Got it. I've added this task:\n\t" + newTask +
                            "\nNow you have " + tasks.taskNumber() + " tasks in the list."));
                } else if (keywords[0].equalsIgnoreCase("event")) {
                    if (command.trim().equals("event")) {
                        throw new QuackyException("Quack? (Please provide a description for your task)");
                    }
                    String[] parts = keywords[1].split(" /from | /to ");
                    Task newTask = new Event(parts[0], LocalDate.parse(parts[1]), LocalDate.parse(parts[2]));
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
                keywords = command.split(" ", 2);
            }
    }
        try {
            tasks.writeToFile("./data/data.txt");
        } catch (IOException e) {
            System.out.println(format(e.getMessage()));
        } finally {
            System.out.println(format("Quack Quack"));
            scanner.close();
        }
    }
}
