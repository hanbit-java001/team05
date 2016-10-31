<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>마이페이지</title>

<link href="/static/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link href="/static/plugins/font-awesome/css/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

<link href="/static/plugins/css/main.css" rel="stylesheet">
<link href="/static/plugins/css/agency.css" rel="stylesheet">
<link href="/static/plugins/css/mypage.css" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!-- [if lt IE 9] -->
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script
	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<!--     [endif] -->
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-brand-centered">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="navbar-brand navbar-brand-centered logo-text">스카이정형외과</div>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbar-brand-centered">
			<ul class="nav navbar-nav">
				<li><a class="mydata-open" href="#">내정보</a></li>
				<li><a class="security-setting" href="#">보안설정</a></li>
				<li><a class="cosult-data-open" href="#">상담내역</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="top-greeting"><p class="top-greeting-text"></p></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="border-table">
		<p class="mydata"></p>
	</div>
	<br>
	<div class="border-table consult-data">
	</div>
</body>
<script src="/static/plugins/jquery/jquery-3.1.0.min.js"></script>

<script src="/static/plugins/vendor/bootstrap/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="/static/plugins/js/jqBootstrapValidation.js"></script>
<script src="/static/plugins/js/contact_me.js"></script>
<script src="/static/plugins/js/agency.js"></script>
<script src="/static/plugins/js/mypage.js"></script>
</html>