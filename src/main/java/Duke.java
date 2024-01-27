import static java.lang.Integer.parseInt;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        List<Task> tasksList = new ArrayList<Task>();

        try {
            File file = new File("data/duke.txt");
            if (!file.exists()) {
                boolean isCreated = false;
                isCreated = file.getParentFile().mkdirs();
                isCreated = file.createNewFile();

                if (!isCreated) {
                    throw new DukeException("Error: File could not be created.");
                }
            }
            else {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    Task task = getTask(line);
                    tasksList.add(task);
                }
                s.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File could not be found.");
        } catch (IOException e) {
            System.out.println("Error: File could not be created.");
        }


        System.out.println("DevGPT:\n\tHello! I'm DevGPT\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                while (true) {
                    System.out.println("User:");
                    String input = scanner.nextLine();

                    if (input.contains("bye")) {
                        isExit = true;
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
                        System.out.println("DevGPT:\n\t" + " Got it. I've added this task: \n\t\t" + task.toString());
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
                        String by = details.split("/by ")[1].trim();
                        Task task = new Deadline(description, getInputDateFormat(by));
                        tasksList.add(task);
                        System.out.println("DevGPT:\n\t" + " Got it. I've added this task: \n\t\t" + task.toString());
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
                        String from = details.split("/from")[1].split("/to")[0].trim();
                        String to = details.split("/to")[1].trim();

                        Task task = new Event(description, getInputDateFormat(from), getInputDateFormat(to));
                        tasksList.add(task);
                        System.out.println("DevGPT:\n\t" + " Got it. I've added this task: \n\t\t" + task.toString());
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
                        System.out.println("\t\t" + task.toString());
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
                        System.out.println("\t\t" + task.toString());
                    } else if (command.equals("delete")) {
                        String[] split = input.split(" ");
                        if (split.length == 1) {
                            throw new DukeException("The index of a task cannot be empty. \n\t" +
                                "Please use the following format: delete <index>");
                        } else if (parseInt(split[1]) > tasksList.size() || parseInt(split[1]) < 1) {
                            throw new DukeException("No such task exists.");
                        }
                        int index = Integer.parseInt(split[1]);
                        Task task = tasksList.remove(index - 1);
                        System.out.println("DevGPT:\n\t Poof! I've removed this task:");
                        System.out.println("\t\t" + task.toString());
                        System.out.println("DevGPT:\n\t Now you have " + tasksList.size() + " tasks in the list.");
                    } else {
                        throw new DukeException(
                            "Your message is not understood. Please use the following:\n\t1. todo <description>" +
                                "\n\t2. deadline <description> /by <dd-mm-yyyy>\n\t3. event <description> /from <dd-mm-yyyy> /to <dd-mm-yyyy>" +
                                "\n\t4. list\n\t5. mark <index>\n\t6. unmark <index>\n\t7. delete <index>\n\t8. bye");
                    }

                }

                scanner.close();
                System.out.println("DevGPT:\n\tBye. Hope to see you again soon!");
                writeToFile(tasksList);

            } catch (NumberFormatException e) {
                System.out.println("DevGPT:\n\t" + "Input error: Please enter a valid number to modify task");
            } catch (DukeException e) {
                System.out.println("DevGPT:\n\t" + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("DevGPT:\n\t" + "Input error: Please enter a valid date in the format dd-mm-yyyy");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static LocalDate getInputDateFormat(String s) throws DateTimeParseException {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern("d-M-yyyy"));
    }

    private static String getLocalDateOutputFormat(LocalDate date) throws DateTimeParseException {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    private static Task getTask(String line) throws DukeException {
        String[] split = line.split(" \\| ");

        // common fields
        String taskType = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];

        Task task = null;
        // class specific fields
        if (taskType.equals("T")) {
            task = new Todo(description);
        } else if (taskType.equals("D")) {
            String by = split[3];
            task = new Deadline(description, getInputDateFormat(by));
        } else if (taskType.equals("E")) {
            String from = split[3];
            String to = split[4];
            task = new Event(description, getInputDateFormat(from), getInputDateFormat(to));
        }

        if (task == null) {
            throw new DukeException("Invalid task type");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    private static void writeToFile(List<Task> tasksList) throws IOException {
        FileWriter fileWriter = new FileWriter("data/duke.txt");
        for (Task task : tasksList) {
            if (task instanceof Todo) {
                fileWriter.write(
                    task.getTaskType() + " | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + "\n");
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                fileWriter.write(
                    deadline.getTaskType() + " | " + (deadline.isDone ? "1" : "0") + " | " + deadline.getDescription() +
                        " | " + getLocalDateOutputFormat(deadline.getBy()) + "\n");
            } else if (task instanceof Event) {
                Event event = (Event) task;
                fileWriter.write(
                    task.getTaskType() + " | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " +
                        getLocalDateOutputFormat(event.getFrom()) + " | " + getLocalDateOutputFormat(event.getTo()) + "\n");
            }
        }
        fileWriter.close();
    }


}