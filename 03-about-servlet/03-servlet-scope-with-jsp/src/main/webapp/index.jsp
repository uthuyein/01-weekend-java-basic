<%@page import="com.jdc.mkt.Counter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

<title>Servlet Scope</title>
</head>
<body>
	<div class="d-flex justify-content-center">
		<div class="container">
			<h1 class="text-primary">Servlet Scope</h1>
			<a href="counter" class="btn btn-outline-primary">Request Counter Servlet</a>
			
			<%! String counter = "Counter"; %>
			
			<table class="table-stripped">
				<tr>
					<td colspan="4">
						<p class="text-success fs-2">Request Scope</p>
						<% 
							Counter c1 = (Counter)request.getAttribute(counter);
							if(null == c1){
								c1 = new Counter();
							}					
						%>											
					</td>
					<td class="text-danger fs-2"><%= c1.getCounter() %></td>
				</tr>
				<tr>
					<td colspan="4">
						<p class="text-success fs-2">Session Scope</p>
						<% 
							Counter c2 = (Counter)session.getAttribute(counter);
							if(null == c2){
								c2 = new Counter();
							}					
						%>											
					</td>
					<td class="text-danger fs-2"><%= c2.getCounter() %></td>
				</tr>
				<tr>
					<td colspan="4">
						<p class="text-success fs-2" >Application Scope</p>
						<% 
							Counter c3 = (Counter)application .getAttribute(counter);
							if(null == c3){
								c3 = new Counter();
							}					
						%>											
					</td>
					<td class="text-danger fs-2" ><%= c3.getCounter() %></td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
</html>