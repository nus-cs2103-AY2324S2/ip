
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Jivox {


    private final DatabaseHandler dbHandler = new DatabaseHandler();

    private final ArrayList<Task> list = dbHandler.load();

    private final Scanner sc = new Scanner(System.in);

    public Jivox(){

    }
    private  enum COMMANDS {
        TODO,DEADLINE,EVENT,
        MARK,UNMARK,DELETE,BYE,LIST
    }
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


    private void mark(int i) throws JivoxException {
        if(i > this.list.size() || i < 0){
            throw new JivoxException("Oops! There are only " + this.list.size() + " Tasks!");
        }
        Task t = this.list.get(i-1);
        t.mark();
        dbHandler.save(this.list);
        addDivider();
        System.out.println("Nice! , I've marked this task as done:\n" + t);
        addDivider();

    }

    private void unmark(int i) throws JivoxException {
        if(i > this.list.size() || i < 0){
            throw new JivoxException("Oops! There are only " + this.list.size() + " Tasks!");
        }
        Task t = this.list.get(i-1);
        t.unmark();
        dbHandler.save(this.list);
        addDivider();
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
        addDivider();
    }

    private COMMANDS getCommandType(String type) throws JivoxException {
        try{
            return COMMANDS.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new JivoxException("Opps! I can't understand your Input, Please try again");
        }
    }

    private void addEvent(String content) throws JivoxException {
        String[] first = content.split("/from");
        if(first.length == 1){
            throw new JivoxException("No time interval (from) received  for the event , Please try again!");
        }
        String[] second = first[1].split("/to");
        if(second.length == 1){
            throw new JivoxException("No time interval received (to) for the event , Please try again!");
        }
        this.list.add(new Event(first[0].trim(),second[0].trim(),second[1].trim()));
        dbHandler.save(this.list);
    }

    private void addTodo(String content) throws DataHandlerException {
        this.list.add(new Todo(content));
        dbHandler.save(this.list);
    }

    private void addDeadline(String content) throws JivoxException {
        String[] in = content.split("/by");
        if(in.length == 1){
            throw new JivoxException("Oooops! Please provide a deadline");
        }
        this.list.add(new Deadline(in[0].trim(),in[1].trim()));
        dbHandler.save(this.list);
    }

    public void add(String type,String description) throws JivoxException {
        switch (type){
            case "todo":
                addTodo(description);
                break;
            case "deadline":
                addDeadline(description);
                break;
            case "event":
                addEvent(description);
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

    public void delete(int i) throws JivoxException {
        if(i > this.list.size() || i < 0){
            throw new JivoxException("Oops! There are only " + this.list.size() + " Tasks!");
        }
        Task t = this.list.get(i-1);
        this.list.remove(i-1);
        addDivider();
        System.out.println(" Noted. I've removed this task:\n" + t);
        System.out.println("Now you have " + this.list.size() +" Tasks in the List");
        addDivider();
    }

    public void addDivider(){
        System.out.println("============================================================");
    }

    public void run(){
        boolean isRunning = true;
        do {
            String rawInput = sc.nextLine();
            String[] input = rawInput.split(" ",2);
            COMMANDS type;
            try {
                type = this.getCommandType(input[0]);
            } catch (JivoxException e){
                System.out.println(e.getMessage());
                continue;
            }
            switch (type){
                case BYE:
                    isRunning = false;
                    sc.close();
                    this.exit();
                    break;
                case DEADLINE:
                    try {

                        this.add("deadline", input[1]);
                    } catch (JivoxException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        if (input.length == 1) {
                            throw new JivoxException("Ooops! Please provide a description!");
                        }
                        this.add("event", input[1]);
                    } catch (JivoxException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case TODO:
                    try {
                        if (input.length == 1) {
                            throw new JivoxException("Ooops! Please provide a description!");
                        }
                        this.add("todo", input[1]);
                    } catch (JivoxException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case MARK:
                    try {
                        if(input.length == 1){
                            throw new JivoxException("Please, provide a task number to mark");
                        }
                        this.mark(Integer.parseInt(input[1]));
                    } catch (JivoxException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case UNMARK:
                    try {
                        if(input.length == 1){
                            throw new JivoxException("Please, provide a task number to mark");
                        }
                        this.unmark(Integer.parseInt(input[1]));
                    } catch (JivoxException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case DELETE:
                    try {
                        if(input.length == 1){
                            throw new JivoxException("Please, provide a task number to delete");
                        }
                        this.delete(Integer.parseInt(input[1]));
                    } catch (JivoxException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case LIST:
                    this.showList();
                    break;
            }
        } while (isRunning);
    }

}
