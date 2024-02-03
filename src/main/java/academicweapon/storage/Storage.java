package academicweapon.storage;

import academicweapon.exceptions.DukeExceptions;
import academicweapon.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;
    public Storage(String filePath) {
        this.filePath = filePath;

        File file = new File(filePath);
        try {
            if (file.exists()) {
                this.file = file;
            } else {
                file.createNewFile();
                this.file = file;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> load() throws DukeExceptions {
        ArrayList<String> lst = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            System.out.println("This is retrieved from the file.");
            while(sc.hasNext()) {
                String currentLine = sc.nextLine();
                DukeExceptions.checkCorruptedFile(currentLine);
                System.out.println(currentLine);
                lst.add(currentLine);
            }
            System.out.println("____________________________________________________________");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, I can't seem to find the file that you want.");
        } catch (DukeExceptions e) {
            throw new DukeExceptions(e.getMessage());
        }
        return lst;
    }

    public void saveFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (Task t: tasks) {
                writer.write(t.fileToString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
