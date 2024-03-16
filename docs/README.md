# BadApple v1.1

Ever needed a **FAST** way to store your _things to do_ ?

We offer a simple command-line Task tracker. Enter your Tasks
_fuss free_ and quick.

![ui showcase](Ui.png)

The chatbot features OMORI characters and 
also comes with little hidden Easter eggs for OMORI fans

## Tasks

Tasks are the key to our app. Adding a Task saves it **locally** on a text file,
so that when you reopen our app it'd be there for you 

The most basic Task is a Todo task

To add a Todo Task, simply use the `todo` command
followed by a description of your task

Example: `todo get Challenger in League`

Output:
```
I'm sure you can get Challenger in League! I'll always be by your side
Todo [] get Challenger in League
```

## Adding deadlines

Use the "deadline" command to add a new task with a deadline

Enter the task description as per usual 

Then a /by followed by a **date in YYYY-MM-DD form**

Example: `deadline CS2103 ip /by 2024-02-23`

Outcome:

```
I'm sure you can CS2103 ip! I'll always be by your side
Deadline [] CS2103 ip (by: 23 Feb 2024 )
```

## Adding Events

Use the "event" command to a Task with a start and end description

Enter the Task description

Then a /from with a description

and finally a /to with a description

Example: `event Taylor Swift Concert /from May 25 2000 /to 2300`

Outcome:
```
Added Taylor Swift Concert
Event [] Taylor Swift Concert (from: May 25 2000 to: 2300)
```

## Listing your tasks
simply type `list` to see all your tasks

sample:
```
1. Todo [] get Challenger in League
2. Deadline [] CS2103 ip (by: 23 Feb 2024 )
3. Event [] Taylor Swift Concert (from: May 25 2000 to: 2300)
```

## Updating or Editing Events

Made a lil typo in your Tasks? Fret not, use the `update` command to fix it!

To update a Task, select the task number and type in the new description.

For `deadline` and `event` you _may_ use their respective 
`/by` , `/from` and `/to` labels to change those accordingly

### Example 1:

Suppose this was your List of tasks
```
1. Todo [] get Challenger in League
2. Deadline [] CS2103 ip (by: 23 Feb 2024 )
3. Event [] Taylor Swift Concert (from: May 25 2000 to: 2300)
```

to change just the description

`update 3 Coldplay Concert`

`list`

result
```
I've changed your task to Event [] Coldplay Concert (from: May 25 2000 to: 2300)

1. Todo [] get Challenger in League
2. Deadline [] CS2103 ip (by: 23 Feb 2024 )
3. Event [] Coldplay Concert (from: May 25 2000 to: 2300)
```
### Example 2:
Change just the /by of a Deadline

`update 2 /by 2024-02-26`

`list`

result
```
I've changed your task due time to 26 Feb 2024

1. Todo [] get Challenger in League
2. Deadline [] CS2103 ip (by: 26 Feb 2024 )
3. Event [] Coldplay Concert (from: May 25 2000 to: 2300)
```

## Marking tasks:
use the `mark` or `unmark` command to indicate a Task's completion

example:
`mark 2`

result:
```
Alright, I have changed your task from 
1. Todo [] get Challenger in League
2. Deadline [X] CS2103 ip (by: 26 Feb 2024 )
3. Event [] Coldplay Concert (from: May 25 2000 to: 2300)
```