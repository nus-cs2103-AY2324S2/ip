import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    protected String userInput;
    protected ArrayList<String> words;
    public Parser (String userInput) {
        this.userInput = userInput;
        this.words = new ArrayList<>(Arrays.asList(userInput.split(" ")));
    }

    // Stitching up all the words to form the description and returning a ToDos object
    public ToDos todoParser() {
        StringBuilder description = new StringBuilder(this.words.get(1));
        for (int i = 2; i < words.size(); i++) {
            description.append(" ").append(this.words.get(i));
        }
        return new ToDos(description.toString());
    }


    public Deadline deadlineParser() {
        StringBuilder description = new StringBuilder(this.words.get(1));
        StringBuilder by  = new StringBuilder();
        int index = this.words.indexOf("/by");

        for (int i = 2; i < words.size(); i++) {
            if (i < index) {
                description.append(" ").append(words.get(i));
            } else if (i > index) {
                by.append(" ").append(words.get(i));
            }
        }
        return new Deadline(description.toString(), by.toString());
    }

    public Events eventsParser() {
        StringBuilder description = new StringBuilder(this.words.get(1));
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        int indexOfFrom = this.words.indexOf("/from");
        int indexOfTo = this.words.indexOf("/to");

        for (int i = 2; i < words.size(); i++) {
            if (i < indexOfFrom) {
                description.append(" ").append(words.get(i));
            } else if (i > indexOfFrom && i < indexOfTo) {
                start.append(" ").append(words.get(i));
            } else if (i > indexOfTo){
                end.append(" ").append(words.get(i));
            }
        }
        return new Events(description.toString(), start.toString(), end.toString());
    }

    public int markParser() {
        return Integer.parseInt(this.words.get(1)) - 1;
    }

    public int unmarkParser() {
        return Integer.parseInt(this.words.get(1)) - 1;
    }


}
