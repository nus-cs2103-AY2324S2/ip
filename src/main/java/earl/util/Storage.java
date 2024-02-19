package earl.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import earl.exceptions.StorageException;
import earl.util.parsers.ParseFunction;

/**
 * Class responsible for reading and writing data to disk.
 */
public class Storage {

    private final String filePath;

    private boolean wasLoadSuccessful = false;

    /**
     * Class constructor.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a {@code Stream} of {@code T} read from the disk.
     * <p>
     * Attempts to find the storage file at the given file path.
     * Starts with an empty file if no existing file is found. Note
     * that while a {@code Stream} is returned, items are evaluated
     * before conversion into a stream to prevent errors from escaping
     * this method due to lazy evaluation.
     *
     * @param parse  a {@code ParseFunction} functional interface
     * @return       a {@code Stream} of {@code T} read
     */
    public <T> Stream<T> load(ParseFunction<T> parse) {
        try {
            // Ensure file exists
            File file = new File(filePath);
            boolean isFolderMade = file.getParentFile().mkdirs();
            boolean isFileMade = file.createNewFile();
            assert file.exists();
            if (isFolderMade || isFileMade) {
                return Stream.empty();
            }
            // Read lines from file
            Scanner sc = new Scanner(file);
            List<T> result = new ArrayList<>();
            while (sc.hasNext()) {
                String entry = sc.nextLine();
                result.add(parse.apply(entry));
            }
            wasLoadSuccessful = true;
            return result.stream();
        } catch (Exception e) {
            return Stream.empty();
        }
    }

    /** Returns if loading from storage occurred without error. */
    public boolean wasLoadSuccessful() {
        return wasLoadSuccessful;
    }

    /**
     * Saves given stream of {@code String} onto the disk.
     *
     * @param dataStream         a {@code Stream} of {@code String} to be saved
     * @throws StorageException  if the file could not be written to
     */
    public void save(Stream<String> dataStream) throws StorageException {
        try (FileWriter fw = new FileWriter(filePath)) {
            String[] data = dataStream.map((str) -> str + "\n")
                    .toArray(String[]::new);
            for (String entry : data) {
                fw.write(entry);
            }
        } catch (IOException e) {
            throw new StorageException();
        }
    }
}
