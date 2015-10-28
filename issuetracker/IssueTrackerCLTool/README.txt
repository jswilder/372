The link to the Proof of Concept screencast is:

This is a list of known bugs:
    1) When a configuration file isn't included in the project folder, the
    configuration frame will throw an exception. To bypass this, just enter in configuration
    information on the frame and save it. This will create a configuration.txt for you.

    2) On the Issue Frame, if you select the main panel without selected a cell,
    the edit and delete buttons will highlight but won't be functional.

    3) Client side has been unsuccessful in connecting to the server side.

    4)

Commands to start the client side:

    unzip POC.zip
    cd IssueClient
    make clean
    make
    make run

Commands to start the server side:

    cd ..
    cd IssueServer
    make clean
    make
    make run

Link to project download:
    https://sites.google.com/a/g.clemson.edu/compsci3720project/project
