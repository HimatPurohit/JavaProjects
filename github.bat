cd %CD%
git status
git add *
@echo off
set /p message="Enter message: "
git commit -m "%message%"
git push origin master
PAUSE