package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Handles the saving and reading of the save file.
 */
public class Storage {

    /**
     * Saves the tasks in the task list into the save file
     *
     * @param taskList the current list of tasks to be saved
     */
    public static void save(TaskList taskList) {
        try {
            FileWriter f = new FileWriter("save.txt");
            for(Task task : taskList.getTaskList()) {
                String taskString = task.convertToText();
                f.write(taskString);
                f.write("\n");
            }
            f.close();
        } catch (java.io.IOException e) {
            System.out.println("Error in saving");
        }
    }

    /**
     * Reads the save file and places the tasks into the taskList.
     *
     * @param taskList the task list to be placed into.
     */
    public static void read(TaskList taskList, Duke duke) {
        File f = new File("save.txt");
        if(!f.isFile()) {
            try {
                f.createNewFile();
            } catch (java.io.IOException e) {
                duke.output("Could not create save file :(");
            }
        } else {
            try {
                Scanner s = new Scanner(f); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String input = s.nextLine();
                    assert input != null : "Input cannot be null";
                    Parser.commands(taskList, input, true, false, duke);
                }
                duke.output("Save loaded!");
            } catch (FileNotFoundException e2) {
                duke.output("File Not Found :(");
            }
        }
    }
}
