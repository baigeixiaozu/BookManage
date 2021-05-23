
$(function() {
    $("#btn").click(function () {
        var name = $("#bookName").val();
        var count = $("#bookCount").val();

        $.ajax({
            type: "get",
            url: "MarketingSystemServlet",
            data: {"bookName": name, "bookCount": count},
            dataType: "json",
            success: function (data) {
                var str = JSON.stringify(data);
                alert("提交成功" + str);
                alert(data.bookName + "/" + data.bookCount);
                $("#div1").html("<h2>" + str + "</h2>");
            },
            error: function (message) {
                alert("提交失败" + JSON.stringify(data));
            }
        })
    })
})