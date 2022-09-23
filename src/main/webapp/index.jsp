<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        table {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
    <h1>와이파이 정보 구하기</h1>
    <a href="/zerobase_wifi/">홈</a>
    | <a href="history.jsp">위치 히스토리 목록</a>
    | <a href="load-wifi.jsp">OPEN API 와이파이 정보 가져오기</a><br>

    <form action="list.jsp" method="get">
        LAT : <input type="text" name="lat" id="lat">
        , LNT : <input type="text" name="lnt" id="lnt">
        <button type="button" id="getLocation">내 위치 가져오기</button>
        <input type="submit" value="근처 WIFI 정보 보기">
    </form>

    <script
            src = "https://code.jquery.com/jquery-3.6.1.js"
            integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
            crossorigin="anonymous"></script>

    <script>
        $('#getLocation').click(function(e) {
            navigator.geolocation.getCurrentPosition(function(pos) {
                var latitude = pos.coords.latitude;
                var longitude = pos.coords.longitude;
                $('#lat').val(latitude);
                $('#lnt').val(longitude);
                alert("현재 위치는 : " + latitude + ", "+ longitude);
            });
        });
    </script>
</body>
</html>