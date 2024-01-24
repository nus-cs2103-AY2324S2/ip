import java.util.Hashtable;
public class Dialogues {

    protected Hashtable<String, String> dialogues;
    public Dialogues() {
        dialogues = new Hashtable<>();
    }
    public String getDialogue(String key) {
        return dialogues.get(key);
    }
    public void printDialogue(String key) {
        String line = "-----------------------------------------------------------------------------------------";
        System.out.println(line);
        String dialogue = getDialogue(key);
        if (dialogue != null) {
            System.out.println(dialogue);
        }
    }

}
