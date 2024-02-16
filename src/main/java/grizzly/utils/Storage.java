package grizzly.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import grizzly.contacts.Contact;
import grizzly.exceptions.RecordCreationException;
import grizzly.tasks.Deadline;
import grizzly.tasks.Event;
import grizzly.tasks.Task;
import grizzly.tasks.Todo;

/**
 * This class implements the saving and reading of bot data into a text file.
 *
 * @author delishad21
 */
public class Storage {
    private File f;

    /**
     * Creates a Storage object, takes in a filepath for the file in which data
     * should be saved.
     *
     * @param filePath File to save data to and load data from.
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        this.f = new File(filePath);
        checkAndCreateFile();
    }

    /**
     * Checks and creates file based on filepath.
     *
     * @throws IOException
     */
    private void checkAndCreateFile() throws IOException {
        // Reading and creating data save file
        // making data folder
        if (!f.getParentFile().exists()) {
            if (!f.getParentFile().mkdir()) {
                throw new IOException("Unable to make directory");
            }
        }

        // making data file
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    /**
     * Reads save data from save file.
     *
     * @return Error log from reading save file
     * @throws FileNotFoundException
     */
    public String readSaveData(Database db) throws FileNotFoundException {
        String errorLog = "";

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            try {
                String line = s.nextLine();
                String recordType = getRecordType(line);
                switch (recordType) {
                case "contact":
                    db.addContact(parseContactFromSave(line));
                    break;
                case "task":
                    db.addTask(parseTaskFromSave(line));
                    break;
                default:
                    // Code will not end up here as getRecordType only returns 2 values
                }
            } catch (RecordCreationException e) {
                errorLog = errorLog + e.getMessage() + "\n";
            }
        }
        s.close();

        return errorLog;
    }

    private String getRecordType(String line) throws RecordCreationException {
        String tag = line.split("\\|")[0];
        switch (tag) {
        case "[T]":
            return "task";
        case "[D]":
            return "task";
        case "[E]":
            return "task";
        case "[C]":
            return "contact";
        default:
            throw new RecordCreationException("Unable to determine the record type " + line);
        }
    }

    /**
     * Saves data from bot back to save file.
     *
     * @param data The data from the bot.
     * @throws IOException
     */
    public String saveData(Database data) throws IOException {
        this.checkAndCreateFile();
        assert this.f.exists();

        FileWriter fw = new FileWriter(f);

        String dataString = "";

        for (int i = 1; i <= data.taskListSize(); i++) {
            dataString = dataString + data.getTask(i).toSave() + "\n";
        }

        for (int i = 1; i <= data.contactListSize(); i++) {
            dataString = dataString + data.getContact(i).toSave() + "\n";
        }

        fw.write(dataString);
        fw.close();

        return data.taskListSize() + " tasks saved\n"
               + data.contactListSize() + " contacts saved\n";
    }

    /**
     * Parses individual task from save file into Tasks.
     *
     * @param task Each line read from save file.
     * @return A Task object generated with information parsed from input.
     * @throws RecordCreationException
     */
    private Task parseTaskFromSave(String task) throws RecordCreationException {
        String[] taskSplit = task.split("\\|");
        boolean isDone = checkTaskDone(taskSplit[1], task);
        try {
            switch (taskSplit[0]) {
            case "[T]":
                return new Todo(isDone, taskSplit[2]);
            case "[D]":
                return new Deadline(isDone, taskSplit[2],
                                    LocalDateTime.parse(taskSplit[3], Parser.INPUT_DT_FORMATTER));
            case "[E]":
                return new Event(isDone, taskSplit[2],
                                LocalDateTime.parse(taskSplit[3], Parser.INPUT_DT_FORMATTER),
                                LocalDateTime.parse(taskSplit[4], Parser.INPUT_DT_FORMATTER));
            default:
                throw new RecordCreationException("No such task: " + taskSplit[0] + " for " + task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RecordCreationException("Task is corrupted: " + task);
        }
    }

    /**
     * Checks if a given task is completed.
     *
     * @param marker
     * @param task
     * @throws RecordCreationException
     */
    private boolean checkTaskDone(String marker, String task) throws RecordCreationException {
        if (marker.equals("[X]")) {
            return true;
        } else if (marker.equals("[ ]")) {
            return false;
        } else {
            throw new RecordCreationException("Unable to determine if task \"" + task + "\" is done or not");
        }
    }

    /**
     * Parses a line of contact from the save.
     *
     * @param contact
     * @throws RecordCreationException
     */
    private Contact parseContactFromSave(String contact) throws RecordCreationException {
        String[] contactSplit = contact.split("\\|");
        try {
            return new Contact(contactSplit[1], contactSplit[2], Integer.parseInt(contactSplit[3]));
        } catch (IndexOutOfBoundsException e) {
            throw new RecordCreationException("Contact is corrupted: " + contact);
        }
    }

}
