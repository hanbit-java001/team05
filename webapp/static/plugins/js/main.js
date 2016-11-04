$(document).ready(function() {

	function showMenu(isLoginIn) {

		$(".after-member").hide();
		$(".befor-member").css("display", "inline-block");

		if(isLoginIn) {
			$(".before-member").hide();
			$(".after-member").css("display", "inline-block");
		}
	}

	callAjax({
		url : "/api/security/isLoggedIn",
		method : "GET",
		success : function(result) {
			showMenu(false);

			if (result.name != "") {
				showMenu(true);
				loginMode(result.name, result.email);
			}
		}
	})

	$(".findPassword").on("click", function() {
		loginFadeOut();
		findFadeIn();
	})

	function loginMode(name, email){
		if (name != "" && email != ""){
			$("#uName").val(name);
			$("#uEmail").val(email);
			$("#uName, #uEmail").attr("readonly", "false");
		}
	}

	var pos = {
			lat : 37.5641694,
			lng : 126.810789
		}

	function initMap() {

		var map = new google.maps.Map(document.getElementById('map'), {
			center : pos,
			zoom : 18,
			scrollwheel : false,
			mapTypeControl : false
		});
		var marker = new google.maps.Marker({
			map : map,
			position : pos
		});
	}

	initMap();

	function loginFadeIn() {
		$("#login-form").fadeIn();
	}

	function loginFadeOut() {
		$("#login-form").fadeOut();
	}

	function joinFadeIn() {
		$("#join-form").fadeIn();
	}

	function joinFadeOut() {
		$("#join-form").fadeOut();
	}

	function findFadeIn() {
		$("#find-form").fadeIn();
	}

	function findFadeOut() {
		$("#find-form").fadeOut();
	}

	$(".page-member").bind('click', function() {
		var pageText = $(this).attr("href");

		if (pageText == "#login") {
			joinFadeOut();
			loginFadeIn();
			$("#user-email").focus();
		} else if (pageText == "#join") {
			loginFadeOut();
			joinFadeIn();
			$("#name").focus();
		} else if (pageText == "#logout") {
			location.href="/api/security/logout";
		} else if (pageText == "#mypage") {
			location.href="/member/mypage";
		}
	})

	$(".btnLoginClose").on("click", function() {
		loginFadeOut();
		$("#user-pw").val("");
	});

	$(".btnJoinClose").on("click", function() {
		joinFadeOut();
		$("#pwd").val("");
		$("#currentpwd").val("");
	});

	$("#login-form input").on("keyup", function(event) {
		if (event.keyCode != 13) {
			return;
		}
		$(".btnLogin").click();
	})


	$(".btnLogin").on("click", function() {

		var email = $("#user-email").val();
		var pw = $("#user-pw").val();

		if (email.trim() == ""){
			alert("아이디를 입력하세요.");
			$("#user-email").focus();
			return;
		} else if (pw.trim() == "") {
			alert("비밀번호를 입력하세요.");
			$("#user-pw").focus();
			return;
		}

		callAjax({
			url : "/api/security/login",
			method : "POST",
			dataType : "json",
			data : {
				mEamil : email,
				mPassword : pw
			},
			success : function(result) {
				alert(result.mName + "님 반갑습니다.");
				location.href="/";
			}
		})
	});

	var isName = /^[가-힣]{2,4}$/;
	var isEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	var isPw = /^[A-za-z0-9'\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{6,16}$/;
	var isTel = /^01([0|1|6|7|8|9]?)\d{3,4}\d{4}$/;

	function insertAjax(url, data) {
		callAjax({
			url : url,
			method : "POST",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(data),
			success : function(result) {
				alert(result.success);
				location.reload();
			}
		});
	}

	$(".btnJoin").on("click", function() {

		var name = $("#name").val().trim();
		var email = $("#email").val().trim();
		var password = $("#pwd").val().trim();
		var currentPassword = $("#currentpwd").val().trim();

		if(name == "" || !isName.test(name)) {
			alert("이름이 제대로 입력되지않았습니다.");
			$("#name").focus();
			return;
		} else if (email == "" || !isEmail.test(email)) {
			alert("이메일이 제대로 입력되지않았습니다.");
			$("#email").focus();
			return;
		} else if (password == "" || !isPw.test(password)) {
			alert("비밀번호가 제대로 입력되지않았습니다.");
			$("#pwd").focus();
			return;
		} else if (currentPassword == "") {
			alert("비밀번호 확인을 입력해주시길 바랍니다.");
			$("#currentpwd").focus();
			return;
		} else if (password != currentPassword) {
			alert("비밀번호와 비밀번호확인이 일치하지않습니다.");
			$("#currentpwd").val("");
			$("#currentpwd").focus();
			return;
		}

		var member = {
				name : name,
				email : email,
				password : password,
				currentPassword : currentPassword
		};

		insertAjax("/api/member/join", member);

	});

	$(".btnfind").on("click", function() {
		callAjax({
			url : "/api/send/findPw",
			method : "POST",
			data : 	{
				toAddress : $("#find-email").val(),
				name : $("#find-name").val(),
			},
			success : function(result) {
				alert(result.success);
			}
		});
	})

	$(".btnfindClose").on("click", function() {
		findFadeOut();
	})

	function helpText(orgInput, content) {
		orgInput.parent(".form-group").find(".help-text").html(content);
	}

	$(".sendMessage").on("click", function() {

		var uName = $("#uName").val().trim();
		var uEmail = $("#uEmail").val().trim();
		var uTel = $("#uTel").val().trim();
		var uMessage = $("#uMessage").val();

		var isValidate = true;

		if(!isName.test(uName)) {
			var errorMsg = "이름이 제대로 입력되지않았습니다.";
			//alert(errorMsg);
			$("#uName").focus();
			helpText($("#uName"), errorMsg);
			isValidate = false;
		}
		if (!isEmail.test(uEmail)) {
			var errorMsg = "이메일이 제대로 입력되지않았습니다.";
			//alert(errorMsg);
			$("#uEmail").focus();
			helpText($("#uEmail"), errorMsg);
			isValidate = false;
		}
		if (!isTel.test(uTel)) {
			var errorMsg = "전화번호가 제대로 입력되지않았습니다.";
			//alert(errorMsg);
			$("#uTel").focus();
			helpText($("#uTel"), errorMsg);
			isValidate = false;
		}
		if (uMessage.length < 10) {
			var errorMsg = "상담내용이 제대로 입력되지않았습니다.";
			//alert(errorMsg);
			$("#uMessage").focus();
			helpText($("#uMessage"), errorMsg);
			isValidate = false;
		}

		if (!isValidate) {
			return;
		}

		var consult = {
				name : uName,
				email : uEmail,
				tel : uTel,
				message : uMessage
		}
		insertAjax("/api/add/consult", consult);
	});

	function inputImage(imgStartNum, imgEndNum, className, html1, html2){

		for(var i = imgStartNum; i <= imgEndNum; i++){
		$(className).append(html + i + html2);
		}
	}

	$('.carousel').carousel({
		interval: 3500,
		pause : "hover"
	});

	var html = "";
	var html2 = "";

	function resetHTML(){
		html = "";
		html2 = "";
	}

	resetHTML();
	html += "<div class='item active' ";
	html += "style=\"background-image:url('/static";
	html += "/plugins/img/병원분들사진 (";
	html2 += ").JPG')\"></div>";
	inputImage(1, 1, ".carousel-inner", html, html2);
	resetHTML();
	html += "<div class='item' ";
	html += "style=\"background-image:url('/static";
	html += "/plugins/img/병원분들사진 (";
	html2 += ").JPG')\"></div>";
	inputImage(2, 4, ".carousel-inner", html, html2);

})