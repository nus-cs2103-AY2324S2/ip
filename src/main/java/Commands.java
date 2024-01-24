import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
public class Commands {
    public static final String indentation = "      ";
    public static final String line = "    -----------------------------------------------------------------------------------------";
    protected Hashtable<String, String> dialogues;
    protected List<String> tasks;
    public Commands() {
        dialogues = new Hashtable<>();
    }
    public String getCommand(String key) {
        return dialogues.get(key);
    }
    public void printDialogue(String message) {
        System.out.println(line);
        String dialogue = getCommand(message);
        if (dialogue != null) {
            System.out.println(indentation + dialogue);
        } else {
            System.out.println(indentation + "added: " + message);
        }
        System.out.println(line);
    }

}
