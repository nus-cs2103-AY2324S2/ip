package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import felix.TaskList;
import notes.Note;
import notes.NoteList;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Handles all loading and saving operations.
 * @author Tan Qin Yong
 */
public class Storage {

    /** The file path where felix.Duke's task list is stored. */
    private static final String FILE_PATH = "./data/felix.txt";
    private static final String FILE_PATH_NOTE = "./data/felix_notes.txt";

    /**
     * Saves the given task list to the file specified by the file path.
     * Creates the necessary directories and file if they don't exist.
     *
     * @param taskList The task list to be saved.
     */
    public void saveToFile(TaskList taskList) {
        try {
            // These will do nothing if files already exists.
            Files.createDirectories(Paths.get("./data"));
            File newFile = new File(FILE_PATH);
            if (!newFile.exists()) {
                boolean success = newFile.createNewFile();
            }
            FileWriter fw = new FileWriter(FILE_PATH);
            saveAllTasks(fw, taskList);
        } catch (IOException io) {
            System.out.println("Error saving file to disk. Exiting...");
        }
    }

    /**
     * Writes all tasks from the provided task list to the given FileWriter object.
     *
     * @param fw        The FileWriter object used to write task data to a file.
     * @param taskList  The task list containing tasks to be saved.
     * @throws IOException If an error occurs while writing the task data.
     */
    public void saveAllTasks(FileWriter fw, TaskList taskList) throws IOException {
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task currentTask = taskList.getTask(i);
            String type = currentTask.getType();
            int isDone = 0;
            if (currentTask.isDone()) {
                isDone = 1;
            }

            switch (type) {
            case "todo": {
                fw.write(type + "|" + isDone + "|" + currentTask.getDescription());
                break;
            }
            case "event": {
                Event currentEvent = (Event) currentTask;
                fw.write(type + "|" + isDone + "|" + currentEvent.getDescription() + "|"
                        + currentEvent.getFrom() + "|" + currentEvent.getTo());
                break;
            }
            case "deadline": {
                Deadline currentDeadline = (Deadline) currentTask;
                fw.write(type + "|" + isDone + "|" + currentDeadline.getDescription() + "|"
                        + currentDeadline.getBy());
                break;
            }
            default: {
                throw new IOException();
            }
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Loads the task list from the file specified by the file path.
     * Creates the necessary directories and file if they don't exist.
     *
     * @return The task list loaded from the file.
     */
    public TaskList loadFile() {
        TaskList taskList = new TaskList();
        try {
            // These will do nothing if files already exists.
            Files.createDirectories(Paths.get("./data"));
            File newFile = new File(FILE_PATH);
            if (!newFile.exists()) {
                boolean success = newFile.createNewFile();
            }
            Scanner sc = new Scanner(newFile);

            taskList = loadAllTasks(sc, taskList);
        } catch (IOException io) {
            System.out.println("Error retrieving data from disk. Exiting...");
        }
        return taskList;
    }

    /**
     * Loads all tasks from the provided scanner object and adds them to the task list.
     * Tasks are parsed from the scanner's input according to the specified format.
     *
     * @param sc        The scanner object used to read task data from a file.
     * @param taskList  The task list object to which tasks will be added.
     * @return The task list containing the loaded tasks.
     * @throws IOException If an error occurs while reading the task data.
     */
    public TaskList loadAllTasks(Scanner sc, TaskList taskList) throws IOException {
        while (sc.hasNext()) {
            String next = sc.nextLine();
            String[] taskArr = next.split("\\|");
            String type = taskArr[0];
            String description = taskArr[2];
            Task newTask = new Task();

            switch (type) {
            case "todo": {
                newTask = new ToDo(description);
                break;
            }
            case "deadline": {
                String by = taskArr[3];

                LocalDate byParsedDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                newTask = new Deadline(description, byParsedDate);
                break;
            }
            case "event": {
                String from = taskArr[3];
                String to = taskArr[4];

                LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                newTask = new Event(description, fromDate, toDate);
                break;
            }
            default: {
                throw new IOException();
            }
            }

            int numDone = Integer.parseInt(taskArr[1]);
            if (numDone == 1) {
                newTask.markAsDone();
            }
            taskList.addTask(newTask, true);
        }
        sc.close();
        return taskList;
    }

    // For notes

    /**
     * Saves the given note list to the file specified by the file path.
     * Creates the necessary directories and file if they don't exist.
     *
     * @param noteList The note list to be saved.
     */
    public void saveNote(NoteList noteList) {
        try {
            // These will do nothing if files already exists.
            Files.createDirectories(Paths.get("./data"));
            File newFile = new File(FILE_PATH_NOTE);
            if (!newFile.exists()) {
                boolean success = newFile.createNewFile();
            }
            FileWriter fw = new FileWriter(FILE_PATH_NOTE);
            saveAllNotes(fw, noteList);
        } catch (IOException io) {
            System.out.println("Error saving file to disk. Exiting...");
        }
    }

    /**
     * Writes all notes from the provided note list to the given FileWriter object.
     *
     * @param fw        The FileWriter object used to write note data to a file.
     * @param noteList  The note list containing note to be saved.
     * @throws IOException If an error occurs while writing the task data.
     */
    public void saveAllNotes(FileWriter fw, NoteList noteList) throws IOException {
        for (int i = 1; i <= noteList.getSize(); i++) {
            Note currentNote = noteList.getNote(i);
            fw.write(currentNote.getDescription());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Loads the note list from the file specified by the file path.
     * Creates the necessary directories and file if they don't exist.
     *
     * @return The note list loaded from the file.
     */
    public NoteList loadNotes() {
        NoteList noteList = new NoteList();
        try {
            // These will do nothing if files already exists.
            Files.createDirectories(Paths.get("./data"));
            File newFile = new File(FILE_PATH_NOTE);
            if (!newFile.exists()) {
                boolean success = newFile.createNewFile();
            }
            Scanner sc = new Scanner(newFile);

            noteList = loadAllNotes(sc, noteList);
        } catch (IOException io) {
            System.out.println("Error retrieving data from disk. Exiting...");
        }
        return noteList;
    }


    /**
     * Loads all note from the provided scanner object and adds them to the note list.
     * Tasks are parsed from the scanner's input according to the specified format.
     *
     * @param sc        The scanner object used to read note data from a file.
     * @param noteList  The note list object to which notes will be added.
     * @return The note list containing the loaded notes.
     * @throws IOException If an error occurs while reading the note data.
     */
    public NoteList loadAllNotes(Scanner sc, NoteList noteList) throws IOException {
        while (sc.hasNext()) {
            String next = sc.nextLine();
            Note newNote = new Note(next);
            noteList.addNote(newNote, true);
        }
        sc.close();
        return noteList;
    }

}


