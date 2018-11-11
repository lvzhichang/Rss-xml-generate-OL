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
<link rel="stylesheet" type="text/css" href="/rss.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>i ♡ feed43</title>
</head>
<body>
<div id="wrap">
<input type="button" onclick="window.location.href='/rss.jsp'" value="Hello world!">
<br>
<%
	List<RssEntry> list=new RssDaoProxy().findAll();
	for(RssEntry re:list)
	{
		%>
		<a href="/bro?id=<%=re.getID()%>"><%=re.getFEED_TITLE()%></a><br>
		<%
	}
%>
</div>
</body>
</html>