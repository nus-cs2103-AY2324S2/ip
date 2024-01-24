import java.util.ArrayList;

public class Tasks extends Commands {
    public Tasks() {
        tasks = new ArrayList<>();
    }
    public void addTasks(String task) {
        tasks.add(task);
    }

    public void printTasks() {
        System.out.println(line);
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(indentation + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(line);
    }
}
