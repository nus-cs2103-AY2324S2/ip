import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private ArrayList<Task> taskList = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        File f = new File(filePath);
        try {
            if (!f.createNewFile()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String data = sc.nextLine();
                    String[] dataSplit = data.split(" \\| ");
                    boolean isDone = dataSplit[1].equals("true");
                    switch (dataSplit[0]) {
                    case "T":
                        Todo todo = new Todo(dataSplit[2]);
                        if (isDone) {
                            todo.mark();
                        } else {
                            todo.unmark();
                        }
                        taskList.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(dataSplit[2], dataSplit[3]);
                        if (isDone) {
                            deadline.mark();
                        } else {
                            deadline.unmark();
                        }
                        taskList.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(dataSplit[2], dataSplit[3], dataSplit[4]);
                        if (isDone) {
                            event.mark();
                        } else {
                            event.unmark();
                        }
                        taskList.add(event);
                        break;
                    }
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("____________________________________________________________\n"
                    + "Contents of data file is not in the correct format.\nPlease delete/modify the file\n"
                            + "____________________________________________________________\n");
        }
        return taskList;
    }

    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList) {
                fw.write(task.writeTask());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
