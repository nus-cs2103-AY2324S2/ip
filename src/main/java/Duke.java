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
        String bot = "[ROLAND] ";
        System.out.println(bot + "Hello! I am ROLAND");
        System.out.println(bot + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        List<String> taskList  = new ArrayList<>();
        while(true){
            String reply = sc.nextLine();
            if (reply.equals("bye")) {
                break;
            } else if (reply.startsWith("list")) {
                System.out.println(bot + "Here are your tasks:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(String.format(i+1 + ". " + taskList.get(i)));
                }
                continue;
            }
            taskList.add(reply);
            System.out.println(bot + "added: " + reply);
        }


        System.out.println(bot + "Bye. Hope to see you again soon!");
    }
}