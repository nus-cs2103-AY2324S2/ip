import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        List<Task> tasksList = new ArrayList<Task>();
        System.out.println("DevGPT:\n\tHello! I'm DevGPT\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                while (true) {
                    System.out.println("User:");
                    String input = scanner.nextLine();

                    if (input.contains("bye")) {
                        break;
                    }

                    String command = input.split(" ")[0];

                    if (command.equals("todo")) {
                        if (input.split(" ").length == 1) {
                            throw new DukeException("The description of a todo cannot be empty. \n\t" +
                                "Please use the following format: todo <description>");
                        }
                        String details = input.split(" ", 2)[1];
                        Task task = new Todo(details);
                        tasksList.add(task);
                        System.out.println("DevGPT:\n\t" + " Got it. I've added this task: \n\t" + task.toString());
                        System.out.println("DevGPT:\n\t Now you have " + tasksList.size() + " tasks in the list.");
                    } else if (command.equals("deadline")) {
                        if (input.split(" ").length <= 3) {
                            throw new DukeException("The deadline and date for a task cannot be empty. \n\t" +
                                "Please use the following format: deadline <description> /by <date>");
                        } else if (!input.contains("/by")) {
                            throw new DukeException("Invalid command for deadline. \n\t" +
                                "Please use the following format: deadline <description> /by <date>");
                        }
                        String details = input.split(" ", 2)[1];
                        String description = details.split("/by ")[0].trim();
                        String by = details.split("/by ")[1];
                        Task task = new Deadline(description, by);
                        tasksList.add(task);
                        System.out.println("DevGPT:\n\t" + " Got it. I've added this task: " + task.toString());
                        System.out.println("DevGPT:\n\t Now you have " + tasksList.size() + " tasks in the list.");
                    } else if (command.equals("event")) {
                        if (input.split(" ").length <= 3) {
                            throw new DukeException("The date for an event cannot be empty. \n\t" +
                                "Please use the following format: event <description> /from <date> /to <date>");
                        } else if (!input.contains("/from") || !input.contains("/to")) {
                            throw new DukeException("Invalid command for event. \n\t" +
                                "Please use the following format: event <description> /from <date> /to <date>");
                        }
                        String details = input.split(" ", 2)[1];
                        String description = details.split("/from")[0].trim();
                        String from = details.split("/from")[1].split("/to")[0];
                        String to = details.split("/to")[1];

                        Task task = new Event(description, from, to);
                        tasksList.add(task);
                        System.out.println("DevGPT:\n\t" + " Got it. I've added this task: " + task.toString());
                        System.out.println("DevGPT:\n\t Now you have " + tasksList.size() + " tasks in the list.");
                    } else if (command.equals("list")) {
                        System.out.println("DevGPT:\n\t Here are the tasks in your list:");
                        for (int i = 0; i < tasksList.size(); i++) {
                            Task task = tasksList.get(i);
                            System.out.println("\t\t" + (i + 1) + ". " + task.toString());
                        }
                    } else if (command.equals("unmark")) {
                        String[] split = input.split(" ");
                        if (split.length == 1) {
                            throw new DukeException("The index of a task cannot be empty. \n\t" +
                                "Please use the following format: unmark <index>");
                        } else if (parseInt(split[1]) > tasksList.size() || parseInt(split[1]) < 1) {
                            throw new DukeException("No such task exists.");
                        }
                        int index = parseInt(split[1]);
                        Task task = tasksList.get(index - 1);
                        task.markAsUndone();
                        System.out.println("DevGPT:\n\t Got it! I've marked this task as not done yet:");
                        System.out.println("\t" + task.toString());
                    } else if (command.equals("mark")) {
                        String[] split = input.split(" ");
                        if (split.length == 1) {
                            throw new DukeException("The index of a task cannot be empty. \n\t" +
                                "Please use the following format: mark <index>");
                        } else if (parseInt(split[1]) > tasksList.size() || parseInt(split[1]) < 1) {
                            throw new DukeException("No such task exists.");
                        }
                        int index = Integer.parseInt(split[1]);
                        Task task = tasksList.get(index - 1);
                        task.markAsDone();
                        System.out.println("DevGPT:\n\t Nice! I've marked this task as done:");
                        System.out.println("\t" + task.toString());
                    } else {
                        throw new DukeException(
                            "Your message is not understood. Please use the following:\n\t1. todo <description>" +
                                "\n\t2. deadline <description> /by <date>\n\t3. event <description> /from <date> /to <date>" +
                                "\n\t4. list\n\t5. mark <index>\n\t6. unmark <index>\n\t7. bye");
                    }

                }
                    scanner.close();
                    System.out.println("DevGPT:\n\tBye. Hope to see you again soon!");
            } catch (NumberFormatException e) {
                System.out.println("DevGPT:\n\t" + "Input error: Please enter a number to mark / unmark the task");
            }
            catch (DukeException e) {
                System.out.println("DevGPT:\n\t" + e.getMessage());
            }
        }
    }
}
