import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected static String filePath = "./data/duke.txt";
    private FileWriter data;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> arr = new ArrayList<>();
        File f = new File(this.filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String[] token = sc.nextLine().split("\\|");
            if (token[0].equals("T")) {
                arr.add(new Todo(token[1], Boolean.parseBoolean(token[2])));
            } else if (token[0].equals("D")) {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                arr.add(new Deadline(token[1], Boolean.parseBoolean(token[2]), LocalDate.parse(token[3], formatter)));
            } else if (token[0].equals("E")) {
                arr.add(new Event(token[1], Boolean.parseBoolean(token[2]), token[3], token[4]));
            }
        }
        sc.close();
        return arr;
    }

    public void save(ArrayList<Task> tl) {
        try {
            this.data = new FileWriter(this.filePath);
            tl.forEach(t -> {
                try {
                    if (t instanceof Todo) {
                        this.data.write(String.format("T|%s|%s\n", t.taskName, t.isDone));
                    } else if (t instanceof Deadline) {
                        this.data.write(String.format("D|%s|%s|%s\n",
                                t.taskName, t.isDone,
                                ((Deadline) t).by.format(DateTimeFormatter.ISO_LOCAL_DATE))
                        );
                    } else if (t instanceof Event) {
                        this.data.write(String.format("E|%s|%s|%s|%s\n", t.taskName, t.isDone, ((Event) t).from, ((Event) t).to));
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            this.data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
