function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": commentContent,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                $("#comment_section").hide();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    window.open("https://github.com/login/oauth/authorize?client_id=e59f3b8eca5fa102d26f&redirect_uri=http://localhost:8287/callback&scope=user&state=1")
                    window.localStorage.setItem("closable", true);
                } else {
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: "json"
    });
    console.log(questionId);
    console.log(commentContent);

}