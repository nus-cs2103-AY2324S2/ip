package earl.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import earl.exceptions.EarlException;
import earl.util.parsers.StorageParser;

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
     * Starts with an empty file if no existing file is found.
     *
     * @return  a {@code Stream} of {@code T} read
     */
    public <T> Stream<T> load(StorageParser<T> parser) {
        try {
            File file = new File(filePath);
            boolean isFolderMade = file.getParentFile().mkdirs();
            boolean isFileMade = file.createNewFile();
            assert file.exists();
            if (isFolderMade || isFileMade) {
                return Stream.empty();
            }
            Stream<T> result = Files.lines(file.toPath())
                    .map((x) -> {
                        try {
                            return parser.parse(x);
                        } catch (EarlException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    });
            wasLoadSuccessful = true;
            return result;
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
     * @param dataStream      a {@code Stream} of {@code String} to be saved
     * @throws EarlException  if the file could not be written to
     */
    public void save(Stream<String> dataStream) throws EarlException {
        try (FileWriter fw = new FileWriter(filePath)) {
            String[] data = dataStream.map((str) -> str + "\n")
                    .toArray(String[]::new);
            for (String line : data) {
                fw.write(line);
            }
        } catch (IOException e) {
            throw new EarlException("Fatal error while saving to storage.");
        }
    }
}
