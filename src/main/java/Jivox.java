
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

    public void mark(int i) throws JivoxException {
        if(i > this.list.size() || i < 0){
            throw new JivoxException("Oops! There are only " + this.list.size() + " Tasks!");
        }
        Task t = this.list.get(i-1);
        t.mark();
        addDivider();
        System.out.println("Nice! , I've marked this task as done:\n" + t);
        addDivider();

    }

    public void unmark(int i) throws JivoxException {
        if(i > this.list.size() || i < 0){
            throw new JivoxException("Oops! There are only " + this.list.size() + " Tasks!");
        }
        Task t = this.list.get(i-1);
        t.unmark();
        addDivider();
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
        addDivider();
    }

    public void add(String type,String description) throws JivoxException {
        switch (type){
            case "todo":
                this.list.add(new Todo(description));
                break;
            case "deadline":
                String[] in = description.split("/by");
                if(in.length == 1){
                    throw new JivoxException("Oooops! Please provide a deadline");
                }
                this.list.add(new Deadline(in[0],in[1]));
                break;
            case "event":
                String[] first = description.split("/from");
                if(first.length == 1){
                    throw new JivoxException("No time interval (from) received  for the event , Please try again!");
                }
                String[] second = first[1].split("/to");
                if(second.length == 1){
                    throw new JivoxException("No time interval received (to) for the event , Please try again!");
                }
                this.list.add(new Event(first[0],second[0],second[1]));
                break;
        }
        addDivider();
        System.out.println("Got it. I've added this task:\n" + this.list.get(this.list.size()-1));
        System.out.println("Now you have " + this.list.size() +" tasks in the list.");
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
                String[] in = input.split(" ",2);
                if(in[0].equals("mark")){
                    try {
                        if(in.length == 1){
                            throw new JivoxException("Please, provide a task number to mark");
                        }
                        this.mark(Integer.parseInt(in[1]));
                    } catch (JivoxException e){
                        System.out.println(e.getMessage());
                    }
                } else if(in[0].equals("unmark")){
                    try {
                        if(in.length == 1){
                            throw new JivoxException("Please, provide a task number to unmark");
                        }
                        this.unmark(Integer.parseInt(in[1]));
                    } catch (JivoxException e){
                        System.out.println(e.getMessage());
                    }
                }
                else{

                    try {
                        if(input.contains("event") || input.contains("todo") || input.contains("deadline")){
                            if(in.length == 1){
                                throw new JivoxException("Ooops! Please provide a description!");
                            }
                            this.add(in[0], in[1]);
                        }else{
                            throw new JivoxException("Ooops! I can't understand your Input , Please try again");
                        }
                    } catch (JivoxException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        } while (isRunning);
    }

}
