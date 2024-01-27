package squid.io;

import squid.constants.FILEPATH;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class SquidFile {
    /**
     * Referenced from:
     * <a href="https://nus-cs2103-ay2324s2.github.io/website/schedule/week3/topics.html">CS2103T Website</a>
     * Returns whether the file at the specified path exists.
     *
     * @param path the filepath to check
     * @return whether the file at the specified path exists.
     */
    private static boolean doesFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * Referenced from:
     * <a href="https://nus-cs2103-ay2324s2.github.io/website/schedule/week3/topics.html">CS2103T Website</a>
     * Writes to the filepath defined in the environment constant.
     *
     * @param textToAdd the text to write/append to the file.
     * @param append whether it should append instead of overwriting the whole file.
     * @throws IOException if there is IO error.
     */
    public static void writeToFile(String textToAdd, boolean append) throws IOException {
        if (!doesFileExist(FILEPATH.DIR)) {
            Files.createDirectory(Path.of(FILEPATH.DIR));
        }
        if (!doesFileExist(FILEPATH.FULL_PATH)) {
            Files.createFile(Paths.get(FILEPATH.FULL_PATH));
        }
        FileWriter fw = new FileWriter(FILEPATH.FULL_PATH, append);
        fw.write(textToAdd);
        fw.close();
    }


    /**
     * Referenced from:
     * <a href="https://nus-cs2103-ay2324s2.github.io/website/schedule/week3/topics.html">CS2103T Website</a>
     *
     * @return String representing a list of tasks.
     * @throws IOException in case of IO error.
     */
    public static String readFromFile() throws IOException {
        File f = new File(FILEPATH.FULL_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String str = "";
        while (s.hasNext()) {
            String line = s.nextLine();
            str += (line + "\n");
        }
        return str;
    }
}
