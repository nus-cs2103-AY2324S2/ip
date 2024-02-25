package shon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import shon.exception.ParameterException;
import shon.note.NoteList;
import shon.task.TaskList;

/**
 * Represents a storage that stores the user's lists.
 */
public class Storage {
    /** The file object storing the data for the user's todo list */
    private File tasks;
    /** The file object storing the data for the user's notes list */
    private File notes;
    /** The different type of task encoded in the data file */
    private enum TaskType {
        T, D, E
    }

    /**
     * Returns a <code>Storage</code> object with the data found at filepath. If no pre-existing data exists, a new file
     * along with any relevant parent directory is created.
     *
     * @throws Error If an IO error occurred while trying to create the new data file. Suggests potential errors in
     *     creating the relevant parent directories, or bug in storage initialisation.
     */
    public Storage() {
        try {
            File dir = new File("./data");
            this.tasks = new File("./data/tasks.txt");
            this.notes = new File("./data/notes.txt");
            boolean isNewDir = dir.mkdirs();
            boolean isNewTasks = this.tasks.createNewFile();
            boolean isNewNotes = this.notes.createNewFile();

            assert dir.exists() : "Parent directory(s) for storage is not created";
            assert this.tasks.exists() : "Storage file for tasks is not created";
            assert this.notes.exists() : "Storage file for notes is not created";
        } catch (IOException e) {
            throw new Error("There is an error in creating/opening the \"Shon.txt\" file."
                    + " Check if new directory is created.");
        }
    }

    /**
     * Loads the pre-existing task list from the stored data file.
     *
     * @return <code>TaskList</code> loaded with the tasks and data from the existing data.
     * @throws Error If the data file is not found. Suggests that data file does not exist and
     *     potential errors in storage initialisation.
     */
    public TaskList loadTasks() {
        try {
            assert this.tasks.exists() : "Storage file for tasks is not created";
            Scanner scanner = new Scanner(this.tasks);
            TaskList tasks = new TaskList();
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                try {
                    TaskType taskType = TaskType.valueOf(String.valueOf(data.charAt(0)));
                    switch (taskType) {
                    case T:
                        addTodo(data, tasks);
                        break;
                    case D:
                        addDeadline(data, tasks);
                        break;
                    case E:
                        addEvent(data, tasks);
                        break;
                    default:
                        System.out.println("Storage file for tasks is in wrong format.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid task type found in tasks storage file. " + e.getMessage());
                } catch (ParameterException e) {
                    System.out.println(e.getMessage());
                }
            }
            this.updateTasks(tasks);
            return tasks;
        } catch (FileNotFoundException e) {
            throw new Error("Check that new tasks file is created (if needed) in initializer.");
        }
    }

    private void addTodo(String data, TaskList list) throws ParameterException {
        String[] d = data.split(" \\| ", 3);
        assert d[0].equals("T") : "Provided data is not a Todo storage data";
        String isDoneStatus = d[1];
        String description = d[2];

        // data format checks
        Storage.checkIsDoneStatus(isDoneStatus, description);

        boolean isDone = isDoneStatus.equals("1");
        list.addTodo(description, isDone);
    }

    private void addDeadline(String data, TaskList list) throws ParameterException {
        String[] d = data.split(" \\| ", 4);
        assert d[0].equals("D") : "Provided data is not a Deadline storage data";
        String isDoneStatus = d[1];
        String description = d[2];
        String by = d[3];

        // data format checks
        Storage.checkIsDoneStatus(isDoneStatus, description);

        boolean isDone = isDoneStatus.equals("1");
        try {
            list.addDeadline(description, by, isDone);
        } catch (DateTimeParseException e) {
            throw new ParameterException("Invalid datetime format for " + description + ": " + e.getParsedString());
        }
    }

    private void addEvent(String data, TaskList list) throws ParameterException {
        String[] d = data.split(" \\| ", 5);
        assert d[0].equals("E") : "Provided data is not an Event storage data";
        String isDoneStatus = d[1];
        String description = d[2];
        String from = d[3];
        String to = d[4];

        // data format checks
        Storage.checkIsDoneStatus(isDoneStatus, description);

        boolean isDone = isDoneStatus.equals("1");
        try {
            list.addEvent(description, from, to, isDone);
        } catch (DateTimeParseException e) {
            throw new ParameterException("Invalid datetime format for " + description + ": " + e.getParsedString());
        }
    }

    /**
     * Writes to the tasks file with the updated data in the TaskList.
     *
     * @param tasks TaskList to be formatted and stored.
     */
    public void updateTasks(TaskList tasks) {
        try {
            FileWriter tasksWriter = new FileWriter(this.tasks.getPath());
            for (String line : tasks.formatData()) {
                tasksWriter.write(line);
                tasksWriter.write(System.lineSeparator());
            }
            tasksWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to tasks data file.");
        }
    }

    /**
     * Writes to the notes file with the updated data in the NoteList.
     *
     * @param notes NoteList to be formatted and stored.
     */
    public void updateNotes(NoteList notes) {
        try {
            FileWriter notesWriter = new FileWriter(this.notes.getPath());
            for (String text : notes.formatData()) {
                notesWriter.write(text);
                notesWriter.write(System.lineSeparator());
            }
            notesWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to notes data file.");
        }
    }

    /**
     * Loads the pre-existing note list from the stored data file.
     *
     * @return <code>NoteList</code> loaded with the tasks and data from the existing data.
     * @throws Error If the data file is not found. Suggests that data file does not exist and
     *     potential errors in storage initialisation.
     */
    public NoteList loadNotes() {
        try {
            assert this.notes.exists() : "Storage file for notes is not created";

            Scanner scanner = new Scanner(this.notes);
            NoteList notes = new NoteList();
            while (scanner.hasNext()) {
                notes.addNote(scanner.nextLine());
            }
            return notes;
        } catch (FileNotFoundException e) {
            throw new Error("Check that new tasks file is created (if needed) in initializer.");
        }
    }

    private static void checkIsDoneStatus(String isDoneStatus, String description) throws ParameterException {
        if (!isDoneStatus.equals("0") && !isDoneStatus.equals("1")) {
            throw new ParameterException("Invalid isDoneStatus in Todo " + description + ".");
        }
    }
}
