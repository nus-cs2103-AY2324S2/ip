import java.util.ArrayList;
import java.util.Scanner;
public class Rick {
    public static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        hello();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                list();
            } else if (input.startsWith("mark ") && input.length() == 6 && Character.isDigit(input.charAt(5))) {
                mark(Character.getNumericValue(input.charAt(5)));
            } else if (input.startsWith("unmark ") && input.length() == 8 && Character.isDigit(input.charAt(7))) {
                unmark(Character.getNumericValue(input.charAt(7)));
            } else {
                add_to_list(input);
            }
        }
    }
    public static void hello() {
        String hello = "Hello! I'm Rick\n"+
                "What can I do for you ?\n";
        reply(hello);
    }

    public static void reply(String arg) {
        String divider = "\n____________________________________________________________\n";
        System.out.println(divider + arg + divider);
    }

    public static void exit(){
        String exit = "Bye. Hope to see you again soon !";
        reply(exit);
    }
    public static void echo(String args){
        reply(args);
    }

    public static void list() {
        String divider = "\n____________________________________________________________\n";
        System.out.println(divider);
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
        System.out.println(divider);
    }

    public static void add_to_list(String arg) {
        list.add("[ ] " + arg);
        String output = "added: " + arg;
        reply(output);
    }

    public static void mark(int input) {
        int i = input - 1;
        if (i >= list.size()) {
            reply("Item not found QAQ");
        } else {
            String item = list.get(i);
            String marked = item.replace("[ ]", "[X]");
            list.set(i, marked);
            String output = "Nice! I've marked this task as done:\n"+ marked;
            reply(output);
        }
    }

    public static void unmark(int input) {
        int i = input - 1;
        if (i >= list.size()) {
            reply("Item not found QAQ");
        } else {
            String item = list.get(i);
            String marked = item.replace("[X]", "[ ]");
            list.set(i, marked);
            String output = "OK, I've marked this task as not done yet:\n"+ marked;
            reply(output);
        }
    }
}
