package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represent the class that deals with loading tasks from the file
 * and saving tasks in the file
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Storage {

    private File directory;
    private File dataFile;


    /**
     * Constructor for a Storage instance
     *
     * @param  directoryPath directory to check/put data files in
     * @param  fileName name of file to check and update data in
     */

    public Storage(String directoryPath, String fileName) {
        directory = new File(directoryPath);
        dataFile = new File(directoryPath + "/" + fileName);
    }


    /**
     * Creates a directory and file should the items do not exist
     * If the items exist, retrieve the data in the file and store it into an ArrayList of Tasks
     *
     * @return an ArrayList of Tasks to be loaded into the TaskList instance
     * @throws DukeException when data file does not exist
     */

    public ArrayList<Task> setUpDirAndFile() throws DukeException {
        ArrayList<Task> previousTasks = new ArrayList<Task>();

        if (!directory.exists()) {
            directory.mkdir();
        }

        boolean hasRecords = true;

        try {
            hasRecords = dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("There seems to be previous records. Loading...");
        }

        if (hasRecords == true) {
            throw new DukeException("No previous records found.");
        }

        if (hasRecords == false) {
            System.out.println("Previous records loaded.\n");
            try {
                Scanner scanner = new Scanner(dataFile);

                while (scanner.hasNextLine()) {
                    previousTasks.add(transferFileContent(scanner.nextLine()));
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        return previousTasks;
    }

    /**
     * Converts a line in the data file into a Task
     *
     * @param  line line in file that is scanned and needed to be operated on
     * @return a Task that is decrypted
     * @throws DukeException when file line cannot be read
     */
    private Task transferFileContent(String line) throws DukeException {
        String[] taskComponents = line.split("@");
        Task taskAdded = new Task("Error. Unable to retrieve Task.");

        if (taskComponents.length == 3) {
            taskAdded = new Todo(taskComponents[2]);
        } else if (taskComponents.length == 4) {
            taskAdded = new Deadline(taskComponents[2], taskComponents[3]);
        } else if (taskComponents.length == 5) {
            taskAdded = new Event(taskComponents[2], taskComponents[3], taskComponents[4]);
        }

        if (taskComponents[1].equals("1")) {
            taskAdded.markAsDone();
        }

        return taskAdded;
    }

    /**
     * Updates and saves to the file in the hard disk everytime the taskList is updated
     *
     * @param  taskToUpdate Task to update file with
     * @param  command whether the Task is to be updated/deleted from the file
     * @param  lineNum at which line in the file the data is to be deleted
     */
    public void updateFile(Task taskToUpdate, int command, int lineNum) {
        String taskString = taskToUpdate.toString(true);
        assert command == Duke.STORAGE_ADD_COMMAND
                || command == Duke.STORAGE_DELETE_COMMAND
                || command == 2 : "Invaliid command used";
        try {
            if (command == Duke.STORAGE_ADD_COMMAND) {

                BufferedWriter writer = new BufferedWriter(new FileWriter(
                        dataFile, true));

                if (dataFile.length() != 0) {
                    writer.newLine();
                }

                writer.write(taskString);
                writer.close();

            } else if (command == Duke.STORAGE_DELETE_COMMAND) {
                BufferedReader reader = new BufferedReader(new FileReader(dataFile));
                String line;
                StringBuilder sb = new StringBuilder();
                int lineNumCount = 1;

                while ((line = reader.readLine()) != null) {
                    if ((lineNumCount == lineNum)) {
                        sb.append("");
                    } else {
                        sb.append(line);
                        sb.append("\n");
                    }
                    lineNumCount++;
                }

                sb.deleteCharAt(sb.lastIndexOf("\n"));
                BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
                writer.write(sb.toString());
                writer.close();
                reader.close();
            } else if (command == Duke.STORAGE_SNOOZE_COMMAND) {
                BufferedReader reader = new BufferedReader(new FileReader(dataFile));
                String line;
                StringBuilder sb = new StringBuilder();
                int lineNumCount = 1;

                while ((line = reader.readLine()) != null) {
                    if ((lineNumCount == lineNum)) {
                        sb.append(taskString + "\n");
                    } else {
                        sb.append(line);
                        sb.append("\n");
                    }
                    lineNumCount++;
                }
                sb.deleteCharAt(sb.lastIndexOf("\n"));
                BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
                writer.write(sb.toString());
                writer.close();
                reader.close();
            } else {
                assert false : "Invalid command sent to storage";
            }
        } catch (IOException e) {
            System.out.println("Oops!");
        }
    }
}
