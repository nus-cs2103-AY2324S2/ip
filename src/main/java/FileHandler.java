import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    private static final File FILE = new File("./data/duck.txt");

    public FileHandler() {
    }

    public void create() throws FileIOException {
        try {
            File parent = FILE.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IOException("Couldn't create dir: " + parent);
            }
            FILE.createNewFile();
        } catch (IOException e) {
            throw new FileIOException(e.getMessage());
        }
    }

    protected void saveInFile(List<Task> list) throws FileIOException {
        try {
            if (!FILE.exists()) {
                create();
            }
            FileWriter fw = new FileWriter(FILE);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                switch (task.type()) {
                    case "D":
                        Deadline deadline = (Deadline) task;
                        fw.write(deadline.type() + "| " + (deadline.getStatusIcon().isBlank() ? "0" : "1") + "| " +
                                deadline.description + "| " +
                                deadline.getBy());
                        break;
                    case "E":
                        Event events = (Event) task;
                        fw.write(events.type() + "| " + (events.getStatusIcon().isBlank() ? "0" : "1") + "| " +
                                events.description + "| " +
                                events.getDate());
                        break;
                    case "T":
                        Todo toDo = (Todo) task;
                        fw.write(toDo.type() + "| " + (toDo.getStatusIcon().isBlank() ? "0" : "1") + "| " +
                                toDo.description);
                        break;
                }
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new FileIOException(e.getMessage());
        }
    }

    protected List<Task> readFromFile() {
        List<Task> list = new ArrayList<>();
        try {
            if (!FILE.exists()) {
                return list;
            }
            FileReader fr = new FileReader(FILE);
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()) {
                String line = sc.nextLine();
                String[] split = line.split("\\| ");
                switch (split[0]) {
                    case "D":
                        Deadline tempDeadline = new Deadline(split[2], split[3]);
                        if (split[1].equals("1")) {
                            tempDeadline.markAsDone();
                        }
                        list.add(tempDeadline);
                        break;
                    case "E":
                        String[] start_end = split[3].split("-");
                        Event tempEvent = new Event(start_end[0], start_end[1], split[2]);
                        if (split[1].equals("1")) {
                            tempEvent.markAsDone();
                        }
                        list.add(tempEvent);
                        break;
                    case "T":
                        Todo tempToDo = new Todo(split[2]);
                        if (split[1].equals("1")) {
                            tempToDo.markAsDone();
                        }
                        list.add(tempToDo);
                        break;
                }
            }
        } catch (IOException e) {
        }
        return list;
    }
}
