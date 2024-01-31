import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checkbot {
    public static final String INDENTATION = "  ";
    private static final String SEPARATOR = INDENTATION + "____________________________________________________________\n";

    public static final String TASK_FILE_DIR = "./tasks.txt";


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

    /**
     * Reads the txt file from the directory and returns it as a TodoList.
     * Invalid entries are ignored and not added to the list, and returns
     * an empty TodoList if the txt file does not exist.
     *
     * @return A TodoList based on the txt file in the directory.
     */
    private static TodoList loadTasks() {
        final String TASK_CODE = "T";
        final String DEADLINE_CODE = "D";
        final String EVENT_CODE = "E";

        File file = new File(TASK_FILE_DIR);
        TodoList todoList = new TodoList();

        try {
            Scanner scanner = new Scanner(file);
            Pattern pattern = Pattern.compile("([TDE]) \\| ([01]) \\| (.*)");

            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                Matcher matcher = pattern.matcher(text);

                if (!matcher.find()) {
                    continue;
                }

                String type = matcher.group(1);
                boolean done = matcher.group(2).equals("1");
                String taskDetails = matcher.group(3);
                Task t;

                if (type.equals(TASK_CODE)) {
                    t = new Todo(taskDetails);
                } else if (type.equals(DEADLINE_CODE)) {
                    Matcher deadlineMatcher = Pattern.compile("(.*) \\| (.*)").matcher(taskDetails);
                    if (!deadlineMatcher.find()) {
                        continue;
                    }
                    String name = deadlineMatcher.group(1);
                    String byWhen = deadlineMatcher.group(2);
                    t = new Deadline(name, byWhen);
                } else {
                    Matcher eventMatcher = Pattern.compile("(.*) \\| (.*) \\| (.*)").matcher(taskDetails);
                    if (!eventMatcher.find()) {
                        continue;
                    }
                    String name = eventMatcher.group(1);
                    String from = eventMatcher.group(2);
                    String to = eventMatcher.group(3);
                    t = new Event(name, from, to);
                }

                if (done) {
                    t.mark();
                }

                todoList.addTask(t);
            }
        } catch (FileNotFoundException e) {
            return todoList;
        }
        return todoList;
    }

    public static void main(String[] args) {
        TodoList todoList = loadTasks();

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
                        todoList.saveTo(TASK_FILE_DIR);
                    } catch (NumberFormatException e) {
                        throw new InvalidIndexException(input.split("mark ")[1]);
                    }
                } else if (input.startsWith("unmark ")) {
                    try {
                        int i = Integer.parseInt(input.split("unmark ")[1]) - 1;
                        todoList.unmarkTask(i);
                        toPrint = "Alright, I have marked this task as incomplete:\n"
                                + INDENTATION + todoList.getTask(i);
                        todoList.saveTo(TASK_FILE_DIR);
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
                        todoList.saveTo(TASK_FILE_DIR);
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
                    todoList.saveTo(TASK_FILE_DIR);
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
