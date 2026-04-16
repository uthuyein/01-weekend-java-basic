<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
</head>
<body>
	<common:navbar></common:navbar>
	<common:content pageName="Add Product Page" icon="bi bi-file-earmark-plus">
		<form action="/product/addProduct" method="post" class="w-50 mb-4">
		<input type="hidden" name="id" class="form-control" value="${not empty product ? product.id : '' }" />
			<div class="form-group mb-3">
			<label for="cat_id" class="form-label ">Category</label> 
			<c:if test="${categories ne null }">
			<select name="cat_id" class="form-select"  >
				<option value="${not empty product ? product.category.id : '' }" selected="selected" >
				${not empty product ? product.category.name : 'Select One' }</option>
				<c:forEach var="cat" items="${categories }">
					<option value="${cat.id }">${cat.name }</option>
				</c:forEach>
			</select>
			</c:if>
		</div>
		<div class="form-group mb-3">
			<label for="name" class="form-label ">Product</label> 
			<input type="text" name="name" class="form-control" value="${not empty product ? product.name : '' }" />
		</div>
		<div class="form-group mb-3">
			<label for="price" class="form-label ">Price</label> 
			<input type="number" name="price" class="form-control" value="${not empty product ? product.price : '' }" />
		</div>
			<button class="btn btn-outline-primary">${not empty product ? 'Update product':'Save product'}</button>
		</form>
		
	</common:content>
</body>
</html>