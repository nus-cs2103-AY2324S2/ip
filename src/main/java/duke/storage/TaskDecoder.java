package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Decodes the storage data file into an object.
 */
public class TaskDecoder {
    /**
     * Decodes the strings to tasks for loading.
     *
     * @param lines every line in the data file
     * @return the decoded tasks
     */
    public static ArrayList<Task> decodeTask(List<String> lines) {
        ArrayList<Task> task = new ArrayList<>();
        for (String line : lines) {
            task.add(stringToTask(line));
        }
        return task;
    }

    private static Task stringToTask(String input) {
        String[] inputContent = input.split(" ", 2);
        char taskType = inputContent[0].charAt(1);
        switch (taskType) {
            case 'T':
                return isMark(new ToDo(inputContent[1]), inputContent[0]);
            case 'D':
                String ddlName = inputContent[1].split(" \\| by: ")[0];
                String[] ddlBy = inputContent[1].split(" \\| by: ")[1].split("/");
                String ddlDate = ddlBy[2] + "/" + ddlBy[1] + "/" + ddlBy[0];
                return isMark(new Deadline(ddlName, ddlDate), inputContent[0]);
            case 'E':
                String evtName = inputContent[1].split(" \\| from: ")[0];
                String evtFrom = inputContent[1].split(" \\| from: ")[1].split(" \\| to: ")[0];
                String evtTo = inputContent[1].split(" \\| to: ")[1];
                return isMark(new Event(evtName, evtFrom, evtTo), inputContent[0]);
            default:
                return null;
        }
    }

    private static Task isMark(Task task, String str) {
        if (str.charAt(4) == 'Y') {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
        return task;
    }
}
