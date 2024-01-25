import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
            String echo = inputFromUser + "\n___________________________________" ;
            System.out.println(echo);
        }
        sc.close();
    }
}
