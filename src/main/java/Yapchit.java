import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Yapchit {
    ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {

        Yapchit bot = new Yapchit();

        bot.intro();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!(input.equals("bye"))){
            bot.parseInput(input);
            input = scanner.nextLine();
        }

        bot.outro();
    }

    private void parseInput(String input){

        String[] parts = input.split(" ");

        if(parts[0].equals("list")){
            printList();
        } else if(parts[0].equals("mark")){
            int num = Integer.parseInt(parts[1]);
            mark(num - 1, true);
        } else if(parts[0].equals("unmark")){
            int num = Integer.parseInt(parts[1]);
            mark(num - 1, false);
        } else if (parts[0].equals("deadline")){
            int byStart = input.indexOf("/by");
            String desc = input.substring(9, byStart);
            String by = input.substring(byStart + 4);
            Task t = new Deadline(desc, by);
            addTask(t);
            printTask(t);

        } else if(parts[0].equals("event")){
            int fromStart = input.indexOf("/from");
            int toStart = input.indexOf("/to");

            String desc = input.substring(6, fromStart);
            String from = input.substring(fromStart + 6, toStart);
            String to = input.substring(toStart + 4);

            Task t = new Event(desc, from, to);
            addTask(t);
            printTask(t);

        } else if(parts[0].equals("todo")){
            String desc = input.substring(5);
            Task t = new ToDo(desc);
            addTask(t);
            printTask(t);
        } else {
            print("invalid entry, try again");
        }
    }

    private void printTask(Task t){
        line();
        print("\tGot it. I've added this task:");
        print("\t\t"+ t.toString());
        String temp = list.size() == 1 ? "task" : "tasks";
        print("\tNow you have " + list.size() +" " + temp + " in the list");
        line();
    }

    private void intro(){
        String intro = "\t--------------------------------------------------\n"
                + "\tHello! I'm Yapchit\n"
                + "\tWhat can I do for you?\n"
                + "\t--------------------------------------------------";
        System.out.println(intro);
    }

    private void printList(){
        System.out.println(	"\t"+"--------------------------------------------------");
        System.out.println(	"\t"+"Here are the tasks in your list:");
        for(int i = 0; i < this.list.size(); i++){

            int idx = i + 1;
            Task item = this.list.get(i);
            System.out.println("\t" + idx + "." + item.toString());
        }
        System.out.println(	"\t"+"--------------------------------------------------");
    }

    private void mark(int idx, boolean val){
        list.get(idx).updateTag(val);
        System.out.println(	"\t"+"--------------------------------------------------");
        if(val){
            System.out.println(	"\t"+"Nice! I've marked this task as done:");
            System.out.println("\t[X]" + " " + list.get(idx).getName());
        } else {
            System.out.println(	"\t"+"OK, I've marked this task as not done yet:");
            System.out.println("\t[ ]" + " " + list.get(idx).getName());
        }
    }

    private void addTask(Task t){
        list.add(t);
    }

    private void outro(){
        String outro = "\t--------------------------------------------------\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t--------------------------------------------------";

        System.out.println(outro);
    }

    private void line(){
        System.out.println("\t--------------------------------------------------");
    }

    private void print(Object o){
        System.out.println(o);
    }
}
