<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>人事管理系统 ——员工管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="${pageContext.request.contextPath }/css/css.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" />
<link
	href="${pageContext.request.contextPath }/js/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-migrate-1.2.1.js"></script>
<script
	src="${pageContext.request.contextPath }/js/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath }/css/pager.css"
	type="text/css" rel="stylesheet" />

<script type="text/javascript">
	$(function() {
		/** 获取上一次选中的部门数据 */
		var boxs = $("input[type='checkbox'][id^='box_']");
		$("#sex").val("${employee.sex}");
		/** 给全选按钮绑定点击事件  */
		$("#checkAll").click(function() {
			// this是checkAll  this.checked是true
			// 所有数据行的选中状态与全选的状态一致
			boxs.attr("checked", this.checked);
		})

		/** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
		$("tr[id^='data_']").hover(function() {
			$(this).css("backgroundColor", "#eeccff");
		}, function() {
			$(this).css("backgroundColor", "#ffffff");
		})

		/** 删除员工绑定点击事件 */
		$("#delete")
				.click(
						function() {
							/** 获取到用户选中的复选框  */
							var checkedBoxs = boxs.filter(":checked");
							if (checkedBoxs.length < 1) {
								$.ligerDialog.error("请选择一个需要删除的员工！");
							} else {
								/** 遍历得到用户选中的所有的需要删除的ids */
								var ids = checkedBoxs.map(function() {
									return this.value;
								})

								$.ligerDialog
										.confirm(
												"确认要删除吗?",
												"删除员工",
												function(r) {
													if (r) {
														// alert("删除："+ids.get());
														// 发送请求
														window.location = "${pageContext.request.contextPath }/employee/removeEmployee.action?ids="
																+ ids.get();
													}
												});
							}
						})
	})
</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td width="15" height="32"><img
				src="${pageContext.request.contextPath }/images/main_locleft.gif"
				width="15" height="32">
			</td>
			<td class="main_locbg font2"><img
				src="${pageContext.request.contextPath }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理
				&gt; 员工查询</td>
			<td width="15" height="32"><img
				src="${pageContext.request.contextPath }/images/main_locright.gif"
				width="15" height="32">
			</td>
		</tr>
	</table>

	<table width="100%" height="90%" border="0" cellpadding="5"
		cellspacing="0" class="main_tabbor">
		<!-- 查询区  -->
		<tr valign="top">
			<td height="30">
				<table width="100%" border="0" cellpadding="0" cellspacing="10"
					class="main_tab">
					<tr>
						<td class="fftd">
							<form name="empform" method="post" id="empform"
								action="${pageContext.request.contextPath }/employee/selectEmployee.action">
								<input type="hidden" name="currentPageNum"
									value="${page.currentPageNum}" id="pagenum" />
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="font3"><input type="hidden"
											id="qx_select_value" value="${jd.qxid }" /> 职位： <select
											name="job.id" id="job.id" style="width:143px;">
												<option value="0">--请选择职位--</option>
												<c:forEach items="${jobs}" var="job">
													<c:choose>
														<c:when test="${employee.job.id == job.id }">
															<option value="${job.id }" selected="selected">${job.name
																}</option>
														</c:when>
														<c:otherwise>
															<option value="${job.id }">${job.name }</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
										</select> 姓名：<input type="text" name="name" value="${employee.name}"> 身份证号码：<input
											type="text" name="cardId" maxlength="18" value="${employee.cardId}"></td>
									</tr>
									<tr>
										<td class="font3">性别： <select name="sex" id="sex"
											style="width:143px;">
												<option value="0">--请选择性别--</option>
												<option value="1">男</option>
												<option value="2">女</option>
										</select> 手机：<input type="text" name="phone" value="${employee.phone }"> 所属部门
											&nbsp;&nbsp;：<select name="dept.id" style="width:100px;">
												<option value="0">--部门选择--</option>
												<c:forEach items="${depts}" var="dept">
													<c:choose>
														<c:when test="${employee.dept.id == dept.id }">
															<option value="${dept.id }" selected="selected">${dept.name
																}</option>
														</c:when>
														<c:otherwise>
															<option value="${dept.id }">${dept.name }</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>

										</select>&nbsp; <input type="submit" value="搜索" /> <input id="delete"
											type="button" value="删除" />
										</td>
									</tr>
								</table>
							</form></td>
					</tr>
				</table></td>
		</tr>

		<!-- 数据展示区 -->
		<tr valign="top">
			<td height="20">
				<table width="100%" border="1" cellpadding="5" cellspacing="0"
					style="border:#c2c6cc 1px solid; border-collapse:collapse;">
					<tr class="main_trbg_tit" align="center">
						<td><input type="checkbox" name="checkAll" id="checkAll">
						</td>
						<td>姓名</td>
						<td>性别</td>
						<td>手机号码</td>
						<td>邮箱</td>
						<td>职位</td>
						<td>学历</td>
						<td>身份证号码</td>
						<td>部门</td>
						<td>联系地址</td>
						<td>建档日期</td>
						<td align="center">操作</td>
					</tr>
					<c:forEach items="${page.records}" var="employee" varStatus="stat">
						<tr id="data_${stat.index}" class="main_trbg" align="center">
							<td><input type="checkbox" id="box_${stat.index}"
								value="${employee.id}">
							</td>
							<td>${employee.name }</td>
							<td><c:choose>
									<c:when test="${employee.sex == 1 }">男</c:when>
									<c:otherwise>女</c:otherwise>
								</c:choose></td>
							<td>${employee.phone }</td>
							<td>${employee.email }</td>
							<td>${employee.job.name }</td>
							<td>${employee.education }</td>
							<td>${employee.cardId }</td>
							<td>${employee.dept.name }</td>
							<td>${employee.address }</td>
							<td><f:formatDate value="${employee.createDate}" type="date"
									dateStyle="long" /></td>
							<td align="center" width="40px;"><a
								href="${pageContext.request.contextPath }/employee/updateEmployee.action?flag=1&id=${employee.id}">
									<img title="修改"
									src="${pageContext.request.contextPath }/images/update.gif" />
							</a></td>
						</tr>
					</c:forEach>
				</table></td>
		</tr>
		<!-- 分页标签 -->
		<TR valign="top">
			<TD align="center">
				<!-- 分页开始 --> <%@ include file="/jsp/commns/page.jsp"%>
				<!-- 分页结束 --></TD>
		</TR>
	</table>
	<div style="height:10px;"></div>
</body>
</html>