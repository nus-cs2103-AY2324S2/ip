import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private static File f = new File("./data/Duke.txt");
    private ArrayList<Task> storage;
    public Storage() { //constructor
        this.storage = new ArrayList<>();
    }
    public void loadFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasksToLoad  = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String task = scanner.nextLine();
            String[] taskDetails = task.split(" \\| ");
            Task newTask;
            if (taskDetails.length == 3) { //todo task
                newTask = new Task(taskDetails[2]);
            } else if (taskDetails.length == 4) { //deadline
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu HHmm");
                LocalDateTime deadline = LocalDateTime.parse(taskDetails[3], formatter);
                newTask = new Deadlines(taskDetails[2], deadline);
            } else {
                newTask = new Events(taskDetails[2], taskDetails[3], taskDetails[4]);
            }
            if (taskDetails[1].equals("1")) { //if task/event/deadline is marked
                newTask.markDone(false);
            }
            tasksToLoad.add(newTask);
        }
        scanner.close();
        tasksToLoad.forEach(x -> this.storage.add(x));
    }
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void deleteFromFile(File file, int num) throws IOException {
        Scanner s = new Scanner(file);
        String newData = "";
        int count = 0;
        while (s.hasNextLine()) {
            String temp = s.nextLine();
            if (count != num) {
                newData += temp + "\n";
            }
            count++;
        }
        FileWriter fw = new FileWriter(file);
        fw.write(newData);
        fw.close();
    }
    private static void changeMarking(File file, int num, String textToReplace) throws IOException {
        Scanner s = new Scanner(file);
        String newData = "";
        int count = 0;
        while (s.hasNextLine()) {
            String temp = s.nextLine();
            if (count != num) {
                newData += temp + "\n";
            } else {
                newData += textToReplace;
            }
            count++;
        }
        FileWriter fw = new FileWriter(file);
        fw.write(newData);
        fw.close();
    }


    public void addItem(Task task) { //to append items to storage
        this.storage.add(task);
        System.out.print("      ________________________________________________________\n");
        System.out.print("      Got it. I've added this task:\n ");
        task.printFullDesc();
        System.out.printf("      Now you have %d %s in the storage.\n", this.storage.size(), (this.storage.size() == 1 ? "task" : "tasks"));
        System.out.print("      ________________________________________________________\n");
        try {
            writeToFile("./data/Duke.txt", task.toStore());
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }

    public void delete(int num) {
        Task taskToDelete = this.storage.get(num - 1);
        System.out.print("      ________________________________________________________\n");
        System.out.print("      Got it. I've removed this task:\n ");
        taskToDelete.printFullDesc();
        this.storage.remove(num - 1);
        System.out.printf("      Now you have %d %s in the storage.\n", this.storage.size(), (this.storage.size() == 1 ? "task" : "tasks"));
        System.out.print("      ________________________________________________________\n");
        try {
            String toDelete = taskToDelete.toStore();
            deleteFromFile(f, num - 1);
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }

    public void mark(int num) {
        Task toMark = this.storage.get(num - 1);
        toMark.markDone(true);
        String toReplace = toMark.toStore();
        try {
            changeMarking(f, num - 1, toReplace);
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }

    public void unmark(int num) {
        Task toUnmark = this.storage.get(num - 1);
        String toDelete = toUnmark.toStore();
        toUnmark.unmark();
        String toReplace = toUnmark.toStore();
        try {
            changeMarking(f, num - 1, toReplace);
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }

    public void printList() {
        if (this.storage.size() == 0) {
            System.out.print("      ________________________________________________________\n");
            System.out.print("      Currently you have 0 tasks in the storage!\n");
            System.out.print("      ________________________________________________________\n");
        } else {
            for (int i = 0; i < this.storage.size(); i++) {
                if (i < this.storage.size() - 1 && this.storage.size() != 1) { //not last element
                    this.storage.get(i).printTaskDesc(i + 1, false);
                } else {
                    this.storage.get(i).printTaskDesc(i + 1, true);
                }
            }
        }
    }
}
