package handler;
import dukeexecpetions.DeadlineEmptyException;
import dukeexecpetions.InvalidCmd;
import items.Items;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** This class represents the loading, adding and deleting of tasks Sir Duke manages*/
public class DataHandler {
    public enum TaskTag {
        T, // Todo
        D, // Deadline
        E // Event
    }
    public static void loadData(Items items)
            throws IOException {
        File data = new File("data/sirDuke.txt");
        Scanner parser;
        try {
            parser = new Scanner(data);
        } catch (FileNotFoundException e) {
            File dir = new File("data");
            if (dir.exists()) {
                data.createNewFile();
            } else {
                dir.mkdir();
                data.createNewFile();
            }
            parser = new Scanner(data);
        }
        while (parser.hasNext()) {
            // parse through the text
            String line = parser.nextLine();
            String[] inputsToCmd;
            TaskTag tag;
            Boolean isDone;
            try {
                tag = TaskTag.valueOf(String.valueOf(line.charAt(0)));
                // a lot of exceptions can happen here
                // 1. what if another number other than 0 is put in the isDone category?
                // 2. what if the first character is not a task tag?
                // 3. how do we detect that the file is of incorrect format?
                switch (tag) {
                    case D:
                        inputsToCmd = line.split("\\s\\|\\s", 4);
                        isDone = (inputsToCmd[1] == "1");
                        items.add(new Deadline(inputsToCmd[2], isDone, inputsToCmd[3]));
                        break;
                    case E:
                        inputsToCmd = line.split("\\s\\|\\s", 5);
                        isDone = (inputsToCmd[1] == "1");
                        items.add(new Event(inputsToCmd[2], isDone, inputsToCmd[3], inputsToCmd[4]));
                        break;
                    case T:
                        inputsToCmd = line.split("\\s\\|\\s", 3);
                        isDone = (inputsToCmd[1] == "1");
                        items.add(new Todo(inputsToCmd[2], isDone));
                        break;
                }
            } catch (IllegalArgumentException e) {
                // throw invalid format exception
            }
        }
    }

    public static void overWriteItems(String itemsToBeUpdated) throws IOException {
        FileWriter fw = new FileWriter("data/sirDuke.txt");
        fw.write(itemsToBeUpdated);
        fw.close();
    }
}
