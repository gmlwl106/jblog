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
							<td><input type="text" id="cmtTxt" name="cmtContent"></td>
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
						<!-- 코멘트가 나오는 부분 -->
						
					</table>
				</div>
				<!-- //comment -->
				
				
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
					
					<div id="paging">
						<ul>
							<c:if test="${blogMap.prev eq true }">
								<li><a href="${pageContext.request.contextPath }/${blogMap.headerVo.id }?crtPage=${blogMap.startPageBtnNo-1}">◀</a></li>
							</c:if>
							
							<c:forEach begin="${blogMap.startPageBtnNo }" end="${blogMap.endPageBtnNo }" step="1" var="page">
								<c:choose>
									<c:when test="${param.crtPage eq page }">
										<li class="active"><a href="${pageContext.request.contextPath }/${blogMap.headerVo.id }?crtPage=${page }">${page }</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.request.contextPath }/${blogMap.headerVo.id }?crtPage=${page }&cateNo=${param.cateNo}">${page }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>							

							<c:if test="${blogMap.next eq true }">
								<li><a href="${pageContext.request.contextPath }/${blogMap.headerVo.id }?crtPage=${blogMap.endPageBtnNo+1 }">▶</a></li>
							</c:if>
						</ul>
					</div>
					<!-- //paging -->
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	$(document).ready(function() {
		console.log("ready");
		
		/* 리스트 그리기 */
		fetchList();
	});
	
	/* 코멘트 리스트 요청 */
	function fetchList() {
		console.log("fetchList");
		
		var postNo = "${blogMap.postVo.postNo}";
		console.log(postNo);
		
		$.ajax({
			//보낼때
			url : "${pageContext.request.contextPath }/${blogMap.headerVo.id }/comments",
			type : "post",
			//contentType : "application/json",
			data : {postNo},
			
			//받을때
			dataType : "json",
			success : function(cmtList){
				/*성공시 처리해야될 코드 작성*/
				console.log(cmtList);
				
				//화면 data + html 그린다
				for(var i=0; i<cmtList.length; i++) {
					render(cmtList[i]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	
	/* 코멘트 저장 클릭 */
	$("#cmtBtn").on("click", function() {
		console.log("코멘트 저장버튼");
		
		var userNo = "${authUser.userNo}";
		var postNo = "${blogMap.postVo.postNo}";
		var cmtContent = $("#cmtTxt").val();
		
		var commentVo = {
				userNo: userNo,
				postNo: postNo,
				cmtContent: cmtContent
		};
		
		
		$.ajax({
			url : "${pageContext.request.contextPath }/${blogMap.headerVo.id }/cmtUpload",
			type : "post",
			//contentType : "application/json",
			data : commentVo,
			//dataType : "json",
			success : function(cmtVo){
				//성공시 처리해야될 코드 작성
				console.log(cmtVo);
				
				render(cmtVo);
				$("#cmtTxt").val("");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	
	/* 코멘트 그리기 */
	function render(cmtVo) {
		console.log("render");
		
		var str = "";
		
		str += "<tr id='cmt"+cmtVo.cmtNo+"'>";
		str += "	<td><b>"+cmtVo.userName+"</b></td>";
		str += "	<td align='left'>"+cmtVo.cmtContent+"</td>";
		str += "	<td>"+cmtVo.regDate+"</td>";
		str += "	<td>";
		if("${authUser.userNo}" == cmtVo.userNo) {
			str += "	<img data-no='"+cmtVo.cmtNo+"' class='btnCmtDel' src='${pageContext.request.contextPath}/assets/images/delete.jpg'>";
		}
		str += "	</td>";
		str += "</tr>";
		
		
		$("#cmtRead").prepend(str);
	}
	
	
	$("#cmtRead").on("click", ".btnCmtDel", function() {
		console.log("삭제버튼");
		
		var $this = $(this);
		var cmtNo = $this.data("no");
		
		$.ajax({
			//보낼때
			url : "${pageContext.request.contextPath }/${blogMap.headerVo.id }/cmtDelete",
			type : "post",
			//contentType : "application/json",
			data : {cmtNo},
			
			//받을때
			//dataType : "json",
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				console.log(result);

				if(result == "success") {
					$("#cmt"+cmtNo).remove();
				} else {
					alert('삭제할 수 없습니다.');
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
</script>
</html>