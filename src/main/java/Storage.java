import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;


public class Storage {
    // Parses file and outputs it as a list
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";

    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public TaskList load() throws FileNotFoundException, IOException {
        TaskList taskList = new TaskList();
        Task task;
        File file = new File(path);
        if (file.exists() == false) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("File creation failed.");
            }
        }

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                // Split by first space
                // System.out.println(sc.nextLine());
                String parts[] = sc.nextLine().split(" ", 3);
                
                if (parts[0].equals(TODO)) {
                    task = new Todo(parts[2], parts[1]);
                } else if (parts[0].equals(DEADLINE)) {
                    task = new Deadline(parts[2], parts[1]);
                } else {
                    task = new Event(parts[2], parts[1]);
                } 
                taskList.addTask(task);
            
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return taskList;
        }
    }
/**
     * Saves task list into a txt file
     * 
     * @param taskList to be saved
     */
    
    public void save(TaskList taskList) throws IOException {
        int length = taskList.getLength();
        String finalOutput = "";
        File file = new File(path);

        for (int i = 0; i < length; i++) {
            finalOutput = finalOutput + taskList.getTask(i).getAttributes() + "\n";
        }

        file.delete();
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(path, false);
        fileWriter.write(finalOutput);
        fileWriter.close();
    }
}
