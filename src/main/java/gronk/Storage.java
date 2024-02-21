package gronk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class.
 * This contains a TaskList object which contains
 * Task objects, a filename to read and write from,
 * and a user interface to output messages to interact
 * with the user.
 */
public class Storage {
    private String filename;
    private TaskList taskList;
    private UserInterface userInterface;

    /**
     * Constructor for Storage object.
     * Initializes a filename for reference later, a
     * TaskList to load data from, and a UserInterface
     * to print messages with.
     *
     * @param filename String of filename.
     */
    public Storage(String filename) {
        this.filename = filename;
        this.taskList = new TaskList();
        this.userInterface = new UserInterface();
    }

    /**
     * Saves the taskList to a file with filename. The format
     * of messages has also been changed.
     */
    public void saveToFile() {
        try {
            FileWriter myWriter = new FileWriter(this.filename);
            for (Task t: this.taskList.getAllTasks()) {
                myWriter.write(t.saveFormat() + "\n");
            }
            myWriter.close();
            this.userInterface.printMessage("\tTasks saved to " + this.filename);
        } catch (IOException e) {
            this.userInterface.printMessage("\tFailed to save.");
        }
    }

    /**
     * Loads a TaskList object containing Task objects from the
     * filename (if it exists).
     */
    public void loadFromFile() {
        try {
            File myFile = new File(this.filename);
            System.out.println("\tWelcome back! Loading tasks...");
            Scanner sc = new Scanner(myFile);
            int numberOfTasks = 0;
            while (sc.hasNextLine()) {
                loadLine(sc.nextLine());
                numberOfTasks += 1;
            }
            System.out.println("\tLoaded " + Integer.toString(numberOfTasks) + " tasks from file.");
        } catch (FileNotFoundException e) {
            System.out.println("\tNo tasks saved. Initializing new session.");
        }
    }

    /**
     * Parses the lines in a file which is being loaded and
     * translates them into instructions to create a new
     * TaskList object.
     *
     * @param m A line from the file that is being loaded.
     */
    public void loadLine(String m) {
        String[] message = m.split(",");
        int messageType = message.length;
        if (messageType == 2) {
            this.taskList.addTask(new Todo(
                    message[1],
                    Integer.parseInt(message[0])
            ));
        } else if (messageType == 3) {
            this.taskList.addTask(Deadline.createDeadline(
                    message[1],
                    Integer.parseInt(message[0]),
                    message[2]
            ));
        } else if (messageType == 4) {
            this.taskList.addTask(Event.createEvent(
                    message[1],
                    Integer.parseInt(message[0]),
                    message[2],
                    message[3]
            ));
        }
    }

    /**
     * Returns the TaskList object which was read from the file.
     *
     * @return A TaskList object which was read from a file.
     */
    public TaskList returnTasks() {
        return this.taskList;
    }

    /**
     * Getter method for the filepath variable.
     *
     * @return A string containing the filepath.
     */
    public String getFilepath() {
        return this.filename;
    }
}
