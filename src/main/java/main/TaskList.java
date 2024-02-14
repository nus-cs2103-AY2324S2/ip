package main;

import Objects.Deadline;
import Objects.Event;
import Objects.Task;
import Objects.Todo;

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

<<<<<<< HEAD
    /**
     * Given a string, parses the tasks and adds it into a list
     * @param str String read from file
     */
    public void addList(String str){
=======
    public void addList(String str) {
>>>>>>> master
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

    public void printList() {
        Ui.showLine();
        System.out.println("Current Tasks JIAYOUS");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i+1, list.get(i).toString());
        }
        Ui.showLine();
<<<<<<< HEAD
=======
    }

    public void printList(ArrayList<Task> taskArrayList){
        Ui.showLine();
        System.out.println("Here's what you're looking for :)");
        for (int i = 0; i < taskArrayList.size(); i++){
            System.out.printf("%d. %s\n",i+1,taskArrayList.get(i).toString());
        }
        Ui.showLine();
    }

    public ArrayList<Task> findList(String str){
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            if (str.equals(list.get(i).getName().trim())){
                temp.add(list.get(i));
            }
        }
        return temp;
>>>>>>> master
    }

    public ArrayList<Task> getList() {
        return list;
    }

<<<<<<< HEAD
    /**
     * Given an index, marks the task in the taskList
     * @param index index of list
     */
    public void mark(int index){
=======
    public void mark(int index) {
>>>>>>> master
        try {
            Task curr = list.get(index);
            curr.markMark();
            Ui.showLine();
            System.out.println("\nCongrats on completing the task!\n" + curr.toString() + "\n");
            Ui.showLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds :(");
        }
    }

<<<<<<< HEAD
    /**
     * Given an index, uncheck the task in the taskList
     * @param index index of list
     */
    public void unmark(int index){
=======
    public void unmark(int index) {
>>>>>>> master
        try {
            Task curr = list.get(index);
            curr.unmarkMark();
            Ui.showLine();
            System.out.println("\nUnmarked the task, :(\n" + curr.toString() + "\n");
            Ui.showLine();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Index out of bounds :(");
        }
    }

    public String noOfTask() {
        return "You have " + list.size() + " tasks in the list";
    }

<<<<<<< HEAD
    /**
     * Given an index, remove the task from the taskList
     * @param index index of list
     */
    public void remove(int index){
=======
    public void remove(int index) {
>>>>>>> master
        try {
            Task curr = list.get(index);
            list.remove(index);
            Ui.showLine();
            System.out.println("byebye task! \n" + curr.toString() + "\n" + noOfTask() + "\n");
            Ui.showLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds :(");
        }
    }

}
