<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BC销售单据</title>
</head>
<body>
<div align="center">
<h2>销售单据维护</h2>
<table align="center" width="100%">
    <tr>
        <th><p>销售单号：<input type="text" name="TBNo" value="${item.tbno}"  readonly="readonly"></input></p></th>
        <th><p>单据日期：<input type="text" name="TBNo" value="${item.tbDate}"  readonly="readonly"></input></p></th>
        <th><p>客户名称：<input type="text" name="TBNo" value="${item.cusName}"  readonly="readonly"></input></p></th>
    </tr>
    <tr>
        <th><p>部门名称：<input type="text" name="TBNo" value="${item.deptName}"  readonly="readonly"></input></p></th>
        <th><p>建档人员：<input type="text" name="TBNo" value="${item.appUser}"  readonly="readonly"></input></p></th>
        <th><p>建档日期：<input type="text" name="TBNo" value="${item.appDate}"  readonly="readonly"></input></p></th>
    </tr>
</table>
<div>
    <hr style="height:5px;border:none;border-top:5px ridge green;" />
</div>
<div align="center">
<h2>销售单单身数据</h2>
<table align="center" width="100%">
    <tr>
        <th>序号</th>
        <th>商品编号</th>
        <th>商品品名</th>
        <th>商品规格</th>
        <th>商品单位</th>
        <th>商品数量</th>
        <th>操作</th>
    </tr>
    <c:if test="${not empty items}">
    <c:forEach items="${items}" var="item">
    <tr>

        <th>${item.it}</th>
        <th>${item.code}</th>
        <th>${item.desc}</th>
        <th>${item.spec}</th>
        <th>${item.unit}</th>
        <th>${item.num}</th>
        <th>
            <a href="FrmTranBCdetail?tbno=${item.tbno}&it=${item.it}">内容</a>
        </th>
    </tr>
    </c:forEach>
    </c:if>
        <tr>

        <th><a href="FrmTranBCb?tbno=${item.tbno}">增加</a></th>
        </tr>
</table>
</div>
<div>
</div>
</div>
<div>${msg}</div>
<div align="left">
    <a href="FrmTranBCs">返回</a>
</div>
</body>
</html>