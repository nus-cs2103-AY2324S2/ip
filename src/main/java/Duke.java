import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        Task[] tasks = new Task[100];
        int counter = 0;

        try {
            counter = getFileContents(tasks, counter);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Cookie");
        System.out.println("What can I do for you?\n");

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

        writeToFile(tasks);
    }

    private static int getFileContents(Task[] tasks, int counter) throws FileNotFoundException{
        try {
            File f = new File("./tasksLog.txt");
            if (!f.exists()) {
                f.createNewFile();
                return counter;
            }

            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                Task task = createTaskFromString(line);
                tasks[counter] = task;
                counter++;
            }
            return counter;

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return counter;
        }
    }

    private static Task createTaskFromString(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; // Invalid format
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                String by = parts[3];
                Deadline deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.markAsDone();
                }
            case "E":
                String fromTo = parts[3];
                String[] fromToParts = fromTo.split(" ");
                String from = fromToParts[0];
                String to = fromToParts[1];
                Event event = new Event(description, from, to);
                if (isDone) {
                    event.markAsDone();
                }
            default:
                return null; // Unknown type
        }
    }

    private static void writeToFile(Task[] tasks) {
        try {
            FileWriter fw = new FileWriter("./tasksLog.txt");
            for (Task task : tasks) {
                if (task == null) {
                    break;
                }
                fw.write(task.toFileString() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}


