<%@ page import="com.zerobase.project1.service.ApiService" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zerobase.project1.dto.Row" %>
<%@ page import="com.zerobase.project1.service.MariaWifiService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: leedahoon
  Date: 2022/09/10
  Time: 8:21 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>load - wifi</title>
</head>
<body>
    <%
        ApiService api = new ApiService();
        MariaWifiService service = new MariaWifiService();
        int result = 0;
        try {
            List<Row> rowList = new ArrayList<>();
            long cnt = api.getTotalCount();
            for (long i = 0; i < cnt; i++) {
                Long start = i * 1000 + 1;
                Long end = start + 999;

                List<Row> subRowList = api.getData(start, end);
                rowList.addAll(subRowList);
            }
            System.out.println("rowList.size() : " + rowList.size());
            result = service.insertWifiInfo(rowList);
            System.out.println("updated row : " + cnt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    %>

    <%=result + "개의 와이파이 정보를 정상적으로 저장하였습니다."%>
    <br><a href="/zerobase_wifi/">홈으로 가기</a>
</body>
</html>
