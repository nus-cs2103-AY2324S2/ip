# Toothless User Guide


<img width="302" alt="image" src="https://github.com/timothysashimi/ip/assets/120008429/389cdb3d-d51f-4426-9a5c-3e4261203eba">


Have you ever wanted your personal dragon chatbot to help you keep track of tasks with or without deadlines? Fret not, for Toothless is here for you!

## Adding deadlines

Toothless can help you keep track of your deadlines using a simple command line.

For example, if you have a deadline to return a book by Sunday, you can input "deadline return book /by Sunday".
The command will start with deadline, followed by the task name and the deadline, separated by a "/by".

The expected output would be:

Got it. I've added this task:
[D][] return book (by: Feb 18 2024)
Now you have 1 task in the list.

## Marking tasks

Toothless can help you keep mark tasks you have completed using a simple command line.

For example, if you have a completed the deadline task "return book", you can input "mark 1"
The command will start with mark, followed by the task index.

The expected output would be:

Nice! I've marked this task as done!
[D][X] return book (by: Feb 17 2024)


Here are examples of how you can use other Toothless's commands:

Finding tasks
find book

Adding a Todo
todo read book

Adding an Event
event project meeting /from 12/2/2024 /to 12/2/2024

Listing all Tasks
list

Unmarking a Task
unmark 1

Deleting a Task
delete 1


Exiting the App
bye
