<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div id="center-content">
		
		<!--메인 해더 자리 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		
		
		<form id="search-form">
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
				
			</table>
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
	
	
	</div>
	<!-- //center-content -->
</body>

<script type="text/javascript">
	
	/* 검색 버튼 클릭했을 때 */
	$("#btnSearch").on("click", function() {
		var keyword = $("[name=keyword]").val();
		var kwdOpt = $("[name=kwdOpt]:checked").val();

		var kwd = {
				keyword: keyword,
				kwdOpt: kwdOpt
		}
		
		$.ajax({
			//보낼때
			url : "${pageContext.request.contextPath }/blogSearch",
			type : "post",
			//contentType : "application/json",
			data : kwd,
			
			//받을때
			dataType : "json",
			success : function(blogList){
				/*성공시 처리해야될 코드 작성*/
				console.log(blogList);
				
				//화면 data + html 그린다
				for(var i=0; i<blogList.length; i++) {
					render(blogList[i]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	

	function render(blogVo) {
		console.log("render");
		
		var str = "";
		str += "<tr>";
		str += "<td><img id='proImg' src='${pageContext.request.contextPath }/"+blogVo.logoFile+"'></td>";
		str += "<td><a href='${pageContext.request.contextPath }/"+blogVo.id+"'>"+blogVo.blogTitle+"</a></td>";
		str += "<td>"+blogVo.userName+"("+blogVo.id+")</td>";
		str += "<td>"+blogVo.joinDate+"</td>";
		str += "</tr>";
		
		$("#result-list").append(str);
	}

</script>
</html>