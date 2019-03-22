<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>My WebSocket</title>
</head>
<body>
<button onclick="conectWebSocket()">连接WebSocket</button>
<button onclick="closeWebSocket()">断开连接</button><br/>
发送对象：<input id="touserId" type="text" placeholder="请输入要发送的对象的用户名"/><br/>
消息内容：<input id="text" type="text" placeholder="请输入要发送的内容"/>
<button onclick="send()">发送消息</button>
<div id="message"></div>
</body>
<script type="text/javascript">
    var websocket = null;

    function conectWebSocket(){
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket'in window) {
            websocket = new WebSocket("ws://localhost:8080/ws");
        } else {
            alert('not support websocket')
        }

        //连接发生错误的回调方法
        websocket.onerror = function() {
            setMessageInnerHTML("error");
        };

        //连接成功建立的回调方法
        websocket.onopen = function(event) {
            setMessageInnerHTML("成功建立连接");
        }

        //接收到消息的回调方法
        websocket.onmessage = function(event) {
            setMessageInnerHTML(event.data);
        }

        //连接关闭的回调方法
        websocket.onclose = function() {
            setMessageInnerHTML("有一连接关闭");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function() {
            websocket.close();
        }
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        // 要传递的对象
        var obj = new Object();
        // 要发送的对象，为空则群发
        var touserId = document.getElementById('touserId').value;
        // 要发送的消息内容
        var message = document.getElementById('text').value;
        obj.message = message;
        touserId = touserId.trim();
        if(touserId != ""){
            console.log('单聊');
            obj.touserId = touserId;
        }else{
            console.log('群聊');
            obj.touserId = "-1";
        }
        //  发送
        websocket.send(JSON.stringify(obj));
    }
</script>
</html>