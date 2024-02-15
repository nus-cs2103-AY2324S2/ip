package hirwan;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import javafx.fxml.FXML;

/**
 * The chatbots main class which simulates talking to a virtual assistant
 */
public class Hirwan {



    public Hirwan() {
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    public String getResponse(String input) {
        return this.parseInput(input);
    }

    static final String FILE_PATH =
            "C:\\Users\\eugen\\Documents\\National University of Singapore\\Y2S2\\CS2103T\\IP\\src\\main\\java\\data\\hirwan.txt";

    /**
     * The main method of the program. This method is the entry point
     * when the program is executed.
     *
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
    }

    public String parseInput(String text) {
//        String logo = "I'm hirwan \n"
//                + "_________________________________\n"
//                + "what can I do for you? \n"
//                + "_________________________________\n";
//
//        Ui.output("Hello! " + logo);

        String output = new String("");
        Tasklist tasks = new Tasklist(Storage.read());

        try {
            int input = Parser.translate(text);

            assert input < 9 && input > 0;

            if (input == 8) {
            } else if (input == 1) {
                output = "";
                for (String i : tasks.getList()) {
                    output += i + "\n";
                }
            } else if (input == 2) {
                try {
                    Todo todo = new Todo(text, tasks);
                    todo.updateData();
                    output = todo.getMessage();
                } catch (StringIndexOutOfBoundsException e) {
                    output = output + "Error: Please enter a description for your todo command";
                }
            } else if (input == 3) {
                try {
                    Deadline deadline = new Deadline(text, tasks);
                    deadline.updateData();;
                    output = deadline.getMessage();
                } catch (StringIndexOutOfBoundsException e) {
                    output = output + "Error: Please enter a description or date for your deadline command";
                }
            } else if (input == 4) {
                try {
                    Event event = new Event(text, tasks);
                    event.updateData();
                    output = event.getMessage();
                } catch (StringIndexOutOfBoundsException e) {
                    output = output + "Error: Please enter a description or date for your event to command";
                }
            } else if (input == 5) {
                try {
                    String number = text.substring(5);
                    int numberint = Integer.parseInt(number);
                    String temp = tasks.get(numberint - 1).substring(9);
                    String type = tasks.get(numberint - 1).substring(2, 5);

                    tasks.set(numberint - 1, ". " + type + "[X] " + temp);
                    output = "Nice! I've marked this task as done: \n" + "[X] " + temp;
                    Storage.writeTask(tasks.getList());
                } catch (IndexOutOfBoundsException e) {
                    output = "Error: Please enter a valid index for marking!";
                } catch (NumberFormatException e) {
                    output = "Error: Please enter a numerical index to mark!";
                }
            } else if (input == 6) {
                try {
                    String number = text.substring(7);
                    int numberInt = Integer.parseInt(number);
                    String temp = tasks.get(numberInt - 1).substring(9);
                    String type = tasks.get(numberInt - 1).substring(2, 5);

                    tasks.set(numberInt - 1, ". " + type + "[ ] " + temp);
                    output = "OK, I've marked this task as not done yet: \n" + "[ ] " + temp;
                    Storage.writeTask(tasks.getList());
                } catch (IndexOutOfBoundsException e) {
                    output = output + "Error: Please enter a valid index for unmarking!";
                } catch (NumberFormatException e) {
                    output = output + "Error: Please enter a numerical index to unmark!";
                }
            } else if (input == 7) {
                try {
                    int numberInt = Integer.parseInt(text.substring(7)) - 1;
                    output = "Noted. I've removed this task:\n"
                            + "  " + tasks.get(numberInt).substring(2) + "\n"
                            + "Now you have " + (tasks.size() - 1) + " tasks in the list.";
                    tasks.delete(numberInt);
                    Storage.writeTask(tasks.getList());
                } catch (IndexOutOfBoundsException e) {
                    output = "Error: Please enter a valid index for deletion!";
                } catch (NumberFormatException e) {
                    output = "Error: Please enter a numerical index to delete!";
                }
            } else if (input == 10) {
                List<Integer> indexes = Hirwan.searchWord(text.substring(5), tasks.getList());
                Hirwan.printSearchResults(indexes, tasks.getList());
            } else if (input == 9) {
                output = "Error: I am sorry but I do not recognise this command";
            } else {
                output = "Bye. Hope to see you again soon!";
            }
        } catch (Exception e) {
            output = e.getMessage();
        }
        tasks.deleteList();

        assert !output.isEmpty();
        return output;
    }

    /**
     * Translates the string given into a LocalDateTime instance by taking in the string and formatting the date and
     * time then return the LocalDateTime instance
     *
     * @param date Date to be converted into LocalDateTime instance
     * @return The translated LocalDateTime instance
     */
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
