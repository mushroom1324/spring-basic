let index = {
    init: function() {
        $("#btn-save").on("click", () => {
            this.save()
        })
    },

    save: function() {
        // alert('user.save() called')
        let data = {
            email: $("#email").val(),
            username: $("#username").val(),
            password: $("#password").val()
        }
        
        // console.log(data)

        // ajax 호출 시 default가 비동기 호출
        // ajax 통신을 이용해 3개의 데이터를 JSON으로 변경하여 insert 요청
        // ajax 가 통신 성공 후 JSON 을 리턴해주면 서버가 자동으로 java object 로 변환해줌 !!
        $.ajax({
            type: "POST",
            url: "/api/user",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
//            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열(생긴게 json 이라면) => javascript 오브젝트로 변경 ( 생략가능 )
        }).done(function(response) {
            // if successful
            alert("register completed.")
            console.log(response)
            location.href = "/"
        }).fail(function(error) {
            // if failed
            alert(JSON.stringify(error));
        });
    }

}

index.init()
