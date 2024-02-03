import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    
    private static final String FOLDER_PATH = "./tasklist";
    private static final String TASKLIST_PATH = FOLDER_PATH + "/tasklist.txt";
    private TaskList taskList;

    /** 
     * Constructor for Storage
     * 
     * @param taskListPath
     */
    public Storage(String taskListPath) {
        this.taskListPath = taskListPath;
    }

    /**
     * Loads the tasklist from file. Creates the folder and file if they don't exist.
     * 
     * @throws IOException if there is an error loading data from file
     */
    private void loadTasklist() {
        try {
            // Clear existing tasks before loading
            this.taskList.clear();  

            Path taskListPath = Paths.get(TASKLIST_PATH);
            Path folderPath = Paths.get(FOLDER_PATH);

            // Create the folder and file if they don't exist
            if (Files.notExists(folderPath)) { 
                Files.createDirectories(folderPath);
            }
            if (Files.notExists(taskListPath)) {
                Files.createFile(taskListPath);
            }
            
            // Load tasks from file
            ArrayList<String> taskListFromFile = new ArrayList<>(Files.readAllLines(taskListPath));

            // For each task in the file, add it to the taskList
            for (String task : taskListFromFile) {
                String[] taskParts = task.split(" \\| ", 3);
                String taskType = taskParts[0];
                String taskStatus = taskParts[1];
                String taskDescription = taskParts[2];
                
                switch (taskType) {
                case "T":
                    Todo newTodo = new Todo (taskDescription);
                    if (taskStatus.equals("done")) {
                        newTodo.markDone();
                    }
                    this.taskList.add(newTodo);
                    break;
                case "D":
                    String[] deadlineParts = taskDescription.split(" \\(by: ", 2);
                    String deadlineDescription = deadlineParts[0];
                    String deadlineByDateTime = deadlineParts[1].substring(0, deadlineParts[1].length() - 1);
                    
                    Deadline newDeadline = new Deadline (deadlineDescription, deadlineByDateTime);

                    if (taskStatus.equals("done")) {
                        newDeadline.markDone();
                    }
                    this.taskList.add(newDeadline);
                    break;
                case "E":
                    String[] eventParts = taskDescription.split(" \\(from: ", 2);
                    String eventDescription = eventParts[0];

                    String eventAt = eventParts[1].substring(0, eventParts[1].length() - 1);
                    String[] eventAtParts = eventAt.split(", to: ", 2);

                    String eventFrom = eventAtParts[0];
                    String eventTo = eventAtParts[1];

                    Events newEvent = new Events (eventDescription, eventFrom, eventTo);
                    if (taskStatus.equals("done")) {
                        newEvent.markDone();
                    }
                    this.taskList.add(newEvent);
                    break;
                default:
                    System.out.println(taskListFromFile); // debug line
                    System.out.println("Unrecognized task type: " + task);
                    throw new IOException("Error loading data from file: error in loadTasklist() try block");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data from file: error in loadTasklist() catch block");
            e.printStackTrace();
        }
    }

    /**
     * Saves data from taskList into the file
     */
    private void saveTaskListToFile() {
        try {
            Path taskListPath = Paths.get(TASKLIST_PATH);
            ArrayList<String> newTaskList = new ArrayList<>();
            
            for (Task task : this.taskList) {
                newTaskList.add(task.toString());
            }

            Files.write(taskListPath, newTaskList);
        } catch (IOException e) {
            System.out.println("Error saving data to file: error in saveTaskListToFile()");
        }
    }
}
