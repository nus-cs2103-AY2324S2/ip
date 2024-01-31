import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checkbot {
    public static final String INDENTATION = "  ";
    private static final String SEPARATOR = INDENTATION + "____________________________________________________________\n";

    public static final String TASK_FILE_DIR = "./tasks.txt";

    private final TodoList todoList;
    private final Storage storage;

    public Checkbot(String filePath) {
        this.storage = new Storage(filePath);
        this.todoList = this.storage.loadTasks();
    }


    /**
     * Creates a Task object and returns it based on the given String input.
     *
     * @param input The task in String format.
     * @return A concrete implementation of the Task object.
     * @throws CheckbotException If input is not formatted correctly.
     */
    private static Task createTask(String input) throws CheckbotException {
        Task task;
        String[] splitString = input.split("todo|deadline|event|\\/(by|from|to)");
        String taskName = splitString.length > 1 ? splitString[1].strip() : "";
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (input.startsWith("todo")) {
            task = new Todo(taskName);
        } else if (input.startsWith("deadline")) {
            Pattern pattern = Pattern.compile("deadline (.*) /by (.*)");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String byWhen = matcher.group(2).strip();
                if (byWhen.isEmpty()) {
                    throw new MissingDeadlineException();
                }
                task = new Deadline(taskName, byWhen);
            } else {
                throw new MissingDeadlineException();
            }
        } else {
            Pattern pattern = Pattern.compile("event (.*) /(from|to)(.*) /(from|to)(.*)");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String firstLabel = matcher.group(2);
                String firstValue = matcher.group(3).strip();
                String secondValue = matcher.group(5).strip();

                String from, to;
                if (firstLabel.equals("from")) {
                    from = firstValue;
                    to = secondValue;
                } else {
                    from = secondValue;
                    to = firstValue;
                }

                if (from.isEmpty()) {
                    throw new MissingFromException();
                }
                if (to.isEmpty()) {
                    throw new MissingToException();
                }

                task = new Event(taskName, from, to);
            } else {
                if (input.contains(" /from ")) {
                    throw new MissingToException();
                }
                throw new MissingFromException();
            }
        }
        return task;
    }

    public static void main(String[] args) {
        new Checkbot(TASK_FILE_DIR).run();
    }

    public void run() {
        String txt = SEPARATOR
                + INDENTATION + "Hello, I'm Checkbot, your personal assistant.\n"
                + INDENTATION + "What tasks do you have to do?\n"
                + SEPARATOR;
        System.out.println(txt);

        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            String toPrint = "";
            try {
                if (input.equals("bye")) {
                    toPrint = "Goodbye!";
                } else if (input.equals("list")) {
                    toPrint = "Here is your todo list:\n" + INDENTATION + todoList;
                } else if (input.startsWith("mark ")) {
                    try {
                        int i = Integer.parseInt(input.split("mark ")[1]) - 1;
                        todoList.markTask(i);
                        toPrint = "Good job! I have marked this task as completed:\n"
                                + INDENTATION + todoList.getTask(i);
                        storage.saveTasks(todoList);
                    } catch (NumberFormatException e) {
                        throw new InvalidIndexException(input.split("mark ")[1]);
                    }
                } else if (input.startsWith("unmark ")) {
                    try {
                        int i = Integer.parseInt(input.split("unmark ")[1]) - 1;
                        todoList.unmarkTask(i);
                        toPrint = "Alright, I have marked this task as incomplete:\n"
                                + INDENTATION + todoList.getTask(i);
                        storage.saveTasks(todoList);
                    } catch (NumberFormatException e) {
                        throw new InvalidIndexException(input.split("unmark ")[1]);
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int i = Integer.parseInt(input.split("delete ")[1]) - 1;
                        Task deletedTask = todoList.deleteTask(i);
                        toPrint = "Alright, I deleted this task:\n"
                                + INDENTATION + INDENTATION + deletedTask + "\n"
                                + INDENTATION + "You have now " + todoList.getLength() + " task"
                                + (todoList.getLength() > 1 ? "s" : "") + " in the list.";
                        storage.saveTasks(todoList);
                    } catch (NumberFormatException e) {
                        throw new InvalidIndexException(input.split("mark ")[1]);
                    }
                } else if (input.startsWith("todo")
                        || input.startsWith("deadline")
                        || input.startsWith("event")) {
                    Task task = createTask(input);
                    todoList.addTask(task);
                    toPrint = "I have added this task to the list:\n"
                            + INDENTATION + INDENTATION + task + "\n"
                            + INDENTATION + "You have now " + todoList.getLength() + " task" + (todoList.getLength() > 1 ? "s" : "") + " in the list.";
                    storage.saveTasks(todoList);
                } else {
                    throw new InvalidCommandException(input);
                }
            } catch (SaveFileException e) {
                toPrint += "\n " + INDENTATION
                        + "However, " + e.getMessage();
            } catch (CheckbotException e) {
                toPrint = e.getMessage();
            }
            txt = SEPARATOR
                    + INDENTATION + toPrint + "\n"
                    + SEPARATOR;
            System.out.println(txt);
        }
    }
}
