<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.upFileArea {
	width: 1005;
	height: 100px;
	border: 1px dotted #333;
	padding: 10px;
	font-weight: bold;
	color: rgb(182, 184, 214);
	font-size: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>

<script type="text/javascript">
	$(function() {
		$(".upFileArea").on("dragenter dragover", function(evt) {
			evt.preventDefault();
		});

		$(".upFileArea").on("drop", function(evt) {
			evt.preventDefault();
			
			
			let files = evt.originalEvent.dataTransfer.files;
			console.log(evt.originalEvent.dataTransfer.files);
	        for(let i = 0; i < files.length; i++){ 
	           let form = new FormData( ) //자바스크립트에서 만들었다. form태그를 객체처럼 만들었다.
	           form.append("uploadFile", files[i]); // 파일의 이름을 컨트롤러 단의 MultipartFile 매개변수명과 동일하도록 한다
	           console.log(files[i]);
				
				$.ajax({
					url : "uploadFile", // 데이터를 수신받을 서버 주소
					type : 'post', // 통신방식(GET, POST, PUT, DELETE)
					data : form,
					dataType : "json",
					async : false,
					processData: false, // text데이터에 대해 쿼리스트링 처리를 하지 않겠다
		            contentType: false, // x-www-form-urlen
					success : function(data) {
						console.log(data);
						
					}
				});
				
			}
			
		});
	});
</script>
</head>
<body>

	<jsp:include page="./../header.jsp"></jsp:include>


	<div class="container">
		<h1>게시판 글 작성</h1>

		<form action="writeBoard.bo" method="post"
			enctype="multipart/form-data">
			<div class="mb-3 mt-3">
				<label for="writer" class="form-label">작성자 :</label> <input
					type="text" class="form-control" id="writer" name="writer" value="" />
			</div>

			<div class="mb-3">
				<label for="title" class="form-label">제목:</label> <input type="text"
					class="form-control" id="title" name="title">
			</div>


			<div class="mb-3">
				<textarea rows="40" style="width: 100%" id="content" name="content"></textarea>

			</div>

			<div class="mb-3">
				<label for="upFile" class="form-label">첨부파일:</label>
				<div class="upFileArea">업로드 파일을 드래그 앱 드랍하세요</div>
				<div class="uploadFiles"></div>

			</div>

			<div class="mb-3">
				<button type="reset" class="btn btn-secondary">취소</button>
				<button type="submit" class="btn btn-success">저장</button>
			</div>

		</form>
	</div>

	<jsp:include page="./../footer.jsp"></jsp:include>
</body>
</html>