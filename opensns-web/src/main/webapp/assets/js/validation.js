$(document).ready(function() {
	// validate signup form on keyup and submit
	$("#signupForm").validate({
		rules : {
			name : "required",
			password : {
				required : true,
				minlength : 8
			},
			cpassword : {
				required : true,
				minlength : 8,
				equalTo : "#password"
			},
			username : {
				required : true,
				email : true
			},
			agree : "required"
		},
		messages : {
			name : "이름을 입력해 주세요",
			password : {
				required : "암호를 입력해 주세요",
				minlength : "암호는 8자 이상이어야 합니다."
			},
			cpassword : {
				required : "암호를 한 번 더 입력해 주세요",
				minlength : "암호는 8자 이상이어야 합니다.",
				equalTo : "암호가 일치하지 않습니다."
			},
			username : "형식에 맞는 이메일을 입력해 주세요.",
			agree : "정책 동의에 체크해 주세요"
		}
	});
	$("#signinForm").validate({
		rules : {
			username : {
				required : true,
				email : true
			},
			password : {
				required : true,
				minlength : 8
			}
		},
		messages : {
			username : "Email을 형식에 맞추어 입력해 주세요",
			password : {
				required : "암호를 입력해 주세요",
				minlength : "암호는 8자 이상이어야 합니다."
			}
		}
	});
});