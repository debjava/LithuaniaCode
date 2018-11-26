<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
       "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<title>Items Display</title>
	<body>
		<b> This is a more sophisticated implementation of Pagination  </b>
		<br>
		<b> The cost of the application is $8000 per user per entity </b>
		<br>
			This application has the following salient features.
		<br>
			<li> Provides the Google type pagination.
			<li> Provides more flexibility to the user to navigate first and last page directly.
		<br>
		<b> Please add to your shopping cart or contact in the following address</b>
		<br>
		<a href="mailTo:debadatta.m@idealinvent.com">Ideal Invent Technologies, Bangalore</a>
		<h1> Displayed Items </h1>
		<table>
			<c:forEach var="item" items="${param.someitems}" varStatus="loopStat">
			<tr>
				<td>
						<c:out value="${item}"/>
				</td>
			</tr>
			</c:forEach>
		</table>
	</body>
</head>
</html>