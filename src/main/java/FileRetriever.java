import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileRetriever {

    private static String workingDirectory = System.getProperty("user.dir");
    private static String dataDirectory = workingDirectory + "\\src\\main\\data";
    private static String saveFilePath = workingDirectory + "\\src\\main\\data\\SavedList.txt";
    public static ArrayList<Task> getSavedTasks () {

        File saveFile = new File(saveFilePath);
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            if (fileExists(saveFilePath)) {
                System.out.println("Previous saved list loaded.");
                taskList = getListOfTasks(saveFile);
            } else {
                if (!directoryExists(dataDirectory)) {
                    createDirectory(dataDirectory);
                }

                saveFile.createNewFile();
                System.out.println("File not found. New save file created.");
            }
            return taskList;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void saveTaskList(ArrayList<Task> taskList) throws Exception {
        File saveFile = new File(saveFilePath);
        BufferedWriter writerObj = new BufferedWriter(new FileWriter(saveFile, false));
        // save new
        for (Task task : taskList) {
            //System.out.println(task.getTaskDetails());
            String taskStringToSave = task.convertTaskToSave();
            //System.out.println(taskStringToSave);
            writerObj.write(taskStringToSave + "\n");
        }
        writerObj.close();
        //System.out.println("Task list changes saved");
    }

    public static boolean directoryExists(String directoryPath) throws Exception {
        boolean isExistent;
        File folderDirectory = new File(directoryPath);
        isExistent = folderDirectory.exists() && folderDirectory.isDirectory();
        return isExistent;
    }

    public static File createDirectory(String directoryPath) throws Exception {
        Files.createDirectories(Paths.get(directoryPath));
        return new File(directoryPath);
    }

    public static boolean fileExists(String filePath) throws Exception {
        boolean isExistent;
        File file = new File(filePath);
        isExistent = file.exists();
        return isExistent;
    }

    public static ArrayList<Task> getListOfTasks(File saveFile) throws Exception {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader readerObj = new BufferedReader(new FileReader(saveFile));
        String nextLine;
        while ((nextLine = readerObj.readLine()) != null) {
            String[] nextLineDetails = nextLine.split("\\|");
            String taskType = nextLineDetails[0];
            Task newTask = new Todo();
            switch (taskType) {
            case "T":
                newTask = new Todo();
                newTask = newTask.convertSaveToTask(nextLine);
                break;
            case "D":
                newTask = new Deadline();
                newTask = newTask.convertSaveToTask(nextLine);
                break;
            case "E":
                newTask = new Event();
                newTask = newTask.convertSaveToTask(nextLine);
                break;
            default:
                break;
            }
            taskList.add(newTask);
        }
        return taskList;
    }
}
