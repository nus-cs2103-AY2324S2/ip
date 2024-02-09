package duke.util;

import duke.task.Task;
import duke.task.TaskList;

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

    public String showList(ArrayList<String> list) {
        String total = "";
        String start =  "Here are the tasks in your list:\n" ;
        for (String i : list) {
            total +=  i + "\n";
        }
        return start + total;
    }

    public String markTask(Task currTask){
        String first = ( "Nice! I've marked this task as done:\n");
        String second = ( currTask.getStatus());
        return first + second;
    }

    public String unmarkTask(Task currTask){
        String first = ( "OK, I've marked this task as not done yet:\n");
        String second = ( currTask.getStatus());
        return first + second;
    }
    public String delete(int index, TaskList list){
        Task currTask = list.getTask(index);
        String currStatus = currTask.getStatus();
        list.deleteTask(index);
        String first = ( "Noted. I've removed this task:\n");
        String second = ( "  " + currStatus + "\n");
        String third = ( "Now you have " + Integer.toString(list.size()) +
                " tasks in the list.");
        return first + second + third;
    }

    public String addTask(Task currTask, TaskList list){
        String first = ( "Got it. I've added this task:\n");
        String second = ( "  " + currTask.getStatus() + "\n");
        String third = ( "Now you have " + Integer.toString(list.size()) +
                " tasks in the list.");
        return first + second + third;
    }

    public String bye(){
        return leave();
    }

    public String showError(String e){
        return e;
    }

    public String showFilteredList(String keyword, TaskList tasks){
        String first =  "Here are the matching tasks in your list:\n";
        ArrayList<String> list = tasks.find(keyword);
        String total = "";
        for (String i : list) {
            total += ( i + "\n");
        }
        return first + total;
    }
}
