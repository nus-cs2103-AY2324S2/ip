package seedu.banter;

import seedu.banter.tasks.Deadline;
import seedu.banter.tasks.Event;
import seedu.banter.tasks.Task;
import seedu.banter.tasks.TaskList;
import seedu.banter.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    private static final String TASK_LIST_FILE_PATH = "data" + File.separator + "tasklist.txt";
    private static final DateTimeFormatter DATE_TIME_STORAGE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
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
                case Todo.TODO_ICON:
                    taskList.addTodo(description, isDone);
                    break;
                case Deadline.DEADLINE_ICON:
                    LocalDateTime by = loadDateTime(tokens[3]);
                    taskList.addDeadline(description, isDone, by);
                    break;
                case Event.EVENT_ICON:
                    LocalDateTime from = loadDateTime(tokens[3]);
                    LocalDateTime to = loadDateTime(tokens[4]);
                    taskList.addEvent(description, isDone, from, to);
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
        String typeStatusDescription = task.getTaskType() + " | " + task.getStatus() + " | " + task.getDescription();
        if (task instanceof Deadline) {
            return typeStatusDescription + " | " + toStorageFormat(((Deadline) task).getDueDate());
        } else if (task instanceof Event) {
            return typeStatusDescription + " | " + toStorageFormat(((Event) task).getStart()) + " | " + 
                    toStorageFormat(((Event) task).getEnd());
        } else {
            return typeStatusDescription;
        }
    }
    
    private String toStorageFormat(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_STORAGE_FORMAT);
    }
    
    private LocalDateTime loadDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_STORAGE_FORMAT);
    }
}
