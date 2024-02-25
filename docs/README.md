# Area User Guide

![Screenshot of Ui.](Ui.png)

Area is a chatbot meant to help you keep track of your tasks whether they are todos,deadlines and events. You can also prioritise your tasks.

## Functionalities

## 1. Add a _To-do_ task.

Adds a **to-do** task.

Instruction Format: `todo <description>`

Example: `todo dinner`

*Expected output*: Adds a task with only name of task.

```
"Got it. I've added this task: [T][] dinner 0 Now you have 4 tasks in the list"
```

## 2. Add a _Deadline_ task.

Adds a **deadline** task.

Instruction Format: `deadline <description> /by <date>`

Example : `deadline submit assignment /by 2001-10-02`

Expected output: Adds a deadline  for the task, sets the date as specified by user input.

```
Got it. I've added this task: 
[D][] submit assignment 0 (by: Oct 2 2001)
Now you have 5 tasks in the list.
```

## 3. Add an _Event_ task.

Adds a **event** task.

Instruction format: `event <description> /from <start> /to <end>`

Example: `event birthday /from 10 am /to 2pm`

Expected output: Adds an event for the task with the start and end date or time specified by user input.

```
Got it. I've added this task:
[E][] birthday 0 (from: 10am to: 2pm)
Now you have 6 tasks in the list.
```

## 4. _Mark_ event.

Allows users to **mark** event as done.

Instruction Format: `mark <index>`

Example: `mark  6`

Expected Output: Marks the task at index in tasklist as done.

```
Nice! I've marked this task as done:
[E][X] birthday 0 (from: 10am to: 2pm)
```

## 5. _Unmark_ event.

Allows users to **unmark** event as done.

Instruction Format: `unmark <index>`

Example: `unmark  6`

Expected Output: Unmarks the task at index in tasklist as done.

```
OK, I've marked this task as not done yet:
[E][] birthday 0 (from: 10am to: 2pm)
```

## 6. _Lists_ all tasks

Lists all tasks including status.

Instruction Format: `list`

Expected output: Lists all the tasks in tasklist. Truncates list at 3 items if list is too long.

```
Here are the tasks:
1.[T][] dinner 5
2.[T][X] lunch 2
3.[E][] birthday 0...
```

## 7. _Delete_ task

Delete task in list based on index.

Instruction format: `delete <index>`

Example:  `delete 6`

Expected output: deltes task at index in the list.

```
Noted. I've removed this task:
[E][] birthday 0 (from: 10am to: 2pm)
Now you have 5 tasks in the list.
```

## 8. _Find_ a task matching *keyword*.

Finds tasks in the list based on keyword. Returns a list of tasks that match the keyword.

Instruction format: `find <keyword>`

Example:    
* `find brunch`
* `find lunch`

Expected output: returns a list of tasks that match the keyword.

```
Here are the tasks:
1. [T][] brunch 0
```

## 9. _Prioritise_ a task.

Prioritises a task in the list based on index and user input.

Instruction format: `priority <index> <prioritylevel>`

Example: `priority 3 2`

Expected output: Changes priority  of task with index 3 from its current value. 

## 10. _bye_ to exit the chat application.

Closes chatbot application.

Instruction format: `bye`

Expected output: Closes the chatbot with a message.

```
Bye. Hope to see you again soon!
```


