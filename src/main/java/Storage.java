import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public static void loadTask(TaskList myList) throws ftException {
        try {
            File save = new File("./data/myTask.txt");
            if (!save.exists()) {
                System.out.println("    There was no save data.");
                save.getParentFile().mkdirs();
                boolean isSuccessful = save.createNewFile();
                System.out.println(isSuccessful ? "    New save data file created." : "    Failed to create a new save data");
            } else {
                System.out.println("    Successfully loaded the save data. ");
            }
            Scanner s = new Scanner(save);
            while (s.hasNext()) {
                String saved = s.nextLine();
                String[] elements = saved.split("\\|");
                elements = Arrays.stream(elements).map(String::trim).toArray(String[]::new);
                String taskType = elements[0];
                switch (taskType) {
                    case "T":
                        myList.add(new ToDo(elements[2], Boolean.parseBoolean(elements[1])));
                        break;
                    case "D":
                        myList.add(new Deadline(elements[2], Boolean.parseBoolean(elements[1]), new Date(elements[3])));
                        break;
                    case "E":
                        myList.add(new Event(elements[2], Boolean.parseBoolean(elements[1]), new Date(elements[3]), new Date(elements[4])));
                        break;
                    default:
                        throw new ftException("    Warning: The file is corrupted. Please delete the file");
                }
                s.nextLine();
            }
        } catch (IOException e) {
            throw new ftException("File not found");
        }
    }

    public static void updateTask(TaskList list) throws ftException {
        try {
            FileWriter fw = new FileWriter("./data/myTask.txt");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                fw.write(task.toSaveFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new ftException("Error in updating the task");
        }
    }

}
