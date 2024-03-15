package sleepy.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlannedTaskTest {
    @Test
    public void getDescription_normalPlan_success() {
        PlannedTask testPlannedTask = new PlannedTask("revise content", "Week 6");
        assertEquals("[P][ ] revise content (after: Week 6)", testPlannedTask.getDescription());
    }
    @Test
    public void getDescription_dateChange_success() {
        PlannedTask testPlannedTask = new PlannedTask("tp v1.2", "2024-02-21");
        assertEquals("[P][ ] tp v1.2 (after: 21 Feb 2024)", testPlannedTask.getDescription());
    }
}
