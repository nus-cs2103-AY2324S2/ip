import java.io.File;
import java.io.IOException;

public class Storage {
    private final static String dirPath = "./data/";


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

        File f = new File(directory, "taskList.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }  
    }

    
}
