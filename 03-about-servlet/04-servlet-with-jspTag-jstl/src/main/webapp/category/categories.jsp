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
		<form action="/category/addCategory" method="post" class="w-50 mb-4">
		<div class="form-group mb-3">
			<label for="name" class="form-label ">Category Name</label> 
			<input type="hidden" name="id" class="form-control" value="${not empty category ? category.id : '' }" />
			<input type="text" name="name" class="form-control" value="${not empty category ? category.name : '' }" />
		</div>
			<button class="btn btn-outline-primary">${not empty category ? 'Update Category':'Save Category'}</button>
		</form>
		<c:choose>
			<c:when test="${categories ne null }">
				<table class="table table-stripped table-hover">
					<thead>
						<tr>
							<th>No.</th>
							<th>Name</th>
							<th>Create At</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cat" items="${categories }" varStatus="i" >
							<tr>
							<td>${i.index +1}</td>
							<td>${cat.name }</td>
							<td>${cat.createDate }</td>
							<td>
								<div>
									<a href="/category/updateCategory?id=${cat.id}" class="btn btn-outline-primary">Update</a>
									<a href="/category/deleteCategory?id=${cat.id}" class="btn btn-outline-danger">Delete</a>
								</div>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<p class="text-primary">There is no categories !</p>
			</c:otherwise>
		</c:choose>
	</common:content>
</body>
</html>