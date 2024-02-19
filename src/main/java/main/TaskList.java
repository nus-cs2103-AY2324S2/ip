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
    ArrayList<Task> list = new ArrayList<>(100);;

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
        String[] splitLine = str.split("\n");
        for (String x :splitLine) {
            String[] splitStr = x.split("\\|");
            addListCase(splitStr);
        }
    }

    public void addListCase(String[] str) {
        switch (str[0]) {
            case "E" :
                list.add(new Event(str[2],Integer.parseInt(str[1]) == 1, LocalDate.parse(str[3]), LocalDate.parse(str[4])));
                break;
            case "D" :
                list.add(new Deadline(str[2],Integer.parseInt(str[1]) == 1, LocalDate.parse(str[3])));
                break;
            case "T" :
                list.add(new Todo(str[2],Integer.parseInt(str[1]) == 1));
                break;
        }
    }

    public String printList() {
        String str = "Current Tasks JIAYOUS\n";

        for (int i = 0; i < list.size(); i++) {
            str += String.format("%d. %s\n", i + 1, list.get(i).toString());
        }

        return str;
    }

    public String printList(ArrayList<Task> taskArrayList){
        String str = "Here's what you're looking for :)";

        for (int i = 0; i < taskArrayList.size(); i++) {
            str += String.format("%d. %s\n", i + 1, taskArrayList.get(i).toString());
        }

        return str;
    }

    public ArrayList<Task> findList(String str){
        ArrayList<Task> temp = new ArrayList<>();

        for (Task task : list) {
            if (str.equals(task.getName().trim())) {
                temp.add(task);
            }
        }

        return temp;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }


    /**
     * Given an index, marks the task in the taskList
     * @param index index of list
     */
    public Pair<Boolean, String> mark(int index) {
        try {
            Task curr = list.get(index);
            curr.markMark();

            return new Pair<>(true,"Congrats on completing the task!\n" + curr + "\n");
        } catch (IndexOutOfBoundsException e) {
            return new Pair<>(true,"Index out of bounds :(");
        }
    }



    /**
     * Given an index, uncheck the task in the taskList
     * @param index index of list
     */
    public Pair<Boolean, String> unmark(int index) {
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
        try {
            Task curr = this.list.get(index);
            this.list.remove(index);

            return new Pair<>(true,"byebye task! \n" + curr.toString() + "\n" + noOfTask() + "\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Pair<>(false,"Index out of bounds :(");
        }
    }

    public void addTask(Task task){
        this.list.add(task);
    }

}
