package CinnamoRoll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

class Storage {
    private final String PATH;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    Storage(String filepath) {
        this.PATH = filepath;
    }
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
    ArrayList<Task> load_data() throws IOException {
        try {
            ArrayList<Task> output = new ArrayList<>();
            BufferedReader bf = new BufferedReader(new FileReader(PATH));
            String input;
            while ((input = bf.readLine()) != null) {
                Task task;
                String[] info = input.split("\\|", 3);
                boolean marked = false;
                switch (info[1]) {
                    case "X":
                        marked = true;
                        break;
                    case " ":
                        marked = false;
                        break;
                    default:
                        System.out.println("Oops! No Markings Provided in Correct Format:(");
                }
                switch (info[0].toUpperCase()) {
                    case "T":
                        task = new Todos(info[2], marked);
                        output.add(task);
                        break;
                    case "D":
                        String[] deadline = info[2].split("/by ");
                        task = new Deadlines(deadline[0], LocalDateTime.parse(deadline[1], this.format), marked);
                        output.add(task);
                        break;
                    case "E":
                        String[] event = info[2].split("/from | /to ");
                        task = new Events(event[0], LocalDateTime.parse(event[1], this.format), LocalDateTime.parse(event[2], this.format), marked);
                        output.add(task);
                        break;
                }
            }
            return output;
        } catch(FileNotFoundException ex){
            File f = new File(PATH);
            f.createNewFile();
            return new ArrayList<Task>();
        }
    }
}
