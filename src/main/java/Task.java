public abstract class Task { // Adapted from Course Website
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public static Task create(String fullString) throws CreateTaskException {
        // s is either `todo`, `event` or `deadline`
        String[] sTokens = fullString.split(" ", 2);
        if (sTokens.length < 2) {
            throw new CreateTaskException("soo what exactly are you trying to do? :)");
        }
        String command = sTokens[0];
        String taskString = sTokens[1];
        switch (command) {
            case "todo":
                return new ToDo(taskString);
            case "deadline":
                // Further split the remaining string based on /by
                if (!taskString.contains("/by")) {
                    throw new CreateDeadlineException(
                            "You can't expect me to track a deadline if you don't give me the.. deadline right? haha\n" +
                                    "please include '/by' followed by the deadline :)");
                }
                String[] descriptionNBy = taskString.split("/by");
                if (descriptionNBy.length > 2) {
                    throw new CreateDeadlineException("Don't procrastinate with multiple deadlines !! :)");
                }
                return new Deadline(descriptionNBy[0].trim(),
                                    descriptionNBy[1].trim());
            case "event":
                // Further split the remaining string based on /from and /to
                if (!taskString.contains("/from") || !taskString.contains("/to")) {
                    throw new CreateEventException(
                            "I'm not sure how you're going for an event without a start time or end time\n" +
                                    "please include '/from' and '/to' so I can keep track for you :)");
                }
                if (taskString.indexOf("/from") > taskString.indexOf("/to")) {
                    throw new CreateEventException("Sorry! /from must appear before /to :)");
                }
                String[] descriptionNFromNTo = taskString.split("/from |/to ");

                if (descriptionNFromNTo.length > 3) {
                    throw new CreateEventException("um.. I'm not sure how I can track for you an event that has\n" +
                            "more than 1 start or end time! :)");
                }
                return new Event(descriptionNFromNTo[0].trim(),
                                 descriptionNFromNTo[1].trim(),
                                 descriptionNFromNTo[2].trim());
        }
        return null; // shouldn't come here, since we have done the check already before calling this factory function
    }
    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                                isDone ? "X" : " ",
                                this.description);
    }
}
