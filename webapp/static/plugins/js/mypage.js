$(document).ready(function() {

	$(".logo-text").on("click", function() {
		location.href="/";
	});

	$(".mydata-open").on("click", function() {
		location.href="/member/mypage";
	});

	$(".member-logout").on("click", function() {
		location.href="/api/security/logout";
	})


	$(".security-setting").on("click", function() {
		console.log(pageNum);
	});

	$(".cosult-data-open").on("click", function() {
		showConsultData();
	});

	var pageNum = 0;

	var itemsPerPage = 3;
	var pagingRange = 3;
	var currentPage = 1;
	var firstPage;
	var lastPage;
	var totalPages;

	var consultData = {};
	var consultArray = [];
	var consultId = 0;

	$.ajax({
		url : "/api/mypage/data"
	}).done(function(result) {
		$(".top-greeting-text").html(result.name + "님 반갑습니다.");
		$(".mydata").append("이름 : " + result.name);
		$(".mydata").append("<br> 이메일 : " + result.email);
		$(".mydata").append("<br><br> <button class='modifydata btn btn-default'>정보수정</button>")
		getConsultCount(result.name);
		$(".modifydata").on("click", function() {
		})
	})

	function getConsult(currentPage) {
		$.ajax({
			url : "/api/consult/data",
			data : {
				pageNum : currentPage
			}
		}).done(function(result) {

			$(".consult-list").empty();

			consultData = {};
			consultArray.length = 0;
			consultArray = [];

			for(var i=0; i<result.length; i++){
				var title = result[i].message;

				if (title.length >= 15) {
					title = title.substr(0,15) + "....";
				}
				consultData = result[i];
				consultArray.push(consultData);
				consultHTML = "";
				consultHTML += consultHTML += "<tr><td>2016-11-02</td>";
				consultHTML += "<td class='consult-hover' idx='" + result[i].consultId + "'>";
				consultHTML	+= title + "</td>";
				if(result[i].reply == "true"){
					consultHTML += "<td>답변완료</td>";
				} else {
					consultHTML += "<td>답변미완료</td>";
				}
				$(".consult-list").append(consultHTML);
			}
			if (result.length == 0)
			$(".consult-list").append("<tr><td class='text-center' colspan='3'>상담접수내역이 존재하지않습니다.</td></tr>");

			$(".consult-hover").on("click", function(){
				var consultIdx = $(this).attr("idx");
				consultId = Number(consultIdx);
				drawConsult(consultId);
			})
			drawPaging(pageNum);
		})
	}

	function drawConsult(consultId) {

		for(var i=0; i < consultArray.length; i++){

			if (consultArray[i].consultId == consultId){

				var consultVO = consultArray[i];


				$(".reply-consult").show();
				$(".reply-consult").css("height", "550px");
				$("#user-message").val(consultVO.message);
				$("#message").val("답변이 등록되지않았습니다.");
				$("#message, #user-message").attr("readonly", true);

				if(consultVO.reply == "true"){
					$("#user-message").val(consultVO.message);
					$("#message").val(consultVO.replyContent);
				}
			}
		}
	}

	function getConsultCount(name) {

	$.ajax({
		url : "/api/consult/count"
	}).done(function(result) {
		var replyIdx = 0;
		$(".consult-data").html("상담접수하신 내용이 존재하지않습니다.");
		if (result.count != 0){
			var consultHTML = "";
			consultHTML += name + "님께서 상담접수 하신 건은 " + result.totalCount +"건입니다.";
			consultHTML += "<br> 총 상담건수 : " + result.totalCount + " &nbsp답변완료 : " + result.replyCount + "개";
			consultHTML += " &nbsp답변미완료 : " + (result.totalCount-result.replyCount) + "개";
			consultHTML += "<br> <button class='cosult-data-openBtn btn btn-default'>상담내역확인하기</button>";
			$(".consult-data").html(consultHTML);

			pageNum = result.totalCount;
		}
		$(".cosult-data-openBtn").on("click", function() {
			showConsultData();
			})
		})
	}

	function showConsultData(){
		$(".mydata-border-table").hide();
		$(".consult-border-table").hide();
		$(".consult-table").css("display", "inline-block");
		getConsult(currentPage);
	}

	function drawPaging(totalCount) {
		firstPage = parseInt((currentPage - 1) / pagingRange) * pagingRange + 1;
		lastPage = firstPage + pagingRange - 1;
		totalPages = parseInt(totalCount / itemsPerPage)
			+ (totalCount % itemsPerPage > 0 ? 1 : 0);

		$(".paging-num").empty();

		$(".paging-num").append("<button class='pageBtn btn btn-default'>이전</button>");

		for(var i = firstPage; i <= lastPage; i++) {
			if (i > totalPages)
				break;

		$(".paging-num").append("<button class='pageBtn btn btn-default'>" + i + "</button>");
		}

		$(".paging-num").append("<button class='pageBtn btn btn-default'>다음</button>");

		$(".pageBtn").on("click", function() {
			var pageText = $(this).text();
			var pageNumber = 0;

			if (pageText == "이전") {
				pageNumber = firstPage - 1;
				if(pageNumber < 1)
					return;
			} else if (pageText == "다음") {
				pageNumber = lastPage + 1;
				if(pageNumber > totalPages)
					return;
			} else {
				pageNumber = Number(pageText);
			}
			 currentPage = pageNumber;
			 getConsult(currentPage);
		});

		if(pageNum == 0){
			$(".paging-num").html("");
		}
	}
})