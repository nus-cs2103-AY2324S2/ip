# Damon User Guide

[Ui.png]

// Product intro goes here


## `rgb(153, 51, 2255)` Feature 1 - Add a new Task

- Add a new Task to current TaskList.
- Command:
  - Command to add ToDo Task: todo {description}
  - Command to add Deadline Task: deadline {description} /by {due date}
    *due date should be in yyyy-mm-dd format*
  - Command to add Event Task: event {description} /from {starttime} /to {endtime)
  - Command to add FixedDuration Task: fixedduration {description} /needs {duration}


## `rgb(153, 51, 2255)` Feature 2 - Delete a Task

- Delete the Task of given index
- Command: delete {Task index}


## `rgb(153, 51, 2255)` Feature 3 - Mark a Task as done

- Mark the Task of given index as done
- Command: mark {Task index}


## `rgb(153, 51, 2255)` Feature 4 - Unmark a Task to not done

- Unmark the Task of given index to not done
- Command: unmark {Task index}


## `rgb(153, 51, 2255)` Feature 5 - Find Task by keyword

- Find Tasks including given keywords in description
- Command: find {keyword}


## `rgb(153, 51, 2255)` Feature 6 - Exit Damon

- Say Goodbye to Damon!
- Command: bye


## `rgb(153, 51, 2255)` Feature 7 - Save TaskList to hard disk

- Damon will automatically save TaskList in "Damon.txt" file and update the file once you change the TaskList. You may change the storage path in **iP\src\main\java\damon\gui\Main.java**, i.e.,
```java
private Damon damon = new Damon("put your storage path here!");
```