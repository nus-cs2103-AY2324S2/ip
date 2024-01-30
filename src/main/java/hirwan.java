import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class hirwan {
    static String FILE_PATH = "C:\\Users\\eugen\\Documents\\National University of Singapore\\Y2S2\\CS2103T\\IP\\src\\main\\java\\data\\hirwan.txt";
    static List<String> List = new ArrayList<>();
//    static List<String> type = new ArrayList<>();
    public static void main(String[] args) {
        String logo = "I'm hirwan \n"
                + "_________________________________\n"
                + "what can I do for you? \n"
                + "_________________________________\n";

        System.out.println("Hello! " + logo);

        try {
            File filePath = new File(FILE_PATH);
            Scanner scan = new Scanner(filePath);
            while (scan.hasNext()) {
                List.add(scan.nextLine());
            }
        } catch(FileNotFoundException e) {
            System.out.println("Error: file to read data from cannot be found!");
        }

        try (BufferedWriter fileWrite = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
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
//                        type.add("[T]");
                        System.out.println("Now you have " + List.size() + " tasks in the list.");
                        writeTask();
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
//                        type.add("[D]");
                        System.out.println("Now you have " + List.size() + " tasks in the list.");
                        writeTask();
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
                        System.out.println("Got it. I've added this task:\n  " + "[E][ ] " + item + " (from: " + start + " to: " + end + ")");
//                        type.add("[E]");
                        System.out.println("Now you have " + List.size() + " tasks in the list.");
                        writeTask();
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Error: Please enter a description or date for your event to command");
                    }
                } else if (mark) {
                    try {
                        String number = text.substring(5);
                        int numberint = Integer.parseInt(number);
                        String temp = List.get(numberint - 1).substring(9);
                        String type = List.get(numberint - 1).substring(2, 5);

                        List.set(numberint - 1, ". " + type + "[X] " + temp);
                        System.out.println("Nice! I've marked this task as done: \n" + "[X] " + temp);
                        writeTask();
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
                        String type = List.get(numberint - 1).substring(2, 5);

                        List.set(numberint - 1, ". " + type + "[ ] " + temp);
                        System.out.println("OK, I've marked this task as not done yet: \n" + "[ ] " + temp);
                        writeTask();
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
                                + "Now you have " + (List.size() - 1) + " tasks in the list.");
                        List.remove(numberint);
//                        type.remove(numberint);
                        writeTask();
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
        } catch (IOException e) {

            // Print the exception
            System.out.print(e.getMessage());
        }
    }

    public static void writeTask() {
        try {
            FileWriter file = new FileWriter(FILE_PATH);
            for (String tasks : List) {
                file.write(tasks + "\n");
            }
            file.close();
        } catch (IOException e) {
            System.out.println("Error: could not write to file");
            e.printStackTrace();
        }
    }
}
