<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category</title>
</head>
<body>
	<common:navbar></common:navbar>
	<common:content pageName="Category Page">
		<form action="/addCategory" method="post">
			<label for="name" class="form-label ">Category Name</label> <input
				type="text" name="name" class="form-control mb-3" />
			<button class="btn btn-outline-primary">Submit</button>
		</form>
		
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>No.</th>
					<th>Name</th>
					<th>Create At</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</common:content>
</body>
</html>