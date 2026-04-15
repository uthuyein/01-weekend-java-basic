<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
</head>
<body>
	<common:navbar></common:navbar>
	<common:content pageName="Product Page">
		
		<c:choose>
			<c:when test="${products ne null }">
				<table class="table table-stripped table-hover">
					<thead>
						<tr>
							<th>No.</th>
							<th>Name</th>
							<th>Category</th>
							<th>Price</th>
							<th>Create At</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="p" items="${products }" varStatus="i" >
							<tr>
							<td>${i.index +1}</td>
							<td>${p.name }</td>
							<td>${p.category.name }</td>
							<td>${p.price }</td>
							<td>${p.createDate }</td>
							<td>
								<div>
									<a href="/product/updateProduct?id=${p.id}" class="btn btn-outline-primary">Update</a>
									<a href="/product/deleteProduct?id=${p.id}" class="btn btn-outline-danger">Delete</a>
								</div>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<p class="text-primary">There is no products yet !</p>
			</c:otherwise>
		</c:choose>
	</common:content>
</body>
</html>