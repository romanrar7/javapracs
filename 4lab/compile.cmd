@echo off
chcp 65001 >nul
if not exist target\classes mkdir target\classes
dir /s /b src\main\java\*.java > sources.txt
javac -encoding UTF-8 -d target\classes @sources.txt
if errorlevel 1 exit /b 1
echo Збірка успішна
