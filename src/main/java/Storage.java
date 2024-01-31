import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeTasks(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : taskList.getTaskList()) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File inputFile = new File("./data/tasks.txt");
            if (inputFile.createNewFile()) {
                // new file created
            } else {
                list = readTasks(inputFile);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    private ArrayList<Task> readTasks(File inputFile) {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner s = new Scanner(inputFile);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String type = line.substring(1, 2);
                String mark = line.substring(4, 5);

                if (type.equals("T")) {
                    String description = line.substring(7);
                    Todo t = new Todo(description);
                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                } else if (type.equals("D")) {
                    String temp = line.substring(7);
                    String[] result = temp.split("\\(by: ");
                    String description  = result[0].trim();
                    String by = result[1].substring(0, result[1].length() - 1);
                    LocalDate date = LocalDate.parse(by.split(" ")[0].trim());
                    LocalTime time = LocalTime.parse(by.split(" ")[1].trim());
                    Deadline t = new Deadline(description, date, time);
                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                } else if (type.equals("E")) {
                    String temp = line.substring(7);
                    String[] result = temp.split("\\(from: ");
                    String description  = result[0];
                    result = result[1].split(" | to: ");
                    String from = result[0];
                    String to = result[1];
                    to = to.substring(0, to.length() - 1);
                    LocalDate startDate = LocalDate.parse(from.split(" ")[0].trim());
                    LocalTime startTime = LocalTime.parse(from.split(" ")[1].trim());
                    LocalDate endDate = LocalDate.parse(to.split(" ")[0].trim());
                    LocalTime endTime = LocalTime.parse(to.split(" ")[1].trim());
                    Event t = new Event(description, startDate, startTime, endDate, endTime);
                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return list;
        }
    }

}
