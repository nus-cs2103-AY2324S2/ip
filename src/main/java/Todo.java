class Todo extends Task {
  public Todo(String description) {
      super(description);
  }

  @Override
  public String toFileString() {
      // Format: T | [Status] | [Description]
      return "T | " + getStatusnumber() + " | " + description;
  }

  @Override
  public String toString() {
      return "[T]" + super.toString();
  }
}

