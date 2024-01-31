package seedu.banter;

import seedu.banter.tasks.Deadline;
import seedu.banter.tasks.Event;
import seedu.banter.tasks.Task;
import seedu.banter.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * Represents the storage of the task list.
 */
public class Storage {
    private static final String TASK_LIST_FILE_PATH = "data" + File.separator + "tasklist.txt";
    private static final DateTimeFormatter DATE_TIME_STORAGE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final String EVENT_REPRESENTATION = "E";
    private static final String DEADLINE_REPRESENTATION = "D";
    private static final String TODO_REPRESENTATION = "T";

    
    /**
     * Loads the task list from the file.
     * @return Task list loaded from the file.
     */
    public TaskList loadTaskList() {
        try {
            File file = new File(TASK_LIST_FILE_PATH);
            Scanner sc = new Scanner(file);
            TaskList taskList = new TaskList();
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] tokens = task.split(" \\| ");
                String taskType = tokens[0];
                String status = tokens[1];
                boolean isDone = status.equals(Task.IS_DONE);
                String description = tokens[2];
                switch (taskType) {
                    case TODO_REPRESENTATION:
                    taskList.loadTodo(description, isDone);
                    break;
                    case DEADLINE_REPRESENTATION:
                    LocalDateTime by = loadDateTime(tokens[3]);
                    taskList.loadDeadline(description, isDone, by);
                    break;
                    case EVENT_REPRESENTATION:
                    LocalDateTime from = loadDateTime(tokens[3]);
                    LocalDateTime to = loadDateTime(tokens[4]);
                    taskList.loadEvent(description, isDone, from, to);
                    break;
                default:
                    System.out.println("Invalid task type: " + taskType);
                    break;
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            handleFileNotFound();
            return new TaskList();
        }
    }
    
    private void handleFileNotFound() {
        try {
            File file = new File(TASK_LIST_FILE_PATH);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create file: " + e.getMessage());
        }
    }
    
    /**
     * Saves the task list to the file.
     * @param taskList Task list to be saved.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(TASK_LIST_FILE_PATH);
            fw.write(toStorageFormat(taskList));
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save task list: " + e.getMessage());
        }
    }
    
    private String toStorageFormat(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(toStorageFormat(task) + "\n");
        }
        return sb.toString();
    }
    
    private String toStorageFormat(Task task) {
        String statusDescription = task.getStatus() + " | " + task.getDescription();
        if (task instanceof Deadline) {
            return DEADLINE_REPRESENTATION + " | " + statusDescription + " | " + toStorageFormat(((Deadline) task).getDueDate());
        } else if (task instanceof Event) {
            return EVENT_REPRESENTATION + statusDescription + " | " + toStorageFormat(((Event) task).getStart()) + " | " + 
                    toStorageFormat(((Event) task).getEnd());
        } else {
            return TODO_REPRESENTATION + statusDescription;
        }
    }
    
    private String toStorageFormat(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_STORAGE_FORMAT);
    }
    
    private LocalDateTime loadDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_STORAGE_FORMAT);
    }
}
