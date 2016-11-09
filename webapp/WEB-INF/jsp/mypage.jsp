<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

<link href="/static/plugins/css/agency.css" rel="stylesheet">
<link href="/static/css/main.css" rel="stylesheet">
<link href="/static/css/mypage.css" rel="stylesheet">
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
					<li><a class="member-logout" href="#">로그아웃</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="mydata-border-table">
		<p class="mydata get-Text"></p>
	</div>
	<br>
	<div class="consult-border-table">
		<p class="consult-data get-Text"></p>
	</div>

	<div class="consult-container">
		<div class="consult-table">
			<table class="table">
				<thead>
					<tr>
						<th>작성일</th>
						<th>제목</th>
						<th>답변상태</th>
					</tr>
				</thead>
				<tbody class="consult-list">
				</tbody>
				<tr class="text-center">
					<td class="paging-num" colspan="3"></td>
				</tr>
			</table>
		</div>

		<div class="reply-consult">
			<div>
				<label for="user-message">글내용 : </label>
				<textarea class="form-control" id="user-message" rows="10"></textarea>
			</div>
			<br>
			<div>
				<label for="message" class="message-label">답변 내용 : </label>
				<textarea class="form-control" id="message" rows="10"></textarea>
			</div>
		</div>
	</div>

	<div class="security-container">
		<div class="security-inputPw">
			<div class="form-group">
				<label for="input-password">보안을 위해 패스워드를 적어주세요.</label> <input
					type="password" class="form-control" id="input-password"
					placeholder="비밀번호 입력">
			</div>
			<div class="bottom-button">
				<button class="btnInputPwd btn btn-primary btn-block">확인</button>
			</div>
		</div>
	</div>

	<div class="modify-container">
		<div class="modify-form">
			<div class="form-group">
				<label for="modify-password">수정하실 비밀번호를 입력해주세요.</label> <input
					type="password" class="form-control" id="modify-password"
					placeholder="비밀번호">
			</div>
			<div class="form-group">
				<label for="modify-password">비밀번호 재확인</label> <input
					type="password" class="form-control" id="modify-currentPassword"
					placeholder="비밀번호재확인">
			</div>
			<div class="bottom-button">
				<button class="btnModifyData btn btn-primary btn-block">수정하기</button>
			</div>
		</div>
	</div>

	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<span class="copyright">&copy; 원장 김동환</span>
				</div>
				<div class="col-md-4">
					<a href="#">민창훈</a> <a href="#">민창훔</a> <a href="#">민창후훔</a>
				</div>
				<div class="col-md-4">
					<ul class="list-inline quicklinks">
						<li><a href="#">민창훈</a></li>
						<li><a href="#">서동수</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>

</body>
<script src="/static/plugins/jquery/jquery-3.1.0.min.js"></script>

<script src="/static/plugins/vendor/bootstrap/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="/static/plugins/js/jqBootstrapValidation.js"></script>
<script src="/static/plugins/js/contact_me.js"></script>
<script src="/static/plugins/js/agency.js"></script>
<script src="/static/js/common.js"></script>
<script src="/static/js/mypage.js"></script>
</html>