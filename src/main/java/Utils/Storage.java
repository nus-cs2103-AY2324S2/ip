package Utils;

import java.io.File;
import java.io.IOException;

public class Storage {

    static String filepath = "data/";
    static String filename = "storage.txt";

    /**
     * Creates a storage file if it does not exist.
     * @throws IOException, SecurityException
     */
    public static void createStorage() throws IOException, SecurityException{
        File path = new File(filepath);

        //create the directory if it does not exist
        if (!path.exists()) {
            boolean created = path.mkdirs();
        }

        File file = new File(filepath + filename);
        boolean fileExists = file.exists();

        //create the file if it does not exist
        if (!fileExists) {
            boolean created = file.createNewFile();
        }
    }

    /**
     * Deletes the storage file if it exists.
     * @throws SecurityException
     */
    public static void deleteStorage() throws SecurityException{
        File file = new File(filepath + filename);

        boolean fileExists = file.exists();

        if (fileExists) {
            boolean deleted = file.delete();
        }
    }


    public static void main(String[] args) throws IOException, SecurityException {

    }
}
