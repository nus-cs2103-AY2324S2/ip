# Campus User Guide
![Ui.png](..%2F..%2FUi.png)

Welcome to Campus, your friendly state of the art chatbot servant. Below is a simple guide to help you get started with using Campus. Campus is a chatbot designed to assist you with managing your daily errands and todos. By instructing Campus, you will be able to describe, list and complete the tasks that are most important to you for a productive day ahead.

## Getting started
Campus is a fully text-cli driven chatbot, the Ui available shows a preview of what a conversation with the chatbot might look like. For its full list of commands, simply type in 'help'.

# Available Commands
We will be going through some of the basic and most common commands available at your disposal.

## Adding Todos
A todo is a task that requires your completion, and requires a NAME parameter.

Usage: `todo NAME`

Example: `todo buy groceries`

Expected Output:
```angular2html
Your directive has been duly noted. The task has been successfully expunged from our records. Should there be any further matters requiring attention or if new tasks arise, do not hesitate to relay them, and they shall be handled with utmost care and efficiency.
added: [T] [ ] buy groceries
Now you have 1 task(s) in the list.
```


## Adding Deadlines
A deadline is a task that requires your completion by a certain date. It requires both a NAME parameter and a END_DATE in HHmm DD/MM/YYYY format.

Usage: `deadline NAME /by END_DATE`

Example: `deadline complete assignment /by 2359 01/01/2024`

Expected Output:
```angular2html
Your directive has been duly noted. The tas has been successfully expunged from our records. Should there be any further matters requiring attention or if new tasks arise, do not hesitate to relay them, and they shall be handled with utmost careand efficiency.
added: [D] [ ] complete assignment (by: 2359 01/01/2024)
Now you have 1 task(s) in the list.
```

## Adding Events
An event is a task that requires your attendance. It has a NAME, START_DATE and END_DATE as the required parameters. Bear in mind that both the START_DATE and END_DATE are to be in HHmm DD/MM/YYYY format.

Usage: `event NAME /from START_DATE /to END_DATE`

Example: `event floor hotpot /from 1900 08/02/2001 /to 2200 08/02/2001`

Expected Output:
```
Your directive has been duly noted. The tas has been successfully expunged fromour records. Should there be any further matters requiring attention or if new tasksarise, do not hesitate to relay them, and they shall be handled with utmost careand efficiency.
added: [E] [ ] floor hotpot (from: 1900 08/02/2001 to: 2200 08/02/2001)
Now you have 1 task(s) in the list.
```

## Displaying Your Tasks
In order to see all the tasks that you have added, type in the `list` command.

Usage: `list`

Example Output:
```angular2html
1. [T] [ ] buy groceries
2. [D] [ ] complete assignment (by: 2359 01/01/2024)
3. [E] [ ] floor hotpot (from: 1900 08/02/2001 to: 2200 08/02/2001)
```

## Marking and Unmarking Tasks
You might have noticed that each task entry has two boxes. The first box indicates the type of task (T - Todo, D - Deadline, E - Event), followed by a second box. This is the status of the task, whether it is completed or uncompleted. To mark a task completed or uncompleted, use the `mark` or `unmark` command to your needs.

Usage: `mark NUMBER`, `unmark NUMBER`. `NUMBER` here refers to the task number in the list.

Example: `mark 1`

Expected Output:
```
Splendid news! Your accomplishment is a testament to your diligence and skill. Should you require further assistance or have additional tasks to undertake, do not hesitate to summon me.
[T] [X] buy groceries
```

Calling `list` again will reflect that your first task has been marked as completed.
```angular2html
1. [T] [X] buy groceries
2. [D] [ ] complete assignment (by: 2359 01/01/2024)
3. [E] [ ] floor hotpot (from: 1900 08/02/2001 to: 2200 08/02/2001)
```

## Find
Suppose your list is very long (you must be a busy person...), Campus allows for you to search for a particular entry by the NAME field. This `find` command searches by substring - which means that if your task was `"dinner outing with family"`, using `find "outing"` will return this entry.

Usage: `find KEYWORD`. `KEYWORD` is the specific substring or well...keyword that you want to search for.

Example: `find hotpot`

Expected Output:
```angular2html
1. [E] [ ] floor hotpot (from: 1900 08/02/2001 to: 2200 08/02/2001)
```

## Delete
Say you want to remove an entry, simply use the `delete` command.

Usage: `delete NUMBER`, where `NUMBER` here refers to the task number in the list. 

Example: `delete 2`

Expected Output:
```angular2html
Your directive has been duly noted. The task has been successfully expunged
from our records. Should there be any further matters requiring attention or if
new tasks arise, do not hesitate to relay them, and they shall be handled with utmost
care and efficiency.
[D] [ ] complete assignment (by: 2359 01/01/2024)
Now you have 2 task(s) in the list.
```

## Exiting Campus
Say that you are done with a long days work and you are ready to retire to bed. To exit Campus, simply type the `exit` command.

Usage: `exit`

That's it! You are now ready to start using Campus and enjoy the benefits of having a helpful chatbot to track your daily tasks at your service. If you have any further questions or need assistance, feel free to ask at any time. Happy chatting and stay productive!