@echo off
REM This file generated by AspectJ installer
REM Created on Sun Sep 20 01:54:25 JST 2020 by jungh

if "%JAVA_HOME%" == "" set JAVA_HOME=C:\Program Files\Java\jdk-14.0.1
if "%ASPECTJ_HOME%" == "" set ASPECTJ_HOME=C:\CST\ClientServer\Assignment1\src\aspectj

if exist "%JAVA_HOME%\bin\java.exe" goto haveJava
if exist "%JAVA_HOME%\bin\java.bat" goto haveJava
if exist "%JAVA_HOME%\bin\java" goto haveJava
echo java does not exist as %JAVA_HOME%\bin\java
echo please fix the JAVA_HOME environment variable
:haveJava
"%JAVA_HOME%\bin\java" -classpath "%ASPECTJ_HOME%\lib\aspectjweaver.jar;%CLASSPATH%" "-javaagent:%ASPECTJ_HOME%\lib\aspectjweaver.jar" %1 %2 %3 %4 %5 %6 %7 %8 %9