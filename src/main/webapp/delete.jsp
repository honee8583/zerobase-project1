<%@ page import="com.zerobase.project1.service.MariaWifiService" %>
<%@ page import="com.zerobase.project1.dto.History" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>

<body>
    <%
        String x_coordinate = request.getParameter("x_coordinate");
        String y_coordinate = request.getParameter("y_coordinate");
        String search_date = request.getParameter("search_date");

        History history = new History(x_coordinate, y_coordinate, search_date);

        MariaWifiService service = new MariaWifiService();
        int result = service.deleteHistory(history);

        if (result > 0) {
            response.sendRedirect("history.jsp");
        }
    %>
</body>
</html>