import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.IOException;

public class Storage {
    private String filePath;
    private File file;
    public Storage(String filePath, String directoryPath) {
        this.filePath = filePath;
        file = new File(filePath);
        File dir = new File(directoryPath);

        if (!dir.exists()) {
            dir.mkdir();
        }
        // Test if file exists
        try {
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(filePath);
                writer.write("");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> loadSave() {
        ArrayList<Task> resultTasks = new ArrayList<Task>();
        try {
            Scanner storageScanner = new Scanner(this.file);

            while (storageScanner.hasNext()) {
                String storageLine = storageScanner.nextLine();
                String actionType = "none";
                String[] storageArr = storageLine.split("\\|");
                String commandType = storageArr[0].toLowerCase().trim();

                switch (commandType) {
                    case "t":
                        actionType = "todo";
                        break;
                    case "d":
                        actionType = "deadline";
                        break;
                    case "e":
                        actionType = "event";
                        break;
                    default:
                        actionType = "none";
                }

                if (actionType == "none") {
                    // Empty file or invalid
                    break;
                } else if (actionType == "todo") {
                    try {
                        if (storageArr.length < 3) {
                            throw new Exception("Invalid save list");
                        }
                        int markedInt = Integer.valueOf(storageArr[1].trim());
                        boolean marked = markedInt == 0 ? false : true;
                        ToDo newTodo = new ToDo(storageArr[2].trim(), marked);
                        resultTasks.add(newTodo);
                        continue;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        resultTasks = new ArrayList<Task>();
                        break;
                    }
                } else if (actionType == "deadline") {
                    try {
                        if (storageArr.length < 4) {
                            throw new Exception("Invalid save list");
                        }

                        int markedInt = Integer.valueOf(storageArr[1].trim());
                        boolean marked = markedInt == 0 ? false : true;
                        String task = storageArr[2].trim();
                        String deadline = storageArr[3].trim();
                        Deadline newDeadline = new Deadline(task, deadline, marked);
                        resultTasks.add(newDeadline);
                        continue;

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        resultTasks = new ArrayList<Task>();
                        break;
                    }
                } else if (actionType == "event") {
                    try {
                        if (storageArr.length < 5) {
                            throw new Exception("Invalid save list");
                        }

                        int markedInt = Integer.valueOf(storageArr[1].trim());
                        boolean marked = markedInt == 0 ? false : true;
                        String task = storageArr[2].trim();
                        String start = storageArr[3].trim();
                        String end = storageArr[4].trim();
                        Event newEvent = new Event(task, start, end, marked);
                        resultTasks.add(newEvent);
                        continue;

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        resultTasks = new ArrayList<Task>();
                        break;
                    }
                }
            }

        } catch (NoSuchElementException e) {
        } catch (Exception e) {
        } finally {
            return resultTasks;
        }
    }

    public void saveToDisk(TaskList sourceTaskList) {
        ArrayList<Task> saveList = sourceTaskList.getArrayList();
        String content = "";
        for (int i = 0; i < saveList.size(); i++) {
            Task curr = saveList.get(i);
            String currTask = curr.toString();
            content += currTask + System.lineSeparator();
        }

        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
