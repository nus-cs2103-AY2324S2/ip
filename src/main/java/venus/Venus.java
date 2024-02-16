package venus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is a deadline class that extends from the Task class.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class Venus {

    private Storage storage;
    private TaskList taskList;

    /**
     * Returns Venus object with storage set as relative file path and empty taskList.
     *
     * @param filePath Relative file path of file.
     */
    public Venus(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    /**
     * Runs the program and exit if a "bye" input is detected.
     */
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

    /**
     * Starts the program in PSVM.
     */
    public static void main(String[] args) {
        Venus venus = new Venus("data" + File.separator + "venus.txt");
        venus.run();
    }
}
