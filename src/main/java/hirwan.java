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
//        List<Integer> indexes = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();

            boolean isList = text.equals("list");
            boolean mark = text.startsWith("mark");
            boolean unmark = text.startsWith("unmark");
            boolean todo = text.startsWith("todo");
            boolean deadline = text.startsWith("deadline");
            boolean event = text.startsWith("event");
            boolean delete = text.startsWith("delete");
            boolean bye = text.equals("bye");

            if (bye) {
                break;
            } else if (isList) {
                for (String element : List) {
                    System.out.println(List.indexOf(element) + 1 + element);
                }
            } else if (todo) {
                try {
                    System.out.println("Got it. I've added this task: \n  " + "[T][ ] " + text.substring(5));
                    List.add(". " + "[T][ ] " + text.substring(5));
//                    indexes.add(count);
                    type.add("[T]");
                    System.out.println("Now you have " + count + " tasks in the list.");
                    count++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Error: Please enter a description for your todo command");
                }
            } else if (deadline) {
                try {
                    String delimiter = " /by";
                    int Index = text.indexOf(delimiter);
                    String Day = text.substring(Index + 5);
                    String item = text.substring(9, Index);
                    List.add(". " + "[D][ ] " + item + " (by: " + Day + ")");
//                    indexes.add(count);
                    System.out.println("Got it. I've added this task:\n  " + "[D][ ] " + item + " (by: " + Day + ")");
                    type.add("[D]");
                    System.out.println("Now you have " + count + " tasks in the list.");
                    count++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Error: Please enter a description or date for your deadline command");
                }
            } else if (event) {
                try {
                    String delimiterstart = " /from";
                    String delimiterend = " /to";
                    int Indexstart = text.indexOf(delimiterstart);
                    int Indexend = text.indexOf(delimiterend);
                    String start = text.substring(Indexstart + 7, Indexend);
                    String end = text.substring(Indexend + 5);
                    String item = text.substring(6, Indexstart);
                    List.add(". " + "[E][ ] " + item + " (from: " + start + " to: " + end + ")");
//                    indexes.add(count);
                    System.out.println("Got it. I've added this task:\n  " + "[E][ ] " + item + " (from: " + start + " to: " + end + ")");
                    type.add("[E]");
                    System.out.println("Now you have " + count + " tasks in the list.");
                    count++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Error: Please enter a description or date for your event to command");
                }
            } else if (mark) {
                try {
                    String number = text.substring(5);
                    int numberint = Integer.parseInt(number);
                    String temp = List.get(numberint - 1).substring(9);
                    List.set(numberint - 1, ". " + type.get(numberint - 1) + "[X] " + temp);
                    System.out.println("Nice! I've marked this task as done: \n" + "[X] " + temp);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Please enter a valid index for marking!");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a numerical index to mark!");
                }
            } else if (unmark) {
                try {
                    String number = text.substring(7);
                    int numberint = Integer.parseInt(number);
                    String temp = List.get(numberint - 1).substring(9);
                    List.set(numberint - 1, ". " + type.get(numberint - 1) + "[ ] " + temp);
                    System.out.println("OK, I've marked this task as not done yet: \n" + "[ ] " + temp);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Please enter a valid index for unmarking!");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a numerical index to unmark!");
                }
            } else if (delete) {
                try {
                    int numberint = Integer.parseInt(text.substring(7)) - 1;
                    System.out.println("Noted. I've removed this task:\n"
                            + "  " + List.get(numberint).substring(2) + "\n"
                            + "Now you have " + (count - 2) + " tasks in the list.");
                    List.remove(numberint);
                    type.remove(numberint);
                    count--;
//                    indexes.remove();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Please enter a valid index for deletion!");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a numerical index to delete!");
                }
            } else {
//                System.out.println("added: " + text);
//                List.add(count + ". [ ][ ] " + text);
//                type.add("[ ]");
//                count++;
                System.out.println("Error: I am sorry but I do not recognise this command");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
