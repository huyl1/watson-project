# Wastson Project
This repository is aimed at replicating IBM's complex Question Answering system, Watson. <br><br>
The system classifies Wikipedia pages and attempts to find their respective articles based on a given query or question.
## Installation
Before running the program, make sure you have [Maven](https://maven.apache.org/install.html) installed and setup correctly. <br><br>
Make sure to run command in directory of the program before running:
```
$ mvn install
```
## Running the Program
To run the a demo of the program, go into: 
```
src/main/java/com/watson/App.java
```
Either compile and run the code using your IDE's internal Java compiler, or execute this command
```
$ javac App.java | java App
```
The program will then build the index (stemming) based off the questions.txt within datasets. <br><br>
It will then prompt the user to input a query, and return the associated documents related to the user's query. In this case, a Wikipedia title. <br><br>
Did we get it right?
## Libraries Used
[Extended Java WordNet Library](https://extjwnl.sourceforge.net/) (extJWNL) <br><br>
[Rapid Automatic Keyword Extraction](https://github.com/Linguistic/rake) (R.A.K.E.) <br><br>
[Word2Vec](https://deeplearning4j.konduit.ai/v/en-1.0.0-beta7/language-processing/word2vec)
