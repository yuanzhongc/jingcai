<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	application.setAttribute("basePath", basePath);
%>


<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'game.jsp' starting page</title>
    
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<meta name="keywords" content="keyword1,keyword2,keyword3">
	<meta name="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${basePath}js/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${basePath}css/animate.css">
	<script type="text/javascript" src="${basePath}js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${basePath}js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="${basePath}js/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${basePath}js/util.js"></script>
	<script type="text/javascript" src="${basePath}js/main-autowh.js"></script>
	<script type="text/javascript" src="${basePath}js/sg/sg.js"></script>
	<style type="text/css">
		* { margin:0; padding:0; }
		header {width:100%; height:80px; background:#E80619;}
		nav {width:100%; height:40px; background:#2563A4; }
		nav .nav {width:1000px; height:40px; }
	
		.content { width:1000px; height:400px; margin:0 auto; margin-top:50px; }
		.content .a-toolbar { }
		table { border:1px solid #ddd; font-size:12px; margin-top:15px;  }
		table th,td,tr {border:1px solid #ddd; vertical-align:middle;  text-align:center; line-height:40px; }
		table .pei { width:40px; height:20px; border:1px solid #ddd; line-height:20px; text-align:center; }
		tr .center { vertical-align:middle;  text-align:center; }
	</style>

  </head>
  
  <body>
    	<!-- 头部信息 -->
		<header>
			<div class="header"> </div>
		</header>
		
		<!-- 导航信息 -->
		<nav>
			<div class="nav"></div>
		</nav>
		
		<!-- 内容信息 -->
		<div class="content">
			<div class="con-nav">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0);">今日赛事</a></li>
					<li><a href="javascript:void(0);">数据分析</a></li>
				</ul>
			</div>
			<div class="a-toolbar">
				<div class="">
					<div class="btn" onclick="list()">刷新</div>
					<div class="btn" onclick="update()">更新</div>
				</div>
			</div>
			<div class="tabArea">
				<table class="table table-hover" id="table-list">
					<thead>
						<tr >
							<th class="center">序号</th>
							<th class="center">编码</th>
							<th class="center">赛事</th>
							<th class="center">截止时间</th>
							<th class="center">主场队  vs 客队</th>
							<th class="center">胜</th>
							<th class="center">平</th>
							<th class="center">负</th>
							<th class="center">数据</th>
							<th class="center">操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- 数据区域 -->
					</tbody>
				</table>
			</div>
		</div>
		
		<script type="text/javascript">
		$(function(){
			
			
		});
		function update(){
			$.ajax({
				type:"post",
				data:{},
				url:"./update",
				success:function(response){
					alert(response.result.msg);
				}
			});
			
		}
		
		
		function list(){
			$.ajax({
				type:"post",
				data:{"date":new Date()},
				url:"./list",
				success:function(response){
					var jdata = response.result.games;
					var len = jdata.length;
					if(len){
						var html = "";
						for(var i=0; i< len ; i++){
							html += "<tr>"+
										"<td>"+(i+1)+"</td>"+
										"<td>"+jdata[i].code+"</td>"+
										"<td>"+jdata[i].name+"</td>"+
										"<td>"+jdata[i].endTime+"</td>"+
										"<td>"+jdata[i].homeTeam + " vs "+jdata[i].guestTeam +"</td>"+
										"<td>"+
										"	<div class='pei'>"+jdata[i].win1+"</div>"+
										"	<div class='pei'>"+jdata[i].win2+"</div>"+
										"</td>"+
										"<td>"+
										"	<div class='pei'>"+jdata[i].ping1+"</div>"+
										"	<div class='pei'>"+jdata[i].ping2+"</div>"+
										"</td>"+
										"<td>"+
										"	<div class='pei'>"+jdata[i].lose1+"</div>"+
										"	<div class='pei'>"+jdata[i].lose2+"</div>"+
										"</td>"+
										"<td>"+jdata[i].ouzhi+"</td>"+
										"<td>"+
										"	<a href='javascript:void(0);'>删除</a>"+
										"	<a href='javascript:void(0);'>下载</a>"+
										"</td>"+
									"</tr>";
						}
						$("tbody").append(html);
					}
				}
			});
				
		}
		
		
		</script>
		
		
  </body>
</html>
