Dim oShell
Dim responseVar
	Set oShell = WScript.CreateObject ("WSCript.shell")
responseVar = MsgBox("Please press Ok to encrypt the file" , 1 , "File encryption")

If responseVar = 1 Then

	oShell.run "java -jar ./lib/encrypter.jar",0
	Set oShell = Nothing
	
End If
responseVare = MsgBox ("File encrypted successfully" , 0 , "Message" )




