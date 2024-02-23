package alfred.util;

import alfred.task.Task;
import alfred.task.TaskList;

import java.util.ArrayList;

public class Ui {
    private final String LINE = "    ____________________________________________________________";
    private final String NAME = "Alfred";
    public Ui(){

    }

    public void separate(){
        System.out.println(LINE);
    }
    public void spacing() {
        separate();
        System.out.println("");
    }

    public String intro() {
        return  "Hello! I'm " + NAME + "\n     What can I do for you?";
    }
    public String leave() {
        return  "Bye. Hope to see you again soon!";

    }

    public ArrayList<String> showList(ArrayList<String> list) {
        assert list != null : "List cannot be null";
        ArrayList<String> total = new ArrayList<>();
        total.add("Here are the tasks in your list:");
        for (String i : list) {
            total.add(i);
        }
        return total;
    }

    public ArrayList<String> markTask(Task currTask){
        ArrayList<String> total = new ArrayList<>();
        total.add( "Nice! I've marked this task as done:");
        total.add( currTask.getStatus());
        return total;
    }

    public ArrayList<String> unmarkTask(Task currTask){
        ArrayList<String> total = new ArrayList<>();
        total.add( "OK, I've marked this task as not done yet:");
        total.add( currTask.getStatus());
        return total;
    }
    public ArrayList<String> delete(int index, TaskList list){
        int initialSize = list.size();
        ArrayList<String> total = new ArrayList<>();
        Task currTask = list.getTask(index);
        String currStatus = currTask.getStatus();
        list.deleteTask(index);

        assert list.size() == initialSize - 1 : "tasks size should decrease by 1";

        total.add( "Noted. I've removed this task:");
        total.add( "  " + currStatus);
        total.add( "Now you have " + Integer.toString(list.size()) +
                " tasks in the list.");
        return total;
    }

    public ArrayList<String> addTask(Task currTask, TaskList list){
        ArrayList<String> total = new ArrayList<>();
        total.add( "Got it. I've added this task:");
        total.add( "  " + currTask.getStatus());
        total.add( "Now you have " + Integer.toString(list.size()) +
                " tasks in the list.");

        return total;
    }

    public ArrayList<String> bye(){
        ArrayList<String> total = new ArrayList<>();
        total.add(leave());
        return total;
    }

    public ArrayList<String> showError(String e){
        ArrayList<String> total = new ArrayList<>();
        total.add(e);
        return total;
    }

    public ArrayList<String> showFilteredList(String keyword, TaskList tasks){
        ArrayList<String> total = new ArrayList<>();
        String first =  "Here are the matching tasks in your list:";
        ArrayList<String> list = tasks.find(keyword);
        total.add(first);
        total.addAll(list);
        return total;
    }

    public ArrayList<String> snooze(Task task) {
        ArrayList<String> total = new ArrayList<>();
        String first = "This task has been delayed by a day:";
        total.add(first);
        total.add( "  " + task.getStatus());
        return total;
    }

    public ArrayList<String> postpone(Task task, int days) {
        ArrayList<String> total = new ArrayList<>();
        String first = "This task has been delayed by " + Integer.toString(days) + " days:";
        total.add(first);
        total.add( "  " + task.getStatus());
        return total;
    }
    public ArrayList<String> reschedule(Task task) {
        ArrayList<String> total = new ArrayList<>();
        String first = "This task has been rescheduled:";
        total.add(first);
        total.add( "  " + task.getStatus());
        return total;
    }
}
