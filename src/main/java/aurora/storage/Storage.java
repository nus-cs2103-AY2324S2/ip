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

/** The Storage class represents an object that handles file storage and retrieval. */
public class Storage {

    /** The filepath to store or load data from. */
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The path to the file to be retrieved and operated on.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList<Task> created from parsing the contents of the file retrieved.
     *
     * @return ArrayList<Task> created from parsing the contents of the file retrieved.
     * @throws IOException If there is no file located at the path.
     * @throws AuroraException If lines within the file are corrupted.
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
     * Returns Task object obtained from processing one line in a file.
     *
     * @param fileLine File Line to be processed
     * @return Task object obtained from processing one line in a file.
     */
    private Task processLine(String fileLine) throws AuroraException {
        try {
            return fileLinesToTask(fileLine);
        } catch (AuroraException e) {
            throw new AuroraException("Corrupted line in data file: " + fileLine);
        }
    }

    /**
     * Returns the updated ArrayList<Task> after associating the DoAfters with the tasks they are
     * supposed to be associated with.
     *
     * @param taskList Task list with unassociated DoAfter objects.
     * @return ArrayList<Task> after associating the DoAfters with the tasks they are
     *         supposed to be associated with.
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
     * Saves a task list to the file path specified.
     *
     * @param taskList Task list to be saved.
     * @throws IOException If there is a problem in saving the file to the file path specified.
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
     * Returns a Task based on the contents of a fileLine processed.
     *
     * @param fileLine FileLine to be processed.
     * @return Task based on the contents of a fileLine processed.
     * @throws AuroraException If the particular fileLine is corrupted.
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
     * Returns a Todo object based on the contents of a fileLine.
     *
     * @param components Component array generated from the file line.
     * @param description Description of the todo.
     * @param isDone Status of the todo.
     * @return Todo object based on the contents of a fileLine.
     */
    private Todo fileLineToTodo(String[] components, String description, boolean isDone) throws AuroraException {
        Todo todo = new Todo(description);

        if (isDone) {
            todo.setDone();
        }
        return todo;
    }

    /**
     * Returns a Deadline object based on the contents of a fileLine.
     *
     * @param components Component array generated from the file line.
     * @param description Description of the deadline.
     * @param isDone Status of the deadline.
     * @return Deadline object based on the contents of a fileLine.
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
     * Returns an Event object based on the contents of a fileLine.
     *
     * @param components Component array generated from the file line.
     * @param description Description of the event.
     * @param isDone Status of the event.
     * @return Event object based on the contents of a fileLine.
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
     * Returns a DoAfter object based on the contents of a fileLine.
     *
     * @param components Component array generated from the file line.
     * @param description Description of the doAfter.
     * @param isDone Status of the doAfter.
     * @return DoAfter object based on the contents of a fileLine.
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
     * Returns a String representation of a Task to be saved to the storage file.
     *
     * @param task Task to be converted into a String to be saved.
     * @return String representation of a Task to be saved to the storage file.
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
