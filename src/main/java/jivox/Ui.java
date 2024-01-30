package jivox;
import jivox.task.Task;
import jivox.task.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public String input(){
        return this.sc.nextLine();
    }

    public void close(){
        this.sc.close();
    }

    public void greet(){
        addDivider();
        System.out.println("Hello! I'm Jivox");
        System.out.println("What can I do for you?");
        addDivider();
    }

    public void showMark(Task t){
        addDivider();
        System.out.println("Nice! , I've marked this task :\n" + t);
        addDivider();
    }

    public void showDelete(Task t, int tasksLeft){
        addDivider();
        System.out.println("Noted. I've removed this task:\n" + t);
        System.out.println("Now you have " + tasksLeft +" Tasks in the List");
        addDivider();
    }

    public void showUnmark(Task t){
        addDivider();
        System.out.println("OK, I've Unmarked this task :\n" + t);
        addDivider();
    }

    public void showAdd(Task t, int numOfTask){
        addDivider();
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + numOfTask +" tasks in the list.");
        addDivider();
    }

    public void showList(TaskList list){
        if(list.getLength() == 0){
            System.out.println("You've No task in the List!");
        }
        else {
            System.out.println("You have Following tasks in your List:- ");
            addDivider();
            for (int i = 0; i < list.getLength(); i++) {
                System.out.println((i + 1) + ". " + list.getTask(i));
            }
            addDivider();
        }
    }

    public void addDivider(){
        System.out.println("============================================================");
    }

    public void exit(){
        addDivider();
        System.out.println("Bye. Hope to see you again soon!");
        addDivider();
    }

    public void showDeadline(TaskList list, LocalDate time){
        System.out.println("You have following Task due on " + time.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ":-");
        for(int i = 0; i < list.getLength(); i++){
            LocalDateTime deadline = list.getTask(i).getDeadline();
            if(deadline != null){
                if(deadline.getMonth() == time.getMonth() && deadline.getYear() == time.getYear() && deadline.getDayOfMonth() == time.getDayOfMonth()){
                    addDivider();
                    System.out.println(list.getTask(i));
                }
            }
        }
        addDivider();
    }

    public void showException(Exception e){
        System.out.println(e.getMessage());
    }
}
