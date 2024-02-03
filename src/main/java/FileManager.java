import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    public static ArrayList<Task> loadFromFile(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
         try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNext()) {
                String[] token = scanner.nextLine().split(" \\| ");
                if (token[0].equals("T")) {
                    ToDo todo = new ToDo(token[2]);
                    if (token[1].equals("X")) {
                        todo.mark();
                    }
                    tasks.add(todo);
                } else if (token[0].equals("D")) {
                    Deadline deadline = new Deadline(token[2], token[3]);
                    if (token[1].equals("X")) {
                        deadline.mark();
                    }
                    tasks.add(deadline);
                } else {
                    String[] time = token[3].split("-");
                    Event event = new Event(token[2], time[0], time[1]);
                    if (token[1].equals("X")) {
                        event.mark();
                    }
                    tasks.add(event);
                }
            }
         } catch (FileNotFoundException e) {
             File dir = new File("./data");
             if(dir.mkdirs()) {
                 System.out.println("Directory 'data' created successfully!");
             }
             File file2 = new File(dir, "riz.txt");
             try {
                 if (file2.createNewFile()) {
                     System.out.println("File 'riz.txt' created successfully!");
                 }
             } catch (IOException ex) {
                 System.out.println("Error creating file...");
             }
         }
         return tasks;
    }


    public static void writeToFile(String filePath, ArrayList<Task> tasks) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                fw.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("There is something wrong: " + e.getMessage());
        }
    }

    public static void printFromFile(String filePath) {
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found...");
        }
    }

    public static int countFromFile(String filePath) {
        File file = new File(filePath);
        int i = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                i++;
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found...");
        }
        return i;
    }
}

