import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Storage {
    private File file;
    private ArrayList<Task> list = new ArrayList<>(100);

    public Storage(String filePath) throws DukeException {
        this.file = new File(filePath);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error creating new file");
            }
        }
    }

    public void save() throws DukeException {
        try{
        FileWriter writer = new FileWriter(file);
        for(int a = 0; a < list.size(); a++ ) {
            Task task = list.get(a);
            String taskString = task.toStore();
            writer.write(taskString + "\n");
        }
        writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving file");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str = bufferedReader.readLine();
            //System.out.println(str);
            while (str != null){
                String[] loadtokens = str.split("/");
                //for(int a = 0; a< loadtokens.length; a++){System.out.println(loadtokens[a]);}
                String type = loadtokens[0];
                String status = loadtokens[1];
                String desc = loadtokens[2];
                Task task;
                switch (type) {
                    case "T":
                        task = new Task(desc);
                        break;
                    case "E":
                        String to = loadtokens[3];
                        String from = loadtokens[4];
                        task = new Event(desc, to, from);
                        break;
                    case "D":
                        String duedate = loadtokens[3];
                        task = new Deadline(desc, duedate);
                        break;
                    default:
                        throw new DukeException("File corrupted.");
                }
                if(status.equals("1")) {
                    task.markAsDone();
                }
                list.add(task);
                str = bufferedReader.readLine();

            }
            bufferedReader.close();

        }   catch (FileNotFoundException e) {
                throw new DukeException("Error loading tasks from file");
            }
            catch (IOException e) {
                throw new DukeException("Error saving file");
            }
        return list;

    }
}
