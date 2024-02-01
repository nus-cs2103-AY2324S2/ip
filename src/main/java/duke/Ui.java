package duke;
import java.util.ArrayList;

public class Ui {
    private final String BORDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private ArrayList<String> printList;


    public Ui() {
        this.printList = new ArrayList<String>();
    }
    
    public void add(String print) {
        this.printList.add(print);
    }

    public void print() {
        System.out.println(this.toString());
    }

    public void print(String printString) {
        this.add(printString);
        this.print();
    }

    public void greeting(String name) {
        this.add(String.format("Hello I'm %s", name));
        this.add("What Can I do for you?");
        this.print();
    }

    public void goodbye() {
        this.add("Saving file!");
        this.add("Goodbye. See you later!");
    }

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
