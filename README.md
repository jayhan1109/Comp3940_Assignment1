# Comp3940_Assignment1

## Teammates
### A01082415 Jeong-Ho Han
### A01024046 Sung-Min Jin
### A01163840 Gyeong-Min Lee

## How To Run
### Server
```
java -javaagent:aspectj\lib\aspectjweaver.jar -classpath .;lib/gson.jar;aspectj\lib\aspectjrt.jar;aspectj\lib\aspectjweaver.jar DirServer
```

### Client (From Console)
```
java Acitivity **[path]**

ex) java Activity C:\
```

### Browser
```
http://localhost:8086/**[path following after C:\]**

ex) http://localhost:8086/CST
(This will bring lists in C:\CST)
```

### Compile
```
javac -cp .;aspectj\lib\aspectjrt.jar;aspectj\lib\aspectjweaver.jar;lib/gson.jar *.java
```

## What we did
#### Jeong-Ho Han
  Implemented Thread Pool, AspectJ and Parsing from Browser
#### Sung-Min Jin
  Implemented Reflection, JSON, and Creating DirUtils.
#### Gyeong-Min Lee
  Implemented HTML & CSS codes, parsing from Console, and error handling.
