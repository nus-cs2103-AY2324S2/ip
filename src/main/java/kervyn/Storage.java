package kervyn;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import kervyn.FXControls.DialogBox;
import kervyn.Tasks.Deadline;
import kervyn.Tasks.Event;
import kervyn.Tasks.Task;
import kervyn.Tasks.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Storage class is responsible for handling file operations such as reading from and writing to the file.
 */
public class Storage {
    private String path;
    private VBox dialogContainer;
    private Image kervynImage;
    /**
     * Constructs a Storage object associated with the file path provided.
     *
     * @param path The file path where tasks are saved and read from.
     */
    public Storage(String path, VBox dialogContainer, Image kervynImage) {
        this.path = path;
        this.dialogContainer = dialogContainer;
        this.kervynImage = kervynImage;
    }

    /**
     * Writes the content to the file specified by this.path. If the file or directory does not exist, they will be created.
     *
     * @param content The content to be written to the file.
     * @return Returns 1 if the write operation was successful, 0 otherwise.
     */
    public short writeToFile(String content) {
        try {
            String[] dirName = this.path.split("/");
            File dir = new File(dirName[0]);

            if (!dir.exists()) {
                dir.mkdir();
            }

            File file = new File(this.path);
            assert(this.path.equals("data/tasks.txt"));

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            fw.write(content);

            fw.close();
            return 1;
        }
        catch (IOException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog("Uh oh, the file/directory doesn't seem to exist. No worries, one will be created for you at the end of your conversation!", kervynImage)
            );
        }
        return 0;
    }

    /**
     * Reads tasks from the file specified by this.path and constructs an ArrayList of Task objects.
     *
     * @return Returns an ArrayList of Task objects read from the file.
     * @throws IOException If there is an issue reading from the file.
     */
    public ArrayList<Task> readTasks() throws IOException {
        String[] dirName = this.path.split("/");
        File dir = new File(dirName[0]);

        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(this.path);
        assert(this.path.equals("data/tasks.txt"));

        if (!file.exists()) {
            file.createNewFile();
        }

        ArrayList<Task> userRequests = new ArrayList<Task>();
        try {
            ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(this.path), StandardCharsets.UTF_8);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, ha");
            for (String line : lines) {
                char[] lineToCharArr = line.trim().toCharArray();
                char type = lineToCharArr[1];
                char check = lineToCharArr[5];
                boolean status = check == 'X';
                String description = "";
                // Finding content for the description
                String[] splitContent = line.split("]");
                switch (type) {
                    case 'T':
                        description = splitContent[2].trim();
                        ToDo newToDo = new ToDo(description, status);
                        userRequests.add(newToDo);
                        break;
                    case 'D':
                        // Further split the last part
                        String[] furtherSplitContent = splitContent[2].split("\\(");
                        String[] finalSplitContent = furtherSplitContent[1].split(":");

                        String temp = finalSplitContent[1];
                        String deadlineStr = temp.substring(0, temp.length() - 1).trim();
                        LocalDateTime deadline = LocalDateTime.parse(deadlineStr, formatter);

                        description = furtherSplitContent[0].trim();
                        Deadline newDeadline = new Deadline(description, status, deadline);
                        userRequests.add(newDeadline);
                        break;
                    case 'E':
                        furtherSplitContent = splitContent[2].split("\\(");
                        description = furtherSplitContent[0].trim();
                        String[] splitAtTo = furtherSplitContent[1].split("to:");
                        String[] splitAtFrom = splitAtTo[0].split("from:");

                        String startDateStr = splitAtFrom[1].trim();
                        temp = splitAtTo[1];
                        String endDateStr = temp.substring(0, temp.length() - 1).trim();

                        LocalDateTime startDate = LocalDateTime.parse(startDateStr, formatter);
                        LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);
                        Event newEvent = new Event(description, status, startDate, endDate);
                        userRequests.add(newEvent);
                        break;
                }
            }
        }
        catch (IOException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog("Uh oh, the file/directory doesn't seem to exist. No worries, one will be created for you at the end of your conversation!", kervynImage)
            );
        }

        return userRequests;
    }
}
