package storage;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

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
    private File f;

    private static final DateTimeFormatter DATETIME_PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        this.f = new File(filePath);
        try {
            if (!f.exists()) {
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    
    public ArrayList<Task> loadStorage(ArrayList<Task> taskArrayList) throws DukeException {
        try {
            Scanner tasklistScanner = new Scanner(this.f);
            while (tasklistScanner.hasNext()) {
                try {
                    String line = tasklistScanner.nextLine();
                    String[] taskArgs = line.split(" \\| ");
                    String taskType = taskArgs[0];
                    boolean isDone = taskArgs[1].equals("0") ? false : true;
                    String taskDescription = taskArgs[2];
                    
                    if (taskType.equals("T")) {
                        ToDo newToDo = new ToDo(taskDescription, isDone);
                        taskArrayList.add(newToDo);
                    } else if (taskType.equals("D")) {
                        LocalDateTime deadlineBy = LocalDateTime.parse(taskArgs[3], DATETIME_PARSE_FORMATTER);
                        Deadline newDeadline = new Deadline(taskDescription, isDone, deadlineBy);
                        taskArrayList.add(newDeadline);
                    } else if (taskArgs[0].equals("E")) {
                        LocalDateTime eventFrom = LocalDateTime.parse(taskArgs[3], DATETIME_PARSE_FORMATTER);
                        LocalDateTime eventTo = LocalDateTime.parse(taskArgs[4], DATETIME_PARSE_FORMATTER);
                        Event newEvent = new Event(taskDescription, isDone, eventFrom, eventTo);
                        taskArrayList.add(newEvent);
                    } else {
                        throw new DukeException("Sorry, tasklist.txt seems to contain a corrupted task type.");
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Sorry, tasks seem to have missing arguments.");
                } catch (DateTimeParseException e) {
                    throw new DukeException("Sorry, task seems to have corrupted datetime. " +
                            "The format should be yyyy-mm-dd hh:mm");
                }
            }
            tasklistScanner.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } 
        return taskArrayList;
    }
    
    public void saveToStorage(ArrayList<Task> taskArrayList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.getFilePath());
            for (Task t : taskArrayList) {
                fw.write(t.toStorageString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Sorry, saving to tasklist.txt failed.");
        }
    }

}
