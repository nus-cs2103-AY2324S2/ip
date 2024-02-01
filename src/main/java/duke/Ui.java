package duke;

import java.util.ArrayList;

public class Ui {
    public void printList(TaskList tasks, int size){
        System.out.println("Here are the tasks in your list!");
        for (int j = 0; j < size; j++) {
            int nr = j + 1;
            System.out.println(nr +"." + tasks.getTask(j).toString()+ ".");
        }
    }

    public void printMessage(String msg){
        System.out.println(msg);
    }
}
