import javafx.scene.input.DataFormat;

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

public class DatabaseHandler {
    private static final File DB = new File("./data/jivox.txt");

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

    public void save(ArrayList<Task> tasks) throws DataHandlerException {
        try{
            if(!DB.exists()){
                create();
            }
            FileWriter fw = new FileWriter(DB);
            for (Task task : tasks) {
                switch (task.getType()) {
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
                        Deadline d = new Deadline(split[2].trim(), LocalDateTime.parse(split[3].replaceFirst(" ",""),formatter));
                        if (split[1].trim().equals("1")) {
                            d.mark();
                        }
                        list.add(d);
                        break;
                    case "E":
                        String[] start_end = split[3].split(" to ");
                        System.out.println(Arrays.toString(start_end));
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
