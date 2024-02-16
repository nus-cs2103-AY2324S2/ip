package actions;

import java.util.Objects;
import tasks.Task;
import ui.Duke;

public class CheckDuplicate {
    public static boolean checkDuplicate(Duke bot, String desc, String type) {
        assert(bot != null);
        assert(bot.getTaskList() != null);
        boolean isDuplicate = false;
        for (Task task : bot.getTaskList().getList()) {
            if (Objects.equals(task.getDescription(), desc) && Objects.equals(task.getTypeIcon(), type)) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }
}
