import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo
                = "██████╗  ██████╗ ██╗      █████╗ ███╗   ██╗██████╗\n"
                + "██╔══██╗██╔═══██╗██║     ██╔══██╗████╗  ██║██╔══██╗\n"
                + "██████╔╝██║   ██║██║     ███████║██╔██╗ ██║██║  ██║\n"
                + "██╔══██╗██║   ██║██║     ██╔══██║██║╚██╗██║██║  ██║\n"
                + "██║  ██║╚██████╔╝███████╗██║  ██║██║ ╚████║██████╔╝\n"
                + "╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝\n";
        System.out.println(logo);
        String bot = "[ROLAND] ";
        System.out.println(bot + "Hello! I am ROLAND");
        System.out.println(bot + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        List<Task> taskList  = new ArrayList<>();
        Task task = null;
        while(true){
            String reply = sc.nextLine();

            if (reply.equals("bye")) {
                break;
            } else if (reply.startsWith("list")) {
                System.out.println(bot + "Here are your tasks:");
                for (int i = 1; i < taskList.size()+1; i++) {
                    task = taskList.get(i-1);
                    System.out.println("    " + i + ". " + task.toString());
                }
                continue;
            } else if (reply.startsWith("mark")) {
                int index = Integer.parseInt(reply.replaceAll("[\\D]", ""));
                task = taskList.get(index-1);
                task.markDone();
                System.out.println(bot + task.toString());
                continue;
            } else if (reply.startsWith("unmark")) {
                int index = Integer.parseInt(reply.replaceAll("[\\D]", ""));
                task = taskList.get(index-1);
                task.markUndone();
                System.out.println(bot + task.toString());
                continue;
            } else if (reply.startsWith("todo")) {
                try {
                    if (reply.length() <= 5) {
                        throw new RolandException("Please provide description for todo");
                    }
                    String description = reply.substring(5,reply.length());
                    task = new ToDos(description);
                } catch (RolandException e) {
                    System.out.println(bot + e.getMessage());
                    continue;
                }

            } else if (reply.startsWith("deadline")) {
                try {
                    if (reply.length() <= 9) {
                        throw new RolandException("Please provide description for deadline");
                    }
                    if (reply.split("/").length != 2) {
                        throw new RolandException("Please include when is the deadline by with /by <deadline>");
                    }
                    String split[] = reply.split(" /");
                    String description = split[0].substring(9, split[0].length());
                    String by = split[1].substring(3, split[1].length());
                    task = new Deadlines(description, by);
                } catch (RolandException e) {
                    System.out.println(bot + e.getMessage());
                    continue;
                }


            } else if (reply.startsWith("event")) {
                try {
                    if (reply.length() <= 6) {
                        throw new RolandException("Please provide description for event");
                    }
                    if (reply.split("/").length != 3) {
                        throw new RolandException("Please include when is the start and end of the event with /from <start> /to <end>");
                    }
                    String split[] = reply.split(" /");
                    String description = split[0].substring(6, split[0].length());
                    String from = split[1].substring(5, split[1].length());
                    String to = split[2].substring(4, split[2].length());
                    task = new Events(description, from, to);
                } catch (RolandException e){
                    System.out.println(bot + e.getMessage());
                    continue;
                }


            } else {
                try {
                    throw new RolandException("I do not understand you :(.");

                } catch (RolandException e) {
                    System.out.println(bot + e.getMessage());
                    continue;
                }
            }

            taskList.add(task);
            System.out.println(bot + "I have added " + task.toString() + " to your list of tasks. You have " + taskList.size() + " task(s) in list");

        }


        System.out.println(bot + "Bye. Hope to see you again soon!");
    }
}