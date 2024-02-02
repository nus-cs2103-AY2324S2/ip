import java.io.*;

public class Storage {
    public static void writeToFile(ItemList il) {
        try {
            final FileOutputStream fout = new FileOutputStream("./data/duke.txt");
            final ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(il);
            out.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to create file: " + e);
        } catch (IOException e) {
            System.out.println("Something went wrong when saving file: " + e);
        }
    }

    public static ItemList readFromFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("./data/duke.txt"));
            final ItemList il = (ItemList) in.readObject();
            return il;
        } catch (EOFException e) {
            return new ItemList();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong when loading save file: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Something went wrong parsing save file: " + e);
        }
        return new ItemList();
    }
}
