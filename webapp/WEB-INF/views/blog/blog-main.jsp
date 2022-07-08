<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<!-- 사용자업로드 이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath }/${blogMap.blogVo.logoFile }">
						
					<div id="nick">${blogMap.blogVo.userName }(${blogMap.headerVo.id })</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${blogMap.cateList }" var="cateVo">
							<li><a href="${pageContext.request.contextPath }/${blogMap.headerVo.id }?cateNo=${cateVo.cateNo}">${cateVo.cateName }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
				<c:choose>
					<c:when test="${not empty blogMap.postVo }">
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>${blogMap.postVo.postTitle }</strong></div>
							<div id="postDate" class="text-left"><strong>${blogMap.postVo.regDate }</strong></div>
							<div id="postNick">${blogMap.blogVo.userName }(${blogMap.headerVo.id })님</div>
						</div>
						<!-- //postBox -->
				
						<div id="post" >${blogMap.postVo.postContent }</div>
						<!-- //post -->
					</c:when>
				
					<c:otherwise>
						<!-- 글이 없는 경우 -->
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
							<div id="postDate" class="text-left"><strong></strong></div>
							<div id="postNick"></div>
						</div>
					    
						<div id="post" >
						</div>
					</c:otherwise>
				</c:choose>
				
				
				<div id="comment">
					<c:if test="${not empty authUser }">
					<table id="cmtWrite">
						<colgroup>
							<col style="width: 60px;">
							<col style="width: 400px;">
							<col style="width: 60px;">
						</colgroup>
						<tr>
							<td>${authUser.id }</td>
							<td><input type="text" id="cmtTxt" name="cmt"></td>
							<td><button id="cmtBtn">저장</button></td>
						</tr>
					</table>
					</c:if>
					
					<table id="cmtRead">
						<colgroup>
							<col style="width: 70px;">
							<col style="width: 400px;">
							<col style="width: 50px;">
							<col style="width: 10px;">
						</colgroup>
						<tr>
							<td>박깜이</td>
							<td align="left">안녕하세요 반가워요 (^0^)/</td>
							<td>2022/07/08</td>
							<td><img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
							
						</tr>
					</table>
				</div>
				
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						
						<c:forEach items="${blogMap.postList }" var="postVo">
							<tr>
								<td class="text-left"><a href="${pageContext.request.contextPath }/${blogMap.headerVo.id }?cateNo=${postVo.cateNo }&postNo=${postVo.postNo}">${postVo.postTitle }</a></td>
								<td class="text-right">${postVo.regDate }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	</div>
	<!-- //wrap -->
</body>
</html>