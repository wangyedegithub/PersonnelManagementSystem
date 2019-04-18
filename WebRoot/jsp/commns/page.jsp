<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
 function topage(currentPageNum){
		document.getElementById("pagenum").value=currentPageNum;
		document.forms[0].submit();
	}
	/* function topagesize(size){
		document.getElementById("pageSize").value=size;
		document.forms[0].submit();
	} */
</script>
<style type="text/css">
	div a{
		border: 1px solid lightblue; 
		padding: 3px 3px 3px 3px;
		border-radius:25%; 
	}
	div a:active {
		border-left: 5px solid lightblue;
		border-top: 5px solid lightblue;
		border-right: 0px solid lightblue;
		border-bottom: 0px solid lightblue;
	}
}
</style>
<div id="pagediv" style="margin-top: 20px">
	<a href="javascript:topage(1)">首页 </a>
	<a href="javascript:topage('${page.prePageNum}')">上一页</a> 
	<c:forEach begin="${page.beginPageNum}" end="${page.endPageNum}" var="snum">
		<a href="javascript:topage('${snum}')">${snum}</a>
	</c:forEach>
	<a href="javascript:topage('${page.nextPageNum}')">下一页</a> 
	<a href="javascript:topage('${page.totalPageNum}')">末页</a> 
	&nbsp;&nbsp;&nbsp;&nbsp;
		第${page.currentPageNum}页/共${page.totalPageNum}页 
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<!-- 每页显示<a href="javascript:topagesize(10)">10</a>条数据 -->
</div>
