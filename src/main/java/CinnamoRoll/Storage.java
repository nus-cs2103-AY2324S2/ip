package CinnamoRoll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the loading of tasks from a file and creating an ArrayList of Task objects.
 * It also provides the functionality to create and handle the file where tasks are stored.
 * The expected format of the file includes representations for Todos, Deadlines, and Events
 */
class Storage {
    private final String path = "./task/Cinnamo.txt";
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Loads tasks from a file specified by the PATH constant and creates an ArrayList of Task objects.
     * The file is expected to have each task represented in a specific format:
     * "T | [X or space] | taskDescription" for Todos,
     * "D | [X or space] | taskDescription /by deadline" for Deadlines,
     * "E | [X or space] | taskDescription /from startDate /to endDate" for Events.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws IOException If there is an error reading the file.
     */

    // 1. Solution below, very specifically only on how to catch exceptions for creating files,
    // and methods used for creating a new file under the context of this project together with date-time format adapted
    // with the requirement of the level 8 specification on ip website, was inspired by previous batch's level 8
    // submission here: https://github.com/david-eom/CS2103T-IP/releases/tag/Level-8

    // 2. Solution below for creating a new file then was further debugged & aided
    // by https://stackoverflow.com/questions/7469018/cannot-make-file-java-io-ioexception-no-such-file-or-directory;
    ArrayList<Task> loadData() throws IOException {
        try {
            ArrayList<Task> userOutput = new ArrayList<>();
            Scanner sc = new Scanner(new FileReader(path));
            String userInput;

            while (sc.hasNextLine()) {
                userInput = sc.nextLine();
                String[] userInfo = trimList(userInput.split("\\|", 3));
                boolean isMarked = false;

                switch (userInfo[1]) {
                case "X":
                    isMarked = true;
                    break;
                case "":
                    break;
                default:
                    System.out.println("Oops! No Markings Provided in Correct Format:(");
                }

                switch (userInfo[0].toUpperCase()) {
                case "T":
                    String todoTask = userInfo[2];
                    userOutput.add(new Todos(todoTask, isMarked));
                    break;
                case "D":
                    String[] deadline = trimList(userInfo[2].split("/by"));
                    userOutput.add(new Deadlines(deadline[0], LocalDateTime.parse(deadline[1],
                            this.format), isMarked));
                    break;
                case "E":
                    String[] event = trimList(userInfo[2].split("/from | /to"));
                    userOutput.add(new Events(event[0], LocalDateTime.parse(event[1], this.format),
                            LocalDateTime.parse(event[2], this.format), isMarked));
                    break;
                default:
                    System.out.println("Loading data unsuccessful: invalid event type or formatting");
                }
            }
            return userOutput;
        } catch (FileNotFoundException ex) {
            this.createFile();
            return new ArrayList<Task>();
        }
    }

    /**
     * Creates a datafile to store the tasks if user does not have one in local machine
     * @throws IOException
     */
    public void createFile() throws IOException {
        File f = new File(path);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    String[] trimList(String[] userInput) {
        String[] trimOutput = userInput;
        for (int i = 0; i < trimOutput.length; i++) {
            trimOutput[i] = trimOutput[i].trim();
        }
        return trimOutput;
    }
}
