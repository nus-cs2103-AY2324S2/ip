package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> task;
    public TaskList(){
        task = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> t){
        task = t;
    }

    public static String addTask(Task t){
        task.add(t);
        String a = "Got it. I've added this task:\n";
        String b = task.get(task.size()-1).toString() + "\n";
        String c = "Now you have " + task.size() + " tasks in the list.";
        return a + b + c;
    }

    public static String mark(int n){ // method to mark task (mark command)
        task.get(n).mark();
        String a = "Nice! I've marked this task as done:\n";
        String b = task.get(n).toString();
        return a + b;
    }
    public static String unMark(int n){ // method to mark task (mark command)
        task.get(n).unMark();
        String a = "OK, I've marked this task as not done yet:\n";
        String b = task.get(n).toString();
        return a + b;
    }
    public static String delete(int n){ // method to mark task (mark command)
        Task delT = task.get(n);
        task.remove(n);
        String a = "Noted. I've removed this task:\n";
        String b = delT.toString() + "\n";
        String c = "Now you have " + task.size() + " tasks in the list. ";
        return a + b + c;
    }


    public static ArrayList<Task> getList(){
        return task;
    }

    public static String showList(){
        String sl = "";
        for (int i = 0 ; i < task.size(); i++){
            sl = sl + (i+1) + "." + task.get(i).toString() + "\n";
        }
        return sl;
    }

}
