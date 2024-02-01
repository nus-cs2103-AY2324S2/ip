import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class contains a method that overwrites the old list with the
 * newly updated list when exiting the chatbot.
 * This class also contains a method that loads the saved list into the chatbot.
 */
public class SaveAndLoad extends Duke {
    public void loadList() {
        try {
            Scanner s = new Scanner(super.list); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String str = s.nextLine();
                char type = str.charAt(4);
                boolean isDone = str.charAt(1) == 'x';
                String task = str.substring(7);
                switch (type) {
                    case 'T':
                        taskList.add(new Todo(task, isDone));
                        break;
                    case 'D':
                        taskList.add(new Deadline(task, isDone));
                        break;
                    case 'E':
                        taskList.add(new Event(task, isDone));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oink! File not found :(");
        }
    }

    public void saveList() {
        try {
            FileWriter fw = new FileWriter(super.list);
            for (Task task : taskList) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oink! Something went wrong... I can't save it!");
        }
    }
}
