import java.util.ArrayList;
import java.util.Scanner;
public class Rick {
    public static ArrayList<Item> list = new ArrayList<>();
    public static void main(String[] args) {
        hello();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    exit();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    list();
                } else if (input.startsWith("mark")) {
                    mark(input);
                } else if (input.startsWith("unmark")) {
                    unmark(input);
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    add_to_list(input);
                } else if (input.startsWith("delete")) {
                    delete(input);
                } else {
                    reply("I don't understand what you are saying... ㅜㅜ");
                }
            } catch (RickException e) {
                reply(e.getMessage());
            } catch (Exception e1) {
                reply("ERROR: Congratulations! You have input a message that the developer did not expect. Report this issue here: https://forms.gle/hnnDTA7qYMnhJvQ46.");
            }
        }
    }
    public static void hello() {
        String hello = "Hello! I'm Rick\n"+
                "What can I do for you ?";
        reply(hello);
    }

    public static void reply(String arg) {
        String divider = "____________________________________________________________";
        System.out.println(divider + "\n" + arg + "\n" + divider);
    }

    public static void exit(){
        String exit = "Bye. Hope to see you again soon !";
        reply(exit);
    }
    public static void echo(String args){
        reply(args);
    }

    public static void list() {
        String divider = "____________________________________________________________";
        System.out.println(divider + "\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
        System.out.println("\n" + divider);
    }

    public static void add_to_list(String arg) throws RickException {
        Item new_item;
        String[] splited = arg.split("\\s+");
        int last = splited.length - 1;
        if (splited.length == 1) {
            throw new RickException("What THING do you want to do...");
        }
        try {
            if (splited[0].equals("todo")) {
                new_item = new ToDo(arg.substring(5));
                list.add(new_item);
            } else if (splited[0].equals("deadline")) {
                if (!arg.contains(" /by ") || splited[last].equals("/by")) {
                    throw new RickException("When is it due? You haven't told me!");
                }
                if (splited[1].equals("/by")) {
                    throw new RickException("What's the deadline about?");
                }
                int i = arg.indexOf("/by");
                String ddl = arg.substring(i + 4);
                String name = arg.substring(9, i - 1);
                new_item = new Deadline(name, ddl);
                list.add(new_item);
            } else if (splited[0].equals("event")) {
                if (!arg.contains(" /from ") || !arg.contains(" /to ") || splited[last].equals("/to") || splited[last].equals("/from")) {
                    throw new RickException("WHEN is the event?");
                }
                if (splited[1].equals("/from") || splited[1].equals("/sto")) {
                    throw new RickException("What event is this?");
                }
                int i = arg.indexOf("/from ");
                int j = arg.indexOf("/to ");
                String name = arg.substring(6, i-1);
                String from = arg.substring(i + 6, j-1);
                String to = arg.substring(j + 4);
                new_item = new Event(name, from, to);
                list.add(new_item);
            } else {
                throw new RickException("It seems that you are missing the space in your instruction. Homesick alien?");
            }
        } catch (RickException e) {
            reply(e.getMessage());
            return;
        } catch (Exception e1) {
            reply("ERROR: Congratulations! You have input a message that the developer did not expect. Report this issue here: https://forms.gle/hnnDTA7qYMnhJvQ46.");
            return;
        }
        String output = "Got it. I've added this task:\n" +
                new_item +
                "\nNow you have " + list.size() + " tasks in the list.";
        reply(output);
    }

    public static void mark(String arg) throws RickException {
        String[] splited = arg.split("\\s+");
        if (splited.length != 2 || !Character.isDigit(arg.charAt(5))) {
            throw new RickException("You have to tell me the number to mark. Try 'mark 1'.");
        }
        int i = arg.charAt(5) - 49;
        if (i >= list.size()) {
            throw new RickException("Item not found QAQ");
        } else {
            Item item = list.get(i);
            item.mark();
            String output = "Nice! I've marked this task as done:\n"+ item;
            reply(output);
        }
    }

    public static void unmark(String arg) throws RickException {
        String[] splited = arg.split("\\s+");
        if (splited.length != 2 || !Character.isDigit(arg.charAt(7))) {
            throw new RickException("You have to tell me the number to unmark. Try 'unmark 1'.");
        }
        int i = arg.charAt(7) - 49;
        if (i >= list.size()) {
            throw new RickException("Item not found QAQ");
        } else {
            Item item = list.get(i);
            item.unmark();
            String output = "OK, I've marked this task as not done yet:\n"+ item;
            reply(output);
        }
    }

    public static void delete(String arg) throws RickException {
        String[] splited = arg.split("\\s+");
        if (splited.length != 2 || !Character.isDigit(arg.charAt(7))) {
            throw new RickException("You have to tell me the number to delete. Try 'delete 1'.");
        }
        int i = arg.charAt(7) - 49;
        try {
            Item item = list.remove(i - 1);
            String output = "Noted. I've removed this task:\n" +
                    item +
                    "\nNow you have " + list.size() + " tasks in the list.";
            reply(output);
        } catch (Exception e) {
            reply("Index wrong lah!");
        }
    }
}
