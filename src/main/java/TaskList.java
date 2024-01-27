import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TaskList {
    protected static final String TASKPATH = "./data/duke.txt";
    private FileWriter data;

    public TaskList() {
        try {
            File f = new File(TASKPATH);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> arr = new ArrayList<>();
        try {
            File f = new File(TASKPATH);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public void save(ArrayList<Task> tl) {
        try {
            this.data = new FileWriter(TASKPATH);
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

    public void close() {
        try {
            this.data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
