@echo off
chcp 65001 >nul
call compile.cmd
if errorlevel 1 exit /b 1
java -cp target\classes MainApp