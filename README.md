# LogAnalytics
Programming Kata: Given an apache log file, show the unique requests per hour.

##Demo
[Live Demo](http://log.helloima.ninja)

##Info
Reading up Quora when I saw an interesting [problem](http://qr.ae/7ApmM1). 
>Given an arbitrary set of webserver log files in the industry-standard Common Log Format, produce a histogram of the number of unique page views per hour.  The solution may be written in any language or combination of languages. You may use any and all resources at your disposal to solve this problem.

It seemed like a great opportunity to play around with some technologies. I could have probably done it a lot faster with a different stack but where's the fun in that?
Small list of some technologies/workflow used:
* Spring Boot (Web
* Angular
* Grok
* Chart JS
* Bower
* TDD
* Github
* Docker

##Code Explained
TBD

##How To Build
* Download the project
* Import into your favorite IDE
* Install bower components
* Perform maven clean-install

##How To Deploy (Local)
* Run spring boot (LogAnalyticsApplication.java)

##Latest Version
* Improved speed
* Larger sample log file

##Todo
* Deploy latest version
* Mask the ui elements during ajax load
* Change the graph size for mobile
* Improve speed further
* Feature: drop a text file and render a graph
* Display/download the text file used in the graph

##Issues
* Ui shows wrong monthday
* Graph placeholder not hiding (angular ng-hide)
