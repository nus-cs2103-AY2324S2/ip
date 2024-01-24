import java.util.Hashtable;
public class Commands {

    protected Hashtable<String, String> dialogues;
    public Commands() {
        dialogues = new Hashtable<>();
    }
    public String getCommand(String key) {
        return dialogues.get(key);
    }
    public void printCommand(String message) {
        String line = "-----------------------------------------------------------------------------------------";
        System.out.println(line);
        String dialogue = getCommand(message);
        if (dialogue != null) {
            System.out.println(dialogue);
        } else {
            System.out.println(message);
        }
        System.out.println(line);
    }

}
