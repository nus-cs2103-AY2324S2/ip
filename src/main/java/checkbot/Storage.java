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
    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath The file path to the file where the tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the txt file from the directory and returns it as a TodoList.
     * Invalid entries are ignored and not added to the list, and returns
     * an empty TodoList if the txt file does not exist.
     *
     * @return A TodoList based on the txt file in the directory.
     */
    public TodoList loadTasks() {
        final String TASK_CODE = "T";
        final String DEADLINE_CODE = "D";
        final String EVENT_CODE = "E";

        File file = new File(filePath);
        TodoList todoList = new TodoList();
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
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
                    assert type.equals((EVENT_CODE)) : "Task should start with an 'E'";
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
        } finally {
            if (scanner != null) {
                scanner.close();
            }
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
