<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<style>
#main_center {
	margin: 0 20px;
	width: 760px;
	height: 480px;
	background: white;
}

td, tr {
	border: 1px solid gray;
	border-collapse: collapse;
}

img {
	height: 80px;
}
</style>    
    
<div id="main_center">
<h1>Product List Page</h1>

<table>
<thead>
	<tr><th>ID</th><th>NAME</th><th>PRICE</th><th>DATE</th><th>IMAGE</th></tr>
</thead>
<tbody>
<c:forEach var="p" items="${productlist }">
	<tr>
		<td><a href ="productdetail.do?id=${p.id }">${p.id }</a></td>
		<td>${p.name }</td>
		<td>${p.price }</td>
		<td>${p.regdate }</td>
		<td><img src = 'img/${p.imgname }'></td>
	</tr>
</c:forEach>
</tbody>
</table>

</div>











