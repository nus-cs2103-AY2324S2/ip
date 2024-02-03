import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private File myFile;
    private Duke duke;

    public Database(String filePath, Duke duke) {
        myFile = new File(filePath);
        this.duke = duke;
    }

    public void createFile() {
        try {
            myFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String msg) {
        try {
            FileWriter myWritter = new FileWriter(myFile, true);
            myWritter.write(msg + "\n");
            myWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFile() {
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

    public void loadData(ArrayList<String> data) {
        Parser commandParser = new Parser();
        for (String i : data) {
            String[] tokens = i.split("/");
            String command = tokens[0].split(" ")[0];
            try {
                duke.addingTask(command, i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deleteLine(int index) {
        ArrayList<String> data = readFile();
        data.remove(index-1);
        clearFile();
        for (String i : data) {
            writeFile(i);
        }
    }

    public void clearFile() {
        try {
            PrintWriter pw = new PrintWriter(myFile);
            pw.print("");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
