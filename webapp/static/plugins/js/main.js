$(document).ready(function() {

	var map;
	var pos = {
		lat : 37.5641694,
		lng : 126.810789
	}

	function initMap() {
		map = new google.maps.Map(document.getElementById('map'), {
			center : pos,
			zoom : 18
		});
		setMarker();
	}

	function setMarker() {
		marker = new google.maps.Marker({
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

	$(".page-member").bind('click', function() {
		var pageText = $(this).attr("href");

		if (pageText == "#login") {
			joinFadeOut();
			loginFadeIn();
		} else if (pageText == "#join") {
			loginFadeOut();
			joinFadeIn();
		}
	})

	$(".btnLoginClose").on("click", function() {
		loginFadeOut();
	});

	$(".btnJoinClose").on("click", function() {
		joinFadeOut();
	});


	$(".btnLogin").on("click", function() {

		var id = $("usr").val();
		var pw = $("pwd").val();

		$.ajax({
			url : "/api/member/login",
			method : "POST",
			dataType : "json",
			data : {
				mId : id,
				pwd : pw
			}
		}).done(function(result) {
		})
	});

	$(".btnJoin").on("click", function() {

		var member = {
			name : $("#name").val(),
			email : $("#email").val(),
			password : $("#joinpw").val(),
			currentPassword : $("#currentpwd").val()
		};

		$.ajax({
			url : "/api/member/join",
			method : "POST",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(member)
		}).done(function(result) {
			alert("회원가입을 성공적으로 완료하였습니다.");
		})
	})

	$(".sendMessage").on("click", function() {
		applyConsult();
	});

	$('.carousel').carousel({
		interval: 3500,
		pause : "hover"
	});

	function inputImage(imgStartNum, imgEndNum, className, html1, html2){

		for(var i = imgStartNum; i <= imgEndNum; i++){
		$(className).append(html + i + html2);
		}
	}

	var html = "";
	var html2 = "";
	html += "<div class='item active' ";
	html += "style=\"background-image:url('/static";
	html += "/plugins/img/병원분들사진 (";
	html2 += ").JPG')\"></div>";
	inputImage(1, 1, ".carousel-inner", html, html2);
	html = "";
	html2 = "";
	html += "<div class='item' ";
	html += "style=\"background-image:url('/static";
	html += "/plugins/img/병원분들사진 (";
	html2 += ").JPG')\"></div>";
	inputImage(2, 4, ".carousel-inner", html, html2);

})