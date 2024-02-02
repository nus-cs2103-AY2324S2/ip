package jmsandiegoo.tyrone.ui;

import jmsandiegoo.tyrone.commands.CommandResult;
import jmsandiegoo.tyrone.common.Messages;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ui {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner input;
    private final PrintWriter output;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream inputStream, OutputStream outputStream) {
        this.input = new Scanner(inputStream);
        this.output = new PrintWriter(outputStream, true);
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public void outputWelcomeMessage() {
        outputToUser(
                Messages.MESSAGE_LOGO,
                LINE_SEPARATOR,
                Messages.MESSAGE_GREET,
                LINE_SEPARATOR,
                DIVIDER);
    }

    public void outputByeMessage() {
        outputToUser(
                DIVIDER,
                LINE_SEPARATOR,
                Messages.MESSAGE_BYE,
                LINE_SEPARATOR,
                DIVIDER);
    }

    public void outputFailedInitMessage() {
        outputToUser(
                DIVIDER,
                LINE_SEPARATOR,
                Messages.MESSAGE_INITIALIZE_FAIL,
                LINE_SEPARATOR,
                DIVIDER);
    }

    public String getRawUserCommand() {
        String rawInputLine = input.nextLine();

        while (shouldIgnore(rawInputLine)) {
            rawInputLine = input.nextLine();
        }

        return rawInputLine;
    }

    public void outputResultToUser(CommandResult cmdResult) {
        outputToUser(
                DIVIDER,
                LINE_SEPARATOR,
                cmdResult.toString(),
                LINE_SEPARATOR,
                DIVIDER
        );
    }

    public void outputExceptionToUser(Exception e) {
        outputToUser(
                DIVIDER,
                LINE_SEPARATOR,
                String.format(Messages.MESSAGE_ERROR, e.getMessage()),
                LINE_SEPARATOR,
                DIVIDER
        );
    }

    public void outputToUser(String... messages) {
        for (String m : messages) {
            this.output.println(m);
        }
    }
}
