# Tommy User Guide
![Ui.png](Ui.png)

Never miss a deadline again!
Tommy is a desktop app that you can use to keep track of your tasks and deadlines. All commands in the app are 
case-insensitive.

## Adding todos
Add todo tasks your list of tasks. When the todo task is added to the list, it will be marked with a `[T]` symbol
and as not done by default.

Example: `todo buy apples`

Output:
```
Got it. I've added this task:
    [T][ ] buy apples
Now you have 1 tasks in the list.
```

## Adding deadlines
Add tasks with deadlines to your list of tasks. You can specify the date and time of the deadline. When the deadline
task is added to the list, it will be marked with a `[D]` symbol and as not done by default.

Example: `deadline return book /by 20/01/2024`

Output:
```
Got it. I've added this task:
    [D][ ] return book (by: 20 Jan 2024)
Now you have 2 tasks in the list.
```

## Adding events
Add events to your list of tasks. You can specify the date and time of the start and end of the event task. 
When the event is added to the list, it will be marked with a `[E]` symbol and as not done by default.

Example: `birthday party /from 20/01/2024 /to 21/01/2024`

Output:
```
Got it. I've added this task:
    [E][ ] birthday party (from: 20 Jan 2024 to: 21 Jan 2024)
Now you have 3 tasks in the list.
```

## Listing all events
List all the tasks in the list. The tasks will be listed in the order they were added. Tasks that are
done will be marked with a `[X]` symbol and tasks that are not done will be marked with a `[ ]` symbol.
Tasks are also marked by their types `[T]` for todo, `[D]` for deadline and `[E]` for event.

Example: `list`

Output:
```
Here are the tasks in your list:
1. [T][ ] buy apples
2. [D][ ] return book (by: 20 Jan 2024)
3. [E][ ] birthday party (from: 20 Jan 2024 to: 21 Jan 2024)
```

## Find events with matching keywords
Find all the tasks in the list that contain the keyword. The tasks will be listed in the order they were added.

Example: `find Jan`

Output:
```
Here are the matching tasks in your list:
1. [D][ ] return book (by: 20 Jan 2024)
2. [E][ ] birthday party (from: 20 Jan 2024 to: 21 Jan 2024)
```

## Mark tasks as done
Set the status of the task as done. The task will be marked with a `[X]` symbol. The tasks are referenced by their index
in the list.

Example: `mark 1`

Output:
```
Nice! I've marked this task as done:
    [T][X] buy apples
```

## Mark tasks as not done
Set the status of the task as not done. The task will be marked with a `[ ]` symbol. The tasks are referenced by their 
index in the list.

Example: `unmark 1`

Output:
```
Nice! I've marked this task as done:
    [T][ ] buy apples
```

## Delete tasks
Remove the task from the list. The tasks are referenced by their index in the list.

Example: `delete 1`

Output:
```
Noted. I've removed this task:
    [T][ ] buy apples
You now have 2 tasks in the list.
```

## Terminate the application
Exit the application.

Example: `bye`

Output:
```
Bye. Hope to see you again soon!
```