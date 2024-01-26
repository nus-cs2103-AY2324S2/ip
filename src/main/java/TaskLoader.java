import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


//Class is to load/save tasks from hard drive for us
public class TaskLoader {
    private String filePath;

    public TaskLoader(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> parseAndLoadTasks() throws FileNotFoundException, RyanGoslingException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String pattern = "^\\s*(\\w+)\\s*\\|\\s*(\\w+)\\s*\\|\\s*(.*?)\\s*\\|\\s*(.*?)\\s*\\|\\s*(.*?)\\s*$";
        ArrayList<Task> listOfTasks = new ArrayList<>();
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(currentLine);
            if (!matcher.matches()) {
                throw new RyanGoslingException("Task lists stored in hard drive is not in expected format!");
            } else {
                String typeOfTask = matcher.group(1);
                int isTaskDone = Integer.parseInt(matcher.group(2));
                String taskDescription = matcher.group(3);
                String timeFrom = matcher.group(4);
                String timeTo = matcher.group(5);
                switch (typeOfTask) {
                case "T":
                    listOfTasks.add(new Todo(taskDescription, isTaskDone));
                    break;
                case "D":
                    listOfTasks.add(new Deadline(taskDescription, timeFrom, isTaskDone));
                    break;
                case "E":
                    listOfTasks.add(new Events(taskDescription, timeFrom, timeTo, isTaskDone));
                    break;
                }
            }
        }
        return listOfTasks;
    }

    public void writeToTaskList(ArrayList<Task> taskList) {
        StringBuilder toAdd = new StringBuilder();
        for (int i = 0; i < taskList.size(); i += 1) {
            Task taskToAdd = taskList.get(i);
            toAdd.append(taskToAdd.getTaskType());
            int taskDone = taskToAdd.isTaskDone() ? 1 : 0;
            String[] possibleTimes = taskToAdd.getTimes();
            toAdd.append(" | ").append(taskDone).append(" | ").append(taskToAdd.getTaskName()).append(" | ").append(possibleTimes[0]).append(" | ").append(possibleTimes[1]);
            if (i != taskList.size()-1) {
                 toAdd.append(System.lineSeparator());
            }
        }
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(toAdd.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing! Weird as f");
        }

    }

}
