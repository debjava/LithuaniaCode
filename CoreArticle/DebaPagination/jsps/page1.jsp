<!-- 
	I application I have developed is meant for freshers
	and novice developers. It does not bear any commercial
	significance.It is a learning application, please do 
	not integrate in your application. It has been made 
	open source. Please enjoy it at your own convenience.

-->
<html>
<head>
	<script>
	/*
	*	This function is used to check the numeric characters.
	*	Since the goto page text field is concerned, it should
	*	not accept any non-numeric characters.It is mainly used
	*	for validation of the text field.
	*	
	*	Author : Debadatta Mishra(PIKU)
	*/

	function checkNumerals( obj )
	{
		var key;
		if (window.event)
			key = window.event.keyCode;
		if(key == 13)
		{
			
			document.inputForm.submit();
		}
		if((key<48 || key>57 || key==32))
			return false;
		else
			return true;
	}
	</script>
	<body>
		
			<form name="inputForm" action="test.do" method="POST">
				<table>
					<tr>
						<td>
							Please enter the total no of records: <input type="text" name="inputText" onkeypress="return checkNumerals(this)">
						</td>
					</tr>

					<tr>
						<td>
							<input type="submit" value="Test">
						</td>
					</tr>
				</table>
			</form>

	</body>
</head>
</html>