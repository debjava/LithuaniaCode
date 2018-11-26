<!-- 
	I application I have developed is meant for freshers
	and novice developers. It does not bear any commercial
	significance.It is a learning application, please do 
	not integrate in your application. It has been made 
	open source. Please enjoy it at your own convenience.

-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
     
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
       "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>

</head>

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
			pgnovalue=document.getElementById('gotopagetext').value;
			gotoPageClick(pgnovalue);
		}
		if((key<48 || key>57 || key==32))
			return false;
		else
			return true;
	}
	/*
	*	This function is used to submit the form by taking the
	*	page no.
	*
	*	Author : Debadatta Mishra(PIKU)
	*/
	function fnClick( pgnovalue )
	{
		window.status = "";
		document.getElementById('pgnos').value=pgnovalue;					
		document.pageform.submit();			
	}
	/*
	*	This function is used when the user provides
	*	the page number in the goto page text field.
	*	It submits the form for the action. Besides
	*	this function performs some minor validations.
	*
	*	Author : Debadatta Mishra(PIKU)
	*/
	function gotoPageClick(pgnovalue)
	{
		var tempTotalPages = document.getElementById('totalNoPages').value;
		if( ( eval(pgnovalue) - 1 ) < 0 )
		{
			document.getElementById('pgnos').value = 0 ;
		}
		else if( ( eval(pgnovalue) - 1 ) >= ( eval(tempTotalPages) ) )  
		{
			document.getElementById('pgnos').value = eval(tempTotalPages) - 1 ;
		}
		else
		{
			document.getElementById('pgnos').value = eval(pgnovalue) - 1 ;
						
			if(pgnovalue==""||pgnovalue==null)
			{
				document.getElementById('pgnos').value = 0;	
			}
			else
			{
				document.getElementById('pgnos').value = eval(pgnovalue) - 1 ;
			}
		}
			document.pageform.submit();				
	}
	/*
	*	This function is used to hide the status bar
	*	when clicking the page .
	*	
	*	Author : Debadatta Mishra(PIKU)
	*/
	function hidestatus()
	{
		window.status = "";
		return true;
	}
					
</script>
	<body>

		<form name="pageform" action="test.do" method="post">

		<%--
			Include the JSP page,
			where you can display the items
		--%>

		<jsp:include page="/ItemsDisplay.jsp">
			<jsp:param name="someitems"				value='${requestScope.pageItems}' />
			<jsp:param name="TOTAL_RECORDS"			value='${requestScope.inputText}' />
		</jsp:include>

		<input type="hidden"  name="pageNos"		value='${requestScope.pageNos}'    id="pgnos" >
		<input type="hidden"  name="totalNoPages"   value='${requestScope.totalPages}' id="totalNoPages">
		<input type="hidden"  name="inputText"		value='${requestScope.inputText}'  id="inputText">

		<table border="0" cellpadding="2" cellspacing="1">

		<tr>
			<%--
				The following is used for displaying the previous.
				Previous link will come into picture when the 
				page no is greater than 0, otherwise do not display
				the previous link.
			--%>
			<c:if test="${pageNos-1 >= 0}">
				<td>
					<a  href="javascript:fnClick('${pageNos-1}')" onMouseOver="return hidestatus();">
						<font color="BLACK">Previous</font>
					</a>
				</td>
			</c:if>

			<%--
				The following is used to display the consecutive numbers
				for pagination. It displays the variable list of page nos
				which comes from the middleware. For the manipulation,please
				see the java code. Besides it also displays the vertical
				bar as the page no separator. Here you will find that
				page nos are always displayed as incremented by 1. Actually
				it comes decremented from the middleware. Here one major
				improvement is that ... will come when current page no is
				greater than the defaultcursorposition. If you want to change
				the defaultcursorposition change it in the middleware so that
				it will be generic. Please do not modify the cursor psosition.
			--%>
			<c:forEach var="pg" items="${pageList}" varStatus="loopStat">
				<c:if test="${!(loopStat.index eq 0 and loopStat.last)}">
					<td>
						<c:if test="${loopStat.count > 1}">
						|
						</c:if>
						<c:choose>
							<c:when test="${pageNos == pg}">
							<b><font size="3" color="GREEN">${pg+1}</font></b>
							</c:when>
							<c:otherwise>
								<a href="javascript:fnClick('${pg}')" onMouseHover="hidestatus()">
									<font color="BLACK">${pg+1}</font>
								</a>
								<c:if test="${loopStat.count == 1 && pageNos > defaultPageCursorPosition}">...</c:if>
							</c:otherwise>
						</c:choose>
					</td>
				</c:if>
			</c:forEach>

			<%--
				The following is used to display the Next link.
				The Next will come into picture if the total no
				of pages displayed is less than the total no of
				overall pages. The logic is same for Next and Last,
				the only difference is that if the user clicks the
				Last, the totalpages will be considered.
			--%>
			<c:if test="${pageNos+1 < totalPages}">
				<td>
					<a onMouseOver="return hidestatus();" href="javascript:fnClick('${pageNos+1}')">
						<font color="BLACK">Next</font>
					</a>
				</td>
				<td>
					&nbsp;&nbsp;
					<a onMouseOver="return hidestatus();" href="javascript:fnClick('${totalPages-1}')" >
						<font color="BLACK">[Last]</font>
					</a>
				</td>
			</c:if>
					<%-- The following line is meant for space adjustment --%>
					<td >&nbsp;</td>
					<%--
						The followings are used to add more sophistication 
						and flexibility in the concept of pagination.
						For more details you can visit the website 
						"http://www.ebay.com" . Here one more thing has to be
						considered that if the total no of pages is greater than
						2, the goto page text box will appear otherwise, it will
						not display.
					--%>					
			<c:choose>
				<c:when test="${totalPages < 2}">
					<%-- Do not display anything --%>
				</c:when>				
				<c:otherwise>
					<td> 
						<b>Go to page</b> 
					</td>
					
					<td>
						<input type="text" name="gotopagetext" id="gotopagetext" size="6" 
						oncopy="return false" onpaste="return false" 
						oncut="return false" onkeypress="return checkNumerals(this)">
					</td>

					<td >
						<input type="submit" id="go" value="Go" class="button" onclick="gotoPageClick(document.getElementById('gotopagetext').value)">
					</td>
				</c:otherwise>
		 </c:choose>

		</tr>
		</table>

	</form>
	</body>
</html>
