package hirwan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Hirwan {
    static final String FILE_PATH =
            "C:\\Users\\eugen\\Documents\\National University of Singapore\\Y2S2\\CS2103T\\IP\\src\\main\\java\\data\\hirwan.txt";

    public static void main(String[] args) {
        String logo = "I'm hirwan \n"
                + "_________________________________\n"
                + "what can I do for you? \n"
                + "_________________________________\n";

        Ui.output("Hello! " + logo);

        Tasklist tasks = new Tasklist(Storage.read());

        try (BufferedWriter fileWrite = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            while (true) {
                String text = Ui.input();
                int input = Parser.translate(text);

                if (input == 8) {
                    break;
                } else if (input == 1) {
                    tasks.printList();
                } else if (input == 2) {
                    try {
                        Ui.output("Got it. I've added this task: \n  " + "[T][ ] "
                                + text.substring(5));
                        tasks.add(". " + "[T][ ] " + text.substring(5));
                        Ui.output("Now you have " + tasks.size() + " tasks in the list.");
                        Storage.writeTask(tasks.getList());
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description for your todo command");
                    }
                } else if (input == 3) {
                    try {
                        String delimiter = " /by";
                        int index = text.indexOf(delimiter);
                        String Day = text.substring(index + 5);
                        String item = text.substring(9, index);

                        LocalDateTime dayDate = translateDate(Day);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");

                        tasks.add(". " + "[D][ ] " + item + " (by: " + dayDate.format(formatter) + ")");
                        Ui.output("Got it. I've added this task:\n  " + "[D][ ] " + item + " (by: "
                                + dayDate.format(formatter) + ")");
                        Ui.output("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description or date for your deadline command");
                    }
                } else if (input == 4) {
                    try {
                        String delimiterstart = " /from";
                        String delimiterend = " /to";
                        int indexStart = text.indexOf(delimiterstart);
                        int indexEnd = text.indexOf(delimiterend);
                        String start = text.substring(indexStart + 7, indexEnd);
                        String end = text.substring(indexEnd + 5);
                        String item = text.substring(6, indexStart);

                        LocalDateTime startDate = translateDate(start);
                        LocalDateTime endDate = translateDate(end);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");

                        tasks.add(". " + "[E][ ] " + item + " (from: " + startDate.format(formatter) + " to: "
                                + endDate.format(formatter) + ")");
                        Ui.output("Got it. I've added this task:\n  " + "[E][ ] " + item + " (from: "
                                + startDate.format(formatter) + " to: " + endDate.format(formatter) + ")");
                        Ui.output("Now you have " + tasks.size() + " tasks in the list.");
                        Storage.writeTask(tasks.getList());
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description or date for your event to command");
                    }
                } else if (input == 5) {
                    try {
                        String number = text.substring(5);
                        int numberint = Integer.parseInt(number);
                        String temp = tasks.get(numberint - 1).substring(9);
                        String type = tasks.get(numberint - 1).substring(2, 5);

                        tasks.set(numberint - 1, ". " + type + "[X] " + temp);
                        Ui.output("Nice! I've marked this task as done: \n" + "[X] " + temp);
                        Storage.writeTask(tasks.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for marking!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to mark!");
                    }
                } else if (input == 6) {
                    try {
                        String number = text.substring(7);
                        int numberInt = Integer.parseInt(number);
                        String temp = tasks.get(numberInt - 1).substring(9);
                        String type = tasks.get(numberInt - 1).substring(2, 5);

                        tasks.set(numberInt - 1, ". " + type + "[ ] " + temp);
                        Ui.output("OK, I've marked this task as not done yet: \n" + "[ ] " + temp);
                        Storage.writeTask(tasks.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for unmarking!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to unmark!");
                    }
                } else if (input == 7) {
                    try {
                        int numberInt = Integer.parseInt(text.substring(7)) - 1;
                        Ui.output("Noted. I've removed this task:\n"
                                + "  " + tasks.get(numberInt).substring(2) + "\n"
                                + "Now you have " + (tasks.size() - 1) + " tasks in the list.");
                        tasks.delete(numberInt);
                        Storage.writeTask(tasks.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for deletion!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to delete!");
                    }
                } else if (input ==9) {
                    Ui.output("Error: I am sorry but I do not recognise this command");
                }
            }
            Ui.output("Bye. Hope to see you again soon!");
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public static LocalDateTime translateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        LocalDateTime dateStored = LocalDateTime.parse(date, formatter);
        return dateStored;
    }
}
