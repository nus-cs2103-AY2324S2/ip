import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }
    public ArrayList<Task> readFile(File dukeFile) {
        ArrayList<Task> res = new ArrayList<>();
        try {
            Scanner token = new Scanner(dukeFile);
            while (token.hasNextLine()) {
                String line = token.nextLine();
                String[] splits = line.split(" [|] ");
                Commands type = Commands.valueOf(splits[0]);
                Task readTask = null;
                switch (type) {
                    case todo:
                        readTask = new ToDo(splits[1], splits[2]);
                        break;
                    case deadline:
                        readTask = new Deadline(splits[1], splits[2], splits[3]);
                        break;
                    case event:
                        readTask = new Event(splits[1], splits[2], splits[3], splits[4]);
                        break;
                }
                res.add(readTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot locate file please try again");
        }
        return res;
    }
    public void addToWriteFile (File dukeFile, Task task) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dukeFile.toString(), true));
            writer.append(task.writeObject());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occur while writing to file");
        }
    }
    public void rewriteFile (File dukeFile, ArrayList<Task> current) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dukeFile.toString()));
            for (int i = 0; i < current.size(); i++) {
                writer.write(current.get(i).writeObject());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occur while writing to file");
        }
    }
}
