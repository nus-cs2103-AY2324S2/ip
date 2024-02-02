package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(){
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> t){
        tasks = t;
    }

    public String addTask(Task t) {
        tasks.add(t);
        String a = "Got it. I've added this task:\n";
        String b = tasks.get(tasks.size()-1).toString() + "\n";
        String c = "Now you have " + tasks.size() + " tasks in the list.";
        return a + b + c;
    }

    public String mark(int n) {
        tasks.get(n).setMark();
        String a = "Nice! I've marked this task as done:\n";
        String b = tasks.get(n).toString();
        return a + b;
    }
    public String unMark(int n) {
        tasks.get(n).setUnMark();
        String a = "OK, I've marked this task as not done yet:\n";
        String b = tasks.get(n).toString();
        return a + b;
    }
    public String delete(int n) {
        Task delT = tasks.get(n);
        tasks.remove(n);
        String a = "Noted. I've removed this task:\n";
        String b = delT.toString() + "\n";
        String c = "Now you have " + tasks.size() + " tasks in the list. ";
        return a + b + c;
    }


    public ArrayList<Task> getList(){
        return tasks;
    }

    public String showList() {
        String sl = "";
        for (int i = 0; i < tasks.size(); i++) {
            sl = sl + (i+1) + "." + tasks.get(i).toString() + "\n";
        }
        return sl;
    }

}
