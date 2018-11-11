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
<%
String id=request.getParameter("id");
RssEntry re=null;
session.setAttribute("openID", -1);
if(id!=null)
{
	int openID=new Integer(id);
	if((re=new RssDaoProxy().findById(openID))!=null){
		session.setAttribute("openID", openID);
	}
}
%>
<div id="wrap">
<p>地址</p>
<input id="url" class="text" value="<%=re==null?"https://":re.getWEB_LINK()%>">
<p>编码</p>
<input id="encode" class="text" value="<%=re==null?"":re.getENCODE()%>">
<input value="Reload" onclick="RssGetWeb();" type="button">
<div id="raw_data" class="textarea">Click [Reload] to see contents</div>
<p>全局匹配</p>
<textarea id="global_regex"class="text" ><%=re==null?"":re.getGLOBAL_REG()%></textarea>
<p>单项匹配</p>
<textarea id="item_regex"class="text" ><%=re==null?"":re.getITEM_REG()%></textarea>
<input value="Extract" onclick="RssExtract();" type="button">
<div id="extract_data" class="textarea">Click [Extract] to see contents</div>
<p>Feed标题</p>
<input id="feed_title" class="text" value="<%=re==null?"":re.getFEED_TITLE()%>">
<p>Feed连接</p>
<input id="feed_link" class="text" value="<%=re==null?"":re.getFEED_LINK()%>">
<p>Feed介绍</p>
<textarea id="feed_desr" class="text" ><%=re==null?"":re.getFEED_DESR()%></textarea>
<p>定义每项标题</p>
<input id="item_title" class="text" value="<%=re==null?"":re.getITEM_TITLE_FORMAT()%>">
<p>定义每项连接</p>
<input id="item_link" class="text" value="<%=re==null?"":re.getITEM_LINK_FORMAT()%>">
<p>定义每项介绍</p>
<textarea id="item_desr" class="text" ><%=re==null?"":re.getITEM_DESR_FORMAT()%></textarea>
<input value="Generate" onclick="RssGenerate();" type="button">
<div id="generate_data" class="textarea">Click [Generate] to see contents</div>
</div>
</body>
</html>