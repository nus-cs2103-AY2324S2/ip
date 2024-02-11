package Jelly;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

public class TaskList {

    private static String taskAdded = "(˃.˂) Got it. I've added this task:";
    private ArrayList<Task> tasks;

    /**
     * Default constructor of TaskList, creates empty TaskList.
     */
    public TaskList(){

        tasks = new ArrayList<Task>();
    }

    /**
     * Copy constructor of TaskList, SHALLOW copies contents of taskList
     * @param taskList TaskList to copy from
     */
    public TaskList(TaskList taskList){ //read from file

       this.tasks = taskList.tasks;
    }

    /**
     * @param i index of element to get
     * @return returns the task at index i
     */
    public Task get(Integer i){

        return tasks.get(i);
    }

    /**
     * @return current number of tasks in the TaskList
     */
    public Integer size(){

        return tasks.size();
    }

    /**
     * Prints message containing number of tasks in the TaskList
     */
    public void printListSize(){

        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * @param name name of deadline
     * @param deadline date/time of deadline
     * @param isDone whether task is done
     */
    public void addDeadline(String name, String deadline, boolean isDone){

        Task task = new Deadline(name, deadline, isDone);
        tasks.add(task);
        System.out.println(taskAdded);
        System.out.println(task);
        printListSize();
    }

    /**
     * @param name name of todo
     * @param isDone whether task is done
     */
    public void addTodo(String name, boolean isDone){

        Task task = new Todo(name, isDone);
        tasks.add(task);
        System.out.println(taskAdded);
        System.out.println(task);
        printListSize();
    }

    /**
     * @param name name of event
     * @param start start date/time of event
     * @param end end date/time of event
     * @param isDone whether task is done
     */
    public void addEvent(String name, String start, String end, boolean isDone){

        Task task = new Event(name, start, end, isDone);
        tasks.add(task);
        System.out.println(taskAdded);
        System.out.println(task);
        printListSize();
    }

    /**
     * @param index index of task to delete
     */
    public void deleteTask(Integer index){

        Task task = tasks.get(index-1);
        tasks.remove(index-1);
        System.out.println("(•-•) Noted, I've removed this task:");
        System.out.println(task);
        printListSize();
    }

    /**
     * @param index index of task to mark as done
     */
    public void markTask(Integer index){

        Task task = tasks.get(index-1);
        task.mark();
        System.out.println("(•ᗜ•) Nice, I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * @param index index of task to mark as not done
     */
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
