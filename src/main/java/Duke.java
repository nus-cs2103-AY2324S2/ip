import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Cookie");
        System.out.println("What can I do for you?\n");

        Task[] tasks = new Task[100];
        int counter = 0;

        while (true) {
            try {
                String input = scanner.nextLine();
                System.out.println(input);
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                if (input.equals("list")) {
                    //print added items
                    for (int i = 1; i <= counter; i++) {
                        Task task = tasks[i - 1];
                        String taskStatus = task.getStatusIcon();
                        String taskDesc = task.toString();
                        System.out.println(i + "." + taskDesc);
                    }
                    continue;
                }
                if (input.startsWith("mark ")) {
                    String taskNum = input.substring(5);
                    int taskNumber = Integer.valueOf(taskNum);

                    if (taskNumber >= 1 && taskNumber <= counter) {
                        Task task = tasks[taskNumber - 1];
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task.toString());
                    } else {
                        System.out.println("Invalid task number. Please provide a valid task number.");
                    }
                    continue;
                }

                if (input.startsWith("delete ")) {
                    String taskNum = input.substring(7);
                    int taskNumber = Integer.valueOf(taskNum);

                    if (taskNumber >= 1 && taskNumber <= counter) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(tasks[taskNumber - 1].toString());

                        for (int i = taskNumber - 1; i < counter - 1; i++) {
                            tasks[i] = tasks[i + 1];
                        }
                        tasks[counter - 1] = null;
                        counter--;

                        System.out.println("Now you have " + counter + " tasks in the list.");
                    } else {
                        throw new DukeException("UH OH! Invalid task number, please provide a valid task number!");
                    }
                    continue;
                }

                if (input.startsWith("todo")) {
                    if (input.length() <= 5) {
                        throw new DukeException("UH OH! Description for todo cannot be empty!");
                    }
                    String taskDesc = input.substring(5);
                    Task t = new Todo(taskDesc);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t.toString());

                    tasks[counter] = t;
                    counter++;

                    System.out.println("Now you have " + counter + " tasks in the list.");

                } else if (input.startsWith("deadline")) {
                    if (input.length() <= 9) {
                        throw new DukeException("UH OH! Description for deadline cannot be empty!");
                    }
                    String toSplit = input.substring(9);
                    String[] parts = toSplit.split("/by");

                    String taskDesc = parts[0].trim();
                    String deadline = parts[1].trim();

                    if (taskDesc.isEmpty() || deadline.isEmpty()) {
                        throw new DukeException("UH OH! Description and deadline cannot be empty!");
                    }

                    Task t = new Deadline(taskDesc, deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t.toString());

                    tasks[counter] = t;
                    counter++;

                    System.out.println("Now you have " + counter + " tasks in the list.");
                } else if (input.startsWith("event")) {
                    if (input.length() <= 6) {
                        throw new DukeException("UH OH! Description for event cannot be empty!");
                    }

                    String toSplit = input.substring(6);
                    String[] parts = toSplit.split("/from");

                    String taskDesc = parts[0].trim();
                    String[] timeParts = parts[1].split("/to");

                    if (timeParts.length != 2) {
                        throw new DukeException("UH OH! Invalid format for event task!");
                    }

                    String from = timeParts[0].trim();
                    String to = timeParts[1].trim();

                    if (taskDesc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new DukeException("UH OH! Description/start time/end time cannot be empty!");
                    }

                    Task t = new Event(taskDesc, from, to);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t.toString());

                    tasks[counter] = t;
                    counter++;

                    System.out.println("Now you have " + counter + " tasks in the list.");
                } else {
                    throw new DukeException("UH OH! I don't understand what you mean.. sorry D:");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}


