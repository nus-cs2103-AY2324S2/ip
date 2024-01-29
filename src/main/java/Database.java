import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private static final String PATH = "./data/saved-data";
    private static File myFile = new File(PATH);

    public static void createFile() {
        try {
            myFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String msg) {
        try {
            FileWriter myWritter = new FileWriter(PATH, true);
            myWritter.write(msg + "\n");
            myWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readFile() {
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
            clearFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void loadData(ArrayList<String> data) {
        for (String i : data) {
            String[] tokens = i.split("/");
            String command = tokens[0].split(" ")[0];
            try {
                Duke.addingTask(command, i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deleteLine(int index) {
        ArrayList<String> data = readFile();
        data.remove(index-1);
        clearFile();
        for (String i : data) {
            writeFile(i);
        }
    }

    public static void clearFile() {
        try {
            PrintWriter pw = new PrintWriter(myFile);
            pw.print("");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
