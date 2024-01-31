import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {

    public Storage() {

    }
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

    public static void read(TaskList taskList) {
        File f = new File("save.txt");
        if(!f.isFile()) {
            try {
                f.createNewFile();
            } catch (java.io.IOException e) {
                System.out.println("Could not create save file :(");
            }
        } else {
            try {
                Scanner s = new Scanner(f); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String input = s.nextLine();
                    Parser.commands(taskList, input, true, false);
                }
                System.out.println("Save loaded!");
            } catch (FileNotFoundException e2) {
                System.out.println("File Not Found :(");
            }
        }
    }
}
