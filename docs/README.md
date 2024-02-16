# MazeDeneroBot User Guide

MazeDeneroBot is a personal assistant chatbot that helps manage your tasks. It can keep track of your tasks, deadlines, and events, so you don't have to.
![image](Ui.png)
## Features

### 1. Adding Tasks

MazeDeneroBot can track three types of tasks:

- **Todo**: A task without any date/time attached to it.
- **Deadline**: A task that needs to be done before a specific date/time.
- **Event**: An event happening at a specific date/time.

#### Adding a Todo

- Format: `todo TASK_DESCRIPTION`
- Example: `todo Read book`

#### Adding a Deadline

- Format: `deadline TASK_DESCRIPTION /by DATE`
- DATE format: `YYYY-MM-DD`
- Example: `deadline Return book /by 2023-01-30`

#### Adding an Event

- Format: `event TASK_DESCRIPTION /from DATE /to DATE`
- DATE format: `YYYY-MM-DD`
- Example: `event Book Club Meeting /from 2023-02-15 /to 2023-02-16`

### 2. Listing All Tasks

To see all your tasks, you can use the list command.

- Format: `list`

### 3. Marking Tasks as Done

Once you've completed a task, you can mark it as done.

- Format: `mark TASK_INDEX`
- Example: `mark 2`

### 4. Marking Tasks as Not Done

If you mistakenly marked a task as done, you can revert it back.

- Format: `unmark TASK_INDEX`
- Example: `unmark 2`

### 5. Deleting Tasks

To remove a task from your list, you can delete it.

- Format: `delete TASK_INDEX`
- Example: `delete 3`

### 6. Finding Tasks

You can search for tasks by keywords.

- Format: `find KEYWORD`
- Example: `find book`

### 7. Exiting Program

You can exit the program by typing `bye`.

- Format: `bye`

## Usage

Below are some examples on how you can use MazeDeneroBot to manage your tasks.

### Example 1: Adding and listing tasks

Input:
```
todo Read book

deadline Submit assignment /by 2023-01-25

list
```

Expected outcome:

```
Got it. I've added this task:
  [T][ ] Read book
Now you have 1 task in the list.

Got it. I've added this task:
  [D][ ] Submit assignment (by: Jan 25 2023)
Now you have 2 task in the list.

1. [T][ ] Read book
2. [D][ ] Submit assignment (by: Jan 25 2023)
```

### Example 2: Marking a task as done

Input:
```
mark 1

list
```

Expected outcome:
```
Nice! I've marked this task as done:
  [T][X] Read book

1. [T][X] Read book
2. [D][ ] Submit assignment (by: Jan 25 2023)
```

## FAQ

**Q**: How do I transfer my data to another Computer?  
**A**: Install the app on the other computer and overwrite the empty data file it creates with the file that contains the data from your previous MazeDeneroBot home folder.


## Saving Data

MazeDeneroBot saves your tasks in the hard disk automatically after any command that changes the data. There's no need to save manually.

## Editing the Data File

MazeDeneroBot data is saved automatically as a TXT file `main/duke.txt`. Advanced users are welcome to update data directly by editing that data file.

**Caution**: If your changes to the data file make its format invalid, MazeDeneroBot will discard all data and start with an empty data file at the next run.

## Command Summary

| Action       | Format                                             |
|--------------|----------------------------------------------------|
| Add Todo     | `todo TASK_DESCRIPTION`                           |
| Add Deadline | `deadline TASK_DESCRIPTION /by YYYY-MM-DD`        |
| Add Event    | `event TASK_DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD` |
| List         | `list`                                             |
| Mark         | `mark TASK_INDEX`                                 |
| Unmark       | `unmark TASK_INDEX`                               |
| Delete       | `delete TASK_INDEX`                               |
| Find         | `find KEYWORD`                                    |
| Exit         | `bye`                                              |