# Pan
Pan is a task assistant that helps you to manage and track your tasks, deadlines and events.
Pan packs a simple yet robust interface, ensuring a seamless user experience.
With Pan in your life, you can expect your daily productivity to double!

## Features
> **Case Sensitivity**
> Do note that the command keywords are case-sensitive!
> For Tasks Descriptions, the case doesn't matter which means that you can enter it the way u want it to show in the window!

### 1. UI Responses
- **Hello by Pan**
- **Bye by Pan**

### Hello by Pan
Whenever you launch Pan, Pan would greet you!

```
Hello! I'm Pan
What can I do for you?
```

### Bye by Pan
This is a command that would ask Pan to say **"bye"** to you.

Example format:

`bye`

```
Bye. Hope to see you again soon!
```

### 2. Manage Your Tasks
- **Add a Task**
- **List Tasks**
- **Mark Task**
- **Unmark Task**
- **Delete a Task**
- **Find a Task**

## Tutorial

###  Add a task
This comamnd would add a task of a given todo, deadline or event.

Example formats:

`todo description`

`deadline description /by dateBy`

`event description /from dateFrom /to dateTo`

```
Got it. I've added this task:
...
Now you have (x) task(s) in the list.
```

### `list` - View all your tasks that are currently within the task list
View from the window all the tasks that are currently inside the list alongside their corresponding dates (if any) and their completion status thus far.

Example on how to use this command:

`list`

```
Here are the tasks in your list:
    [D][X] sleep (by: Dec 03 2024)
    [E][X] play (from: Jan 12 2024 to: Dec 02 2024)
    [E][X] study (from: Jan 01 2024 to: Dec 01 2024)
```

### `mark` - Mark a task
Mark a task as complete.

Example on how to use this command:

`mark index_number`

> **Take Note**
> 
> Using the `list` command would allow you to know what is the index number of a given task.
> For Example, if the tasklist have 1 task, we can only mark using the `index_number` **1**

```
Nice! I've marked this task as done:
    [T][X] some Todo task
```

### `unmark`- Unmark a task
Unmark a task as incomplete.

Example on how to use this command:

`unmark index_number`

> **Take Note**
> 
> Using the `list` command would allow you to know what is the index number of a given task.
> For Example, if the tasklist have 1 task, we can only unmark using the `index_number` **1**

```
OK, I've unmarked this task:
    [T][] some Todo task
```

### `delete` - Remove a task
Deletes a given task
 
Example on how to use this command:

`delete index_number`

> **Take Note**
> 
> Using the `list` command would allow you to know what is the index number of a given task.
> For Example, if the tasklist have 1 task, we can only delete using the `index_number` **1**

```
Noted. I have removed this task:
    [T][X] some Todo task
Now you have (x) task in the list.
```

### `find` - Search for a task using a keyword
Returns all tasks in the task list that matches the input keyword

Example on how to use this command:

`find keyword`

```
Here are the matching tasks in your list:
    [D][X] sleep (by: Dec 03 2024)
    [E][X] study (from: Jan 01 2024 to: Dec 01 2024)
```

> **Take Note**
> 
> When using the `find` command, Pan is case-sensitive.
> In other words, the keyword of `S` would not be matched with a task that contains `s` character unless it contains `S` character.