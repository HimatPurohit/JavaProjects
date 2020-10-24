Dim sInput
sInput = InputBox("Commit Message")

if trim(sInput)=Empty Then
MsgBox "Commit Message is empty"
Wscript.Quit
end if
Set shell = CreateObject("WScript.Shell")
shell.Run "github.bat "& Chr(34) & sInput & Chr(34)