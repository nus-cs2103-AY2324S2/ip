import java.util.Hashtable;
import java.util.List;
public class Commands {
    public static final String INDENTATION = "      ";
    public static final String LINE = "    -----------------------------------------------------------------------------------------";
    protected Hashtable<String, String> dialogues;
    public Commands() {
        dialogues = new Hashtable<>();
    }
    public String getCommand(String key) {
        return dialogues.get(key);
    }
    public void printDialogue(String message) {
        System.out.println(LINE);
        String dialogue = getCommand(message);
        if (dialogue != null) {
            System.out.println(INDENTATION + dialogue);
        } else {
            System.out.println(INDENTATION + "added: " + message);
        }
        System.out.println(LINE);
    }

}
