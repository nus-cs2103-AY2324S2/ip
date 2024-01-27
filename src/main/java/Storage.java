import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final static String dirPath = "./data/";
    private final static String filePath = "./data/taskList.txt";
    private static File file;


    public static void init() {
        try{ 
            create();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void create() throws IOException {
        File directory = new File(dirPath);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.out.println("Failed to create directory.");
                return;
            } 
        }

        file = new File(directory, "taskList.txt");
        if (!file.exists()) {
             file.createNewFile();  
        }  
    }

    public static void store() {
        FileWriter fw = null;
        try {
            // To reset the file
            fw = new FileWriter(filePath);
            fw.write("");
            fw.close();;

            fw = new FileWriter(filePath, true);
            for (int i = 1; i <= TaskList.listSize(); i++) {
                String textToAppend = TaskList.getTask(i).toString();
                System.out.println(textToAppend);
                fw.write(textToAppend + "\n");
            }   
        } catch (IOException e) {
            System.out.println("An error occurred while storing data: " + e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println("An error occurred while closing the FileWriter: " + e.getMessage());
            }
        }
    }

}
