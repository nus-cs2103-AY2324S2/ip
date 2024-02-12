package virtue;

import java.io.FileWriter;
import java.io.IOException;

public class VirtueFileWriter {

    public static void writeToFile(VirtueTaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter("data/virtue.txt");
        fileWriter.write(taskList.fileFormat());
        fileWriter.close();
    }
}
