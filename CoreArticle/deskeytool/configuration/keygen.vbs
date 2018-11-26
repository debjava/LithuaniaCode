Dim oShell
Dim responseVar
	Set oShell = WScript.CreateObject ("WSCript.shell")
responseVar = MsgBox("Please press Ok to generate the secret key" , 1 , "Secret key generation")

If responseVar = 1 Then

	oShell.run "java -jar ./lib/keygen.jar",0
	Set oShell = Nothing
	
End If
responseVare = MsgBox ("Secret key generated successfully" , 0 , "Message" )




