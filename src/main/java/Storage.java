import java.io.File;
import java.io.IOException;

public class Storage {
    private final static String dirPath = "./data/";
    private static File file;


    public static void init() {
        create();
    }

    private static void create() {
        File directory = new File(dirPath);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.out.println("Failed to create directory.");
                return;
            } 
        }

        file = new File(directory, "taskList.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }  
    }

}
