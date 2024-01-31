package jivox;

import jivox.exception.DataHandlerException;
import jivox.task.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * DatabaseHandler handles saving and loading tasks from a file database.
 */
public class DatabaseHandler {
    private File DB = null;

    /**
     * Creates the handler for the given file path.
     *
     * @param filePath The path to the database file.
     */
    public DatabaseHandler(String filePath){
        this.DB = new File(filePath);
    }

    /**
     * Creates the database file if it doesn't exist.
     *
     * @throws DataHandlerException If there is an error creating the file.
     */
    public void create() throws DataHandlerException {
        Path path = Paths.get(DB.getPath());
        try {
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new DataHandlerException(e.getMessage());
        }
    }

    /**
     * Saves the given task list to the database.
     *
     * @param tasks The task list to save.
     * @throws DataHandlerException If there is an error writing to the file.
     */
    public void save(TaskList tasks) throws DataHandlerException {
        try{
            if(!DB.exists()){
                create();
            }
            FileWriter fw = new FileWriter(DB);
            for (int i = 0; i < tasks.getLength(); i++) {
                Task task =  tasks.getTask(i);
                switch (tasks.getTask(i).getType()) {
                    case "D":
                        Deadline d = (Deadline) task;
                        fw.write(d.saveFormat());
                        break;
                    case "E":
                        Event e = (Event) task;
                        fw.write(e.saveFormat());
                        break;
                    case "T":
                        Todo t = (Todo) task;
                        fw.write(t.saveFormat());
                        break;
                }
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e){
            throw new DataHandlerException(e.getMessage());
        }
    }

    /**
     * Loads the tasks from the database file.
     *
     * @return The list of tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            if (!DB.exists()) {
                return list;
            }
            FileReader fr = new FileReader(DB);
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()) {
                String line = sc.nextLine();
                String[] split = line.split("\\|");
                switch (split[0].trim()) {
                    case "D":
                        Deadline d = new Deadline(split[2].trim(),
                                LocalDateTime.parse(split[3].replaceFirst(" ",""),formatter));
                        if (split[1].trim().equals("1")) {
                            d.mark();
                        }
                        list.add(d);
                        break;
                    case "E":
                        String[] start_end = split[3].split(" to ");
                        Event e = new Event(split[2].trim(),LocalDateTime.parse(start_end[0].replaceFirst(" ",""),formatter),
                                LocalDateTime.parse(start_end[1],formatter));
                        if (split[1].trim().equals("1")) {
                            e.mark();
                        }
                        list.add(e);
                        break;
                    case "T":
                        Todo t = new Todo(split[2].trim());
                        if (split[1].trim().equals("1")) {
                            t.mark();
                        }
                        list.add(t);
                        break;
                }
            }
        } catch (IOException e){

        }
        return list;


    }


}
