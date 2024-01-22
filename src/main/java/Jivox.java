import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Jivox {


    ArrayList<Task> list = new ArrayList<>();
    public void greet(){
        addDivider();
        System.out.println(" Hello! I'm Jivox");
        System.out.println(" What can I do for you?");
        addDivider();
    }

    public void exit(){
        addDivider();
        System.out.println(" Bye. Hope to see you again soon!");
        addDivider();
    }

    public void mark(int i){
        Task t = this.list.get(i-1);
        t.mark();
        addDivider();
        System.out.println("Nice! , I've marked this task as done:\n" + t.toString());
        addDivider();

    }

    public void unmark(int i){
        Task t = this.list.get(i-1);
        t.unmark();
        addDivider();
        System.out.println("OK, I've marked this task as not done yet:\n" + t.toString());
        addDivider();
    }

    public void add(String input){
        this.list.add(new Task(input));
        addDivider();
        System.out.println("added: " + input);
        addDivider();
    }

    public void showList(){
        addDivider();
        for(int i = 0; i < this.list.size(); i++){
            System.out.println((i+1) + ". " + this.list.get(i));
        }
        addDivider();
    }

    public void addDivider(){
        System.out.println("============================================================");
    }

    public void run(){
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        do {
            String input = sc.nextLine();
            if(input.equals("bye")){
                isRunning = false;
            } else if(input.equals("list")){
                this.showList();
            } else{
                String[] in = input.split(" ");
                if(in[0].equals("mark")){
                    this.mark(Integer.parseInt(in[1]));
                } else if(in[0].equals("unmark")){
                    this.unmark(Integer.parseInt(in[1]));
                }
                else{
                    this.add(input);
                }
            }
        } while (isRunning);

    }

}
