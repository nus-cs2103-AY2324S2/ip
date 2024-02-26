package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import bob.task.TaskStub;

public class UiTest {
    @Test
    public void getListResponse_nonEmptyTasks() {
        assertEquals("a\n1. a\n2. a\n3. a", Ui.getListResponse(new ArrayList<>(List.of(
                new TaskStub("a"),
                new TaskStub("a"),
                new TaskStub("a")
        )), "a"));
    }

    @Test
    public void getListResponse_emptyTasks() {
        assertEquals("a", Ui.getListResponse(new ArrayList<>(), "a"));
    }
}
