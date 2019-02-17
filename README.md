# BrickerSnatch
## BrickHackV - Team whats9plus10

BrickerSnatch is an interactive novel generator 
that allows users with no coding experience to create their own interactive novels.

In order to use BrickerSnatch, you'll need javafx and the json-simple library, that's it!
(If you're using the latest version of java, you'll need to install javafx separately.)

To play one of our novels, you can simple run BrickerSnatchGUI or BrickerSnatchPTUI and pass in
the name of one of our stories as a program argument. You can choose either Wegmans.json or 
LegendOfTheBrick.json.

Enjoy!

To create your own story, you just need to create your own json file in the Stories directory.
The format of the json is the following:

```{
    "image dimenstions": [
        {"width": "~number~"}, 
        {"height": "~number~"}
    ],
    
    "choices": [
        {"name": "~Choice name~",
        "current scene": "~Current Scene~",
        "result scene": "~Result Scene~"},
        ...
    ],
    "scenes": [
        {"name": "~Scene name~",
        "background": "~Path to image from the Assets directory~",
        "dialog": "Scene Dialog",
        "choices": [
            "~Choice name1~",
            "~Choice name2~",
            ...
            ]
        },
        ...
    ]
}
```

In the format above, eveything in between tildes(~) are things 
you need to fill out with your own content. You can add as many choices
and scenes as you wish! However, make sure they reference each other properly.
 If you spell a choice improperly in the list of choices inside a scene, 
 the program will not behave as expected. Similarly, if you spell the name of
 a scene wrong within a choice, the program will misbehave.
#### Contributors

* __Marina Bacino__ - Wegmans.json story and visuals
* __Yancarlos Diaz__ - Model, PTUI and GUI implementation
* __Alec Mahoney__ - Model and GUI implementation
* __Alex Scholeno__ - Model implementation and LegendOfTheBrick story and visuals