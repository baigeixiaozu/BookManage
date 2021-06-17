const tableBody = function (){
    const tbody = $("#tbody");
    const API = "Api/Store/query"
    let queryType;
    let curPage = 1;
    let totalPage;
    let order = [1, 0];
    let startDate = null, endDate = null;

    let init = (type)=>{
        queryType = type;
        query(1);
        orderEvent();
        pageNav();
        if(type!=="queryAll")DateEvent();
    }
    let query = (page)=>{
        httpGet(API + queryType, {
            start: startDate,
            end: endDate,
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
                        <td><a href="store/edit.jsp?bookId=${ele.id}" target="_blank">${ele.name}</a></td>
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
        next[0].disabled = curPage >= totalPage;

        pre.on("click", function(){
            if(curPage-1>0) {
                curPage--;
                query(curPage)
            }
            pre[0].disabled = curPage <= 1;
            next[0].disabled = curPage >= totalPage;

        })
        next.on("click", function(){
            if(curPage+1<=totalPage) {
                curPage++
                query(curPage)
            }
            pre[0].disabled = curPage <= 1;
            next[0].disabled = curPage >= totalPage;
        })
    }
    let middleNav = ()=>{
        const middle = $("#page-nav>#middle");
        middle.empty()
        for (let i = 1; i <= totalPage; i++) {
            middle.append(`<button>${i}</button>`);
            middle.children()[i-1].onclick = function () {
                curPage = i;
                document.getElementById("pre").disabled = curPage <= 1;
                document.getElementById("next").disabled = curPage >= totalPage;
                query(i)
            }
        }

        let disabled = $("#page-nav>#middle>button[disabled]");
        if(disabled.length > 0)
            disabled[0].disabled = false
        $("#page-nav #middle button")[curPage - 1].disabled = true;

        $("#page-nav #pre")[0].disabled = curPage <= 1;
        $("#page-nav #next")[0].disabled = curPage >= totalPage;
    }
    let orderEvent = ()=>{
        const thead = $("#tableHead").children();
        for (let i = 0; i < thead.length; i++) {
            thead[i].onclick = ()=>{
                if(order[0] === i + 1) {
                    order[1] = ++order[1] % 2;
                }

                let icon = $("#tableHead td>i");
                for (const ie of icon) {
                    ie.style.display="none"
                }
                thead[i].lastChild.style.display="block"
                thead[i].lastChild.style.transform = order[1]===0?"rotate(180deg)":"rotate(0deg)";
                order[0] = i + 1;
                query(1)
            }
        }
    }
    let DateEvent = ()=>{
        document.getElementById("startDate").onchange = e=>{
            let date = new Date(e.target.value);
            startDate = convertDate(date);
        }
        document.getElementById("endDate").onchange = e=>{
            let date = new Date(e.target.value);
            endDate = convertDate(date);
            query(1);
        }
    }
    let convertDate = (date = new Date())=>{
        let month = date.getMonth()<9?'0'+(date.getMonth()+1):(date.getMonth()+1);
        let day = date.getDate()<10?'0'+date.getDate():date.getDate();
        let hour = date.getHours()<10?'0'+date.getHours():date.getHours();
        let m = date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes();
        let s = date.getSeconds()<10?'0'+date.getSeconds():date.getSeconds();
        return `${date.getFullYear()}-${month}-${day} ${hour}:${m}:${s}`;
    }
    return {
        init: init
    }
}();
