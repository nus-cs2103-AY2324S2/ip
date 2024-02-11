package checkbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import checkbot.exception.SaveFileException;
import checkbot.task.Deadline;
import checkbot.task.Event;
import checkbot.task.Task;
import checkbot.task.Todo;
import checkbot.task.TodoList;

/**
 * Handles the loading and saving of tasks to a txt file.
 */
public class Storage {
    private static final String TASK_CODE = "T";
    private static final String DEADLINE_CODE = "D";
    private static final String EVENT_CODE = "E";
    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath The file path to the file where the tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static Task parseTaskFromStorage(Matcher matcher) {
        if (!matcher.find()) {
            return null;
        }

        String type = matcher.group(1);
        boolean done = matcher.group(2).equals("1");
        String taskDetails = matcher.group(3);
        Task task;

        if (type.equals(TASK_CODE)) {
            task = new Todo(taskDetails);
        } else if (type.equals(DEADLINE_CODE)) {
            Matcher deadlineMatcher = Pattern.compile("(.*) \\| (.*)").matcher(taskDetails);
            if (!deadlineMatcher.find()) {
                return null;
            }
            String name = deadlineMatcher.group(1);
            String byWhen = deadlineMatcher.group(2);
            task = new Deadline(name, byWhen);
        } else {
            assert type.equals((EVENT_CODE)) : "Task should start with an 'E'";
            Matcher eventMatcher = Pattern.compile("(.*) \\| (.*) \\| (.*)").matcher(taskDetails);
            if (!eventMatcher.find()) {
                return null;
            }
            String name = eventMatcher.group(1);
            String from = eventMatcher.group(2);
            String to = eventMatcher.group(3);
            task = new Event(name, from, to);
        }

        if (done) {
            task.mark();
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
    public TodoList loadTasks() {

        File file = new File(filePath);
        TodoList todoList = new TodoList();

        try (Scanner scanner = new Scanner(file)) {
            Pattern pattern = Pattern.compile(
                    String.format("([%s%s%s]) \\| ([01]) \\| (.*)", TASK_CODE, DEADLINE_CODE, EVENT_CODE));

            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                Matcher matcher = pattern.matcher(text);

                Task task = parseTaskFromStorage(matcher);
                if (task == null) {
                    continue;
                }

                todoList.addTask(task);
            }
        } catch (FileNotFoundException e) {
            return todoList;
        }
        return todoList;
    }

    /**
     * Saves the tasks of the given TodoList to the txt file specified by filePath.
     *
     * @param todoList The TodoList to save to the txt file.
     * @throws SaveFileException If there is an error saving the file.
     */
    public void saveTasks(TodoList todoList) throws SaveFileException {
        try {
            Writer writer = new FileWriter(filePath);
            writer.write(todoList.formatForFile());
            writer.close();
        } catch (IOException e) {
            throw new SaveFileException();
        }
    }
}
