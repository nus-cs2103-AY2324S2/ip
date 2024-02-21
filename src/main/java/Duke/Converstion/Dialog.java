package Duke.Converstion;

public class Dialog {
    public static String greetUser() {
        String output = "\t______________________________________________________";
        output += "\tHello! \n\tWhat can I do for you?";
        output += "\t______________________________________________________" + "\n\n";
        return output;
    }

    public static String fairwellUser() {
        String output = "\t______________________________________________________";
        output += "\tLater";
        output += "\t______________________________________________________" + "\n\n";
        return output;
    }

    public static String printLine() {
        return "\t______________________________________________________" + "\n";
    }
}
