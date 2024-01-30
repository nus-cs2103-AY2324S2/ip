import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StorageHelper {
    private static final String directoryPath = "data";
    private static final String fileName = "task_list.txt";

    public File loadFile() throws IOException {
        // check if the directory exists if not create it
        File directory = new File(directoryPath);
        directory.mkdir();

        // check if the file exists if not create it
        File file = new File(directoryPath + "/" + fileName);
        file.createNewFile();

        return file;
    }

    public void saveFile(String content) throws IOException {
        // check if the directory exists if not create it
        File directory = new File(directoryPath);
        directory.mkdir();

        // check if the file exists if not create it
        File file = new File(directoryPath + "/" + fileName);
        file.createNewFile();

        // write into the file
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }

}