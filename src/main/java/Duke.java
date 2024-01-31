
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.print_message(e.getMessage());
            }
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
}
class TaskList{
    private ArrayList<task> strlist = new ArrayList<>();
    private int number_task = 0;
    TaskList(ArrayList<task> tasklist){
        this.strlist = tasklist;
        number_task = this.strlist.size();
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
    }
    public String delete(int index){
        String temp = strlist.get(index-1).toString();
        strlist.remove(index-1);
        number_task--;
        return temp;
    }
    public String mark(int index){
        strlist.get(index-1).mark();
        return strlist.get(index-1).toString();
    }
    public String unmark(int index){
        strlist.get(index-1).unmark();
        return strlist.get(index-1).toString();
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
    public Deadline(String message, LocalDate toDate){
        super(message);
        this.date = toDate;
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
    public Event(String message, LocalDate from_date, LocalDate to_date){
        super(message);
        this.from_date = from_date;
        this.to_date = to_date;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(date, formatter);
        return date1;
    }
    public static Command parse(String msg){
            if (msg.equals("bye")) {
                return new ExitCommand();
            } else if (msg.equals("list")) {
                return new ListCommand();
            } else if (msg.length() >= 4 && msg.substring(0, 4).equals("todo")) {
                return new AddCommand(new Todos(msg.substring(4)));

            } else if (msg.length() >= 8 && msg.substring(0, 8).equals("deadline")) {
                String[] strarr = processDeadlineMsg(msg.substring(8));
                return new AddCommand(new Deadline(strarr[0], parseDate(strarr[1])));
            } else if (msg.length() >= 5 && msg.substring(0, 5).equals("event")) {
                String[] strarr = processEventMsg(msg.substring(5));
                return new AddCommand(new Event(strarr[0], parseDate(strarr[1]),parseDate(strarr[2])));
//                return new AddCommand(new Event(msg.substring(5)));
            } else if (msg.length() > 4 && msg.substring(0, 4).equals("mark")) {
                int number = Integer.parseInt(msg.substring(5));
                return new MarkCommand(number);
            } else if (msg.length() > 6 && msg.substring(0, 6).equals("unmark")) {
                int number = Integer.parseInt(msg.substring(7));
                return new UnmarkCommand(number);
            } else if (msg.length() > 6 && msg.substring(0, 6).equals("delete")) {
                int number = Integer.parseInt(msg.substring(7));
                return new DeleteCommand(number);
            } else {
                return new DontknowCommand();
            }
    }
    private static String[] processEventMsg(String msg){
        String[] arr = new String[3];
        String[] strarr = msg.split("/from");
        arr[0] = strarr[0].trim();
        String[] strArr = strarr[1].split("/to");
        arr[1] = strArr[1].trim();
        arr[2]=strArr[0].trim();
        return arr;
    }
    private static String[] processDeadlineMsg(String msg){
        String[] arr = new String[2];
        String[] strarr = msg.split("/by");
        arr[0] = (strarr[0].trim());
        arr[1] = strarr[1].substring(0).trim();
        return arr;
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
            System.out.println("Problem writing to hard disk.");
//            e.printStackTrace();
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
            return new ArrayList<>();
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
        Ui.print_message("OK, I've marked this task as not done yet:\n  "+temp);
        storage.writeDisk(tsks.accessList());
    }
    public boolean isExit(){
        return false;
    }

}
class ExitCommand extends Command{
    public void execute(TaskList tsks, Ui ui, Storage storage){
        Ui.print_message("Bye");
    }
    public boolean isExit(){
        return true;
    }
}
class ListCommand extends Command{
    public void execute(TaskList tsks, Ui ui, Storage storage){
        Ui.print_message(tsks.toString());
    }
    public boolean isExit(){
        return false;
    }
}
class DontknowCommand extends Command{
    public void execute(TaskList tsks, Ui ui, Storage storage){
        Ui.print_message("OOPS!!! I'm sorry, but I don't know what that means");
    }
    public boolean isExit(){
        return false;
    }
}
