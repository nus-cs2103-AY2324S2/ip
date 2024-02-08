package gronk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filename;
    private TaskList taskList;
    private UserInterface userInterface;

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
            System.out.println("\tLoaded "  + Integer.toString(numberOfTasks) + " tasks from file.");
        } catch (FileNotFoundException e) {
            System.out.println("\tNo tasks saved. Initializing new session.");
        }
    }

    public void loadLine(String m) {
        String[] message = m.split(",");
        int messageType = message.length;
        if (messageType == 2) { // todo
            this.taskList.addTask(new Todo(
                    message[1],
                    Integer.parseInt(message[0])
            ));
        } else if (messageType == 3) { // deadline
            this.taskList.addTask(Deadline.createDeadline(
                    message[1],
                    Integer.parseInt(message[0]),
                    message[2]
            ));
        } else if (messageType == 4) { // event
            this.taskList.addTask(new Event(
                    message[1],
                    Integer.parseInt(message[0]),
                    message[2],
                    message[3]
            ));
        }
    }

    public TaskList returnTasks() {
        return this.taskList;
    }

    public String getFilepath() {
        return this.filename;
    }

    public Storage(String filename) {
        this.filename = filename;
        this.taskList = new TaskList();
        this.userInterface = new UserInterface();
    }
}
