package hirwan;


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The storage class that deals with all interactions with the text file used to store the list of activities
 */
class Storage {
    static List<String> tasks = new ArrayList<>();
    static String FILE_PATH =
            "C:\\Users\\eugen\\Documents\\National University of Singapore\\Y2S2\\CS2103T\\IP\\src\\main\\java\\data\\hirwan.txt";

    /**
     * The read method that reads in the data from the text file that stores the list into the hard disk
     * @return The list of activities that the user has to do in the form of a string list
     */
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

    /**
     * The writeTask method that takes in the list of activities to be stored and updates the text file
     * to match this list
     * @param List The list of activities to store into the hard disk text file
     */
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