### Zhang Danrui's Project Portfolio Page
#### Project: CEGMods
CEGMods is a task and schedule management desktop CLI App for NUS CEG students. The application is written in Java. 

Below are my contributions to the project.
- New Feature:
    1. Added the ability to view timetable including enrolled modules and added events. [#20](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/20) [#32](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/32)
        - What it does: allow users to view all modules' slots and events
        - Justification: This feature helps to visualize all the events added in time order which is clearer for users 
        to manage their schedule.
        - Highlight: This feature requires searching all the module slots and event happened today or this week and 
        sorting them in time order.
    1. Added the ability to view upcoming deadlines for today and upcoming week [#118](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/118)
        - What it does: allow users to view today's deadlines and this week deadlines
        - Justification: This feature helps users to visualize upcoming deadlines with date and time which can remind 
        users to prioritize their work.
        - Highlight: This feature requires filtering all the deadline tasks, checking these deadlines date and sorting 
        them according to their remaining time which is more convenient for users.
    1. Added the ability to storage task list and load task list from storage file [#32](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/32)
        - What it does: allow users storage added to-do task,event and deadline and load the stored task list everytime 
        users run the app.
        - Justification: This feature helps users to view previous added tasks which is more convenient so that users do
         not need to type tasks repeatedly.
    1. Added the ability to check time conflict when adding modules and events [#36](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/36) [#39](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/39) [#90](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/90)
        - What it does: check time conflict with exist modules events when adding a module or event
        - Justification: This feature helps users to check time conflict before adding events or modules into the 
        timetable.
        - Highlight: This feature requires checking all the slots of each module and events date time which needs 
        consider different situations and implement the algorithm frequently.
    1. Added the ability to delete all the past deadlines [#115](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/115)
        - What it does: allow users to clear all the deadlines in the past [#115](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/115)
        - Justification: This feature helps users to clear all the past deadlines instead of deleting them one by ine
    1. Added the ability to delete all the tasks have been marked as done [#115](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/115)
    1. Added the ability to view different types of task list including todo list, deadline list,event list and undone 
    list [#115](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/115)
    1. Added the ability to display current date,time and ongoing event [#118](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/118)
    1. Modified task class and added the ability to print and find tasks in the task list[#32](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/32) 

- Code contributed: [Response link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#sort=groupTitle&groupSelect=groupByAuthors&search=zhangcaicai123&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=zhangcaicai123&tabRepo=AY2021S1-CS2113T-F11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~other)
- Enhancement to exist features:
    1. Update the ability to convert input time into LocalDateTime format including module class, event and deadline class [#32](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/32)
    1. Modify the deadline class so that user can view the remaining time for every deadline when printing the task list and timetable [#115](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/115)
    1. Modify the event class to allow user set event duration [#32](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/32)
    1. Modify add module method to check correct time format before adding [#115](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/115)
    1. Modify module class to taking modules with two lecture slots into consideration [#94](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/94)
- Documentation: 
    - UG: Added the documentation for features: [#122](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/122) [#121](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/121)
        `print todo list`,`print event list`,`print deadline list`, `print undone task list`,
        `clear deadlines`,`delete done task` and `check modules`
    - DG: 
        - Added the set up instruction part
        - Added the design and implementations part for `task` `tasklist` and `timetable`
        - Added the Appendix for target user profile, value proposition and user stories
- Contributions beyond the project team:
    - Reporting bugs for other team's repo: [link](https://github.com/zhangcaicai123/ped/issues)
