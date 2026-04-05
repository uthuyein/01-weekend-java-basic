<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

<title>Cookie vs Session</title>
</head>
<body>
	<div class="container mt-3 p-3">
		<div class="row">
			<div class="col-md-4">Cookie</div>
			<div class="col-md-8 justify-content-end">
				<%
					for(Cookie c : request.getCookies() ){
						if(c.getName().equals("admin@gmail.com")){				
				%>
						<%=c.getValue() %>
					<% 		
						}
					}%>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">Session</div>
			<div class="col-md-4 justify-contend-end">
				<%= session.getAttribute("password") %>
			</div>
			<div class="col-md-4">
				<button class="btn btn-primary">Invalidate
					<% session.invalidate(); %>
				</button>
			</div>
		</div>
	</div>
</body>
</html>