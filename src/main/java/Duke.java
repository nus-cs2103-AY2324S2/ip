import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo
                = "██████╗  ██████╗ ██╗      █████╗ ███╗   ██╗██████╗\n"
                + "██╔══██╗██╔═══██╗██║     ██╔══██╗████╗  ██║██╔══██╗\n"
                + "██████╔╝██║   ██║██║     ███████║██╔██╗ ██║██║  ██║\n"
                + "██╔══██╗██║   ██║██║     ██╔══██║██║╚██╗██║██║  ██║\n"
                + "██║  ██║╚██████╔╝███████╗██║  ██║██║ ╚████║██████╔╝\n"
                + "╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝ \n";
        System.out.println("Hello from\n" + logo);
        String bot = "[ROLAND]: ";
        System.out.println(bot + "Hello! I am ROLAND");
        System.out.println(bot + "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        while(true){
            String reply = sc.nextLine();
            if (reply.equals("bye")) {
                break;
            }
            System.out.println(bot + reply);
        }


        System.out.println(bot + "Bye. Hope to see you again soon!");
    }
}