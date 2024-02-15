package earl.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import earl.exceptions.EarlException;
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
     * Starts with an empty file if no existing file is found.
     *
     * @param parse  a {@code ParseFunction} functional interface
     * @return       a {@code Stream} of {@code T} read
     */
    public <T> Stream<T> load(ParseFunction<T> parse) {
        try {
            File file = new File(filePath);
            boolean isFolderMade = file.getParentFile().mkdirs();
            boolean isFileMade = file.createNewFile();
            assert file.exists();
            if (isFolderMade || isFileMade) {
                return Stream.empty();
            }
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
     * @param dataStream      a {@code Stream} of {@code String} to be saved
     * @throws EarlException  if the file could not be written to
     */
    public void save(Stream<String> dataStream) throws EarlException {
        try (FileWriter fw = new FileWriter(filePath)) {
            dataStream.map((str) -> str + "\n")
                    .forEach((str) -> {
                        try {
                            fw.write(str);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        } catch (Exception e) {
            throw new EarlException("Alas, a grievous misfortune occurred "
                    + "during the endeavour to save.");
        }
    }
}
