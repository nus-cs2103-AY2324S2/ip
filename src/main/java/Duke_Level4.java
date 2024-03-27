import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke_Level4 {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        List<String> list = new ArrayList<>();
        String[] task = new String[100];
        for (int i = 0; i < task.length; i++) {
            task[i] = "[]";
        }
        String[] done = new String[100];
        for (int i = 0; i < done.length; i++) {
            done[i] = "[] ";
        }
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        while(! str.equals("bye")){
            if(str.substring(0, 4).equals("mark")){
                int temp = Integer.parseInt(str.substring(5));
                done[temp] = "[X] ";
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task[temp] + done[temp] + list.get(temp - 1));
            }
            else if(str.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + task[i] + done[i] + list.get(i));
                }
            }
            else if (str.startsWith("todo")) {
                list.add(str.substring(5));
                int lastIndex = list.size() - 1;
                if (lastIndex >= 0) {
                    task[lastIndex] = "[T]";
                    done[lastIndex] = "[] ";
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task[lastIndex] + done[lastIndex] + list.get(lastIndex));
                } else {
                    System.out.println("Got it. I've added this task:");
                    System.out.println("[T][] " + list.get(0));
                }
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
            else if(str.startsWith("event")) {
                String[] splits = str.split("/from");
                task[list.size()] = "[E]";
                done[list.size()] = "[] ";
                int idxfrom = str.indexOf("/from");
                int idxto = str.indexOf("/to");
                String from = str.substring(idxfrom + 6, idxto);
                String to = str.substring(idxto + 4);
                String temp = "(from: " + from + "to: " + to + ")";
                list.add(splits[0] + temp);
                System.out.println("Got it. I've added this task:");
                System.out.println(task[list.size() - 1] + done[list.size() - 1] + list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
            else if(str.startsWith("unmark")) {
                int temp = Integer.parseInt(str.substring(7));
                done[temp] = "[] ";
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task[temp] + done[temp] + list.get(temp - 1));
            }
            else if(str.startsWith("deadline")){
                task[list.size()] = "[D]";
                done[list.size()] = "[] ";
                String temp = " (by: " + str.split("/by")[1].trim() + ")";
                list.add(str.substring(8).split("/by")[0].trim() + temp);
                System.out.println("Got it. I've added this task:");
                System.out.println(task[list.size() - 1] + done[list.size() - 1] + list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
            str = s.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

