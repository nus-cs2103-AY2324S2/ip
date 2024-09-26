# CorgiBot User Guide
___
CorgiBot is a chatbot that helps you manage your tasks, deadlines and events.
It is built with a simple to use interface and easy to understand commands. 

![Screenshot of GUI](./Ui.png)
___

## Features
CorgiBot will track all the tasks you want to record!

### `Todo` 
Add a to-do task to the list<br/>
Example of usage:

`todo <TASK_NAME>`

Expected output

```
Got it. I've added this task:
<TASK_DESCRIPTION>
Now you have <NUMBER_OF_TOTAL_TASKS> tasks in the list.
```

### `Deadline`
Add a task with a deadline to the list <br />
Example of usage:

`deadline <TASK_NAME> /by <DUE_DATE IN D/M/YYYY HHMM FORMAT>`

Expected output:

```
Got it. I've added this task:
<TASK_DESCRIPTION>
Now you have <NUMBER_OF_TOTAL_TASKS> tasks in the list.
```

### `Event` 
Add an event Task to the list <br />
Example of usage:

`event <TASK_NAME> /from <START_DATE IN D/M/YYYY HHMM FORMAT> /to
<END_DATE IN D/M/YYYY HHMM FORMAT>`

Expected output:

```
Got it. I've added this task:
<TASK_DESCRIPTION>
Now you have <NUMBER_OF_TOTAL_TASKS> tasks in the list.
```

### `Delete`
Delete a task from the list <br />
Example of usage:

`delete <INDEX_OF_TASK>`

Expected output:

```
Noted. I've removed this task:
<REMOVED_TASK_DESCRIPTION>
Now you have <REMAINING_NUMBER> tasks in the list.
```

### `List`
Check the list of all tasks to be done <br />
Example of usage:

`List`

Expected output:

```
Here are the tasks in your list:
1.<TASK1_DESCRIPTION>
2.<TASK2_DESCRIPTION>
3.<TASK3_DESCRIPTION>
```

### `MarkDone`
Mark a task as done <br />
Example of usage:

`mark <INDEX_OF_TASK>`

Expected output:
```
Nice! I've marked this task as done:
[X] <TASK_DESCRIPTION>
```

### `MarkUnDone`
Mark a task as not done <br />
Example of usage:

`unmark <INDEX_OF_TASK>`

Expected output:

```
OK, I've marked this task as not done yet:
[ ] <TASK_DESCRIPTION>
```

### `Find` 
Find a task from your list <br />
Example of usage:

`find (any string intended to search for)`

Expected output:

```
Here are the matching tasks in your list:
1.<TASK1_DESCRIPTION>
2.<TASK2_DESCRIPTION>
3.<TASK3_DESCRIPTION>
```

### `Reminder`
Get reminder on the upcoming deadlines <br />
Example of usage:

`reminder`

Expected output:

```
Please be reminded that you have upcoming deadlines:
1.<DEADLINE1_DESCRIPTION>
2.<DEADLINE2_DESCRIPTION>
3.<DEADLINE3_DESCRIPTION>
```

### `Bye`
Exit the chatBot. <br/>
Example of usage:

`Bye`

Expected output:
```
Bye. Hope to see you again soon!
```