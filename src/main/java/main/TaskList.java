package main;

import Objects.Deadline;
import Objects.Event;
import Objects.Task;
import Objects.Todo;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

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
        if (list.isEmpty()){
            return "list is empty :(";
        }
        String str = "Current Tasks JIAYOUS\n";

        for (int i = 0; i < list.size(); i++) {
            str += String.format("%d. %s\n", i + 1, list.get(i).toString());
        }

        return str;
    }

    public String printList(ArrayList<Task> taskArrayList){
        if (taskArrayList.isEmpty()){
            return "Can't find what you're looking for :(";
        }
        String str = "Here's what you're looking for :)\n";

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
        assert index >= 0 && index < list.size() : "Invalid index provided";
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
        this.list.add(task);
        assert !list.isEmpty() : "Task list is empty after adding a task";
    }

    public static String printReminder(ArrayList<Task> reminders){
        if (reminders.isEmpty()){
            return  "No upcoming tasks";
        }
        String str = "REMINDER! Upcoming tasks: \n";
        for (int i = 0; i < reminders.size(); i++) {
            str += String.format("%d. %s\n", i + 1, reminders.get(i).toString());
        }

        return str;

    }
    public String remindTask(){
        LocalDate dateNow = LocalDate.now();
        ArrayList<Task> reminders = new ArrayList<>();
        for (Task task : list){
            if(compareDate(task,dateNow)){
                reminders.add(task);
            }
        }
        return printReminder(reminders);
    }

    public LocalDate reminderDate(Task task){
        if(task instanceof Deadline){
            return ((Deadline) task).getDeadline();
        }else if(task instanceof Event){
            return ((Event) task).getStartDate();
        }
        return null;
    }

    public boolean compareDate(Task task, LocalDate dateNow){
        LocalDate d = reminderDate(task);
        if(d == null){
            return false;
        } else return DAYS.between(dateNow, d) <= 2;
    }

}
