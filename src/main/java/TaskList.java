import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList(){
        this.list = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> list){
        this.list = list;
    }

    public ArrayList<Task> getList(){
        return this.list;
    }

    public void add(String taskType, String taskName, String date1, String date2){
        if(taskType.equals("todo")){
            Task currentTask = new ToDos(taskName);
            this.list.add(currentTask);
            System.out.println("added new task: " + currentTask);
        }
        else if(taskType.equals("deadline")){
            Task currentTask = new Deadlines(taskName, date1);
            this.list.add(currentTask);
            System.out.println("added new task: " + currentTask);
        }
        else if(taskType.equals("event")){
            Task currentTask = new Events(taskName, date1, date2);
            this.list.add(currentTask);
            System.out.println("added new task: " + currentTask);
        }
    }
    public void delete(String taskName){
        int deleteNumber = Integer.parseInt(taskName);
        System.out.println("removed task: " + this.list.get(deleteNumber - 1));
        this.list.remove(deleteNumber - 1);
        System.out.println("Total number of tasks so far: " + this.list.size());
    }

    public void mark(String taskName){

    }
}

