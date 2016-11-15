$(document).ready(function() {


	$(".logo-text").on("click", function() {
		location.href="/";
	});

	$(".admin-logout").on("click", function() {
		location.href="/api/security/logout";
	})

	$(".consult-true").on("click", function() {
		currentPage = 1;
		getConsult(currentPage, "true");
		$(".reply-text").html("답변완료 목록입니다.");
		$(".reply-consult").hide();
	})

	$(".consult-false").on("click", function() {
		currentPage = 1;
		getConsult(currentPage, "false");
		$(".reply-text").html("답변 하셔야 할 목록입니다.");
		$(".reply-consult").hide();
	})

	var itemsPerPage = 10;
	var pagingRange = 10;
	var currentPage = 1;
	var firstPage;
	var lastPage;
	var totalPages;

	var consultData = {};
	var consultArray = [];
	var consultId = 0;
	var consultFlag = "";

	$(".consult-false").click();

	function getConsult(currentPage, flag) {
		$.ajax({
			url : "/api/admin/consult",
			data : {
				pageNum : currentPage,
				flag : flag
			}
		}).done(function(result) {

			$(".consult-list").empty();

			consultData = {};
			consultArray.length = 0;
			consultArray = [];
			consultFlag = flag;

			console.log(result);

			for(var i=0; i<result.consultVO.length; i++){
				var title = result.consultVO[i].message;

					if (title.length >= 15) {
						title = title.substr(0,15) + "....";
					}

					consultData = result.consultVO[i];
					consultArray.push(consultData);
					consultHTML = "";
					consultHTML += consultHTML += "<tr><td>2016-11-02</td>";
					consultHTML += "<td class='consult-hover' idx='" + result.consultVO[i].consultId + "'>";
					consultHTML	+= title + "</td>";
					consultHTML += "<td>" + result.consultVO[i].name + "</td>";
					consultHTML += "<td>" + result.consultVO[i].tel + "</td> </tr>"
					$(".consult-list").append(consultHTML);
			}

			if (result.totalCount == 0)
			$(".consult-list").append("<tr><td class='text-center' colspan='4'>내역이 존재하지않습니다.</td></tr>");

			$(".consult-hover").on("click", function(){
				var consultIdx = $(this).attr("idx");
				consultId = Number(consultIdx);
				drawConsult(consultId);
				$(".reply-consult").show();
				$("#user-message").attr("readonly", true);
				$("#message").focus();
			});
			drawPaging(result.totalCount);
		});
	}


	function drawConsult(consultId) {

		for(var i=0; i < consultArray.length; i++){

			if (consultArray[i].consultId == consultId){

				var consultVO = consultArray[i];
				$(".message-label").html("답변 하실 내용 :");
				$(".toAddress").html(consultVO.email);
				$(".name").html("이름 : " + consultVO.name);
				$(".tel").html("휴대폰 번호 : " + consultVO.tel);
				$("#user-message").val(consultVO.message);
				$("#message").attr("readonly", false);
				$("#message").val("");
				$("#message").focus();
				$(".sendMessage").show();

				if(consultVO.reply == "true"){
					$(".message-label").html("답변 하신 내용 :");
					$("#message").val(consultVO.replyContent);
					$("#message").attr("readonly", true);
					$(".sendMessage").hide();
				}
			}
		}
	}

	$(".sendMessage").on("click", function() {

		callAjax({
			url : "/api/send/email",
			method : "POST",
			data : {
				toAddress : $(".toAddress").text(),
				text : $("#message").val(),
				consultId : consultId
			},
			success : function(result) {
				alert(result.success);
				location.reload();
			}
		})
	});

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
			 getConsult(currentPage, consultFlag);
		});

		if(totalCount == 0){
			$(".paging-num").html("");
		}
	}

})