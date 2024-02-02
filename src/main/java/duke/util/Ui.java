package duke.util;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Ui {
    private final String line = "    ____________________________________________________________";
    private final String indent = "     ";
    private final String name = "Alfred";
    public Ui(){

    }

    public void separate(){
        System.out.println(line);
    }
    public void spacing() {
        separate();
        System.out.println("");
    }

    public void intro() {
        separate();
        System.out.println(indent + "Hello! I'm " + name + "\n     What can I do for you?");
        spacing();
    }
    public void leave() {
        System.out.println(indent + "Bye. Hope to see you again soon!");
        spacing();
    }

    public void showList(ArrayList<String> list){
        separate();
        System.out.println(indent + "Here are the tasks in your list:");
        for (String i : list) {
            System.out.println(indent + i);
        }
        spacing();
    }

    public void markTask(Task currTask){
        separate();
        System.out.println(indent + "Nice! I've marked this task as done:");
        System.out.println(indent + currTask.getStatus());
        spacing();
    }

    public void unmarkTask(Task currTask){
        separate();
        System.out.println(indent + "OK, I've marked this task as not done yet:");
        System.out.println(indent + currTask.getStatus());
        spacing();
    }
    public void delete(int index, TaskList list){
        Task currTask = list.getTask(index);
        separate();
        System.out.println(indent + "Noted. I've removed this task:");
        System.out.println(indent + "  " + currTask.getStatus());
        list.deleteTask(index);
        System.out.println(indent + "Now you have " + Integer.toString(list.size()) +
                " tasks in the list.");
        spacing();
    }

    public void addTask(Task currTask, TaskList list){
        separate();
        System.out.println(indent + "Got it. I've added this task:");
        System.out.println(indent + "  " + currTask.getStatus());
        System.out.println(indent + "Now you have " + Integer.toString(list.size()) +
                " tasks in the list.");
        spacing();
    }

    public void bye(){
        separate();
        leave();
    }

    public void showError(String e){
        separate();
        System.out.println(indent + e);
        spacing();
    }

    public void showFilteredList(String keyword, TaskList tasks){
        separate();
        System.out.println(indent + "Here are the matching tasks in your list:");
        ArrayList<String> list = tasks.find(keyword);
        for (String i : list) {
            System.out.println(indent + i);
        }
        spacing();
    }
}
