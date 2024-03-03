package eve.fileStorage;
// will split into this next time

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import eve.tasks.Deadline;
import eve.tasks.Event;
import eve.tasks.Task;
import eve.tasks.Todo;


/*
 * Storage class is used to handle the file storage of the tasks,
 * it contains the methods to load the tasks from the file and to write the tasks into the file
 */
public class Storage {
    // private static final String directoryPath = "./data";
    private static final String filePath = "./data/Eve.txt";
    private static File file;

    public Storage() {
        this.file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // The below functions are adapted from CS2103T website Week 3
    public void loadFileContents(ArrayList<Task> list) throws FileNotFoundException {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Scanner s = new Scanner(filePath); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String temp = s.nextLine();
                    String[] tempyArr = temp.split("|");
                    Task tempTask = null;

                    if (tempyArr[0].equals("T")) {
                        tempTask = new Todo(tempyArr[2], tempyArr[1]);
                        list.add(tempTask);
                    } else if (tempyArr[0].equals("D")) {
                        tempTask = new Deadline(tempyArr[2], tempyArr[3], tempyArr[1]);
                        list.add(tempTask);
                    } else if (tempyArr[0].equals("E")) {
                        tempTask = new Event(tempyArr[2], tempyArr[3], tempyArr[4], tempyArr[1]);
                        list.add(tempTask);
                    } else {
                        //do nth
                    }
                }
                s.close();
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task: tasks) {
                fw.write(task.toStore());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
    // //from CS2103T website
    // try {
    //     printFileContents(filePath);
    // } catch (FileNotFoundException e) {
    //     System.out.println("File not found");
    // }
    // String file2 = "./temp/lines.txt";
    // try {
    //     writeToFile(file2, "first line" + System.lineSeparator() + "second line");
    // } catch (IOException e) {
    //     System.out.println("Something went wrong: " + e.getMessage());
    // }

}
