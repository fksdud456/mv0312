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
</style>

<script>
$(document).ready(function(){
	$('#u_bt').click(function(){
		var c = confirm('���� �Ͻðڽ��ϱ�?');
		if(c == true){
			$('#user_detail').attr({
				'method':'post',
				'action':'userupdate.do'
			});
			$('#user_detail').submit();
		};
	});
	
	$('#d_bt').click(function(){
		var c = confirm('���� �Ͻðڽ��ϱ�?');
		if(c == true){
			$('#user_detail').attr({
				'method':'post',
				'action':'userdelete.do'
			});
			$('#user_detail').submit();
		};
	});
});
</script>

<div id="main_center">
<h1>Detail Page</h1>

<form id="user_detail">
ID<input type = "text" name="id" id="id" value =${user.id } readonly><br>
PWD<input type="text" name="pwd" id="pwd" value = ${user.pwd }><br>
NAME<input type="text" name="name" id="name" value = ${user.name }><br>
<input type="button" value="UPDATE" id="u_bt">
<input type="button" value="DELETE" id="d_bt">
</form>

</div>











