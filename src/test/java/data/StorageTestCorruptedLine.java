package data;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import core.Ui;

//CHECKSTYLE.OFF: MissingJavadocType
public class StorageTestCorruptedLine {
    private Path tempFile;
    private Storage storage;
    private Ui mockUi;

    @BeforeEach
    public void setUp(@TempDir Path tempDir) throws Exception {
        tempFile = tempDir.resolve("testTasksCorrupted.txt");
        mockUi = mock(Ui.class);
        storage = new Storage(tempFile.toString(), mockUi);

        List<String> lines = Arrays.asList("T | 1 | Read book", "X | 2 | Invalid task");
        Files.write(tempFile, lines);
    }

    //CHECKSTYLE.OFF: MissingJavadocMethod
    @Test
    public void load_withCorruptedLine_outputsCorruptedMessage() {
        storage.load();

        verify(mockUi, times(1)).showLoadingError("corrupted");
    }

    @AfterEach
    public void tearDown() throws Exception {
        Files.deleteIfExists(tempFile);
    }
}
