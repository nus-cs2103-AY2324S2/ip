import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import YapchitExceptions.YapchitException;
import YapchitExceptions.InvalidDetailException;
import YapchitExceptions.InvalidKeywordException;
import java.io.File;
import java.io.FileWriter;


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

        String filePath = "./src/main/data/dataStore.txt";

        bot.handleFileImport(filePath);


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
        bot.handleFileUpdate(filePath);
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
                handleDeadline(input, true);
                break;

            case EVENT:
                handleEvent(input, true);
                break;

            case TODO:
                handleTodo(input, true);
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

    private void handleEvent(String input, boolean newTask) throws InvalidDetailException{
        int fromStart = input.indexOf("/from");
        int toStart = input.indexOf("/to");

        char done = '0';

        if(!newTask){
            done = input.charAt(input.length() - 1);
            input = input.substring(0, input.length() - 1);
        }

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
                if(newTask){
                    printTask(t);
                } else {
                    t.updateTag(done == '1' ? true : false);
                }
            }
        }
    }

    private void handleDeadline(String input, boolean newTask) throws InvalidDetailException{

        int byStart = input.indexOf("/by");

        char done = '0';

        if(!newTask){
            done = input.charAt(input.length() - 1);
            input = input.substring(0, input.length() - 1);
        }

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
                if(newTask){
                    printTask(t);
                } else {
                    t.updateTag(done == '1' ? true : false);
                }
            }
        }
    }

    private void handleTodo(String input, boolean newTask) throws  InvalidDetailException{

        char done = '0';

        if(!newTask){
            done = input.charAt(input.length() - 1);
            input = input.substring(0, input.length() - 1);
        }

        if(5 >= input.length()){
            throw new InvalidDetailException("todo description cannot be an empty string. Please retry");
        }
        String desc = input.substring(5).strip();

        if(desc.length() == 0){
            throw new InvalidDetailException("todo description cannot be an empty string. Please retry");
        } else {
            Task t = new ToDo(desc);
            addTask(t);

            if(newTask){
                printTask(t);
            } else {
                t.updateTag(done == '1' ? true : false);
            }
        }
    }

    private void handleFileImport(String filePath) {
        File f = new File(filePath);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e){
            return;
        }

        while (s.hasNext()) {
            String input = s.nextLine();
            String[] parts = input.split(" ");

            try {
                Operations k = Operations.valueOf(parts[0].toUpperCase());
                switch (k) {
                    case EVENT:
                        this.handleEvent(input, false);
                        break;
                    case DEADLINE:
                        this.handleDeadline(input, false);
                        break;
                    case TODO:
                        this.handleTodo(input, false);
                        break;
                }
            } catch (Exception e){
                this.print("Error in parsing file. Some of the contents may be corrupted");
                break;
            }
        }

    }

    private void handleFileUpdate(String filePath){
        String toWrite = "";
        for(Task t : list){
            if(t instanceof ToDo){
                toWrite = toWrite + "todo "+ t.getName() + (t.getTag() == true ? "1" : "0\n");
            }

            if(t instanceof Event){
                toWrite = toWrite
                        + "\nevent "+ t.getName()
                        + " /from " + ((Event) t).getFrom()
                        + " /to " + ((Event) t).getTo()
                        +(t.getTag() == true ? "1" : "0");
            }

            if(t instanceof Deadline){
                toWrite = toWrite
                        + "\nevent "+ t.getName()
                        + " /by " + ((Deadline) t).getBy()
                        +(t.getTag() == true ? "1" : "0");
            }
        }
        File f = new File(filePath);


        File dirCheck = f.getParentFile();
        if(!dirCheck.exists()){
            dirCheck.mkdirs();
        }

        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e){
            print("Error in creating file. " + e.getMessage());
        }

        try{
            this.writeToFile(filePath, toWrite);
        } catch (IOException e){
            print(e.getMessage());
            return;
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

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private void line(){
        print("\t--------------------------------------------------");
    }

    private void print(Object o){
        System.out.println(o);
    }
}
