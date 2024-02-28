package goblin;

import goblin.task.Task;
import goblin.exception.OrkException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void fortTeat_test() {
        assertEquals("haha", Ui.forTest());
    }
}
