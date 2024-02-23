# Lery User Guide
> The biggest and greatest chatbot in the whole wide world!!


![Screenshot of Lery chatbot GUI](./Ui.png)


Welcome to the world of Lery 🐩 , your trusty canine chatbot companion!
You can trust Lery the Dog, to store all your upcoming tasks and deadlines safely and securely.




# Features
🐩 can keep track of these types of tasks
1. Todo tasks
2. Deadline tasks
3. Events

🐩 can also
- **Sort** tasks (by their type and deadline)
- **Find** tasks (by their task name)
- **Delete** tasks 
- **Mark** tasks as done (or unmark)
- **List** all the tasks 



## Todo
Syntax:
`todo <task name>`

Use: 
Add a todo task to the list

Example: `todo submit CS2106 quiz`

Expected response:
```
Woof! Got it. I've added this task:
[T][] submit CS2106 quiz
Now you have 1 tasks in the list.
```

## Deadline
Syntax:
`deadline <task name> /by <date>`

Use:
Add a deadline task to the list and deadline set to *19th Feb 2024*.

> [!IMPORTANT]
> Dates must follow this format: yyyy-MM-dd.

Example: `deadline CS2103 increment /by 2024-02-19`

Expected response:
```
Woof! Got it. I've added this task:
[D][] CS2103 increment (by: Feb 19 2024)
Now you have 1 tasks in the list.
```

## Event
Syntax:
`event <task name> /from <day of the week> <time> /to <time>`

Use:
Add a deadline task to the list and deadline set to *19th Feb 2024*.


Example: `event project meeting /from Mon 2pm /to 4pm`

Expected response:
```
Woof! Got it. I've added this task:
[E][] project meeting (from: Mon 2pm to: 4pm)
Now you have 1 tasks in the list.
```

## Sort
Syntax:
`sort`

Use:
Sorts all the tasks in the list by their type and deadline

Expected response:
```
Woof! List is sorted by their deadline and task type.
```

## Find
Syntax:
`find <string>`

Use:
Find all the tasks that contain the string in the input.

Example: `find project`

Expected response:
```
Woof! Here are the tasks in your list:
1.[E][ ] project meeting (from: Mon 2pm to: 4pm)
```

## Delete
Syntax:
`delete <task number>`

Use:
Delete the task corresponding to the task number from the tasklist.

Example: `delete 1`

Expected response:
```
Woof! I've removed this task:
[T][ ] submit CS2106 quiz
Now you have 2 tasks in the list.
```

## Mark and Unmark
Syntax:
`mark <task number>` or
`unmark <task number>`

Use:
Mark the task as completed in the tasklist.
Or unmark the task to indicate that the task has not been completed.

Example: `mark 1` or `unmark 1`

Expected response:
```
Woof! I've marked this task as done:
[X] submit CS2106 quiz
```
or 
```
Woof! I've marked this task as not done yet:
[] submit CS2106 quiz
```

## List
Syntax:
`list`

Use:
List out all the tasks in a form of a numbered list

Expected response:
```
Woof! Here are the tasks in your list:
1.[T][ ] submit CS2106 quiz
2.[D][ ] CS2103 increment (by: Feb 19 2024)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
```


