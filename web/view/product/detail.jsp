<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
#main_center{
	margin:0 20px;
	width:760px;
	height:480px;
	background:white;
}
img {
height : 80px;
}
input.image{
height : 80px;
}
</style>

<script>
$(document).ready(function(){
	$('#u_bt').click(function(){
		var c = confirm('수정 하시겠습니까?');
		if(c == true){
			$('#product_detail').attr({
				'method':'post',
				'action':'productupdate.do',
				'enctype':'multipart/form-data'
			});
			$('#product_detail').submit();
		};
	});
	
	$('#d_bt').click(function(){
		var c = confirm('삭제 하시겠습니까?');
		if(c == true){
			$('#product_detail').attr({
				'method':'post',
				'action':'productdelete.do',
					'enctype':'multipart/form-data'
			});
			$('#product_detail').submit();
		};
	});
});
</script>

<div id="main_center">
<h1>Detail Page</h1>
	<form id="product_detail">
		<input type="hidden" name="imgname" value=${product.imgname }>
		ID :<input type="text" name="id" id="id" value=${product.id } readonly><br> 
		NAME<input type="text" name="name" id="name" value=${product.name }><br> 
		PRICE<input type="text" name="price" id="price" value=${product.price }><br>
		DATE : ${product.regdate }<br> 
		<img src='img/${product.imgname }'>
		<input type="file" name="mf"><br> 
		<input type="button" value="UPDATE" id="u_bt">
		<input type="button" value="DELETE" id="d_bt">
	</form>
</div>










