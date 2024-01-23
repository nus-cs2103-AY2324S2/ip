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
            if (msg.equals("bye")){
                break;
            }
            if (msg.equals("list")){
                zhen.print_message(db.toString());
                continue;
            }
            db.insert(msg);
        }
    }
}
class database{
    private ArrayList<String> strlist = new ArrayList<>();
    public void insert(String msg){
        strlist.add(msg);
        zhen.print_message("added: "+msg);
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