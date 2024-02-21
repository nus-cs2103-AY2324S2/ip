package seedu.mamta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Manages loading and saving of task data to and from file.
 */
public class Storage {

    /**
     * Loads task data from the specified file.
     * @param filePath The path to the file containing task data.
     */
    public static void loadTaskData(String filePath) {
        assert !filePath.isEmpty() : "File path is not empty";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitOutput = line.split("\\|");
                String TaskType = splitOutput[0];
                String TaskContent = splitOutput[2];
                int StartDateIndex = 3;
                int EndDateIndex = 4;

                switch (TaskType) {
                case "T":
                    if (splitOutput[1].equals("X")) {
                        TaskList.addTask(new Todo(true, TaskContent));
                    } else {
                        TaskList.addTask(new Todo(false, TaskContent));
                    }
                    break;
               case "D":
                   if (splitOutput[1].equals("X")) {
                       TaskList.addTask(new Deadline(true, TaskContent,splitOutput[StartDateIndex]));
                   } else {
                       TaskList.addTask(new Deadline(false, TaskContent,splitOutput[StartDateIndex]));
                   }
                   break;
               case "E":
                   if (splitOutput[1].equals("X")) {
                       TaskList.addTask(new Event(true, TaskContent,splitOutput[StartDateIndex], splitOutput[EndDateIndex]));
                   } else {
                       TaskList.addTask(new Event(false, TaskContent,splitOutput[StartDateIndex], splitOutput[EndDateIndex]));
                   }
                   break;
                }
            }
        } catch (IOException e) {
            System.out.println(MamtaException.IOException());
        }
    }

    /**
     * Saves tasks to the specified file.
     * @param filePath The path to the file where tasks will be saved.
     */
    public static void saveTasks(String filePath) {
        assert !filePath.isEmpty() : "File path is not empty";
        //prepare the output to be saved first
        StringBuilder output = new StringBuilder();
        for (Task task: TaskList.getTasks()) {
            output.append(task.toString()).append("\n");
        }
        //save the output
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(String.valueOf(output));
            System.out.println("String has been successfully saved to the file.");
        } catch (IOException e) {
            System.out.println(MamtaException.IOException());
        }

    }


}
