import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

        zhen zh = new zhen();
        zh.greeting();
        zh.print_message("Hello! I'm ZHEN\n What can I do for you? ");
        while(true){
            String msg = zh.getuserinput();
            if (msg.equals("bye")){
                break;
            }
            zh.print_message(msg);
        }
        zh.print_message("Bye. Hope to see you again soon");
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
    public void print_message(String msg){
        System.out.println("\n ----------------------------------");
        System.out.println(" "+msg);
        System.out.println("\n ----------------------------------");
    }
    public String getuserinput(){
        return obj.nextLine();
    }
}