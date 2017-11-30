function refreshMsg(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            responsetext=this.responseText;
            var objs = JSON.parse(responsetext);
            dom=document.getElementById("msgs");
            dom.innerHTML=""            
            for (i=objs.length-1;i>=0;i--){
                var tb=document.createElement("TABLE");
                var tr1=document.createElement("TR");
                var p1=document.createElement("P");
                p1.innerHTML="<b>"+objs[i].username+"</b> "+new Date(objs[i].msg_time).toLocaleString();
                tr1.appendChild(p1);
                var tr2=document.createElement("TR");
                var tr2text=document.createTextNode(objs[i].msg);
                tr2.appendChild(tr2text);
                tb.appendChild(tr1);
                tb.appendChild(tr2);
                dom.appendChild(tb)
            }
            dom.scrollTop = dom.scrollHeight;
       }
    };
    xhttp.open("GET", "refreshMsg", true);
    xhttp.send(); 
}

function sendMsg(){
    msg=document.getElementById("sendbox").value;
    if (msg.length==0){
        alert("Can not send empty message!");
    }else{
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if (this.responseText=="success"){
                    document.getElementById("sendbox").value="";
                    refreshMsg(true);
                }
           }
        };
        xhttp.open("POST", "newMsg", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("msg="+msg);
    }

}

function toggleAuto(){
    var toggle = document.getElementById("autoBTN");
    var st=0;
    if (toggle.innerHTML=="Auto:OFF"){
        st=setInterval(refreshMsg,1000);
        toggle.innerHTML="Auto:ON";
    }else{
        clearInterval(st);
        toggle.innerHTML="Auto:OFF";
    }
    
}