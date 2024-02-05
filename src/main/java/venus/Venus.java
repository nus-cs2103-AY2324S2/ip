package venus;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Venus {

    Storage storage;
    TaskList taskList;

    public Venus(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    public void run() {
        try {
            System.out.println(Ui.getStart());
            taskList.loadTasks(storage);
            Scanner sc = new Scanner(System.in);
            String words = sc.nextLine().trim();
            while (!words.equals("bye")) {
                taskList.setTasks(words);
                words = sc.nextLine();
            }
            sc.close();
            taskList.saveTasks(storage);
        } catch (FileNotFoundException e) {
            Ui.formatResponse("File cannot be created or cannot be modified,"
                    + " please check file access modifiers!");
        }
        System.out.println(Ui.getEnd());
    }
    public static void main(String[] args) {
        Venus venus = new Venus("data" + File.separator + "venus.txt");
        venus.run();
    }
}
