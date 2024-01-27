import java.util.*;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<Task>();
    }

    public void addTask(String name){

        tasks.add(new Task(name));
        System.out.println("added: " + name);
    }

    public void markTask(Integer index){

        Task task = tasks.get(index-1);
        task.mark();
        System.out.println("Nice, I've marked this task as done:");
        System.out.println(task);
    }

    public void unmarkTask(Integer index){

        Task task = tasks.get(index-1);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    @Override
    public String toString(){

        String string = "";

        for(Integer i=1;i<=tasks.size();++i){

            string += i + ". " + tasks.get(i-1);

            if(i != tasks.size()){
                string += "\n";
            }
        }

        return string;
    }
}
