import java.io.FileNotFoundException;
import java.io.IOException;

import duke.Storage;
import duke.TaskList;

public class Test {
    
    public static void main(String[] args)  throws FileNotFoundException, IOException {
        Storage storage = new Storage("./data/saved_tasks.txt");
        try {
            TaskList taskList = storage.load();
            System.out.println(taskList.showList());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
