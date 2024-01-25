---
runme:
  id: 01HMZD4NRPP8TSXCEWV9VGZEMC
  version: v2.2
---

# User Guide

## Features

### Feature-Greetings & Farewells

Chat bot is able to say greetings and farewells upon starting or ending a conversation.

### Feature-Storage

Chat bot is able to store and list out tasks from the user via the "store" and "list" commands.

### Feature-Mark & Unmark as done

Chat bot is able to modify stored tasks as done or undone.

## Usage

### `add *` or `a *` - Add a task, where * is the name of the task

Stores a string into the storage.

Example of usage:

`store userinputedstring` or `s userinputedstring`

Expected outcome:

Bot replies with a confirmation message and stores the string.

```sh {"id":"01HMZD4NRPP8TSXCEWV6YPTZE0"}
| User Input:
> s read book
| Sure I'll store it right away!
```

### `list` or `l` - Lists out all stored tasks

Lists out all strings stored in the storage.

Example of usage:

`list` or `l`

Expected outcome:

Bot replies with a confirmation message and prints all the stored strings in the order that they were stored.

```sh {"id":"01HMZMAJCPTDT1GMQ9NRCDH4V2"}
| User Input:
> l
| Here's everything that I'd stored!
| 1.[ ] userinputedstring
```

### `mark *` or `m *` - Marks a task as done, where * is index of the task (starting from 1)

Marks a specific task as done, given its index as a integer.

Example of usage:

`mark 1` or `m 1`

Expected outcome:

If the task exists, the bot replies with a confirmation message and marks the specified task as done. Otherwise, the bot replies with a error messsage.

```sh {"id":"01HMZRCM91X6GB5EH09NJFQP7Y"}
// If valid
| User Input:
> m 1
| Fantastic news!
| You've just upgraded that task from a to-do to a ta-daa!

// If invalid
| User Input:
> m 0
| It appears the parameters might be doing a little dance of confusion!
| Could you please check the parameters and give them another whirl?
```

### `unmark *` or `u *` - Marks a task as not done, where * is index of the task (starting from 1)

Marks a specific task as not done, given its index as a integer.

Example of usage:

`unmark 1` or `u 1`

Expected outcome:

If the task exists, the bot replies with a confirmation message and marks the specified task as not done. Otherwise, the bot replies with a error messsage.

```sh {"id":"01HMZQS7Z1TQDRK0F62XPS3GPN"}
// If valid
| User Input:
> u 1
| Task status reversed!
| Sometimes even completed tasks could use an encore.

// If invalid
| User Input:
> u 0
| It appears the parameters might be doing a little dance of confusion!
| Could you please check the parameters and give them another whirl?
```
