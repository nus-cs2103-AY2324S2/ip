package aurora.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import aurora.objects.Deadline;
import aurora.objects.AuroraException;
import aurora.objects.DoAfter;
import aurora.objects.Event;
import aurora.objects.Task;
import aurora.objects.Todo;
import aurora.parser.Parser;

/**
 * Storage is a class that handles file storage and retrieval.
 */
public class Storage {

    /** The filepath to store or load data from. */
    private String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @params filePath. filePath to the file to be retrieved and operated on
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method that returns an ArrayList of tasks after loading the data from the filePath specified.
     *
     * @return ArrayList of tasks in the file.
     * @throws IOException If there is no file located at the path.
     * @throws AuroraException If the file is corrupted.
     */
    public ArrayList<Task> loadTasks() throws IOException, AuroraException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        ArrayList<Task> taskList = new ArrayList<>();
        List<String> fileLines = Files.readAllLines(file.toPath());

        for (String line : fileLines) {
            Task task = processLine(line);
            if (task != null) {
                taskList.add(task);
            }
        }
        return setTasksForDoAfters(taskList);
    }

    /**
     * Helper function for processing each line.
     *
     * @param fileLine File Line to be processed
     * @return processed file line
     */
    private Task processLine(String fileLine) throws AuroraException {
        try {
            return fileLinesToTask(fileLine);
        } catch (AuroraException e) {
            throw new AuroraException("Corrupted line in data file: " + fileLine);
        }
    }

    /**
     * Helper function for populating to DoAfters with events
     *
     * @param taskList unpopulated tasklist
     * @return populated taskList
     */
    public ArrayList<Task> setTasksForDoAfters(ArrayList<Task> taskList) {
        for (Task task : taskList) {
            if (!(task instanceof DoAfter)) {
                continue;
            }

            DoAfter doAfterTask = (DoAfter) task;
            int prevTaskNumber = doAfterTask.getTaskNumber();
            if (prevTaskNumber == -1 || prevTaskNumber == -2) {
                doAfterTask.setTask(null);
                doAfterTask.setHasNoAssociatedTask(true);
                continue;
            }

            Task prevTask = taskList.get(prevTaskNumber);
            doAfterTask.setTask(prevTask);
        }
        return taskList;
    }

    /**
     * Method that writes an ArrayList of tasks to a filePath.
     *
     * @param taskList TaskList to be written to file.
     * @throws IOException If there is a problem in saving the file to the specified directory.
     */
    public void saveTasks(ArrayList<Task> taskList) throws IOException, AuroraException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        assert !taskList.isEmpty() : "Nothing to save";
        for (Task task : taskList) {
            bufferedWriter.write(taskToFileLine(task) + "\n");
        }

        bufferedWriter.close();
    }

    /**
     * Method that parses a line from the loaded file and returns a task object based on the contents of the line.
     *
     * @param fileLine Line from file loaded to be parsed.
     * @return Task parsed from the file line.
     * @throws AuroraException If the particular line is corrupted.
     */
    private Task fileLinesToTask(String fileLine) throws AuroraException {
        String[] components = fileLine.split(" \\| ");
        if (components.length < 3) {
            throw new AuroraException("Invalid line");
        }
        String type = components[0];
        boolean isDone = components[1].trim().equals("1");
        String description = components[2].trim();

        try {
            switch (type) {
            case "T":
                return fileLineToTodo(components, description, isDone);
            case "D":
                return fileLineToDeadline(components, description, isDone);
            case "E":
                return fileLineToEvent(components, description, isDone);
            case "DA":
                return fileLineToDoAfter(components, description, isDone);
            default:
                throw new AuroraException("Unknown task type.");
            }
        } catch (DateTimeParseException e) {
            throw new AuroraException("Invalid date format in task: " + e.getMessage());
        }
    }

    /**
     * Convert a fileLine to a todo
     *
     * @param components Component array of the file line.
     * @param description Description of the todo
     * @param isDone status of the todo
     * @return a todo object
     */
    private Todo fileLineToTodo(String[] components, String description, boolean isDone) throws AuroraException {
        Todo todo = new Todo(description);

        if (isDone) {
            todo.setDone();
        }
        return todo;
    }

    /**
     * Cnverts a fileLine to a deadline
     *
     * @param components Component array of the file line.
     * @param description Description of the deadline
     * @param isDone status of the deadline
     * @return a deadline object
     */
    private Deadline fileLineToDeadline(String[] components, String description, boolean isDone)
            throws AuroraException {
        if (components.length < 4) {
            throw new AuroraException("Invalid format for a deadline.");
        }
        LocalDateTime dateLdt = Parser.parseDateFromStorage(components[3].trim());

        Deadline deadline = new Deadline(description, dateLdt);

        if (isDone) {
            deadline.setDone();
        }
        return deadline;
    }


    /**
     * Converts a fileLine to an event
     *
     * @param components Component array of the file line.
     * @param description Description of the event
     * @param isDone status of the event
     * @return an event object
     */
    private Event fileLineToEvent(String[] components, String description, boolean isDone) throws AuroraException {
        if (components.length < 5) {
            throw new AuroraException("Invalid format for an event.");
        }
        LocalDateTime startLdt = Parser.parseDateFromStorage(components[3].trim());
        LocalDateTime endLdt = Parser.parseDateFromStorage(components[4].trim());

        Event event = new Event(description, startLdt, endLdt);

        if (isDone) {
            event.setDone();
        }
        return event;
    }

    /**
     * Converts a fileLine to a doAfter
     *
     * @param components Component array of the file line.
     * @param description Description of the doAfter
     * @param isDone status of the doAfter
     * @return an doAfter object
     */
    private DoAfter fileLineToDoAfter(String[] components, String description, boolean isDone)
            throws AuroraException {
        if (components.length < 5) {
            throw new AuroraException("Invalid format for a doAfter.");
        }
        DoAfter doAfter;
        int typeOfDoAfter = Integer.parseInt(components[3].trim());
        if (typeOfDoAfter == 1) {
            LocalDateTime timeAfter = Parser.parseDateFromStorage(components[4].trim());
            doAfter = new DoAfter(description, timeAfter);
        } else {
            int taskNumber = Integer.parseInt(components[4].trim());
            doAfter = new DoAfter(description, taskNumber);
        }

        if (isDone) {
            doAfter.setDone();
        }

        return doAfter;
    }

    /**
     *  Converts a task to a fileLine to ba saved into the txt file.
     *
     * @param task Task to be converted into a fileLine
     * @return fileLine in String format
     */
    private String taskToFileLine(Task task) {
        if (task instanceof Todo) {
            Todo currTask = (Todo) task;
            return currTask.toFileString();
        } else if (task instanceof Deadline) {
            Deadline currTask = (Deadline) task;
            return currTask.toFileString();
        } else if (task instanceof Event) {
            Event currTask = (Event) task;
            return currTask.toFileString();
        } else {
            DoAfter currTask = (DoAfter) task;
            return currTask.toFileString();
        }
    }
}
