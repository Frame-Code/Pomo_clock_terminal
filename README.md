### Prerequisites
- Have Docker installed
- To run it, you need to download the LibreTranslate image using the following command
`$docker run -d --name libretranslate -p 5001:5000 -e LT_LOAD_ONLY="en,es" libretranslate/libretranslate:latest `
Or you can download the **libretranslate.ps1** file and run it in your terminal to initialize everything that needs to be initialized on the Docker side.


### How to test it
1. Download the executable in this link [Click here](https://github.com/Frame-Code/Pomo_clock_t1erminal/releases/download/v1.0/clock_app.jar)

2. Execute the jar file using the terminal:
`$ java -jar <jar_file_name> <time_minutes_pomodoro> <time_minutes_rest>  <total_pomodoros> "some title of task" `
E.g: A pomodoro of 25 minutes, 4 cicles and 5 minutes of rest (assuming it is in the folder where the jar file is located):
`$ java -jar  clock_app 25 5 4 "Study design_patterns" `

Also is possible create a bat file to execute the jar file (asssuming the jar file is located in a unique place) and add the bat file on the path of envionment variables :D

