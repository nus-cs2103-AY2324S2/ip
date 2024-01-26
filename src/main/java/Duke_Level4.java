import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke_Level4 {
    public static void main(String[] args) {
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
        while(true){
            if(str.substring(0, 4).equals("mark")){
                int temp = Integer.parseInt(str.substring(5));
                done[temp] = "[X] ";
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task[temp] + done[temp] + list.get(temp - 1));
            }
            else if(str.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= list.size(); i++){
                    System.out.println(i + ". " + task[i] + done[i] + list.get(i - 1));
                }
            }
            else if(str.substring(0, 4).equals("todo")){
                list.add(str.substring(5));
                System.out.println("Got it. I've added this task:");
                task[list.size()] = "[T]";
                done[list.size()] = "[] ";
                System.out.println(task[list.size() - 1] + done[list.size() - 1] + list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
            else if (str.substring(0, 5).equals("event")) {
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
            else if(str.substring(0, 6).equals("unmark")) {
                int temp = Integer.parseInt(str.substring(7));
                done[temp] = "[] ";
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task[temp] + done[temp] + list.get(temp - 1));
            }
            else if(str.substring(0, 8).equals("deadline")){
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
    }
}
