package duke.util;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Ui {
    private final String LINE = "    ____________________________________________________________";
    private final String INDENT = "     ";
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

    public void intro() {
        separate();
        System.out.println(INDENT + "Hello! I'm " + NAME + "\n     What can I do for you?");
        spacing();
    }
    public void leave() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        spacing();
    }

    public void showList(ArrayList<String> list){
        separate();
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (String i : list) {
            System.out.println(INDENT + i);
        }
        spacing();
    }

    public void markTask(Task currTask){
        separate();
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + currTask.getStatus());
        spacing();
    }

    public void unmarkTask(Task currTask){
        separate();
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + currTask.getStatus());
        spacing();
    }
    public void delete(int index, TaskList list){
        Task currTask = list.getTask(index);
        separate();
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + "  " + currTask.getStatus());
        list.deleteTask(index);
        System.out.println(INDENT + "Now you have " + Integer.toString(list.size()) +
                " tasks in the list.");
        spacing();
    }

    public void addTask(Task currTask, TaskList list){
        separate();
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + "  " + currTask.getStatus());
        System.out.println(INDENT + "Now you have " + Integer.toString(list.size()) +
                " tasks in the list.");
        spacing();
    }

    public void bye(){
        separate();
        leave();
    }

    public void showError(String e){
        separate();
        System.out.println(INDENT + e);
        spacing();
    }

    public void showFilteredList(String keyword, TaskList tasks){
        separate();
        System.out.println(INDENT + "Here are the matching tasks in your list:");
        ArrayList<String> list = tasks.find(keyword);
        for (String i : list) {
            System.out.println(INDENT + i);
        }
        spacing();
    }
}
