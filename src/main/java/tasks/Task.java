package tasks;

class Task {  // default access modifier
    private String description;

    Task(String description) {  // default access modifier
        this.description = description;
    }

    @Override
    public String toString() {  // default access modifier
        return description;
    }
}
