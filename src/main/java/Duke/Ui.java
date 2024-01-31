package Duke;

import java.util.Scanner;
import Duke.command.*;
import Duke.task.*;
public class Ui{
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
