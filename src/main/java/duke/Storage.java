package duke;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/data/duke.txt";
    private String filepath;

    public Storage(String filepath){
        this.filepath = filepath;
    }
    public void saveToFile(int size, ArrayList<Task> tasks){
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            for (int i =0; i < size; i++){
                Task task = tasks.get(i);
                writer.println(task.toFileString());
            }
        } catch (IOException e){
            System.out.println("Could not save tasks to file" + e.getMessage());
        }
    }

    public ArrayList<Task> loadFromFile(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        int index = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while((line = reader.readLine()) != null){
                //adding the task from the file to be read
                //index++;
                //tasks.set(index,duke.Task.fromFileString(line));
                tasks.add(Task.fromFileString(line));
                index++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found! Creating new Data File...");
            saveToFile(0, tasks);
        } catch (IOException e){
            System.out.println("Error loading file" + e.getMessage());
        }
        return tasks;

    }

}
