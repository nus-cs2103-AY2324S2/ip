# Alfred User Guide

![img.png](img.png)

Welcome to Alfred, your personal aide in the world of coding. Just like the faithful butler serving the Dark Knight in the shadows of Gotham City, Alfred is here to assist you in your coding endeavors.

Alfred is a reliable, efficient, and intelligent chatbot designed to help you navigate through your coding challenges. Whether it's a simple syntax error or a complex algorithm, Alfred is equipped to provide you with the guidance you need.

Just as Alfred Pennyworth is always there to support Batman, our Alfred is here to support you in your coding journey. With Alfred by your side, you can conquer any coding challenge that comes your way. Welcome to a world where coding becomes as thrilling as a night in Gotham!

## Adding Deadlines

Alfred allows you to add deadlines to your task list.

Example: `deadline return book /by 01/12/2022 1800`

This will add a deadline task to return a book by 6pm on 1st December 2022.

```
Task added: Got it. I've added this task:
 [D][ ] return book (by: Dec 1 2022 18:00)
Now you now have 1 task in the list.
```
## Adding Todos

Alfred allows you to add todo tasks to your task list.

Example: `todo read book`

This will add a todo task to read a book.

## Adding Events

Alfred allows you to add events to your task list.

Example: `event book club /from 01/12/2022 1800 /to 01/12/2022 2000`

This will add an event task for a book club from 6pm to 8pm on 1st December 2022.

```
Task added: Got it. I've added this task:
 [E][ ] book club (from: Dec 1 2022 18:00 to: Dec 1 2022 20:00)
Now you now have 1 task in the list.
```
## Listing Tasks

Alfred allows you to list all tasks in your task list.

Example: `list`

This will list all tasks in your task list.

```
1.[T][ ] read book 
2.[D][ ] return book (by: Dec 1 2022 18:00) 
3.[E][ ] book club (from: Dec 1 2022 18:00 to: Dec 1 2022 20:00)
```

## Marking Tasks as Done

Alfred allows you to mark tasks as done.

Example: `done 1`

This will mark the first task in your task list as done.

`Nice! I've marked this task as done: 
 [T][X] read book`
## Deleting Tasks

Alfred allows you to delete tasks from your task list.

Example: `delete 1`

This will delete the first task in your task list.
```
Noted. I've removed this task: 
 [T][X] read book
You now have 0 tasks in the list.
```
## Retrieve Urgent Tasks

Alfred allows you to retrieve urgent tasks from your task list.

Example: `urgent`

This will retrieve all urgent tasks from your task list.
```
1. [D][ ] return book (by: Dec 1 2022 18:00)
2. [D][ ] defeat joker (by: Dec 2 2022 18:00)
3. [E][ ] book club (from: Dec 2 2022 18:00 to: Dec 2 2022 20:00)
```
## Find Tasks

Alfred allows you to find tasks from your task list.

Example: `find book`

This will find all tasks with the keyword "book" from your task list.
```
1. [D][ ] return book (by: Dec 1 2022 18:00)
2. [E][ ] book club (from: Dec 2 2022 18:00 to: Dec 2 2022 20:00)
```

## Find Tasks by Date

Alfred allows you to find tasks by date from your task list.

Example: `date 01/12/2022 1800`

This will find all tasks on 1st December 2022 1800 from your task list.
```
1. [D][ ] return book (by: Dec 1 2022 18:00)
2. [E][ ] book club (from: Dec 1 2022 18:00 to: Dec 1 2022 20:00)
```

## Exiting the Program

Alfred allows you to exit the program. This saves your task list to a file for future use.

Example: `bye`

This will exit the program.

```
Bye! Hope to see you again soon.
```