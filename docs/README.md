# Mitsuki User Guide

![Screenshot of product](Ui.png)

Hello, I am Mitsuki, a chatbot who helps you to manage your tasks!

Please refer to the details below on how to give me commands.

## Help page

Display a list of commands you can give me.

Example: `help`

Expected output:
```
Here is a list of commands you can give me:
1. help
2. list
3. todo
4. deadline
5. event
6. fixed
7. mark
8. unmark
9. delete
10. bye
11. find
```

## Viewing your task list

Display your current task list.

Example: `list`

This displays your current task list to you.

Expected output (of an example list):
```
Here are the items in your list:
1. [T][ ] play with Mikazaki
2. [D][X] master rasengan (By 20 Dec 2024)
3. [E][X] play Gemaki (From 2pm to 5pm)
4. [F][ ] vandalise hokage stone faces (Needs 2 hours)
```
## Adding todos

Add a todo item to your task list.

Example: `todo play with Mikazaki`

This adds a todo item 'play with Mikazuki' to your list.

Expected output:
```
OK! I have aded the task 'play with Mikazuki' to your list! :)
Now you have 1 task(s) in the list.
```
## Adding deadlines

Add a deadline item to your task list.

Example: `deadline master rasengan /by 20/12/2024`

This adds a deadline item 'master rasengan (By 20 Dec 2024)' to your list.

Expected output: 

```
OK! I have added the task 'master rasengan' to your list! :)
Now you have 2 task(s) in the list.
```

## Adding events

Add an event item to your task list.

Example: `event play Gemaki /From 2pm /to 5pm`

This adds an event item 'play Gemaki (From 2pm to 5pm)' to your list.

Expected output:
```
OK! I have added the task 'play Gemaki' to your list! :)
Now you have 3 task(s) in the list.
```


## Adding fixed duration tasks

Add a fixed duration task to your task list.

Example: `fixed vandalise hokage stone faces /2 hours`

This adds a fixed duration task 'vandalise hokage stone faces (Needs 2 hours)' to your list.

Expected output:
```
OK! I have added the task 'vandalise hokage stone faces' to your list! :)
Now you have 4 task(s) in the list.
```

## Marking a task as done

Mark a task as done.

Example: `mark 1`

This marks the first task on your list as done.

Expected output:
```
Ok, I have marked this task as done. :D
[T][X] play with Mikazuki
```

## Marking a task as not done

Mark a task as not done.

Example: `unmark 1`

This marks the first task on your list as not done.

Expected output:
```
Ok, I have marked this task as not doneyet. :O
[T][ ] play with Mikazuki
```

## Deleting a task 

Delete a task from your list.

Example: `delete 4`

This deletes the fourth task on your list.

Expected output:
```
Alright, I have removed the following task from your list:
[F][ ] vandalise hokage stone faces (Needs 2 hours)
Now you have 3 task(s) in the list.
```

## Finding a task

Find a task related to the given keyword.

Example: `find Mikazuki`

This displays all tasks in your list that are related to the keyword 'Mikazuki'.

Expected output:
```
Here are the tasks related to: Mikazuki
1. [T][ ] play with Mikazuki
```

## Saying goodbye

Say goodbye and save your task list automatically.

Example: `bye`

This says goodbye and saves your task list for future reference.

Expected output:
```
I have saved your list for future reference. :D
Bye! Hope to see you again soon!
```