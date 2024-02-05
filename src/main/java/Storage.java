import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class Storage {
    public static void loadTaskData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitOutput = line.split("\\|");

                switch (splitOutput[0]) {
                case "T":
                    if (splitOutput[1].equals("X")) {
                        TaskList.addTask(new Todo(true, splitOutput[2]));
                    } else {
                        TaskList.addTask(new Todo(false, splitOutput[2]));
                    }
                    break;
               case "D":
                   if (splitOutput[1].equals("X")) {
                       TaskList.addTask(new Deadline(true, splitOutput[2],splitOutput[3]));
                   } else {
                       TaskList.addTask(new Deadline(false, splitOutput[2],splitOutput[3]));
                   }
                   break;
               case "E":
                   if (splitOutput[1].equals("X")) {
                       TaskList.addTask(new Event(true, splitOutput[2],splitOutput[3], splitOutput[4]));
                   } else {
                       TaskList.addTask(new Event(false, splitOutput[2],splitOutput[3], splitOutput[4]));
                   }
                   break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTasks(String filePath) {
        //prepare the output to be saved first
        StringBuilder output = new StringBuilder();
        for (Task task: TaskList.getHistory()) {
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
