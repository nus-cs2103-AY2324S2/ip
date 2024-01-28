import java.util.*;
public class Lia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Lia :)");
        System.out.println("What can I do for you?");

        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("exit")) {
                    break;
                } else if (input.equals("list")) {
                    if (tasks.isEmpty()) {
                        throw new LiaException("The task list is currently empty.");
                    }

                    for (int j = 1; j <= tasks.size(); j++) {
                        Task task = tasks.get(j - 1);
                        if (task instanceof Todo) {
                            System.out.println(j + ". [" + task.getTaskIcon() + "]" +
                                    "[" + task.getStatusIcon() + "] " + task.description);
                        } else if (task instanceof Deadline) {
                            System.out.println(j + ". [" + task.getTaskIcon() + "]" +
                                    "[" + task.getStatusIcon() + "] " + task.description +
                                    " (by: " + ((Deadline) task).date + ")");
                        } else if (task instanceof Event) {
                            System.out.println(j + ". [" + task.getTaskIcon() + "]" +
                                    "[" + task.getStatusIcon() + "] " + task.description +
                                    " (from: " + ((Event) task).start + " to: " + ((Event) task).end + ")");
                        }
                    }
                } else if (input.startsWith("mark")) {
                    String[] tokens = input.split(" ");
                    int pos = Integer.parseInt(tokens[1]);
                    if (pos <= 0 || pos > tasks.size()) {
                        throw new LiaException("There are only " + tasks.size() + " tasks in the list");
                    }

                    Task task = tasks.get(pos - 1);
                    task.markAsDone();
                    System.out.println("I've marked this task as done:");
                    if (task instanceof Todo) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description);
                    } else if (task instanceof Deadline) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (by: " + ((Deadline) task).date + ")");
                    } else if (task instanceof Event) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (from: " + ((Event) task).start + " to: " + ((Event) task).end + ")");
                    }
                } else if (input.startsWith("unmark")) {
                    String[] tokens = input.split(" ");
                    int pos = Integer.parseInt(tokens[1]);
                    if (pos <= 0 || pos > tasks.size()) {
                        throw new LiaException("There are only " + tasks.size() + " tasks in the list");
                    }

                    Task task = tasks.get(pos - 1);
                    task.markAsNotDone();
                    System.out.println("I've marked this task as not done:");
                    if (task instanceof Todo) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description);
                    } else if (task instanceof Deadline) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (by: " + ((Deadline) task).date + ")");
                    } else if (task instanceof Event) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (from: " + ((Event) task).start + " to: " + ((Event) task).end + ")");
                    }
                } else if (input.startsWith("todo")) {
                    String todo = input.replaceFirst("todo", "").trim();
                    if (todo.isEmpty()) {
                        throw new LiaException("Task description cannot be empty.");
                    }

                    tasks.add(new Todo(todo));
                    System.out.println("I've added this task:");
                    Task todoTask = tasks.get(tasks.size() - 1);
                    System.out.println("[" + todoTask.getTaskIcon() + "]" +
                            "[" + todoTask.getStatusIcon() + "] " + todoTask.description);
                    System.out.println("You have " + tasks.size() + " task(s) in the list.");
                } else if (input.startsWith("deadline")) {
                    String deadline = input.replaceFirst("deadline", "").split("/by")[0].trim();
                    if (deadline.isEmpty()) {
                        throw new LiaException("Task description cannot be empty.");
                    }

                    String date = input.split("/by")[1].trim();
                    tasks.add(new Deadline(deadline, date));
                    System.out.println("I've added this task:");
                    Task deadlineTask = tasks.get(tasks.size() - 1);
                    System.out.println("[" + deadlineTask.getTaskIcon() + "]" +
                            "[" + deadlineTask.getStatusIcon() + "] " + deadlineTask.description +
                            " (by: " + ((Deadline) deadlineTask).date + ")");
                    System.out.println("You have " + tasks.size() + " task(s) in the list.");
                } else if (input.startsWith("event")) {
                    String event = input.replaceFirst("event", "").split("/from")[0].trim();
                    if (event.isEmpty()) {
                        throw new LiaException("Event description cannot be empty.");
                    }

                    String range = input.split("/from")[1].trim();
                    String start = range.split("/to")[0].trim();
                    String end = range.split("/to")[1].trim();
                    tasks.add(new Event(event, start, end));
                    System.out.println("I've added this task:");
                    Task eventTask = tasks.get(tasks.size() - 1);
                    System.out.println("[" + eventTask.getTaskIcon() + "]" +
                            "[" + eventTask.getStatusIcon() + "] " + eventTask.description +
                            " (from: " + ((Event) eventTask).start + " to: " + ((Event) eventTask).end + ")");
                    System.out.println("You have " + tasks.size() + " task(s) in the list.");
                } else if (input.startsWith("delete")) {
                    String[] tokens = input.split(" ");
                    int pos = Integer.parseInt(tokens[1]);
                    if (pos <= 0 || pos > tasks.size()) {
                        throw new LiaException("There are only " + tasks.size() + " tasks in the list.");
                    }

                    Task task = tasks.get(pos - 1);
                    tasks.remove(pos - 1);
                    System.out.println("I've removed this task:");
                    if (task instanceof Todo) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description);
                    } else if (task instanceof Deadline) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (by: " + ((Deadline) task).date + ")");
                    } else if (task instanceof Event) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (from: " + ((Event) task).start + " to: " + ((Event) task).end + ")");
                    }
                    System.out.println("You have " + tasks.size() + " task(s) in the list.");
                } else if (input.equals("help")) {
                    System.out.println("list");
                    System.out.println("- Lists out all your tasks");
                    System.out.println("todo <task description>");
                    System.out.println("- Adds a task");
                    System.out.println("deadline <task description> /by <due by>");
                    System.out.println("- Adds a task with a deadline");
                    System.out.println("event <event description> /from <starts at> to <ends at>");
                    System.out.println("- Adds an event");
                    System.out.println("mark <task number>");
                    System.out.println("- Marks task at specified position as done");
                    System.out.println("unmark <task number>");
                    System.out.println("- Marks task at specified position as not done");
                    System.out.println("delete <task number>");
                    System.out.println(" - Deletes task at specified position");
                    System.out.println("exit");
                    System.out.println("- Ends the conversation");
                } else {
                    throw new LiaException("Invalid command. Type help for a list of valid commands and their usages.");
                }
            } catch (LiaException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }
}