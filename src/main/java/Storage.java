import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static String FILE_PATH;

    public Storage (String filePath) {
        Storage.FILE_PATH = filePath;
    }

    public static void updateSaved(ArrayList<Task> AL) {
        try {
            File data = initDataFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(data));

            for (Task t : AL) {
                writer.write(t.savedFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public ArrayList<Task> loadTasks() {
        try {
            File data = initDataFile();
            ArrayList<Task> taskAL = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(data));

            String task;
            while ((task = reader.readLine()) != null) {
                String[] taskInfo = task.split(" \\| ");

                if (taskInfo[0].equals("T")) {
                    Todo todo = new Todo(taskInfo[2]);
                    if (Integer.parseInt(taskInfo[1]) == 1) {
                        todo.mark();
                    }
                    taskAL.add(todo);
                } else if (taskInfo[0].equals("D")) {
                    Deadline deadline = new Deadline(taskInfo[2], taskInfo[3]);
                    if (Integer.parseInt(taskInfo[1]) == 1) {
                        deadline.mark();
                    }
                    taskAL.add(deadline);
                } else {
                    Event event = new Event(taskInfo[2], taskInfo[3], taskInfo[4]);
                    if (Integer.parseInt(taskInfo[1]) == 1) {
                        event.mark();
                    }
                    taskAL.add(event);
                }
            }
            
            reader.close();
            return taskAL;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static File initDataFile () throws IOException {
        File data = new File(FILE_PATH);
        if (!data.getParentFile().exists()) {
            data.getParentFile().mkdirs();
        }
        if (!data.exists()) {
            data.createNewFile();
        }

        return data;
    }
}
