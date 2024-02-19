package main;

import Objects.Deadline;
import Objects.Event;
import Objects.Task;
import Objects.Todo;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * An object that has methods to change tasks in the list
 */
public class TaskList {
      ArrayList<Task> list = new ArrayList<Task>(100);;

    public TaskList(String str) {
        addList(str);
    }

    public TaskList() {
    }

    /**
     * Given a string, parses the tasks and adds it into a list
     * @param str String read from file
     */

    public void addList(String str) {
//        Objects.Task task = new Objects.Task(str,false);
//        list.add(task);
//        System.out.println(line+"\nadded : "+str+"\n"+line);
        String[] splitLine = str.split("\n");
        for (String x :splitLine) {
            String[] splitStr = x.split("\\|");
            switch (splitStr[0]) {
                case "E" :
                    list.add(new Event(splitStr[2],Integer.parseInt(splitStr[1]) == 1, LocalDate.parse(splitStr[3]), LocalDate.parse(splitStr[4])));
                    break;
                case "D" :
                    list.add(new Deadline(splitStr[2],Integer.parseInt(splitStr[1]) == 1, LocalDate.parse(splitStr[3])));
                    break;
                case "T" :
                    list.add(new Todo(splitStr[2],Integer.parseInt(splitStr[1]) == 1));
                    break;
            }
        }
    }

    public String printList() {
        if (list.isEmpty()){
            return "list is empty :(";
        }
        String str = "Current Tasks JIAYOUS\n";
        for (int i = 0; i < list.size(); i++) {
            str += String.format("%d. %s\n", i+1, list.get(i).toString());
        }
        return str;
    }

    public String printList(ArrayList<Task> taskArrayList){
        if (taskArrayList.isEmpty()){
            return "Can't find what you're looking for :(";
        }
        String str = "Here's what you're looking for :)\n";
        for (int i = 0; i < taskArrayList.size(); i++) {
            str += String.format("%d. %s\n", i+1, taskArrayList.get(i).toString());
        }
        return str;
    }

    public ArrayList<Task> findList(String str){
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            if (str.equals(list.get(i).getName().trim())){
                temp.add(list.get(i));
            }
        }
        return temp;

    }

    public ArrayList<Task> getList() {
        return list;
    }


    /**
     * Given an index, marks the task in the taskList
     * @param index index of list
     */
    public Pair<Boolean, String> mark(int index) {
        assert index >= 0 && index < list.size() : "Invalid index provided";
        try {
            Task curr = list.get(index);
            curr.markMark();
            return new Pair<Boolean,String>(true,"Congrats on completing the task!\n" + curr.toString() + "\n");
        } catch (IndexOutOfBoundsException e) {
            return new Pair<Boolean,String>(false,"Index out of bounds :(");
        }
    }



    /**
     * Given an index, uncheck the task in the taskList
     * @param index index of list
     */
    public Pair<Boolean, String> unmark(int index) {
        assert index >= 0 && index < list.size() : "Invalid index provided";
        try {
            Task curr = list.get(index);
            curr.unmarkMark();
            return new Pair<>(true,"\nUnmarked the task, :(\n" + curr.toString() + "\n");
        } catch (IndexOutOfBoundsException e){
            return new Pair<>(false,"Index out of bounds :(");
        }
    }

    public String noOfTask() {
        return "You have " + list.size() + " tasks in the list";
    }

    /**
     * Given an index, remove the task from the taskList
     * @param index index of list
     */
    public Pair<Boolean, String> remove(int index) {
        assert list != null : "Task list is null";
        try {
            Task curr = list.get(index);
            int initialSize = list.size();
            list.remove(index);
            assert list.size() == initialSize - 1 : "Task list size did not decrease after removing a task";
            return new Pair<>(true,"byebye task! \n" + curr.toString() + "\n" + noOfTask() + "\n");
        } catch (IndexOutOfBoundsException e) {
            return new Pair<>(false,"Index out of bounds :(");
        }
    }

    public void addTask(Task task){
        list.add(task);
        assert !list.isEmpty() : "Task list is empty after adding a task";
    }

}
