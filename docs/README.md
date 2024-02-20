# TheCount User Guide

The Count is a **task management GUI app**.  
The Count will help manage your tasks <ins>efficiently</ins>.

![Product Image](./Ui.png)

Keep track of your tasks using The Count!

## Adding deadlines

Example: `deadline CS3230 Assignment /by 2024-02-21` <br>
Outcome: Adds "1.[D][] CS3230 Assignment (by: Feb 21 2024)" to the existing list of tasks<br>
Format: `deadline (event) /by (yyyy-mm-dd)`

```
Ah-ah-ah! I have added ONE task:
[D][] CS3230 Assignment (by: Feb 21 2024)
You have one, two... 1 task(s)!
```

## Adding events

Example: `event Part-Time Job /from Aug 6th /to 8th`<br>
Outcome: Adds "1.[E][] Part-Time Job (from: Aug 6th to: 8th)" to the existing list of tasks<br>
Format: `event (event) /from (start-time) /to (end-time)`

```
Ah-ah-ah! I have added ONE task:
[E][] Part-Time Job (from: Aug 6th to: 8th)
You have one, two... 1 task(s)!
```

## Adding tags

Example: `tag 1 work`<br>
Outcome: Tags first task with "#work"<br>
Format: `tag (event number) (tag to add)`

```
I've tagged this task:
[D][] CS3230 Assignment #work (by: Feb 21 2024)
```

## Marking events as done

Example: `mark 2`<br>
Outcome: Marks event 2 as done<br>
Format: `mark (event number)`

```
Ah-ah-ah! ONE! I've marked this task as done:
[E][X] Part-Time Job (from Aug 6th to: 8th)
```

## Listing events

Example: `list`<br>
Outcome: Returns all tasks added so far<br>
Format: `list`

```
Here are the tasks in your list:
1. [D][] CS3230 Assignment #work (by: Feb 21 2024)
2. [E][X] Part-Time Job (from: Aug 6th to: 8th)
```
