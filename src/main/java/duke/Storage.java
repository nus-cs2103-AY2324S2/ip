package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File f;
    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedLst = new ArrayList<>();
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (IOException e) {
            throw new DukeException("Sorry, I can't find the file to load from");
        }
        while (s.hasNext()) {
            String[] taskArr = s.nextLine().split(" \\| ");
            switch (taskArr[0]) {
            case "T":
                Task newTodo = Todo.fromSaveFormat(taskArr);
                loadedLst.add(newTodo);
                break;
            case "D":
                Task newDeadline = Deadline.fromSaveFormat(taskArr);
                loadedLst.add(newDeadline);
                break;
            case "E":
                Task newEvent = Event.fromSaveFormat(taskArr);
                loadedLst.add(newEvent);
                break;
            }
        }
        return loadedLst;
    }

    public void save(TaskList tl) throws IOException{
        f.getParentFile().mkdirs();
        FileWriter cfw = new FileWriter(f);
        for (Task t : tl.getLst()) {
            cfw.write(t.toSaveFormat() + "\n");
        }
        cfw.close();
    }
}