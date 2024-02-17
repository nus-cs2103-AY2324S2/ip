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


        String output = new String("");
        Tasklist tasks = new Tasklist(Storage.read());
        Tasklist previousTasks = tasks.copyList();

        //undo only for todo, deadline, event, delete, mark, unmark commands

        try {
            int input = Parser.translate(text);

            assert input < 9 && input > 0;

            if (input == 8) {
            } else if (input == 1) {
                Listcommand listcommand = new Listcommand(tasks);
                output = listcommand.getMessage();
            } else if (input == 2) {
                Todo todo = new Todo(text, tasks, previousTasks);
                todo.updateData();
                output = todo.getMessage();
            } else if (input == 3) {
                Deadline deadline = new Deadline(text, tasks);
                deadline.updateData();
                output = deadline.getMessage();
            } else if (input == 4) {
                Event event = new Event(text, tasks);
                event.updateData();
                output = event.getMessage();
            } else if (input == 5) {
                Markcommand markcommand = new Markcommand(text, tasks);
                markcommand.updateData();
                output = markcommand.getMessage();
            } else if (input == 6) {
                Unmarkcommand unmarkcommand = new Unmarkcommand(text, tasks);
                unmarkcommand.updateData();
                output = unmarkcommand.getMessage();
            } else if (input == 7) {
                Deletecommand deletecommand = new Deletecommand(text, tasks);
                output = deletecommand.getMessage();
                deletecommand.updateData();
            } else if (input == 10) {
                List<Integer> indexes = Hirwan.searchWord(text.substring(5), tasks.getList());
                Hirwan.printSearchResults(indexes, tasks.getList());
//            } else if (input == 11) {
//                Undocommand undocommand = new Undocommand(tasks, previousTasks);
//                undocommand.undo();
//                undocommand.getMessage();
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
