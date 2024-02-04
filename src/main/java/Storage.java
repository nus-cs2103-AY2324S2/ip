import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);
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
                    LocalDateTime by = LocalDateTime.parse(token[3], DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
                    Deadline deadline = new Deadline(token[2], by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
                    if (token[1].equals("X")) {
                        deadline.mark();
                    }
                    tasks.add(deadline);
                } else {
                    String[] time = token[3].split(" - ");
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime from = LocalDateTime.parse(time[0], inputFormatter);
                    LocalDateTime to = LocalDateTime.parse(time[1], inputFormatter);
                    Event event = new Event(token[2], from.format(outputFormatter), to.format(outputFormatter));
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


    public void writeToFile(ArrayList<Task> tasks) {
        try (FileWriter fw = new FileWriter(this.filePath)) {
            for (Task task : tasks) {
                fw.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("There is something wrong: " + e.getMessage());
        }
    }

    public void printFromFile() {
        File file = new File(this.filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found...");
        }
    }

    public int countFromFile() {
        File file = new File(this.filePath);
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

