import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String greeting = "___________________________________\n"
            + "Hello! I'm Jinni\n"
            + "What can I do for you?\n"
            + "___________________________________" ;
        
        System.out.println(greeting);
        
        while(true) {
            String inputFromUser = sc.nextLine();
            if ("bye".equals(inputFromUser.toLowerCase())) {
                String bye = "___________________________________\n"
                    + "Bye. Hope to see you again soon!\n"
                    + "___________________________________";
                System.out.println(bye);
                break;
            }
            else if ("list".equals(inputFromUser.toLowerCase())){
                if(list.size() == 0) {
                    System.out.println("\n___________________________________");
                } else {
                    int num = 1;
                    for(String s: list) {
                        String toPrint = num + ". " + s + "\n";
                        num++;
                        System.out.println(toPrint);
                    }
                    System.out.println("___________________________________");
                }
            } else {
                String echo = "added: " + inputFromUser + "\n___________________________________" ;
                System.out.println(echo);
                list.add(inputFromUser);
            }
        }
        sc.close();
    }
}
