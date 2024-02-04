package duke;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Venus {

    Storage storage;
    TaskList taskList;

    public Venus(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    public void run() throws FileNotFoundException {
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
        System.out.println(Ui.getEnd());
    }
    public static void main(String[] args) throws FileNotFoundException {
        Venus venus = new Venus("data/venus.txt");
        venus.run();
    }
}
