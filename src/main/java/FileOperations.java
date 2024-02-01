import java.io.File;
import java.io.IOException;

public class FileOperations {
    private FileOperations() {}

    public static File loadFile() throws IOException {
        File data = new File("../../data/data.txt");
        data.createNewFile();
        return data;
    }

    public static ChatSession createChatSession(File file) {
        //
    } 
}
