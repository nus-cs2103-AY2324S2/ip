import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskConverter {

    public static List<String> saveConvert(ArrayList<Task> taskList){
        List<String> stringList = new ArrayList<>();
        for (Task task : taskList) {
            String saveLine = task.getType() + "%" + task.isDone() + "%" + task.getName() + "%" + task.getTime();
            stringList.add(saveLine);
        }
        return stringList;
    }

    public static ArrayList<Task> loadConvert(List<String> strings) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String str : strings) {
            String[] split = str.split("%");
            if (split[0].equals("[T]")) {
                Task currTask = new Todo(split[2]);
                currTask.setDone(Boolean.parseBoolean(split[1]));
                tasks.add(currTask);
            } else if (split[0].equals("[D]")) {
                Task currTask = new Deadline(split[2], split[3]);
                currTask.setDone(Boolean.parseBoolean(split[1]));
                tasks.add(currTask);
            } else if (split[0].equals("[E]")) {
                Task currTask = makeEvent(split[2], split[3]);
                currTask.setDone(Boolean.parseBoolean(split[1]));
                tasks.add(currTask);
            }
        }
        return tasks;
    }

    public static Event makeEvent(String name, String timeline){
        String[] timeSplit = timeline.split(" ");
        int toIndex = finder("to:", timeSplit);
        String startTime = String.join(" ", Arrays.copyOfRange(timeSplit, 1, toIndex));
        String endTime = String.join(" ", Arrays.copyOfRange(timeSplit, toIndex + 1, timeSplit.length));
        Event current = new Event(name, startTime, endTime);
        return current;
    }

    public static int finder(String checker, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(checker)) {
                return i;
            }
        }
        return -1;
    }
}
