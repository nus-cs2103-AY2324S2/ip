package duke;
import java.util.ArrayList;

/**
 * Class that prints to the system
 */
public class Ui {
    private final String BORDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private ArrayList<String> printList;

    /**
     * Constructor for Ui
     */
    public Ui() {
        this.printList = new ArrayList<String>();
    }
    
    /**
     * Adds the String to be printed
     * @param print String to be printed to the system
     */
    public void add(String print) {
        this.printList.add(print);
    }

    /**
     * Prints everything in the list
     */
    public void print() {
        System.out.println(this.toString());
    }

    /**
     * Adds the String to tbe printed
     * @param print String to be added then printed immediately to the system
     */
    public void print(String print) {
        this.add(print);
        this.print();
    }

    /**
     * Greets the user
     * @param name String name of chatbot
     */
    public void greeting(String name) {
        this.add(String.format("Hello I'm %s", name));
        this.add("What Can I do for you?");
        this.print();
    }

    /**
     * Prints everything in the list with borders and clear it
     */
    @Override
    public String toString() {
        String finalString = "";
        for (int i = 0; i < printList.size(); i++) {
            if (i == printList.size() - 1) {
                finalString += String.format("\t%s", printList.get(i));
            } else {
                finalString += String.format("\t%s\n", printList.get(i));
            }
        }
        printList.clear();
        return String.format("\t%s\n%s\n\t%s",
                this.BORDER,
                finalString,
                this.BORDER);
    }
}
