# Lia User Guide

![Lia](Ui.png)

Lia helps you keep track of everything that you need to do. It's,

* Just like texting a friend
* Except that it's not
* Unless your friend is a MIPS processor
* Whose name is Lia
* Then I guess it's kind of like texting your friend


## Adding and Listing Out Tasks

### Add a Todo Task
Adds a task

Example: todo (description)

```
[T][ ][ ] (description)
```

### Add a Deadline Task
Adds a task with a deadline

Example: deadline (description) /by yyyy-MM-dd

```
[D][ ][ ] (description) (by: MMM dd yyyy)
```

### Add an Event Task
Adds a task with a start and end

Example: event (description) /from (start) / to (end)

```
[E][ ][ ] (description) (from: start to: end)
```

### List out Tasks
Lists out all tasks

Example: list

```
1. [T][ ][ ] (description)
2. [D][ ][ ] (description) (by: MMM dd yyyy)
3. [E][ ][ ] (description) (from: start to: end)
```

## Marking and Deleting Tasks

### Mark a Task as Done
Marks task at specified position as done

Example: mark (pos)

```
[T][X][ ] (description)
```

### Mark a Task as Not Done
Marks task at specified position as not done

Example: unmark (pos)

```
[T][ ][ ] (description)
```

### Mark a Task as Important
Marks task at specified position as important

Example: imp (pos)

```
[T][ ][!] (description)
```

### Delete a Task
Deletes task at specified position

Example: delete (pos)

## Finding a Task and Exiting

### Find a Task
Finds a task with specified keyword

Example: find (keyword)

```
1. [T][ ][ ] (keyword)
2. [D][ ][ ] (keyword) (by: MMM dd yyyy)
3. [E][ ][ ] (keyword) (from: start to: end)
```

### Exit the Program
Ends the conversation

Example: exit
