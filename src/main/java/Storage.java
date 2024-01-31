import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> load() {
        ArrayList<Task> items = new ArrayList<>();
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                String[] currLine = line.split(" \\| ");
                switch (currLine[0]) {
                    case "T":
                        items.add(new Todo(currLine[2]));
                        break;
                    case "D":
                        items.add(new Deadline(currLine[2], currLine[3]));
                        break;
                    case "E":
                        items.add(new Event(currLine[2], currLine[3], currLine[4]));
                        break;
                    default:
                        break;
                }
                if (currLine[1].equals("1")) {
                    items.get(items.size() - 1).markAsDone();
                }
                line = br.readLine();
            }
        } catch (IOException | HenryException e) {
            System.err.println(e);
        }
        return items;
    }
    public void save(ArrayList<Task> items) {
        File file = new File(filePath);
        try {
            FileWriter fw = new FileWriter(file);
            for (Task item : items) {
                fw.write(item.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
