<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<header class="d-flex align-items-center justify-content-between">
		<h1 class="ml-3">Memo</h1>
		
		<c:if test="${not empty userId }">  <!-- session에서 사용되는 키는 바로 가져올 수 있음 session.setAttribute("userId", user.getId());에서 가져옴-->
			<div class="mr-3"> ${userName } 님 <a href="/user/sign_out">로그아웃</a></div>
		</c:if>
		
	</header>

</body>
</html>