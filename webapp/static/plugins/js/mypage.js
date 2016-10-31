$(document).ready(function() {

	$(".logo-text").on("click", function() {
		location.href="/";
	})

	$(".mydata-open").on("click", function() {
		alert("MYDATA 발생1");
	})
	$(".security-setting").on("click", function() {
		alert("보안설정 발생1");
	})
	$(".cosult-data-open").on("click", function() {
		alert("상담내역 발생1");
	})

})