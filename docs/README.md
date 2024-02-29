# Damon User Guide

[Ui.png]

// Product intro goes here


## Feature 1 - Add a new Task

- Add a new Task to current TaskList.
- Command:
  - Command to add ToDo Task: todo {description}
  - Command to add Deadline Task: deadline {description} /by {due date}
    *due date should be in yyyy-mm-dd format*
  - Command to add Event Task: event {description} /from {starttime} /to {endtime)
  - Command to add FixedDuration Task: fixedduration {description} /needs {duration}


## Feature 2 - Delete a Task

- Delete the Task of given index
- Command: delete {Task index}


## Feature 3 - Mark a Task as done

- Mark the Task of given index as done
- Command: mark {Task index}


## Feature 4 - Unmark a Task to not done

- Unmark the Task of given index to not done
- Command: unmark {Task index}


## Feature 5 - Find Task by keyword

- Find Tasks including given keywords in description
- Command: find {keyword}


## Feature 6 - Exit Damon

- Say Goodbye to Damon!
- Command: bye


## Feature 7 - Save TaskList to hard disk

- Damon will automatically save TaskList in "Damon.txt" file and update the file once you change the TaskList. You may change the storage path in **iP\src\main\java\damon\gui\Main.java**, i.e., change
```java
private Damon damon = new Damon("..\\Damon.txt");
```
to
```java
private Damon damon = new Damon("put your storage path here!");
```