### Observations when using AI tools

I used GitHub Copilot throughout the project, and here are some observations when using the tool:

1. GitHub Copilot seems to understand the IP requirements very thoroughly, and I believe it's due to past semester iPs being public on GitHub and
used as one of its training data (since Copilot trains on all public repos). For the early increments before the GUI has been created, Copilot
knows the exact command and output format being illustrated on the course website. However, when I changed some of the formats, including the
format of the entries when I save the tasks into a text file, Copilot was often unable to adhere to my format, so some of the auto line completions
on the parser is slightly off.

2. Copilot is unable to do high level design, especially when dealing with abstractions. Even if some descriptive class names are provided, like
Command and CommandCreator, Copilot would still include command execution functionality in CommandCreator, by suggesting execute as a method header.
Given this tendency, I did all high level designs by myself and wrote some indicators on what each class and method is supposed to do, before accepting
any of Copilot's suggestions. But it does OK for straightforward design decisions that are seen everywhere (e.g. Event inherits from Task).

3. Copilot is excellent in auto-completing short phrases in code and documentation. It gives accurate JavaDoc method comments, and for code auto-completion,
it performs well if there is an easily observable pattern / logic within the code. For long chunks of the code, a tip would be to describe what you want to
do in a (temporary) comment and Copilot will be able to generate the whole chunk of code. It's often inaccurate through, but serves as a good starting point.
However, it is usually unable to auto complete JavaFX related code, and even made up some calls to non-existing methods.

So I will say I'm treating Copilot as an advanced auto-complete feature instead of using it to code. It helps in reducing tediousness in programming but
still has a long way to go. For this iP, I would have coded almost the exact same thing by myself if I do not have access to Copilot.

Another AI tool I used is StableDiffusionXL, used for generating images with a single short prompt. It outputs very quickly compared to the other image
generation AI tools I used before. Hence I decided to use it for generating the images for the user and Echon:

> Prompt: cartoon chatbot called Echon
> 
> Result:  
> <img src="src/main/resources/images/DaEchon.png" alt="AI's response for Echon" width="100"/>
> 
> Prompt: cartoon user of the Echon chatbot
> 
> Result:  
> <img src="src/main/resources/images/DaUser.png" alt="AI's response for user" width="100"/>

The AI gets it done in one shot. Quite impressive!
