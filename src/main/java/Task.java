public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[Y] " : "[N] "; //mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + this.name;
    }

    public static Task stringToTask(String input) {
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
