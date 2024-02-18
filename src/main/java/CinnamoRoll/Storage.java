package CinnamoRoll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
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
            ArrayList<Task> output = new ArrayList<>();
            Scanner sc = new Scanner(new FileReader(path));
            String input;

            while (sc.hasNextLine()) {
                input = sc.nextLine();
                Task task;
                String[] info = input.split("\\|", 3);
                boolean isMarked = false;

                switch (info[1].trim()) {
                case "X":
                    isMarked = true;
                    break;
                case "":
                    isMarked = false;
                    break;
                default:
                    System.out.println("Oops! No Markings Provided in Correct Format:(");
                }

                switch (info[0].trim().toUpperCase()) {
                case "T":
                    String todotask = info[2].trim();
                    task = new Todos(todotask, isMarked);
                    output.add(task);
                    break;
                case "D":
                    String[] deadline = info[2].trim().split("/by");
                    String deadlinetask = deadline[0].trim();
                    String datetime = deadline[1].trim();
                    task = new Deadlines(deadlinetask, LocalDateTime.parse(datetime,
                            this.format), isMarked);
                    output.add(task);
                    break;
                case "E":
                    String[] event = info[2].trim().split("/from | /to");
                    String eventdetail = event[0].trim();
                    String eventfrom = event[1].trim();
                    String eventto = event[2].trim();
                    task = new Events(eventdetail, LocalDateTime.parse(eventfrom, this.format),
                            LocalDateTime.parse(eventto, this.format), isMarked);
                    output.add(task);
                    break;
                default:
                    System.out.println("Loading data unsuccessful: invalid event type or formatting");
                }
            }
            return output;
        } catch (FileNotFoundException ex) {
            File f = new File(path);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            return new ArrayList<Task>();
        }
    }
}
