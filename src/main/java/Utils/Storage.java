package Utils;

import java.io.File;
import java.io.IOException;

public class Storage {


    /**
     * Creates a storage file if it does not exist.
     * @throws IOException
     */
    public static void createStorage() throws IOException, SecurityException{
        String filepath = "data/dude.txt";
        File file = new File(filepath);

        boolean fileExists = file.exists();

        if (!fileExists) {
            boolean created = file.createNewFile();
        }
    }



}
