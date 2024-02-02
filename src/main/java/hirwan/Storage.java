package hirwan;


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Storage {
    static List<String> tasks = new ArrayList<>();
    static String FILE_PATH =
            "C:\\Users\\eugen\\Documents\\National University of Singapore\\Y2S2\\CS2103T\\IP\\src\\main\\java\\data\\hirwan.txt";

    public static List<String> read() {
        try {
            File filePath = new File(FILE_PATH);
            Scanner scan = new Scanner(filePath);
            while (scan.hasNext()) {
                tasks.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            Ui.output("Error: file to read data from cannot be found!");
        }
        return tasks;
    }

    public static void writeTask(List<String> List) {
        try {
            FileWriter file = new FileWriter(FILE_PATH);
            for (String tasks : List) {
                file.write(tasks + "\n");
            }
            file.close();
        } catch (IOException e) {
            Ui.output("Error: could not write to file");
            e.printStackTrace();
        }
    }

}