<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
</head>
<body>
	<common:navbar></common:navbar>
	<common:content>
	<div class="d-flex justify-content-center">
			<form action="/" method="post" class=" border border-2 p-3 w-50 border-primary-subtle rounded-end" >
			<p class="fs-3 text-primary">Login Form</p>
			<hr />		
				<div class="mb-3">
					<label for="email" class="form-label">LoginId</label> <input
						type="email" class="form-control" name="email">
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input
						type="password" class="form-control" name="password">
				</div>
				<div class="mb-3 form-check">
					<input type="checkbox" class="form-check-input" name="saveId" >
					<label class="form-check-label" for="saveId">Save
						Password</label>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	
	</common:content>
</body>
</html>