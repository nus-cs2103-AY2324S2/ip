package main;

import Objects.Deadline;
import Objects.Event;
import Objects.Task;
import Objects.Todo;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
      ArrayList<Task> list = new ArrayList<Task>(100);;

    public TaskList(String str) {
        addList(str);
    }

    public TaskList() {
    }

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
        String str = "Current Tasks JIAYOUS\n";
        for (int i = 0; i < list.size(); i++) {
            str += String.format("%d. %s\n", i+1, list.get(i).toString());
        }
        return str;
    }

    public String printList(ArrayList<Task> taskArrayList){
        String str = "Here's what you're looking for :)";
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

    public Pair<Boolean, String> mark(int index) {
        try {
            Task curr = list.get(index);
            curr.markMark();
            return new Pair<Boolean,String>(true,"Congrats on completing the task!\n" + curr.toString() + "\n");
        } catch (IndexOutOfBoundsException e) {
            return new Pair<Boolean,String>(true,"Index out of bounds :(");
        }
    }

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

    public Pair<Boolean, String> remove(int index) {
        try {
            Task curr = list.get(index);
            list.remove(index);
            return new Pair<>(true,"byebye task! \n" + curr.toString() + "\n" + noOfTask() + "\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Pair<>(false,"Index out of bounds :(");
        }
    }

    public void addTask(Task task){
        list.add(task);
    }

}
