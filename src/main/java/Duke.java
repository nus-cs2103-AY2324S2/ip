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
        List<Task> taskList  = new ArrayList<>();
        while(true){
            String reply = sc.nextLine();

            if (reply.equals("bye")) {
                break;
            } else if (reply.startsWith("list")) {
                System.out.println(bot + "Here are your tasks:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    System.out.println(String.format(i+1+". ["+task.getStatusIcon()+"] " + task.getDescription() ));
                }
                continue;
            } else if (reply.startsWith("mark")) {
                int index = Integer.parseInt(reply.replaceAll("[\\D]", ""));
                Task task = taskList.get(index-1);
                task.markDone();
                System.out.println(String.format(bot + "I have marked ["+task.getStatusIcon()+"] " + task.getDescription() + " as done") );
                continue;
            } else if (reply.startsWith("unmark")) {
                int index = Integer.parseInt(reply.replaceAll("[\\D]", ""));
                Task task = taskList.get(index-1);
                task.markUndone();
                System.out.println(String.format(bot +"I have marked ["+task.getStatusIcon()+"] " + task.getDescription() + " as not done") );
                continue;
            }

            Task task = new Task(reply);
            taskList.add(task);
            System.out.println(bot + "added: " + task.getDescription());
        }


        System.out.println(bot + "Bye. Hope to see you again soon!");
    }
}