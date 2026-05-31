@echo off
chcp 65001 >nul
if not exist target\classes mkdir target\classes
if exist sources.txt del sources.txt
for %%f in (src\*.java) do (
    echo %%f | findstr /i "Test.java" >nul
    if errorlevel 1 echo %%f>>sources.txt
)
if not exist sources.txt (
    echo РќРµРјР°С” С„Р°Р№Р»С–РІ РґР»СЏ РєРѕРјРїС–Р»СЏС†С–С—
    exit /b 1
)
javac -encoding UTF-8 -d target\classes @sources.txt
if errorlevel 1 exit /b 1
echo Р—Р±С–СЂРєР° СѓСЃРїС–С€РЅР°