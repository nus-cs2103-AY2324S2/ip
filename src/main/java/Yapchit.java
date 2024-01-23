import java.util.ArrayList;
import java.util.Scanner;
import YapchitExceptions.YapchitException;
import YapchitExceptions.InvalidDetailException;
import YapchitExceptions.InvalidKeywordException;

public class Yapchit {
    ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {

        Yapchit bot = new Yapchit();

        bot.intro();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!(input.equals("bye"))){
            try{
                bot.parseInput(input);
            } catch (YapchitException e){
                bot.print(e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }

        bot.outro();
    }

    private void parseInput(String input) throws YapchitException{

        String[] parts = input.split(" ");

        if(parts[0].equals("list")){

            if(parts.length != 1){
                throw new InvalidDetailException("Invalid detail after keyword. Please retry");
            } else {
                printList();
            }

        } else if(parts[0].equals("mark") ){

            if(parts.length != 2){
                throw new InvalidDetailException("Invalid detail after mark. Please retry");
            } else {
                try {
                    int idx = Integer.parseInt(parts[1]);
                    mark(idx - 1, true);
                } catch (Exception e){
                    throw new InvalidDetailException("Invalid detail after mark. Please retry");
                }
            }

        } else if(parts[0].equals("unmark") && parts.length == 2){

            if(parts.length != 2){
                throw new InvalidDetailException("Invalid detail after unmark. Please retry");
            } else {
                try {
                    int num = Integer.parseInt(parts[1]);
                    mark(num - 1, false);
                } catch (Exception e){
                    throw new InvalidDetailException("Invalid detail after unmark. Please retry");
                }
            }

        } else if (parts[0].equals("deadline")){

            int byStart = input.indexOf("/by");
            if(byStart == -1){
                throw new InvalidDetailException("Missing 'by' parameter in deadline detail");
            } else {
                if(9 == byStart || byStart + 4 >= input.length()){
                    throw new InvalidDetailException("Deadline description and/or by parameter cannot be empty");
                }
                String desc = input.substring(9, byStart);
                String by = input.substring(byStart + 4);

                if(desc.strip().length() == 0 || by.strip().length() == 0){
                    throw new InvalidDetailException("Deadline description and/or by parameter cannot be empty");
                } else {
                    Task t = new Deadline(desc, by);
                    addTask(t);
                    printTask(t);
                }
            }
        } else if(parts[0].equals("event")){
            int fromStart = input.indexOf("/from");
            int toStart = input.indexOf("/to");

            if(fromStart == -1 || toStart == -1 || fromStart >= toStart){
                throw new InvalidDetailException("invalid /from and /to parameters. Please retry");
            } else {
                if(6 == fromStart || fromStart + 6 == toStart || toStart + 4 >= input.length()){
                    throw new InvalidDetailException("Event description and/or to/from parameters cannot be empty");
                }
                String desc = input.substring(6, fromStart);
                String from = input.substring(fromStart + 6, toStart);
                String to = input.substring(toStart + 4);

                if(desc.strip().length() == 0 || from.strip().length() == 0 || to.strip().length() == 0){
                    throw new InvalidDetailException("Event description and/or to/from parameters cannot be empty");
                } else {
                    Task t = new Event(desc, from, to);
                    addTask(t);
                    printTask(t);
                }
            }

        } else if(parts[0].equals("todo")){
            if(5 >= input.length()){
                throw new InvalidDetailException("todo description cannot be an empty string. Please retry");
            }
            String desc = input.substring(5);

            if(desc.strip().length() == 0){
                throw new InvalidDetailException("todo description cannot be an empty string. Please retry");
            } else {
                Task t = new ToDo(desc);
                addTask(t);
                printTask(t);
            }
        } else {
            throw new InvalidKeywordException("You have entered an invalid keyword. " +
                    "Valid keywords are ['mark', 'unmark', 'deadline', 'todo', 'event', 'bye', 'list']");
        }
    }

    private void printTask(Task t){
        line();
        print("\tGot it. I've added this task:");
        print("\t\t"+ t.toString());
        String temp = list.size() == 1 ? "task" : "tasks";
        print("\tNow you have " + list.size() +" " + temp + " in the list");
        line();
    }

    private void intro(){
        String intro = "\t--------------------------------------------------\n"
                + "\tHello! I'm Yapchit\n"
                + "\tWhat can I do for you?\n"
                + "\t--------------------------------------------------";
        System.out.println(intro);
    }

    private void printList(){
        System.out.println(	"\t"+"--------------------------------------------------");
        System.out.println(	"\t"+"Here are the tasks in your list:");
        for(int i = 0; i < this.list.size(); i++){

            int idx = i + 1;
            Task item = this.list.get(i);
            System.out.println("\t" + idx + "." + item.toString());
        }
        System.out.println(	"\t"+"--------------------------------------------------");
    }

    private void mark(int idx, boolean val) throws InvalidDetailException{
        if(idx >= list.size()){
            throw new InvalidDetailException("Invalid item index, please try again");
        } else {
            list.get(idx).updateTag(val);
            System.out.println("\t" + "--------------------------------------------------");
            if (val) {
                System.out.println("\t" + "Nice! I've marked this task as done:");
                System.out.println("\t" + list.get(idx).toString());
            } else {
                System.out.println("\t" + "OK, I've marked this task as not done yet:");
                System.out.println("\t" + list.get(idx).toString());
            }
        }
    }

    private void addTask(Task t){
        list.add(t);
    }

    private void outro(){
        String outro = "\t--------------------------------------------------\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t--------------------------------------------------";

        System.out.println(outro);
    }

    private void line(){
        System.out.println("\t--------------------------------------------------");
    }

    private void print(Object o){
        System.out.println(o);
    }
}
