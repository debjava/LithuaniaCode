Dim oShell
Dim responseVar
	Set oShell = WScript.CreateObject ("WSCript.shell")
responseVar = MsgBox("Please press Ok to decrypt the file" , 1 , "File encryption")

If responseVar = 1 Then

	oShell.run "java -jar ./lib/decrypter.jar",0
	Set oShell = Nothing
	
End If
responseVare = MsgBox ("File decrypted successfully" , 0 , "Message" )




