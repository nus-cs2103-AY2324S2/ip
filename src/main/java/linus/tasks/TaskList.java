package linus.tasks;

import java.util.ArrayList;
import java.util.Comparator;

import linus.exceptions.IncorrectFormatException;
import linus.exceptions.LinusCeption;
import linus.exceptions.LoadDataCorruptedException;
import linus.exceptions.NumberOutOfBoundsException;

/**
 * Class for storing all the tasks in the list
 */
public class TaskList {
    
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * 
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * 
     * @return int size of the list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Adds task into the list
     * @param task Task to be added
     */
    public void addNewTask(Task task) {
        list.add(task);
    }

    /**
     * Clears the list, called when file is corrupted
     */
    public void reset() {
        list.clear();
    }

    /**
     * 
     * @param number String of number
     * @return Task that is inputted by the user
     * @throws LinusCeption when number is not recognizeable or outside the range of the list
     */
    public Task getTask(String number) throws LinusCeption {
        try {
            int taskNumber = Integer.parseInt(number) - 1;
            Task task = list.get(taskNumber);
            return task;
        } catch (NumberFormatException e) {
            throw new IncorrectFormatException("The number given is unrecognizable");
        } catch (IndexOutOfBoundsException e) {
            throw new NumberOutOfBoundsException("The number is not in this list!");
        }
    }

    /**
     * 
     * @param number String of number
     * @return Task that is inputted by the user
     * @throws LinusCeption when number is not recognizeable or outside the range of the list
     */
    public Task delete(String number) throws LinusCeption {
            Task task = this.getTask(number);
            int taskNumber = Integer.parseInt(number) - 1;
            list.remove(taskNumber);
            return task;
    }

    /**
     * sorts the list by the comparator
     * @param taskComparator Comparator for task
     */
    public void sort(Comparator<Task> taskComparator) {
        list.sort(taskComparator);
    }

    /**
     * Loads the list from text that was initially from the file
     * @param dataStrings ArrayList of data in String form
     */
    public void loadList(ArrayList<String> dataStrings) throws LinusCeption {
        try {
            for (String line : dataStrings) {
                textToTask(line);
            }
        } catch (LinusCeption e) {
            throw e;
        }
        
    }

    /**
     * Parse data texts from file and add task into Task List
     * @param line String of Task data
     */
    public void textToTask(String line) throws LinusCeption {
        try {
            String[] separate = line.split(";;");
            String taskType = separate[0];
            boolean isDone = separate[1].equals("1") ? true : false;
            String description = separate[2];
            Task task;
    
            switch (taskType) {
                case "T":
                    task = new ToDo(description, isDone);
                    break;
                case "D":
                    String by = separate[3];
                    task = new Deadline(description, by, isDone);
                    break;
                case "E":
                    String from = separate[3];
                    String to = separate[4];
                    task = new Event(description, from, to, isDone);
                    break;
                default:
                    throw new LinusCeption("Task not recognized");
    
            }
            list.add(task);
        } catch (LinusCeption e) {
            throw e;
        } catch (Exception e) {
            throw new LoadDataCorruptedException("File is corrupted");
        } 
        
    }

    /**
     * Create a save format for the data to be saved into a file
     * @return ArrayList of Task data strings
     */
    public ArrayList<String> saveFormat() {
        ArrayList<String> dataToText = new ArrayList<>();
        for (Task task : list) {
            dataToText.add(task.saveFormat());
        }
        return dataToText;
    }

    @Override
    public String toString() {
        String finalString = "";
        for (Task task : list) {
            finalString += task.toString() + "\n";
        }
        return finalString;

    }

}
