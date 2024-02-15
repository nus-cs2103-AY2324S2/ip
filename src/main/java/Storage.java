import java.io.File;
import java.io.IOException;
import java.util.List;


public class Storage {
    private static final String path = "./storage.txt";
    protected File file;

    public Storage(){
        createFile();
        this.file = new File(path);
    }

    public void storeList(List<Task> taskList){
        
    }
    private void createFile() {
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
