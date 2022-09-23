<%@ page import="com.zerobase.project1.dto.Row" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.zerobase.project1.service.MariaWifiService" %>
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

    <br>
    <table>
        <thead>
            <tr>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류</th>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
        </thead>
        <tbody>
        <%
            String lat = request.getParameter("lat");
            String lnt = request.getParameter("lnt");
            MariaWifiService service = new MariaWifiService();
            int updateHistoryRows = service.insertLocationHistory(lat, lnt);
            List<Row> rowList = service.selectWifiInfo(lat, lnt);

            if (rowList != null && rowList.size() > 0) {
                for (int i = 0; i < rowList.size(); i++) {
                    Row row = rowList.get(i);
        %>
            <tr>
                <td><%=row.getManage_no()%></td>
                <td><%=row.getBorough()%></td>
                <td><%=row.getWifi_name()%></td>
                <td><%=row.getStreet_name_address()%></td>
                <td><%=row.getAddress_detail()%></td>
                <td><%=row.getInstallation_location()%></td>
                <td><%=row.getInstallation_type()%></td>
                <td><%=row.getInstallation_agency()%></td>
                <td><%=row.getService_specification()%></td>
                <td><%=row.getNet_type()%></td>
                <td><%=row.getInstall_year()%></td>
                <td><%=row.getIn_out()%></td>
                <td><%=row.getWifi_connection()%></td>
                <td><%=row.getX_coordinate()%></td>
                <td><%=row.getY_coordinate()%></td>
                <td><%=row.getWork_date()%></td>
            </tr>
        <%
                }
            }
        %>

        </tbody>
    </table>

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