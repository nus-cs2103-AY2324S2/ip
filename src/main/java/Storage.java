import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File f;
    private FileWriter fw;
    private Scanner s;

    public Storage(String file, String parent) {
        this.f = new File(parent +"/" + file);
        File parentDir = new File(parent);
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    public void writeToFile(TaskList list) {
        try {
            this.fw = new FileWriter(f);
            String data = write(list);
            this.fw.write(data);
            this.fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String write(TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
            if (i < list.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public ArrayList<Task> readFromFile() throws DukeException{
        ArrayList<Task> list = new ArrayList<Task>();
        if (f.exists()) {
            try {
                this.s = new Scanner(this.f);
                while (this.s.hasNext()) {
                    list.add(read(this.s.nextLine()));
                }
            } catch (FileNotFoundException e) {
                throw new DukeException(e.getMessage());
            }
        }
        return list;
    }

    private Task read(String s) {
        String[] cols = s.split(" \\| ");
        Task t = null;
        if (cols.length == 3) {
            t = new Todo(cols[2]);

        } else if (cols.length == 4) {
            t = new Deadline(cols[2], cols[3]);
        } else if (cols.length == 5) {
            t = new Event(cols[2], cols[3], cols[4]);
        }
        if (cols[1].equals("1")) {
            t.done();
        } else {
            t.undo();
        }

        return t;
    }

}
