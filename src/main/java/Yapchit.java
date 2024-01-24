import java.util.ArrayList;
import java.util.Scanner;
import YapchitExceptions.YapchitException;
import YapchitExceptions.InvalidDetailException;
import YapchitExceptions.InvalidKeywordException;

public class Yapchit {
    private ArrayList<Task> list = new ArrayList<>();

    enum Operations {
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        TODO,
        DELETE;
    }

    public static void main(String[] args) {

        Yapchit bot = new Yapchit();

        bot.intro();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!(input.toLowerCase().equals("bye"))){
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
        Operations k;
        try{
            k = Operations.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e){
            throw new InvalidKeywordException("You have entered an invalid keyword. " +
                    "Valid keywords are ['mark', 'unmark', 'deadline', 'todo', 'event', 'bye', 'list', 'delete']");
        }

        switch(k){
            case LIST:
                handleList(parts);
                break;

            case MARK:
                handleMark(parts);
                break;

            case UNMARK:
                handleUnmark(parts);
                break;

            case DELETE:
                handleDelete(parts);
                break;

            case DEADLINE:
                handleDeadline(input);
                break;

            case EVENT:
                handleEvent(input);
                break;

            case TODO:
                handleTodo(input);
                break;

            default:
                throw new InvalidKeywordException("You have entered an invalid keyword. " +
                        "Valid keywords are ['mark', 'unmark', 'deadline', 'todo', 'event', 'bye', 'list', 'delete']");
        }
    }

    private void handleList(String[] parts) throws InvalidDetailException{
        if(parts.length != 1){
            throw new InvalidDetailException("Invalid detail after keyword. Please retry");
        } else {
            line();
            print("\t" + "Here are the tasks in your list:");
            for (int i = 0; i < this.list.size(); i++) {

                int idx = i + 1;
                Task item = this.list.get(i);
                print("\t" + idx + "." + item.toString());
            }
            line();
        }
    }

    private void handleDelete(String[] parts) throws InvalidDetailException{
        if(parts.length != 2){
            throw new InvalidDetailException("Invalid detail after delete. Please retry");
        } else {
            try {
                int num = Integer.parseInt(parts[1]);
                delete(num - 1);
            } catch (Exception e){
                throw new InvalidDetailException("Invalid detail after delete. Please retry");
            }
        }
    }

    private void handleMark(String[] parts) throws InvalidDetailException{
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
    }

    private void handleUnmark(String[] parts) throws InvalidDetailException{
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
    }

    private void handleEvent(String input) throws InvalidDetailException{
        int fromStart = input.indexOf("/from");
        int toStart = input.indexOf("/to");

        if(fromStart == -1 || toStart == -1 || fromStart >= toStart){
            throw new InvalidDetailException("invalid /from and /to parameters. Please retry");
        } else {
            if(6 == fromStart || fromStart + 6 == toStart || toStart + 4 >= input.length()){
                throw new InvalidDetailException("Event description and/or to/from parameters cannot be empty");
            }
            String desc = input.substring(6, fromStart).strip();
            String from = input.substring(fromStart + 6, toStart).strip();
            String to = input.substring(toStart + 4).strip();

            if(desc.length() == 0 || from.length() == 0 || to.length() == 0){
                throw new InvalidDetailException("Event description and/or to/from parameters cannot be empty");
            } else {
                Task t = new Event(desc, from, to);
                addTask(t);
                printTask(t);
            }
        }
    }

    private void handleDeadline(String input) throws InvalidDetailException{
        int byStart = input.indexOf("/by");
        if(byStart == -1){
            throw new InvalidDetailException("Missing 'by' parameter in deadline detail");
        } else {
            if(9 == byStart || byStart + 4 >= input.length()){
                throw new InvalidDetailException("Deadline description and/or by parameter cannot be empty");
            }
            String desc = input.substring(9, byStart).strip();
            String by = input.substring(byStart + 4).strip();

            if(desc.length() == 0 || by.length() == 0){
                throw new InvalidDetailException("Deadline description and/or by parameter cannot be empty");
            } else {
                Task t = new Deadline(desc, by);
                addTask(t);
                printTask(t);
            }
        }
    }

    private void handleTodo(String input) throws  InvalidDetailException{
        if(5 >= input.length()){
            throw new InvalidDetailException("todo description cannot be an empty string. Please retry");
        }
        String desc = input.substring(5).strip();

        if(desc.length() == 0){
            throw new InvalidDetailException("todo description cannot be an empty string. Please retry");
        } else {
            Task t = new ToDo(desc);
            addTask(t);
            printTask(t);
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
        print(intro);
    }

    private void delete(int idx){
        Task t = list.remove(idx);
        line();
        print("\tNoted. I've removed this task:");
        print("\t\t" + t.toString());
        String temp = list.size() == 1 ? "task" : "tasks";
        print("\tNow you have " + list.size() +" " + temp + " in the list");
        line();
    }

    private void mark(int idx, boolean val) throws InvalidDetailException{
        if(idx >= list.size()){
            throw new InvalidDetailException("Invalid item index, please try again");
        } else {
            list.get(idx).updateTag(val);
            line();
            if (val) {
                print("\t" + "Nice! I've marked this task as done:");
                print("\t\t" + list.get(idx).toString());
            } else {
                print("\t" + "OK, I've marked this task as not done yet:");
                print("\t\t" + list.get(idx).toString());
            }
            line();
        }
    }

    private void addTask(Task t){
        list.add(t);
    }
    private void outro(){
        String outro = "\t--------------------------------------------------\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t--------------------------------------------------";

        print(outro);
    }

    private void line(){
        print("\t--------------------------------------------------");
    }

    private void print(Object o){
        System.out.println(o);
    }
}
