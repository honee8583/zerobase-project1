package com.zerobase.project1.service;

import com.zerobase.project1.dto.History;
import com.zerobase.project1.dto.Row;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MariaWifiService {
    Connection con = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;

    String url = "jdbc:mariadb://localhost:3306/wifidb";
    String dbUserId = "wifiuser";
    String dbPassword = "1111";
    ResultSet rs = null;
    public int insertWifiInfo(List<Row> rowList) {
        int cnt = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "insert into wifi_info " +
                    "(manage_no, borough, wifi_name, street_name_address, address_detail, installation_location, " +
                    "installation_type, installation_agency, service_specification, net_type, install_year, in_out, wifi_connection, " +
                    "x_coordinate, y_coordinate, work_date) " +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            pstmt = con.prepareStatement(sql);

            for (Row row : rowList) {
                pstmt.setString(1, row.getManage_no());
                pstmt.setString(2, row.getBorough());
                pstmt.setString(3, row.getWifi_name());
                pstmt.setString(4, row.getStreet_name_address());
                pstmt.setString(5, row.getAddress_detail());
                pstmt.setString(6, row.getInstallation_location());
                pstmt.setString(7, row.getInstallation_type());
                pstmt.setString(8, row.getInstallation_agency());
                pstmt.setString(9, row.getService_specification());
                pstmt.setString(10, row.getNet_type());
                pstmt.setString(11, row.getInstall_year());
                pstmt.setString(12, row.getIn_out());
                pstmt.setString(13, row.getWifi_connection());
                pstmt.setString(14, row.getX_coordinate());
                pstmt.setString(15, row.getY_coordinate());
                pstmt.setString(16, row.getWork_date());

                if (pstmt.executeUpdate() > 0) {
                    cnt++;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return cnt;
    }

    public List<Row> selectWifiInfo(String lat, String lnt) {
        List<Row> rowList = new ArrayList<>();
        int lat3 = (int)  Float.parseFloat(lat);
        int lnt3 = (int)  Float.parseFloat(lnt);
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "select * from wifi_info " +
                    "where TRUNCATE(x_coordinate, 0) = ? " +
                    "and TRUNCATE(y_coordinate, 0) = ? " +
                    "limit 20";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(lat3));
            pstmt.setString(2, String.valueOf(lnt3));

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Row row = new Row();
                row.setManage_no(rs.getString("manage_no"));
                row.setBorough(rs.getString("borough"));
                row.setWifi_name(rs.getString("wifi_name"));
                row.setStreet_name_address(rs.getString("street_name_address"));
                row.setAddress_detail(rs.getString("address_detail"));
                row.setInstallation_location(rs.getString("installation_location"));
                row.setInstallation_type(rs.getString("installation_type"));
                row.setInstallation_agency(rs.getString("installation_agency"));
                row.setService_specification(rs.getString("service_specification"));
                row.setNet_type(rs.getString("net_type"));
                row.setInstall_year(rs.getString("install_year"));
                row.setIn_out(rs.getString("in_out"));
                row.setWifi_connection(rs.getString("wifi_connection"));
                row.setX_coordinate(rs.getString("x_coordinate"));
                row.setY_coordinate(rs.getString("y_coordinate"));
                row.setWork_date(rs.getString("work_date"));
                rowList.add(row);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return rowList;
    }

    public Integer insertLocationHistory(String lat, String lnt) {
        int latInt = (int)  Float.parseFloat(lat);
        int lntInt = (int)  Float.parseFloat(lnt);
        int result  = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "insert into location_history values(?, ?, now())";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(latInt));
            pstmt.setString(2, String.valueOf(lntInt));

            result = pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public List<History> selectLocationHistory() {
        List<History> historyList = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "select * from location_history order by search_date desc";

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                History history = new History();
                history.setX_coordinate(rs.getString("x_coordinate"));
                history.setY_coordinate(rs.getString("y_coordinate"));
                history.setSearch_date(rs.getString("search_date"));
                historyList.add(history);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return historyList;
    }

    public Integer deleteHistory(History history) {
        int result = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "delete from location_history " +
                    "where x_coordinate=? and y_coordinate=? and search_date=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, history.getX_coordinate());
            pstmt.setString(2, history.getY_coordinate());
            pstmt.setString(3, history.getSearch_date());

            result = pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
