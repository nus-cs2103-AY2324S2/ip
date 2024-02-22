package duke;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The Storage Class represents an instance containing
 * information of where to store and retrieve ItemList data
 * stored on disk. It also provides methods to read from and write
 * to disk by serializing and deserializing the current ItemList.
 */
public class Storage {

    private String path = "./duke.txt";

    /**
     * Creates a Storage instance containing the filepath
     * to the date storage location on the disk. The Storage
     * object is instantiated when the main method in Duke is run.
     *
     * @param filepath is a string that determines where the saved
     *                 ItemLists are read from and written to on the
     *                 disk.
     */
    public Storage(String filepath) {
        if (new File(filepath).exists()) {
            this.path = filepath;
        }
        File test = new File(this.path);
        assert test.exists();
    }

    /**
     * Takes a ItemList Object and stores it on disk in duke.txt in
     * its serialized form.
     *
     * @param il is the Itemlist object to be serialized and stored.
     */
    public void writeToFile(ItemList il) {
        try {
            final FileOutputStream fout = new FileOutputStream(this.path);
            final ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(il);
            out.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to create file: " + e);
        } catch (IOException e) {
            System.out.println("Something went wrong when saving file: " + e);
        }
    }

    /**
     * Takes a serialized ItemList Object stored on disk in duke.txt and
     * returns its deserialized form for use.
     *
     * @return an ItemList object that has been deserialized from duke.txt
     */
    public ItemList readFromFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.path));
            final ItemList il = (ItemList) in.readObject();
            return il;
        } catch (FileNotFoundException | EOFException e) {
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
