
import java.io.*;
import java.sql.Array;
import java.sql.DataTruncation;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
//    public static void main(String[] args) {
//        zhen zh = new zhen();
//        zh.greeting();
//        zhen.print_message("Hello! I'm ZHEN\n What can I do for you? ");
////        zh.echo(); // level1
//        zh.store_task(); // level2
//        zhen.print_message("Bye. Hope to see you again soon");
//    }
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
//        try {
            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
//            try {
                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
        }
    }

    public static void main(String[] args) {
        new Duke("database.ser").run();
    }
}
class Ui{
    private Scanner obj = new Scanner(System.in);
    private String logo="ZZZZZ   H   H  EEEEE  N   N\n" +
            "   Z    H   H  E      NN  N\n" +
            "  Z     HHHHH  EEEE   N N N\n" +
            " Z      H   H  E      N  NN\n" +
            "ZZZZZ   H   H  EEEEE  N   N\n";
    public void showWelcome(){
        System.out.println("Hello from\n" + logo);
        print_message("Hello! I'm ZHEN\n What can I do for you? ");
    }
    public static void print_message(String msg){
        System.out.println("\n ----------------------------------");
        System.out.println(" "+msg);
        System.out.println("\n ----------------------------------");
    }
    public String readCommand(){
        return obj.nextLine();
    }

//    public void store_task(){
////        database db = new database();
////        db.readDisk();
//        while(true){
//            try {
//                String msg = getuserinput();
//                if (msg.equals("bye")) {
//                    break;
//                } else if (msg.equals("list")) {
//                    print_message(tasks.toString());
//                } else if (msg.length() >= 4 && msg.substring(0, 4).equals("todo")) {
//                    db.insert(new Todos(msg.substring(4)));
//                } else if (msg.length() >= 8 && msg.substring(0, 8).equals("deadline")) {
//                    db.insert(new Deadline(msg.substring(8)));
//                } else if (msg.length() >= 5 && msg.substring(0, 5).equals("event")) {
//                    db.insert(new Event(msg.substring(5)));
//                } else if (msg.length() > 4 && msg.substring(0, 4).equals("mark")) {
//                    int number = Integer.parseInt(msg.substring(5));
//                    db.mark(number);
//                } else if (msg.length() > 6 && msg.substring(0, 6).equals("unmark")) {
//                    int number = Integer.parseInt(msg.substring(7));
//                    db.unmark(number);
//                } else if (msg.length() > 6 && msg.substring(0, 6).equals("delete")) {
//                    int number = Integer.parseInt(msg.substring(7));
//                    db.delete(number);
//                } else {
//                    print_message("OOPS!!! I'm sorry, but I don't know what that means");
//                }
//            }catch(IllegalArgumentException e){
//                print_message(" OOPS!!! The description cannot be empty.");
//            }
//        }
//    }
}
class TaskList{
    private ArrayList<task> strlist = new ArrayList<>();
    private int number_task = 0;
    TaskList(ArrayList<task> tasklist){
        this.strlist = tasklist;
        number_task = this.strlist.size();
    }
    TaskList(){
    }
    public int accessNumberTask(){
        return number_task;
    }
    public ArrayList<task> accessList(){
        return strlist;
    }
    public void insert(task tsk){
        strlist.add(tsk);
        number_task++;
//        Ui.print_message("Got it. I've added this task:\n  "
//                +tsk.toString()+"\n "+
//                "Now you have "+number_task+" tasks in the list.");
//        Storage.writeDisk();
    }
    public String delete(int index){
        String temp = strlist.get(index-1).toString();
        strlist.remove(index-1);
        number_task--;
        return temp;
//        Ui.print_message("Noted. I've removed this task:\n  "
//                +temp+"\n "+
//                "Now you have "+number_task+" tasks in the list.");
//        writeDisk();
    }
    public String mark(int index){
        strlist.get(index-1).mark();
//        Ui.print_message("Nice! I've marked this task as done:\n  "
//                +strlist.get(index-1));
        return strlist.get(index-1).toString();
//        writeDisk();
    }
    public String unmark(int index){
        strlist.get(index-1).unmark();
//        Ui.print_message("OK, I've marked this task as not done yet:\n  "
//                +strlist.get(index-1));
        return strlist.get(index-1).toString();
//        writeDisk();
    }
    @Override
    public String toString(){
        String str = "";
        for (int i=0; i<strlist.size();i++){
            if (i == strlist.size()-1){
                str = str + (i+1)+". "+ strlist.get(i);
                break;
            }
            str = str + (i+1)+". "+ strlist.get(i)+ "\n ";
        }
        return str;
    }
//    public void writeDisk() {
//        try {
//            FileOutputStream file = new FileOutputStream("database.ser");
//            ObjectOutputStream out = new ObjectOutputStream(file);
//            out.writeObject(strlist);
//            out.close();
//            file.close();
//        }catch(IOException e){
////            System.out.println("Problem writing to hard disk.");
//            e.printStackTrace();
//        }
//    }
//
//    public void readDisk() {
//        try {
//            FileInputStream file = new FileInputStream("database.ser");
//            ObjectInputStream in = new ObjectInputStream(file);
//            strlist = (ArrayList<task>) in.readObject();
//            number_task = strlist.size();
//            in.close();
//            file.close();
//        } catch (Exception e){
//            return;
//        }
//    }
}
class task implements Serializable{
    private String message;
    private boolean state = false;
    public task(String msg){
        this.message = msg;
        if (msg.length()==0){
            throw new IllegalArgumentException("input can't be empty");
        }
    }
    public void mark(){
        state = true;
    }

    public void unmark(){
        state = false;
    }
    protected boolean access_state(){
        return state;
    }
    protected String access_message(){
        return message;
    }
    protected void change_message(String msg){
        this.message = msg;
    }

    @Override
    public String toString(){
        if (state == true){
            return "[X] "+ message;
        }
        else{
            return "[ ] "+ message;
        }
    }
}
class Todos extends task{
    public Todos(String msg){
        super(msg);
    }

    @Override
    public String toString(){
        if (access_state()){
            return "[T][X] "+ access_message();
        }
        else{
            return "[T][ ] "+ access_message();
        }
    }
}
class Deadline extends task{
    LocalDate date;
    public Deadline(String message){
        super(message);
        process_msg(message);
    }
    private void process_msg(String msg){
//        change_message(access_message().substring(0,access_message().lastIndexOf('/')));
//        this.date = access_message().substring(access_message().lastIndexOf('/')+4);
        String[] strarr = msg.split("/by");
        change_message(strarr[0].trim());
        String temp = strarr[1].substring(0).trim();
        this.date =Parser.parseDate(temp);
    }

    @Override
    public String toString(){
        String msg;
        if (access_state()){
            msg = "[D][X] "+ access_message();
        }
        else{
            msg = "[D][ ] "+ access_message();
        }
        return msg+" (by: "+date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))+")";
    }
}
class Event extends task{
    LocalDate from_date;
    LocalDate to_date;
    public Event(String message){
//        super(message.substring(6,message.lastIndexOf('/')).substring(0,message.substring(6,message.lastIndexOf('/')).lastIndexOf('/')));
//        String temp =message.substring(6,message.lastIndexOf('/'));
//        this.to_date = message.substring(message.lastIndexOf('/')+4);
//        this.from_date =temp.substring(temp.lastIndexOf('/')+6);
        super(message);
        process_msg(message);
    }
    private void process_msg(String msg){
        String[] strarr = msg.split("/from");
        change_message(strarr[0].trim());
        String[] strArr = strarr[1].split("/to");
        String temp = strArr[1].trim();
        this.to_date = Parser.parseDate(temp);
        temp=strArr[0].trim();
        this.from_date =Parser.parseDate(temp);
    }

    @Override
    public String toString(){
        String msg;
        if (access_state()){
            msg = "[E][X] "+ access_message();
        }
        else{
            msg = "[E][ ] "+ access_message();
        }
        return msg+" (from: "+from_date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                +" to: "+ to_date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))+")";
    }
}
class Parser{
    public static LocalDate parseDate(String date){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date1 = LocalDate.parse(date, formatter);
//            return date1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return date1;
        }catch(Exception e){
            return null;
        }
    }
    public static Command parse(String msg){
        try {
//            String msg = getuserinput();
            if (msg.equals("bye")) {
//                break;
                return new ExitCommand();
            } else if (msg.equals("list")) {
//                print_message(tasks.toString());
                return new ListCommand();
            } else if (msg.length() >= 4 && msg.substring(0, 4).equals("todo")) {
//                db.insert(new Todos(msg.substring(4)));
                return new AddCommand(new Todos(msg.substring(4)));
            } else if (msg.length() >= 8 && msg.substring(0, 8).equals("deadline")) {
//                db.insert(new Deadline(msg.substring(8)));

                return new AddCommand(new Deadline(msg.substring(8)));
            } else if (msg.length() >= 5 && msg.substring(0, 5).equals("event")) {
//                db.insert(new Event(msg.substring(5)));
                return new AddCommand(new Event(msg.substring(5)));
            } else if (msg.length() > 4 && msg.substring(0, 4).equals("mark")) {
                int number = Integer.parseInt(msg.substring(5));
//                db.mark(number);
                return new MarkCommand(number);
            } else if (msg.length() > 6 && msg.substring(0, 6).equals("unmark")) {
                int number = Integer.parseInt(msg.substring(7));
//                db.unmark(number);
                return new UnmarkCommand(number);
            } else if (msg.length() > 6 && msg.substring(0, 6).equals("delete")) {
                int number = Integer.parseInt(msg.substring(7));
//                db.delete(number);
                return new DeleteCommand(number);
            } else {
//                print_message("OOPS!!! I'm sorry, but I don't know what that means");
                return null;
            }
        }catch(IllegalArgumentException e){
//            print_message(" OOPS!!! The description cannot be empty.");
            return null;
        }
    }
}
class Storage{
    private String filePath;
    Storage(String filePath){
        this.filePath = filePath;
    }
    public void writeDisk(ArrayList<task> strList) {
        try {
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(strList);
            out.close();
            file.close();
        }catch(IOException e){
//            System.out.println("Problem writing to hard disk.");
            e.printStackTrace();
        }
    }
    public ArrayList<task> load() {
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            ArrayList<task> strlist = (ArrayList<task>) in.readObject();
            in.close();
            file.close();
            return strlist;
        } catch (Exception e){
            return null;
        }
    }
}
abstract class Command{
    public abstract void execute(TaskList tsk, Ui ui, Storage storage);
    public abstract boolean isExit();

}
class AddCommand extends Command{
    private task tsk;
    AddCommand(task tsk){
        this.tsk = tsk;
    }
    public void execute(TaskList tsks, Ui ui, Storage storage){

        tsks.insert(this.tsk);
        Ui.print_message("Got it. I've added this task:\n  "
                +tsk.toString()+"\n "+
                "Now you have "+tsks.accessNumberTask()+" tasks in the list.");
        storage.writeDisk(tsks.accessList());
    }
    public boolean isExit(){
        return false;
    }
}

class DeleteCommand extends Command{
    private int deleteIndex;
    DeleteCommand(int index){
        this.deleteIndex = index;
    }
    public void execute(TaskList tsks, Ui ui, Storage storage){
        String temp = tsks.delete(deleteIndex);
        Ui.print_message("Noted. I've removed this task:\n  "
                +temp+"\n "+
                "Now you have "+tsks.accessNumberTask()+" tasks in the list.");
        storage.writeDisk(tsks.accessList());
    }
    public boolean isExit(){
        return false;
    }
}
class MarkCommand extends Command{
    private int markIndex;
    MarkCommand(int index){
        this.markIndex = index;
    }
    public void execute(TaskList tsks, Ui ui, Storage storage){
        String temp = tsks.mark(markIndex);
//        Ui.print_message("Noted. I've removed this task:\n  "
//                +temp+"\n "+
//                "Now you have "+tsks.accessNumberTask()+" tasks in the list.");
        Ui.print_message("Nice! I've marked this task as done:\n  "+ temp);
        storage.writeDisk(tsks.accessList());
    }
    public boolean isExit(){
        return false;
    }
}
class UnmarkCommand extends Command{
    private int unmarkIndex;
    UnmarkCommand(int index){
        this.unmarkIndex = index;
    }
    public void execute(TaskList tsks, Ui ui, Storage storage){
        String temp = tsks.unmark(unmarkIndex);
//        Ui.print_message("Noted. I've removed this task:\n  "
//                +temp+"\n "+
//                "Now you have "+tsks.accessNumberTask()+" tasks in the list.");
//        Ui.print_message("Nice! I've marked this task as done:\n  "+ temp);
        Ui.print_message("OK, I've marked this task as not done yet:\n  "+temp);
        storage.writeDisk(tsks.accessList());
    }
    public boolean isExit(){
        return false;
    }

}
class ExitCommand extends Command{
    public void execute(TaskList tsks, Ui ui, Storage storage){
//        String temp = tsks.unmark(unmarkIndex);
//        Ui.print_message("Noted. I've removed this task:\n  "
//                +temp+"\n "+
//                "Now you have "+tsks.accessNumberTask()+" tasks in the list.");
//        Ui.print_message("Nice! I've marked this task as done:\n  "+ temp);
        Ui.print_message("Bye");
    }
    public boolean isExit(){
        return true;
    }
}
class ListCommand extends Command{
    public void execute(TaskList tsks, Ui ui, Storage storage){
//        String temp = tsks.unmark(unmarkIndex);
//        Ui.print_message("Noted. I've removed this task:\n  "
//                +temp+"\n "+
//                "Now you have "+tsks.accessNumberTask()+" tasks in the list.");
//        Ui.print_message("Nice! I've marked this task as done:\n  "+ temp);
        Ui.print_message(tsks.toString());
    }
    public boolean isExit(){
        return false;
    }
}
