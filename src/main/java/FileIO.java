import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileIO {

    private static final File FILE = new File("./data/logfile.txt");

    public FileIO() {
    }

    public static void createFile() throws IOException {

        if (!FILE.getParentFile().exists()) {
            FILE.getParentFile().mkdirs();
        }
        if (!FILE.exists()) {
            FILE.createNewFile();
        }
    }

    public List<Task> readFromFile() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Task> l = new ArrayList<>();
        try {
            if (!FILE.exists()) {
                createFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileReader reader = new FileReader(FILE);
        Scanner sc = new Scanner(reader);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] array_split = line.split(" \\| ");
            boolean isDone = array_split[1].trim().equals("1");
            if (array_split[0].equals("T")) {
                ToDo todo = new ToDo(array_split[2]);
                if (isDone) {
                    todo.markDone();
                }
                l.add(todo);
            } else if (array_split[0].equals("D")) {
//                String description = array_split[2];
//                LocalDateTime endTime = LocalDateTime.parse(array_split[3], formatter);
//                Deadline deadline = new Deadline(description, endTime);
//                //Deadline deadline = new Deadline(array_split[2], LocalDateTime.parse(array_split[3]));
//                if (array_split[1].equals("1")) {
//                    deadline.markDone();
//                }
//                l.add(deadline);
                String description = array_split[2];
                LocalDateTime endTime;
                try {
                    endTime = LocalDateTime.parse(array_split[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                } catch (DateTimeParseException e) {
                    LocalDate date = LocalDate.parse(array_split[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    endTime = date.atStartOfDay();
                }
                Deadline deadline = new Deadline(description, endTime);
                if (isDone) {
                    deadline.markDone();
                }
                l.add(deadline);
            } else if (array_split[0].equals("E")) {
                //Event event = new Event(array_split[1], array_split[2], array_split[3]);
                if (array_split[1].equals("1")) {
                    String description = array_split[2];
                    String[] dates = array_split[3].split(" to ");
                    LocalDate startTime = LocalDate.parse(dates[0], formatter);
                    LocalDate endTime = LocalDate.parse(dates[1], formatter);
                    Event event = new Event(description, startTime, endTime);
                    if (isDone) {
                        event.markDone();
                    }
                    l.add(event);
                }
            }
        }
        return l;
    }

//    private static void printFileContents(String filePath) throws FileNotFoundException {
//        File f = new File(filePath);
//        Scanner s = new Scanner(f);
//        while (s.hasNext()) {
//            System.out.println(s.nextLine());
//        }
//    }

//    private static void writeToFile(String filePath, String textToAdd) throws IOException {
//        FileWriter fw = new FileWriter(filePath);
//        fw.write(textToAdd);
//        fw.close();
//    }

//    private static void appendToFile(String filePath, String textToAppend) throws IOException {
//        FileWriter fw = new FileWriter(filePath, true);
//        fw.write(textToAppend);
//        fw.close();
//    }


//    protected void saveToFile(List<Task> l) throws IOException {
//        createFile();
//
//        try (FileWriter fw = new FileWriter(FILE, false)) {
//            for (Task task : l) {
//                String taskString = formatTaskForFile(task) + System.lineSeparator();
//                fw.write(taskString);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//    private String formatTaskForFile(Task task) {
//        String status = task.getStatusIcon();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        if (task instanceof ToDo) {
//            return "T | " + status + " | " + task.description;
//        }
//        else if (task instanceof Deadline) {
//            Deadline deadline = (Deadline) task;
//            String formattedEndTime = deadline.endTime.format(formatter);
//            return "D | " + status + " | " + deadline.description + " | " + formattedEndTime;
//        }
//        else if (task instanceof Event) {
//            Event event = (Event) task;
//            String formattedStartTime = event.startTime.format(formatter);
//            String formattedEndTime = event.endTime.format(formatter);
//            return "E | " + status + " | " + event.description + " | " + formattedStartTime + " to " + formattedEndTime;
//            // return "E | " + status + " | " + task.description + " | " + ((Event) task).startTime + "-" + ((Event) task).endTime + task.getExactTime();
//        }
//        return "";
//    }




    private String formatTaskForFile(Task task) {
        String status = task.getStatusIcon();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (task instanceof ToDo) {
            return "T | " + status + " | " + task.description;
        }
        else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String formattedEndTime = deadline.endTime.format(formatter);
            return "D | " + status + " | " + deadline.description + " | " + formattedEndTime;
        }
        else if (task instanceof Event) {
            Event event = (Event) task;
            String formattedStartTime = event.startTime.format(formatter);
            String formattedEndTime = event.endTime.format(formatter);
            return "E | " + status + " | " + event.description + " | " + formattedStartTime + " to " + formattedEndTime;
            // return "E | " + status + " | " + task.description + " | " + ((Event) task).startTime + "-" + ((Event) task).endTime + task.getExactTime();
        }
        return "";
    }

    public void saveToFile(List<Task> l) throws IOException {
        createFile();

        try (FileWriter fw = new FileWriter(FILE, false)) {
            for (Task task : l) {
                String taskString = formatTaskForFile(task) + System.lineSeparator();
                fw.write(taskString);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
