package FileHandler;

import java.io.*;
import java.util.List;
import Actions.Action;

public class FileInput {

    private FileInput() {
        throw new AssertionError("Constructor is not allowed");
    }

    public static List<Action> execInput(List<Action> actionList) throws Exception {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("taylor.txt"));
            actionList = (List<Actions.Action>) ois.readObject();
            int pos = 1;
            for (Actions.Action act : actionList) {
                System.out.println(pos++ + ". " + act);
            }
            return actionList;
        } catch (
                FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println("Please create file in " + System.getProperty("user.dir"));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No past data");
        } catch (ClassCastException e) {
            System.out.println("Content are corrupted!");
        }
        return actionList;
    }

    public static void execOutput(List<Action> actionList) throws Exception {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("taylor.txt"));
            oos.writeObject(actionList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
