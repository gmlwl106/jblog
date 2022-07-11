<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

</head>
<body>
	<div id="center-content">
		
		<!--메인 해더 자리 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		
		
		<form id="search-form" action="${pageContext.request.contextPath }/search" method="get">
			<fieldset>
				<input type="text" name="keyword" >
				<button id="btnSearch" type="submit" >검색</button>
			</fieldset>
			
			<fieldset>
				<label for="rdo-title">블로그 제목</label> 
				<input id="rdo-title" type="radio" name="kwdOpt" value="optTitle" checked> 
				
				<label for="rdo-userName">블로거 이름</label> 
				<input id="rdo-userName" type="radio" name="kwdOpt" value="optName" > 
			</fieldset>
		</form>
		
		<div id="resultList">
			
			<table id="result-list">
				<colgroup>
					<col style="width:50px;">
					<col style="width:500px;">
					<col style="width:100px;">
					<col style="width:100px;">
				</colgroup>
				
				<c:forEach items="${bMap.bList }" var="blogVo">
				<tr>
					<td><img id="proImg" src="${pageContext.request.contextPath }/${blogVo.logoFile}"></td>
					<td><a href="${pageContext.request.contextPath }/${blogVo.id}">${blogVo.blogTitle }</a></td>
					<td>${blogVo.userName }(${blogVo.id })</td>
					<td>${blogVo.joinDate }</td>
				</tr>
				</c:forEach>
			</table>
			
			<div id="paging">
				<ul>
				<c:if test="${bMap.bList != null }">
					<c:if test="${bMap.prev eq true }">
						<li><a href="${pageContext.request.contextPath }/search?keyword=${param.keyword }&kwdOpt=${param.kwdOpt }&crtPage=${bMap.startPageBtnNo-1}">◀</a></li>
					</c:if>
					
					<c:forEach begin="${bMap.startPageBtnNo }" end="${bMap.endPageBtnNo }" step="1" var="page">
						<c:choose>
							<c:when test="${param.crtPage eq page }">
								<li class="active"><a href="${pageContext.request.contextPath }/search?keyword=${param.kwdOpt }&kwdOpt=${param.keyword }&crtPage=${page }">${page }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath }/search?keyword=${param.keyword }&kwdOpt=${param.kwdOpt }&crtPage=${page }">${page }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>							

					<c:if test="${bMap.next eq true }">
						<li><a href="${pageContext.request.contextPath }/search?keyword=${param.keyword }&kwdOpt=${param.kwdOpt }&crtPage=${bMap.endPageBtnNo+1 }">▶</a></li>
					</c:if>
				</c:if>
				</ul>
			</div>
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
	
	
	</div>
	<!-- //center-content -->
</body>


</html>