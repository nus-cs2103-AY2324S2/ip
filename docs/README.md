<!-- @format -->

# Ciara - High performance tasks manager solution

![Screenshot of Ciara GUI](Ui.png)

> - This project is under **active** development
> - There will be bugs and breaking changes
> - **Do not use this app as the sole way of storing important tasks**

Ciara is a simple tasks command line based tasks manager developed with speed as the main focus.

## Features

- Text-based
- Low learning curve
- FAST to use

## Getting Started

1. Grab the latest release from [here](https://github.com/RyanNgWH/ip/releases)
1. Launch the application
1. Add your tasks
1. Profit!

---

Alternatively, you could launch it from the command line with the following command:

```bash
java -jar ciara.jar
```

> This project was developed with `Java 11` and is not guaranteed to work with other versions of Java

## Supported Commands

```bash
usage:    <command> <arguments>
commands:
    bye                                   # Exit the application
    list      [/archive] [/date]          # List all tasks (choose between archived or not) in the application (with an optional date filter - in the format YYYY/MM/DD)
    mark      {index}                     # Mark the task as completed
    unmark    {index}                     # Mark the task as not completed
    archive   {index}                     # Archive the task
    unarchive {index}                     # Unarchive the task
    delete    {index}                     # Delete the task
    todo      {description}               # Create a todo with the specified description
    deadline  {description} {/by}         # Create a deadline with the specified description and deadline
    event     {description} {/from} {/to} # Create an event with the specified description and duration
```

## Sample Commands

### List

```bash
list                            # List all tasks
list /archive                   # List all archived tasks
list /date 2024/12/12           # List all tasks occuring on 12 Dec 2024
list /archive /date 2024/12/12  # List all archived tasks occuring on 12 Dec 2024
```

### Mark & Unmark

```bash
mark 1      # Mark the 1st task (as shown in the `list` command)

unmark 2    # Unmark the 2nd task (as shown in the `list` command)
```

### Archive, Unarchive & Delete

```bash
archive 1   # Archive the 1st task (as shown in the `list`` command)

unarchive 2 # Unarchive the 2nd task (as shown in the `list /archive` command)

delete 3    # Delete the 3rd task (as shown in the `list`` command)
```

### Todo, Deadline & Event

```bash
todo buy bread                                                      # Create a new "Todo" task with the description "buy bread"

deadline project submission /by 2024/02/23 14:00                    # Create a new "Deadline" task with the description "project submission" that is due on 23 Feb 2024 at 2pm

event taengoo concert /from 2024/02/24 17:00 /to 2024/02/24 20:00   # Create a new "Event" task with the description "taengoo concert" that occurs from 23 Feb 2024 at 5pm to 8pm
```

## Roadmap

- [x] Managing tasks
- [x] Managing deadlines
- [ ] Reminders (coming soon)
