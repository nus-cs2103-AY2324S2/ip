import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Data {

    private static ArrayList<String> taskList = new ArrayList<String>();
    private static final String FILE = "./data/duke.txt";

    private File file;
    private File folder;

    public Data(String path){
        file = new File(path);
        folder = new File("./data");
    }

    public void createFolder(){
        if(!folder.exists()){
            folder.mkdir();
        }
    }

    public void createFile(){
        try{
            file.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getTasks() {
        return taskList;
    }

    public  void setTasks() {
        taskList = Duke.instructions;
    }

    public  void loadTasks() {
        Path path = Paths.get(FILE);
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] sentence = line.split(" ", 2);
                    
                    if(sentence.length == 1 ){
                        DukeException error = new DukeException(line);
                        System.out.println(error.toString());
                    }else{
                        String command = sentence[0];
                        if(command.equals("todo") || command.equals("deadline") || command.equals("event")){
                            Duke.addTask(line);
                        }else if(command.equals("mark")||command.equals("unmark")||command.equals("delete")){
                            Duke.modifyTask(line);
                        }else{
                            // any instruction that is not clear that has not been covered
                            DukeException error = new DukeException(line);
                            System.out.println(error.toString());
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks from file: " + e.getMessage());
            }
        }
        
    }

    public void saveTasks() {
        try {
            FileWriter writer = new FileWriter(FILE,true);
            for (String instruction : Duke.instructions) {
                writer.write(instruction + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
