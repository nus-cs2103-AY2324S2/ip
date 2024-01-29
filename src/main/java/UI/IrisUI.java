package UI;

import TaskList.Tasks.Task;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class IrisUI {
    private final Scanner reader;
    private final String INDENT = "    ";
    private final String CHATDELIMITER = INDENT + "_____________________";
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";
    private final PrintStream out;
    public IrisUI() {
        this.reader = new Scanner(System.in);
        this.out = System.out;
    }
    public void helloMessage(){
        this.printWithDelimiter("Hello I am Iris.\nWhat can I do for you?");
    }
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    public String getUserCommand() {
        String fullInputLine = reader.nextLine();
        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = this.reader.nextLine();
        }
        return fullInputLine;
    }

    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }
    public void printWithDelimiter(String output) {
        this.out.println(this.CHATDELIMITER);
        this.out.println(this.indent(output));
        this.out.println(this.CHATDELIMITER);
    }

    private String indent(String str) {
        String[] lines = str.split("\n");
        StringBuilder indentedString = new StringBuilder();
        for (String line : lines) {
            String indentedLine = this.INDENT + line;
            indentedString.append(indentedLine).append("\n");
        }
        return indentedString.toString();
    }

    public void showInitFailedMessage() {
        this.printWithDelimiter("Unable to read cache");
    }
}
