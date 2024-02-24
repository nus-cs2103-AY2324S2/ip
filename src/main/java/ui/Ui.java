package ui;

import parser.Parser;

import java.util.Scanner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;

import java.nio.charset.StandardCharsets;

public class Ui {

    private static final String NAME = "Tartar";

    private static final String GREETING = String.format("Hello! I'm %s\n", NAME);

    private static final String DIVIDER = "____________________________________________________________\n";

    private static final String QUESTION = "What can I do for you?";

    private static final String BYE = " Bye. Hope to see you again soon!";

    private static final String MARKED = "Nice! I've marked this task as done:";

    private static final String UNMARKED = "OK, I've marked this task as not done yet:";

    private static final String RECOMMENDER = "RECCOMENDATION";

    private static final String QUOTE_STRING =
            ">  You must do the things you think you cannot do.\n>  - Eleanor Roosevelt";

    private Scanner sc;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns a string containing the greeting string.
     *
     * @return a string containing the greeting string.
     */
    public String greeting() {
        return QUOTE_STRING + "\n" + GREETING;
    }
    /**
     * Returns a string containing the divider string.
     * @return a string containing the divider string.
     */
    public String divider() {
        return DIVIDER;
    }
    /**
     * Returns a string containing the question string.
     * @return a string containing the question string.
     */
    public String question() {
        return QUESTION;
    }

    /**
     * Returns a string containing the marked string.
     *
     * @return a string containing the marked string.
     */
    public String mark() {
        return MARKED;
    }

    /**
     * Returns a string containing the unmarked string.
     *
     * @return a string containing the unmarked string.
     */
    public String unmark() {
        return UNMARKED;
    }

    /**
     * Returns the user's input as a string.
     *
     * @return the user's input as a string.
     */
    public String getCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Returns the chatbot's response as a string.
     *
     * @param userInput the user's input
     * @return the response.
     */
    public String getResponse(String userInput, Parser parser) {
        // Save the original output stream
        PrintStream originalOut = System.out;
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);
        // Set the new output stream
        System.setOut(newOut);
        // Run the command
        // Assuming that the runCommand method runs the command and prints the output to the console
        try {
            parser.parse(userInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Reset the output stream
        System.setOut(originalOut);
        // Get the output printed to the console
        String response = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        return response;
    }

    /**
     * Returns a string containing the bye string.
     *
     * @return a string containing the bye string.
     */
    public static String bye() {
        return BYE;
    }

    /**
     * Returns a string containing the given text wrapped in a divider.
     *
     * @param text the text to be wrapped in a divider
     * @return a string containing the given text wrapped in a divider
     */
    public String dividerWrapper(String text) {
        return "\n" + text + "\n";
    }

    /**
     * Returns a string containing the given text wrapped in a recommender.
     *
     * @param text the text to be wrapped in a recommender
     * @return a string containing the given text wrapped in a recommender
     */
    public String recommenderWrapper(String text) {
        return RECOMMENDER + "\n" + text + "\n" + RECOMMENDER;
    }

    /**
     * Returns a string containing the greeting and question wrapped in a divider.
     *
     * @return a string containing the greeting and question wrapped in a divider
     */
    public String greetingBox() {
        return dividerWrapper(greeting() + question());
    }

    /**
     * Returns a string containing the quote string.
     *
     * @return a string containing the quote string.
     */
    public String quote() {
        return QUOTE_STRING;
    }



}
