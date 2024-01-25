public abstract class Task { // Adapted from Course Website
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public static Task create(String fullString) {
        // s is either `todo`, `event` or `deadline`
        String[] sTokens = fullString.split(" ", 2);
        switch (sTokens[0]) {
            case "todo":
                return new ToDo(sTokens[1]);
            case "deadline":
                // Further split the remaining string based on /by
                String[] descriptionNBy = sTokens[1].split("/by", 2);
                return new Deadline(descriptionNBy[0].trim(),
                                    descriptionNBy[1].trim());
            case "event":
                // Further split the remaining string based on /from and /to
                String[] descriptionNFromNTo = sTokens[1].split("/from |/to ", 3);
                return new Event(descriptionNFromNTo[0].trim(),
                                 descriptionNFromNTo[1].trim(),
                                 descriptionNFromNTo[2].trim());
        }
        return null; // shouldn't come here, since we done the check already before calling this factory function
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
