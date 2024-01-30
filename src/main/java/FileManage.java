import java.io.*;
import java.util.ArrayList;
public class FileManage {
    private static final String PATH = "./data/duke.txt";

    public static void saveFiles(ArrayList<Task> tasks) {
        try {
            File file = new File(PATH);
            File dir = new File("./data");

            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oot = new ObjectOutputStream(fout);
            oot.writeObject(tasks);
            oot.close();
            fout.close();
        } catch (IOException e) {
            System.out.println("Oops! Something goes wrong!");
        }
    }

    public static ArrayList<Task> loadFiles() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(PATH);

        if (!file.exists()) {
            return tasks;
        }

        try {
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            tasks = (ArrayList<Task>) objInputStream.readObject();
            objInputStream.close();
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Oops! Something is missing");
        } catch (ClassNotFoundException e) {
            System.out.println("Oops! class doesn't find");
        }

        return tasks;
    }
}
