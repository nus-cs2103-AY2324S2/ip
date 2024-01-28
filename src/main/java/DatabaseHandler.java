import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DatabaseHandler {
    private static final File DB = new File("./data/jivox.txt");

    public void create() throws DataHandlerException {
        try{
            File parent = DB.getParentFile();
            if(parent != null && !parent.exists() && !parent.mkdirs()){
                throw new IOException("Can't create dir: " + parent);
            }
            DB.createNewFile();
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
                        fw.write(d.getType() + " | " + (d.getStatus() ? "1" : "0") + " | " + d.getDescription() + "| " + d.getDeadline());
                        break;
                    case "E":
                        Event e = (Event) task;
                        fw.write(e.getType() + " | " + (e.getStatus() ? "1" : "0") + " | " + e.getDescription() + "| " + e.getStart() + "-" + e.getEnd());
                        break;
                    case "T":
                        Todo t = (Todo) task;
                        fw.write(t.getType() + " | " + (t.getStatus() ? "1" : "0") + " | " + t.getDescription());
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
        try {
            if (!DB.exists()) {
                return list;
            }
            FileReader fr = new FileReader(DB);
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()) {
                String line = sc.nextLine();
                String[] split = line.split("\\|");
                System.out.println(Arrays.toString(split));
                switch (split[0].trim()) {
                    case "D":
                        Deadline d = new Deadline(split[2].trim(), split[3].trim());
                        if (split[1].trim().equals("1")) {
                            d.mark();
                        }
                        list.add(d);
                        break;
                    case "E":
                        String[] start_end = split[3].split("-");
                        Event e = new Event(split[2].trim(),start_end[0].trim(), start_end[1].trim());
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
