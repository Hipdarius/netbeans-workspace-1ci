@echo off
if not exist build\classes mkdir build\classes
javac -encoding UTF-8 -d build\classes src\orbitpatrol\*.java
if errorlevel 1 exit /b 1
java -cp build\classes orbitpatrol.MainFrame
