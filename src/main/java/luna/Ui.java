package luna;

import java.io.BufferedReader;
import java.io.StringReader;

import luna.entry.ListEntry;

/**
 * Handles the interaction between the user and the program. A UI handles the input and output of the program.
 */
public class Ui {
    private final String name;

    private BufferedReader br;

    public Ui(String n) {
        this.name = n;
    }

    /**
     * Calls the UI to call the shifted_print function on a greeting message;
     */
    public void greet() {
        this.shiftedPrint(this.greetingString());
    }

    /**
     * Returns a String read by the buffered reader which takes its input form the system input.
     * Checks for Exception during reading, if so return "".
     *
     *
     * @return String read from System input
     */
    public String readInput() {
        try {
            return br.readLine();
        } catch (Exception e) {
            return "";
        }
    }

    public String readInput(String input) {
        return input;
    }

    /**
     * Returns a constructed string with for the IU greeting message
     *
     * @return Greeting message
     */
    private String greetingString() {
        String msg = "";
        msg += String.format("%30s", " （„• ֊ •„)♡\n");
        msg += "heyo! my name is ✦" + this.name + "✦\n";
        msg += "What would you like for me to do???\n";
        return msg;
    }

    /**
     * Returns a string which prints out a cat-like shape;
     *
     * @return a cat string
     */
    private String sadCatImg() {
        return "⠀               />    フ\n"
                + "               | 　_  _|\n"
                + "           ___/` ミ＿_xノ\n"
                + "          /　　　     |\n"
                + "         /　 ヽ　     ﾉ\n"
                + "        │　    |　|　|\n"
                + "     /￣|　    |　|　|\n"
                + "    | (￣ ヽ＿ヽ_)__)\n"
                + "    ＼二二)⠀⠀\n";
    }

    /**
     * Prints the sign-off String in the shiftPrint format
     */
    public void exitMessage() {
        shiftedPrint(signoffString());
    }

    /**
     * Returns a constructed String for the user to see when the program exits
     *
     * @return Sign-off string.
     */

    private String signoffString() {
        String msg = "";
        msg += "okay then, bye\n";
        msg += sadCatImg();

        return msg;
    }

    /**
     * Prints out the given text to be in the ui format
     *
     * @param text the string that is to be printed in the ui format
     */
    public void shiftedPrint(String text) {
        try {
            BufferedReader br = new BufferedReader(new StringReader(text));
            String readText = br.readLine();
            StringBuilder output = new StringBuilder();
            while (readText != null) {
                output.append("      ").append(readText).append("\n");
                readText = br.readLine();
            }
            System.out.print(output);
        } catch (Exception e) {
            //
        }
    }

    /**
     * Prints the given Task list in the ui format.
     *
     * @param tl The task list to be shown
     */
    public void showList(TaskList tl) {
        String toPrint = listBuilder(tl);
        this.shiftedPrint(toPrint);
    }

    /**
     * Returns a string converted from the given task list.
     *
     * @param tl The task list to be converted.
     * @return Task list in String.
     */
    public String listBuilder(TaskList tl) {
        StringBuilder text = new StringBuilder();

        if (tl.isEmpty()) {
            text.append("List is Empty");
        } else {
            text.append("These are your outstanding tasks\n");
            for (int i = 0; i < tl.getSize(); i++) {
                ListEntry ent = tl.getEntry(i);
                text.append((i + 1)).append(".").append(ent.toString()).append("\n");
            }
        }

        return text.toString();
    }
}
