import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        zhen zh = new zhen();
        zh.greeting();
        zhen.print_message("Hello! I'm ZHEN\n What can I do for you? ");
//        zh.echo(); // level1
        zh.store_task(); // level2
        zhen.print_message("Bye. Hope to see you again soon");
    }
}
class zhen{
    private Scanner obj = new Scanner(System.in);
    private String logo="ZZZZZ   H   H  EEEEE  N   N\n" +
            "   Z    H   H  E      NN  N\n" +
            "  Z     HHHHH  EEEE   N N N\n" +
            " Z      H   H  E      N  NN\n" +
            "ZZZZZ   H   H  EEEEE  N   N\n";
    public void greeting(){
        System.out.println("Hello from\n" + logo);
    }
    public static void print_message(String msg){
        System.out.println("\n ----------------------------------");
        System.out.println(" "+msg);
        System.out.println("\n ----------------------------------");
    }
    public String getuserinput(){
        return obj.nextLine();
    }
    public void echo(){
        while(true){
            String msg = getuserinput();
            if (msg.equals("bye")){
                break;
            }
            print_message(msg);
        }
    }
    public void store_task(){
        database db = new database();
        while(true){
            try {
                String msg = getuserinput();
                if (msg.equals("bye")) {
                    break;
                } else if (msg.equals("list")) {
                    zhen.print_message(db.toString());
                } else if (msg.length() >= 4 && msg.substring(0, 4).equals("todo")) {
                    db.insert(new Todos(msg.substring(4)));
                } else if (msg.length() >= 8 && msg.substring(0, 8).equals("deadline")) {
                    db.insert(new Deadline(msg.substring(8)));
                } else if (msg.length() >= 5 && msg.substring(0, 5).equals("event")) {
                    db.insert(new Event(msg.substring(5)));
                } else if (msg.length() > 4 && msg.substring(0, 4).equals("mark")) {
                    int number = Integer.parseInt(msg.substring(5));
                    db.mark(number);
                } else if (msg.length() > 6 && msg.substring(0, 6).equals("unmark")) {
                    int number = Integer.parseInt(msg.substring(7));
                    db.unmark(number);
                } else {
                    print_message("OOPS!!! I'm sorry, but I don't know what that means");
                }
            }catch(IllegalArgumentException e){
                print_message(" OOPS!!! The description cannot be empty.");
            }
        }
    }
}
class database{
    private ArrayList<task> strlist = new ArrayList<>();
    public void insert(task tsk){
        strlist.add(tsk);
        zhen.print_message("Got it. I've added this task:\n  "
                +tsk.toString()+"\n "+
                "Now you have "+task.num_task+" tasks in the list.");
    }
    public void mark(int index){
        strlist.get(index-1).mark();
        zhen.print_message("Nice! I've marked this task as done:\n  "
                +strlist.get(index-1));
    }
    public void unmark(int index){
        strlist.get(index-1).unmark();
        zhen.print_message("OK, I've marked this task as not done yet:\n  "
                +strlist.get(index-1));
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
class task{
    private String message;
    private boolean state = false;
    public static int num_task = 0;
    public task(String msg){
        this.message = msg;
        if (msg.length()==0){
            throw new IllegalArgumentException("input can't be empty");
        }
        num_task++;
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
    String date;
    public Deadline(String message){
        super(message);
        process_msg(message);
    }
    private void process_msg(String msg){
        change_message(access_message().substring(0,access_message().lastIndexOf('/')));
        this.date = access_message().substring(access_message().lastIndexOf('/')+4);
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
        return msg+" (by: "+date+")";
    }
}
class Event extends task{
    String from_date;
    String to_date;
    public Event(String message){
//        super(message.substring(6,message.lastIndexOf('/')).substring(0,message.substring(6,message.lastIndexOf('/')).lastIndexOf('/')));
//        String temp =message.substring(6,message.lastIndexOf('/'));
//        this.to_date = message.substring(message.lastIndexOf('/')+4);
//        this.from_date =temp.substring(temp.lastIndexOf('/')+6);
        super(message);
        process_msg(message);
    }
    private void process_msg(String msg){
        String[] strarr = msg.split("/");
        change_message(strarr[0]);
        this.to_date = strarr[2].substring(3);
        this.from_date =strarr[1].substring(5);
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
        return msg+" (from: "+from_date+" to: "+ to_date+")";
    }
}
