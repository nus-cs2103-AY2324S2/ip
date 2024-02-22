# Lamball User Guide 🐐


<img src="https://github.com/ongzhili/ip/blob/master/docs/Ui.png/">


Lamball is a helpful pal that you never knew you needed. 

- [x] Helps you manage your tasks and deadlines
- [x] Guides you through the commands!
- [x] Helps you take note of anything with memos!
- [ ] Reminds you about them (coming soon!)

## Features
- Words in UPPER_CASE are parameters to be supplied by the user.<br>e.g in `todo DESCRIPTION`, `DESCRIPTION` is the parameter(s) in question, which can be used as `todo Task 1`

## ToDo List Features
### Adding a ToDo to list: `todo`
Adds a todo to the list.

Format: `todo DESCRIPTION`
- ToDos with the same description as other tasks on the list will not be added.

Examples:
- `todo User Guide`


```
Added ToDo:
    [T][ ] User Guide
  Now you have 1 tasks in the list.
```

### Adding a Deadline to list: `deadline`
Adds a deadline to the list.

Format: `deadline DESCRIPTION /by DATE`
- `DATE` provided is in the format `YYYY-MM-DD`
- `DATE` provided **cannot be before system date**
- Deadlines with the same description as other tasks on the list will not be added.

Examples:
- `deadline User Guide /by 2024-02-24`


```
Added Deadline:
    [D][ ] User Guide (by: Feb 24 2024)
  Now you have 1 tasks in the list.
```

### Adding an Event to list: `event`
Adds an event to the list.

Format: `event DESCRIPTION /from DATE1 /to DATE2`
- `DATE1`, `DATE2` provided is in the format `YYYY-MM-DD`
- `DATE1`, `DATE2` provided **cannot be before system date**
- `DATE1` **cannot be before** `DATE2` , but they can be both on the same day.
- Events with the same description as other tasks on the list will not be added.

Examples:
- `Event User Guide /from 2024-02-24 /to 2024-02-25`


```
Added Deadline:
    [E][ ] User Guide (from: Feb 24 2024 to: Feb 25 2024)
  Now you have 1 tasks in the list.
```

### Marking a task as completed: `mark`
Marks an existing task as completed.

Format: `mark INDEX`
- Marks the task at the specified `INDEX`. The index refers to the index number shown <br>
in the **full** task list.
- The task list is one-indexed.
- `INDEX` **must be a positive integer** (e.g 1,2,3)
- `INDEX` **must lie within the size of the task list**
- Marking a completed task will effectively do nothing.

Examples:
- `todo User Guide` followed by `mark 1` will result in the following output:

```
...
I have maaarked the task as done:
    [D][X] User Guide
```

### Marking a task as incomplete: `unmark`
Marks an existing task as incomplete.

Format: `unmark INDEX`
- Unmarks the task at the specified `INDEX`. The index refers to the index number<br> shown
in the **full** task list.
- The task list is one-indexed.
- `INDEX` **must be a positive integer** (e.g 1,2,3)
- `INDEX` **must lie within the size of the task list**
- Unmarking an incomplete task will effectively do nothing.

Examples:
- `mark 1` followed by `unmark 1` will result in the following output:

```
...
I have maaarked the task as undone:
    [D][ ] User Guide
```

### Deleting a task: `delete`
Deletes an existing task.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`. The index refers to the index number<br> shown
in the **full** task list.
- The task list is one-indexed.
- `INDEX` **must be a positive integer** (e.g 1,2,3).
- `INDEX` **must lie within the size of the task list**

Examples:
- `todo User Guide` followed by `delete 1` will result in the following output:
```
...
I have removed this taaask:
    [D][ ] User Guide
  Now you have 0 tasks in the list.
```
### Listing all tasks: `list`
Shows a list of all tasks.

Format: `list`
- Should not be followed by anything (even whitespaces!)

Examples:
- `list` after `todo User Guide` will result in the following:
```
Here aaaaare the taaaasks in your list:
    1. [T][ ] User Guide
```


### Locating tasks by descriptor: `find`
Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`
- The search is case-sensitive. e.g `uSeR` will not match `User`.
- Only the description is searched.
- Non-full words can be matched e.g `find er Gui` will match `User Guide`.
- Tasks with descriptions containing the keyword will be returned in list view.

Examples:
- `find user` returns nothing.
- `find er Gui` returns the `User Guide` task in the following format:

```
Here aaaaare the taaaasks in your list:
    1. [T][ ] User Guide
```

## Managing notes: Memo
Memo features are accessible by adding `memo` at the start of the command.
### Adding a memo: `memo add`
Adds a memo.

Format: `memo add MEMO`

Examples:
- `memo add Waist Size: 20`
- `memo add Jerry wants spaghetti for dinner`

### Viewing memos: `memo list`
Returns a view of all memos added.

Format: `memo list`


### Deleting a memo: `memo del`
Deletes an existing memo.

Format: `memo del INDEX`
- Deletes the memo at the specified `INDEX`. The index refers to the index number<br> shown
in the **full** memo list.
- The memo list is one-indexed.
- `INDEX` **must be a positive integer** (e.g 1,2,3).
- `INDEX` **must lie within the size of the memo list**

Examples:
`memo del 1`
### Removing all memos: `memo clear`
Clears the memo list.

Format: `memo clear`


## Miscellaneous features
### Exiting the program: `bye`
Exits the program.

Format: `bye`
- Should not be followed by anything (even whitespaces!)

### Saving the data
Lamball saved task list and memo data are saved in the hard disk automatically after anyt command that changes the data. There is no need to save manually.

### Editing the data file
Lamball data are saved as `.txt` files at:
- `[JAR File location]/src/main/java/data/list.txt` for Task list.
- `[JAR File location]/src/main/java/data/memo.txt` for Memo list.

❗ **Caution** If your changes to the data file makes the format invalid,<br>
Lamball will discard the invalid line at the next run.<br>
Hence, it is recommended to take a backup of the file before editing it.

## FAQ
**Q:** Can I transfer my data to another computer?

**A:** Run the JAR file in another computer, and overwrite the empty data file<br>
it creates with the file that contains the data of your previous Lamball home folder.

## Command Summary

| Action       | Format                                   |
| :----------  | :--------------------------------------- |
| Add todo     | `todo DESCRIPTION`                       |
| Add deadline | `deadline DESCRIPTION /by DATE`          |
| Add event    | `event DESCRIPTION /from DATE1 /to DATE2`|
| Mark         | `mark INDEX`                             |
| Unmark       | `unmark INDEX`                           |
| Delete task  | `delete INDEX`                           |
| Find         | `find KEYWORD`                           |
| List         | `list`                                   |
| Add memo     | `memo add DESCRIPTION`                   |
| Delete memo  | `memo del INDEX`                         |
| Clear memos  | `memo clear`                             |
| List memos   | `memo list`                              |
| Exit         | `bye`                                    |

