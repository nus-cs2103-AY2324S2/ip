package toothless.storage;

import toothless.exception.ToothlessException;
import toothless.task.Deadline;
import toothless.task.Event;
import toothless.task.Task;
import toothless.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    private static final DateTimeFormatter DATETIME_PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public Storage(String filePath) throws ToothlessException {
        this.filePath = filePath;
        this.file = new File(filePath);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new ToothlessException(e.getMessage());
        }
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    
    public ArrayList<Task> loadStorage() throws ToothlessException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner tasklistScanner = new Scanner(this.file);
            while (tasklistScanner.hasNext()) {
                try {
                    String line = tasklistScanner.nextLine();
                    String[] taskArgs = line.split(" \\| ");
                    String taskType = taskArgs[0];
                    boolean isDone = taskArgs[1].equals("0") ? false : true;
                    String taskDescription = taskArgs[2];
                    
                    if (taskType.equals("T")) {
                        ToDo newToDo = new ToDo(taskDescription, isDone);
                        tasks.add(newToDo);
                    } else if (taskType.equals("D")) {
                        LocalDateTime deadlineBy = LocalDateTime.parse(taskArgs[3], DATETIME_PARSE_FORMATTER);
                        Deadline newDeadline = new Deadline(taskDescription, isDone, deadlineBy);
                        tasks.add(newDeadline);
                    } else if (taskArgs[0].equals("E")) {
                        LocalDateTime eventFrom = LocalDateTime.parse(taskArgs[3], DATETIME_PARSE_FORMATTER);
                        LocalDateTime eventTo = LocalDateTime.parse(taskArgs[4], DATETIME_PARSE_FORMATTER);
                        Event newEvent = new Event(taskDescription, isDone, eventFrom, eventTo);
                        tasks.add(newEvent);
                    } else {
                        throw new ToothlessException("Sorry, tasklist.txt seems to contain a corrupted task type.");
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new ToothlessException("Sorry, tasks seem to have missing arguments.");
                } catch (DateTimeParseException e) {
                    throw new ToothlessException("Sorry, task seems to have corrupted datetime. " +
                            "The format should be yyyy-mm-dd hh:mm");
                }
            }
            tasklistScanner.close();
        } catch (IOException e) {
            throw new ToothlessException(e.getMessage());
        } 
        return tasks;
    }
    
    public void saveToStorage(ArrayList<Task> tasks) throws ToothlessException {
        try {
            FileWriter fw = new FileWriter(this.getFilePath(), false);
            for (Task t : tasks) {
                fw.write(t.toStorageString() + System.lineSeparator());
            }
            fw.close();
            System.out.println("\tSuccessfully saved task data to tasklist.txt.");
        } catch (IOException e) {
            throw new ToothlessException("Sorry, saving to tasklist.txt failed.");
        }
    }

}
