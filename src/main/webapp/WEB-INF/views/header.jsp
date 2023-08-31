<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
<style >
	.userImg{
		width: 20px;
		height: 20px;
	}
</style>
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link active"
					href="${contextPath }/index.jsp">ray</a></li>
				<li class="nav-item"><a class="nav-link" href="${contextPath }/board/listAll">게시판</a></li>

				<c:choose>
					<c:when test="${sessionScope.loginUser == null }">
						<li class="nav-item"><a class="nav-link"
							href="${contextPath }/member/register.jsp">회원가입</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${contextPath }/member/login.jsp">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link"
							href="${contextPath }/member/myPage.mem?userId=${sessionScope.loginUser.userId }">
							<img src="${contextPath }/${sessionScope.loginUser.memberImg}" class="userImg">
							${sessionScope.loginUser.userId }
							</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${contextPath }/member/logout.mem">로그아웃</a></li>
							
							<c:if test="${sessionScope.loginUser.isAdmin == 'Y' }">
								<li class="nav-item"><a class="nav-link"
								 href="${contextPath }/admin/admin.jsp">관리자 페이지</a></li>
							</c:if>
							
					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</nav>
</body>
</html>