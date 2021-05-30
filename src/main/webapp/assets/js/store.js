const tableBody = function (){
    const tbody = $("#tbody");
    const API = "Api/Store/query"
    let queryType;
    let curPage = 1;
    let totalPage;
    let order = [1, 0];

    let init = (type)=>{
        queryType = type;
        query(1);
        orderEvent();
    }
    let query = (page)=>{
        httpGet(API + queryType, {
            page: page,
            order: order.join()
        }).then(res=>{
            tbody.empty();   // 清空
            const data = res.data;
            curPage = data.curPage
            totalPage = data.pageCnt
            let list = data.list;
            for (const ele of list) {
                tbody.append(genTr(ele))
            }
            middleNav()
        })
    }
    function genTr(ele){
        if("All" === queryType){
            return `<tr>
                        <td>${ele.id}</td>
                        <td>${ele.name}</td>
                        <td>${ele.author}</td>
                        <td>${ele.publish}</td>
                        <td>${ele.isbn}</td>
                        <td>${ele.price}</td>
                        <td>${ele.count}</td>
                        </tr>`
        }else if("In" === queryType || "Out" === queryType){
            return `<tr>
                        <td>${ele.id}</td>
                        <td>${ele.name}</td>
                        <td>${ele.count}</td>
                        <td>${ele.time}</td>
                        </tr>`
        }
    }
    let pageNav = ()=>{
        const pre = $("#page-nav #pre");
        const next = $("#page-nav #next");
        pre[0].disabled = curPage <= 1;

        pre.on("click", function(){
            curPage--;
            pre[0].disabled = curPage <= 1;
            next[0].disabled = false;
            query(curPage)

        })
        next.on("click", function(){
            curPage++
            pre[0].disabled = false;
            next[0].disabled = curPage >= totalPage;
            query(curPage)
        })
    }
    let middleNav = ()=>{
        const middle = $("#page-nav>#middle");
        if(middle.children().length === 0) {
            pageNav();
            for (let i = 1; i <= totalPage; i++) {
                middle.append(`<button>${i}</button>`);
                middle.children()[i-1].onclick = function () {
                    curPage = i;
                    document.getElementById("pre").disabled = curPage <= 1;
                    document.getElementById("next").disabled = curPage >= totalPage;
                    query(i)
                }
            }
        }

        let disabled = $("#page-nav>#middle>button[disabled]");
        if(disabled.length > 0)
            disabled[0].disabled = false
        $("#page-nav #middle button")[curPage - 1].disabled = true;

    }
    let orderEvent = ()=>{
        const thead = $("#tableHead").children();
        for (let i = 0; i < thead.length; i++) {

            thead[i].onclick = ()=>{
                if(order[0] === i + 1)
                    order[1] = ++order[1]%2;
                order[0] = i + 1;
                query(1)
            }
        }
    }
    return {
        init: init
    }
}();
