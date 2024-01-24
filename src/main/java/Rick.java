import java.util.ArrayList;
import java.util.Scanner;
public class Rick {
    public static ArrayList<Item> list = new ArrayList<>();
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
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){
                add_to_list(input);
            } else if (input.startsWith("delete") && input.length() == 8 && Character.isDigit(input.charAt(7))){
                delete(Character.getNumericValue(input.charAt(7)));
            } else {
                reply("I don't understand what you are saying... ㅜㅜ");
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
            System.out.println((i+1) + ". " + list.get(i).print());
        }
        System.out.println(divider);
    }

    public static void add_to_list(String arg) {
        Item new_item;
        String new_name = "";
        try {
            if (arg.startsWith("todo ")) {
                new_item = new ToDo(arg.substring(5));
                new_name = new_item.print();
                list.add(new_item);

            } else if (arg.startsWith("deadline ") && arg.contains("/by ")) {
                int i = arg.indexOf("/by ");
                String ddl = arg.substring(i + 4);
                String name = arg.substring(9, i);
                new_item = new Deadline(name, ddl);
                new_name = new_item.print();
                list.add(new_item);
            } else if (arg.startsWith("event ") && arg.contains("/from ") && arg.contains("/to ")) {
                int i = arg.indexOf("/from ");
                int j = arg.indexOf("/to ");
                String name = arg.substring(6, i);
                String from = arg.substring(i + 6, j);
                String to = arg.substring(j + 4);
                new_item = new Event(name, from, to);
                new_name = new_item.print();
                list.add(new_item);
            } else {
                new_name = "Nah. No task was added because your input is wrong :P";
            }
        } catch (RickException e) {
            reply(e.getMessage());
            return;
        } catch (Exception e1) {
            reply("There's something wrong with your input...");
            return;
        }
        String output = "Got it. I've added this task:\n" +
                new_name +
                "\nNow you have " + list.size() + " tasks in the list.";
        reply(output);
    }

    public static void mark(int input) {
        int i = input - 1;
        if (i >= list.size()) {
            reply("Item not found QAQ");
        } else {
            Item item = list.get(i);
            item.mark();
            String output = "Nice! I've marked this task as done:\n"+ item.print();
            reply(output);
        }
    }

    public static void unmark(int input) {
        int i = input - 1;
        if (i >= list.size()) {
            reply("Item not found QAQ");
        } else {
            Item item = list.get(i);
            item.unmark();
            String output = "OK, I've marked this task as not done yet:\n"+ item.print();
            reply(output);
        }
    }

    public static void delete(int i) {
        try {
            Item a = list.remove(i - 1);
            String output = "Noted. I've removed this task:\n" +
                    a.print() +
                    "\nNow you have " + list.size() + " tasks in the list.";
            reply(output);
        } catch (Exception e) {
            reply("Index wrong lah!");
        }
    }
}
