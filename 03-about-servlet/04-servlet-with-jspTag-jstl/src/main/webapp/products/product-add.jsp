<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Product</title>
</head>
<body>
	<common:navbar></common:navbar>
	<common:content pageName="Add New Product">
		<form action="/addProduct" method="post" class="w-50">
		<div class="form-group mb-3">
				<label for="category" class="form-label ">Category</label> 
				<select class="form-control">
					<option  value="">Select One</option>
				</select>
			</div> 
			<div class="form-group mb-3">
				<label for="name" class="form-label ">Product Name</label> 
				<input type="text" name="name" class="form-control mb-3" />
			</div>
			<div class="form-group mb-3">
				<label for="price" class="form-label ">Product Price</label> 
				<input type="number" name="price" class="form-control mb-3" />
			</div>
			<button class="btn btn-outline-primary">Submit</button>
		</form>
	</common:content> 
</body>
</html>