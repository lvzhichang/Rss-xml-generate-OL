<%@page import="onem.cjq.web.database.RssDaoProxy"%>
<%@page import="java.util.List"%>
<%@page import="onem.cjq.web.mod.RssEntry"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="/ajax.js"></script>
<script>
	$(document).ready(function(){
	  $(".b_copy").bind("click",function(){
	    var url=$(this).parent().parent().find("a")[0];
	    url.select();
	    document.execCommand("Copy");
	    alert("复制链接成功！");
	  });
	});
</script>
<link rel="stylesheet" type="text/css" href="/rss.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>i ♡ feed43</title>
</head>
<body>
<div id="wrap">
<input type="button" onclick="window.location.href='/rss.jsp'" value="Hello world!">
<br>

<table>
<%
	List<RssEntry> list=new RssDaoProxy().findAll();
	for(RssEntry re:list)
	{
		%>
		
			<tr>
				<td>
					<a href="/bro?id=<%=re.getID()%>"><%=re.getFEED_TITLE()%></a>
				</td>
				<td>
					<a href="/rss.jsp?id=<%=re.getID()%>">编辑</a>
				</td>
				<td>
					<a href="#" onclick="RssDelete(<%=re.getID()%>);">删除</a>
				</td>
			</tr>
		
		<%
	}
%>
</table>

</div>
</body>
</html>