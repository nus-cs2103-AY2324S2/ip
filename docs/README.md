# Chingu User Guide

![img.png](Ui.png)

Chingu (친구) means a friend in Korean. 
Like a friend, it can help you to list your tasks 
that you have to work on. Just like any other task app,
you can use Chingu to update yourself on minimal 
list of tasks you need to work on.

## Exiting Chingu
Exits from Chingu program.

Format: `bye`

## Adding Todos
Adds a new Todo task to the Tasklist.

Format: `Todo [description] /priority [priority-level]`

**Examples and Expected output**:

Todo make a soup for James /priority low
```
Expected output from Chingu:

Got it, my friend! I've added this task:
    [T][ ] Make a soup for James Priority: low
You have <number> tasks in the list.
```

## Adding Deadlines
Adds a new Deadline task to the Tasklist.

Format: `Deadline [description] /by [by-date] /priority [priority-level]`

**Examples and Expected output**:

Deadline submit the Research Project Final Report /by 2024/05/30 /priority High
```
Expected output from Chingu:

Got it, my friend! I've added this task:
    [D][ ] submit the Research Project Final Report Priority High (by: May 30 2024)
You have <number> tasks in the list.
```

// Describe the action and its outcome.

// Give examples of usage

## Adding Events
Adds a new Event task to the Tasklist.

Format: `Event`

**Examples and Expected output**:

Event NUS Football ICG /from 2024/02/11 1200 /to 2024/02/11 2100 /priority High
```
Expected output from Chingu:

Got it, my friend! I've added this task:
    [E][ ] NUS FOOTBALL ICG Priority: High (from: Feb 11 2024 1200 to: Feb 11 2024 2100)
You have <number> tasks in the list.
```

## List Tasks
Shows a list of all the Tasks in the Tasklist.

Format: `list`

**Expected output Example**:

```
Expected output from Chingu:

Here are the tasks in your list:
1. [T][] make a soup for James Priority: low
2. [D][] submit the Research Project Final Report Priority High (by: May 30 2024)
3. [E][] NUS FOOTBALL ICG Priority: High (from: Feb 11 2024 1200 to: Feb 11 2024 2100)
```

## Marking Task
Marks a Task as completed Task

Format: `Mark [\index]`

**Expected output Example**:

Mark 1
```
Expected output from Chingu:

Nice! I've marked this task as done:
    [T][X] make a soup for James Priority: low
```

## Unmarking Task
Unmarks a Task as uncompleted Task

Format: `Unmark [\index]`


**Examples and Expected output**:

Unmark 1
```
Expected output from Chingu:

OK, I've marked this task as not done yet:
    [T][ ] make a soup for James Priority: low
```

## Delete Task
Delete a Task from the Tasklist

Format: `Delete [\index]`

**Examples and Expected output**:

Delete 3
```
Expected output from Chingu:

Noted. I've removed this task:
    [E][] NUS FOOTBALL ICG Priority: High (from: Feb 11 2024 1200 to: Feb 11 2024 2100)
```

## Find Tasks
Finds Tasks which have content that contains the given keywords.

Format: `Find <keywords>`

**Examples and Expected output**:

Find Report
```
Here are the matching tasks in your list:
1. [D][] submit the Research Project Final Report Priority High (by: May 30 2024)
```

## Credits

I acknowledge that portions of the source code are 
referenced from CS2103T website, SE-EDU, Javatpoint, W3Schools website and my teammate 
(Wang Lifu - Github Profile: @LifHoshi) for following purposes.
- CS2103T website and SE-EDU: For most deliverable sample codes including Java FXML
- JavaTpoint: Checking how exception classes are implemented
- W3Schools: Writing and reading files that concerns saving and loading tasks from data
- Wang Lifu: regarding the part on saving file correctly (especially for Jar file)

I have done my upmost best to add comments to parts of code (except for CS2103T website and SE-EDU)
where references to above sources have been made in this repository.