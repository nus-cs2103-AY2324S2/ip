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
            String msg = getuserinput();
            task tsk = new task(msg);
            if (msg.equals("bye")){
                break;
            }
            if (msg.equals("list")){
                zhen.print_message(db.toString());
                continue;
            }
            if (msg.length()>4 && msg.substring(0,4).equals("mark")){
                int number = Integer.parseInt(msg.substring(5));
                db.mark(number);
                continue;
            }
            if (msg.length()>6 &&msg.substring(0,6).equals("unmark")){
                int number = Integer.parseInt(msg.substring(7));
                db.unmark(number);
                continue;
            }
            db.insert(tsk);
        }
    }
}
class database{
    private ArrayList<task> strlist = new ArrayList<>();
    public void insert(task tsk){
        strlist.add(tsk);
        zhen.print_message("added: "+tsk.toString().substring(4));
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
    public task(String msg){
        this.message = msg;
    }
    public void mark(){
        state = true;
    }
    public void unmark(){
        state = false;
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