package Jelly;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

public class TaskList {

    private static String taskAdded = "(˃.˂) Got it. I've added this task:";
    private ArrayList<Task> tasks;

    public TaskList(){

        tasks = new ArrayList<Task>();
    }

    public TaskList(TaskList taskList){ //read from file

       this.tasks = taskList.tasks;
    }

    public Task get(Integer i){

        return tasks.get(i);
    }

    public Integer size(){

        return tasks.size();
    }

    private void parse(){


    }

    public void printListSize(){

        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addDeadline(String name, String deadline, boolean isDone){

        Task task = new Deadline(name, deadline, isDone);
        tasks.add(task);
        System.out.println(taskAdded);
        System.out.println(task);
        printListSize();
    }

    public void addTodo(String name, boolean isDone){

        Task task = new Todo(name, isDone);
        tasks.add(task);
        System.out.println(taskAdded);
        System.out.println(task);
        printListSize();
    }

    public void addEvent(String name, String start, String end, boolean isDone){

        Task task = new Event(name, start, end, isDone);
        tasks.add(task);
        System.out.println(taskAdded);
        System.out.println(task);
        printListSize();
    }

    public void deleteTask(Integer index){

        Task task = tasks.get(index-1);
        tasks.remove(index-1);
        System.out.println("(•̀-•́) Noted, I've removed this task:");
        System.out.println(task);
        printListSize();
    }

    public void markTask(Integer index){

        Task task = tasks.get(index-1);
        task.mark();
        System.out.println("(•̀ᗜ•́) Nice, I've marked this task as done:");
        System.out.println(task);
    }

    public void unmarkTask(Integer index){

        Task task = tasks.get(index-1);
        task.unmark();
        System.out.println("(ᗒᗣᗕ) OK, I've marked this task as not done yet:");
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
