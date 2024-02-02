package hirwan;

import hirwan.Parser;
import hirwan.Ui;
import hirwan.Tasklist;
import hirwan.Storage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class hirwan {
    static String FILE_PATH = "C:\\Users\\eugen\\Documents\\National University of Singapore\\Y2S2\\CS2103T\\IP\\src\\main\\java\\data\\hirwan.txt";

    public static void main(String[] args) {
        String logo = "I'm hirwan \n"
                + "_________________________________\n"
                + "what can I do for you? \n"
                + "_________________________________\n";

        Ui.output("Hello! " + logo);

        Tasklist List = new Tasklist(Storage.read());

        try (BufferedWriter fileWrite = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            while (true) {
                String text = Ui.input();
                int input = Parser.translate(text);

                if (input == 8) {
                    break;
                } else if (input == 1) {
                    List.printList();
                } else if (input == 2) {
                    try {
                        Ui.output("Got it. I've added this task: \n  " + "[T][ ] " + text.substring(5));
                        List.add(". " + "[T][ ] " + text.substring(5));
                        Ui.output("Now you have " + List.size() + " tasks in the list.");
                        Storage.writeTask(List.getList());
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description for your todo command");
                    }
                } else if (input == 3) {
                    try {
                        String delimiter = " /by";
                        int Index = text.indexOf(delimiter);
                        String Day = text.substring(Index + 5);
                        String item = text.substring(9, Index);

                        LocalDateTime dayDate = translateDate(Day);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");

                        List.add(". " + "[D][ ] " + item + " (by: " + dayDate.format(formatter) + ")");
                        Ui.output("Got it. I've added this task:\n  " + "[D][ ] " + item + " (by: " + dayDate.format(formatter) + ")");
                        Ui.output("Now you have " + List.size() + " tasks in the list.");
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description or date for your deadline command");
                    }
                } else if (input == 4) {
                    try {
                        String delimiterstart = " /from";
                        String delimiterend = " /to";
                        int Indexstart = text.indexOf(delimiterstart);
                        int Indexend = text.indexOf(delimiterend);
                        String start = text.substring(Indexstart + 7, Indexend);
                        String end = text.substring(Indexend + 5);
                        String item = text.substring(6, Indexstart);

                        LocalDateTime startDate = translateDate(start);
                        LocalDateTime endDate = translateDate(end);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");

                        List.add(". " + "[E][ ] " + item + " (from: " + startDate.format(formatter) + " to: " + endDate.format(formatter) + ")");
                        Ui.output("Got it. I've added this task:\n  " + "[E][ ] " + item + " (from: " + startDate.format(formatter) + " to: " + endDate.format(formatter) + ")");
                        Ui.output("Now you have " + List.size() + " tasks in the list.");
                        Storage.writeTask(List.getList());
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description or date for your event to command");
                    }
                } else if (input == 5) {
                    try {
                        String number = text.substring(5);
                        int numberint = Integer.parseInt(number);
                        String temp = List.get(numberint - 1).substring(9);
                        String type = List.get(numberint - 1).substring(2, 5);

                        List.set(numberint - 1, ". " + type + "[X] " + temp);
                        Ui.output("Nice! I've marked this task as done: \n" + "[X] " + temp);
                        Storage.writeTask(List.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for marking!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to mark!");
                    }
                } else if (input == 6) {
                    try {
                        String number = text.substring(7);
                        int numberint = Integer.parseInt(number);
                        String temp = List.get(numberint - 1).substring(9);
                        String type = List.get(numberint - 1).substring(2, 5);

                        List.set(numberint - 1, ". " + type + "[ ] " + temp);
                        Ui.output("OK, I've marked this task as not done yet: \n" + "[ ] " + temp);
                        Storage.writeTask(List.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for unmarking!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to unmark!");
                    }
                } else if (input == 7) {
                    try {
                        int numberint = Integer.parseInt(text.substring(7)) - 1;
                        Ui.output("Noted. I've removed this task:\n"
                                + "  " + List.get(numberint).substring(2) + "\n"
                                + "Now you have " + (List.size() - 1) + " tasks in the list.");
                        List.delete(numberint);
                        Storage.writeTask(List.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for deletion!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to delete!");
                    }
                } else if (input == 10) {
                    List<Integer> indexes = hirwan.searchWord(text.substring(5), List.getList());
                    hirwan.printSearchResults(indexes, List.getList());
                } else if (input == 9) {
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

    /**
     * The method to search the tasks list to check if each element contains the search word
     *
     * @param word The search word to be found
     * @return the list of indexes of tasks that contain the search word
     */
    public static List<Integer> searchWord(String word, List<String> tasks) {
        List<Integer> indexes = new ArrayList<>();
        for (String element : tasks) {
            if (element.contains(word)) {
                indexes.add(tasks.indexOf(element));
            }
        }
        return indexes;
    }

    /**
     * The print search result method that prints the list of search results from the tasklist
     * that match the search term
     *
     * @param indexes the list of indexes of items in the tasklist that having matching substrings to the search term
     */
    public static void printSearchResults(List<Integer> indexes, List<String> tasks) {
        Ui.output("Here are the results of your search in the tasklist:\n");
        if (indexes.size() == 0) {
            Ui.output("There are no matching tasks with the search word!");
        } else {
            for (int index : indexes) {
                Ui.output((index + 1) + tasks.get(index));
            }
        }
    }
}
