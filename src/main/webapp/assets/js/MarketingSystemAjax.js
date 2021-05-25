
$(function() {
    $("#btn").click(function () {
        var bookname=[];
        $("#books li").each(function(i){
            bookname[i]=$(this).text();
        });
        var bookcount=[];
        $("#books input").each(function (i){
         bookcount[i]=$(this).val();
        })
        $.ajax({
            type: "post",
            url: "/BookManage_war_exploded/MarketingSystemServlet",
            data: {"bookName":bookname, "bookCount":bookcount},
            dataType: "json",
            success: function (data) {
                var str = JSON.stringify(data);
                alert("提交成功");
                alert(data.bookName + "/" + data.bookCount);
            //    $("#div1").html("<h2>" + str + "</h2>");
            },
            error: function (data) {
                alert("提交失败"+JSON.stringify(data));
            }
        })
    })
})

