package Objects;

/**
 * Task class which has basic functionalities and fields of a task
 */
public class Task {
    private String name;
    private boolean isMarked = false;
    public Task(String name,boolean isMarked) {
        this.name = name;
        this.isMarked = isMarked;
    }

    public String getName() {
        return name;
    }
    public boolean getMark() {
        return isMarked;
    }
    public void markMark() {
        this.isMarked = true;
    }

    public void unmarkMark() {
        this.isMarked = false;
    }


    @Override
    public String toString() {
        if (getMark()) {
            return "[X] " + getName();
        } else {
            return "[ ] " + getName();
        }
    }

    public String toStringFile() {
        if (getMark()) {
            return "1|" + getName();
        } else {
            return "0|" + getName();
        }
    }
}
