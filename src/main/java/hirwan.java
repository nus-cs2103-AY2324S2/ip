import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class hirwan {
    public static void main(String[] args) {
        String logo = "I'm hirwan \n"
                + "_________________________________\n"
                + "what can I do for you? \n"
                + "_________________________________\n";

        System.out.println("Hello! " + logo);
        int count = 1;
        List<String> List = new ArrayList<>();
        List<String> type = new ArrayList<>();

        while(true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();

            boolean mark = text.startsWith("mark");
            boolean unmark = text.startsWith("unmark");
            boolean todo = text.startsWith("todo");
            boolean deadline = text.startsWith("deadline");
            boolean event = text.startsWith("event");

            if (text.equals("bye")) {
                break;
            } else if (text.equals("list")) {
                for (String element : List) {
                    System.out.println(element);
                }
            } else if (todo || deadline || event) {
                String typestr = "";
                if (todo) {
                    try {
                        typestr = "[T]";
                        System.out.println("Got it. I've added this task: \n  " + typestr + "[ ] " + text.substring(5));
                        List.add(count + ". " + typestr + "[ ] " + text.substring(5));
                        type.add(typestr);
                        System.out.println("Now you have " + count + " tasks in the list.");
                        count++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please enter a description for your todo command");
                    }
                } else if (deadline) {
                    try {
                        typestr = "[D]";
                        String delimiter = " /by";
                        int Index = text.indexOf(delimiter);
                        String Day = text.substring(Index + 5);
                        String item = text.substring(9, Index);
                        List.add(count + ". " + typestr + "[ ] " + item + " (by: " + Day + ")");
                        System.out.println("Got it. I've added this task:\n  " + typestr + "[ ] " + item + " (by: " + Day + ")");
                        type.add(typestr);
                        System.out.println("Now you have " + count + " tasks in the list.");
                        count++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please enter a description or date for your deadline command");
                    }
                } else if (event) {
                    try {
                    typestr = "[E]";
                    String delimiterstart = " /from";
                    String delimiterend = " /to"    ;
                    int Indexstart = text.indexOf(delimiterstart);
                    int Indexend = text.indexOf(delimiterend);
                    String start = text.substring(Indexstart + 7, Indexend);
                    String end = text.substring(Indexend + 5);
                    String item =  text.substring(6, Indexstart);
                    List.add(count + ". " + typestr + "[ ] " + item + " (from: " + start + " to: " + end + ")");
                    System.out.println("Got it. I've added this task:\n  " + typestr + "[ ] " + item + " (from: " + start + " to: " + end + ")");
                    type.add(typestr);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    count++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please enter a description or date for your event to command");
                    }
                }
            } else if (mark) {
                String number = text.substring(5);
                int numberint = Integer.parseInt(number);
                if (numberint > List.size()) {
                    System.out.println("Sorry please enter a valid index!");
                    break;
                }
                String temp = List.get(numberint - 1).substring(10);
                List.set(numberint - 1, numberint + ". " + type.get(numberint - 1) + "[X] " + temp);
                System.out.println("Nice! I've marked this task as done: \n" + "[X] " + temp);
            } else if (unmark) {
                String number = text.substring(7);
                int numberint = Integer.parseInt(number);
                if (numberint > List.size()) {
                    System.out.println("Sorry please enter a valid index!");
                    break;
                }
                String temp = List.get(numberint - 1).substring(10);
                String typestr = "";
                List.set(numberint - 1, numberint + ". " + type.get(numberint - 1) + "[ ] " + temp);
                System.out.println("OK, I've marked this task as not done yet: \n" + "[ ] " + temp);
            } else {
                System.out.println("added: " + text);
                List.add(count + ". [ ][ ] " + text);
                type.add("[ ]");
                count++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
