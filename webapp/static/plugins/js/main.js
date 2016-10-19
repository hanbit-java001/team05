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

	function applyConsult() {

		var name = $("#name").val().trim();
		var email = $("#email").val().trim();
		var phone = $("#phone").val().trim();
		var message = $("#message").val().trim();

		if (name == "") {
		}
		if (email == "") {
		}
		if (phone == "") {
		}
	}

	$(".page-member").bind('click', function() {
		var pageText = $(this).attr("href");

		if (pageText == "#login") {
			loginFadeIn();
		} else if (pageText == "#join") {

		}
	})

	$(".btnLogin").on("click", function() {
		loginFadeOut();
	});

	$(".btnLoginClose").on("click", function() {
		loginFadeOut();
	});

	$(".sendMessage").on("click", function() {
		applyConsult();
	});

})