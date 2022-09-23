package com.zerobase.project1.dto;

import lombok.*;

public class Row {
    private String manage_no;               // 관리번호
    private String borough;                 // 자치구
    private String wifi_name;               // 와이파이명
    private String street_name_address;     // 도로명주소
    private String address_detail;          // 상세주소
    private String installation_location;   // 설치위치(층)
    private String installation_type;       // 설치유형
    private String installation_agency;     // 설치기관
    private String service_specification;   // 서비스구분
    private String net_type;                // 망종류
    private String install_year;            // 설치년도
    private String in_out;                  // 실내외구분
    private String wifi_connection;         // 와이파이 접속환경
    private String x_coordinate;            // x 좌표
    private String y_coordinate;            // y 좌표
    private String work_date;               // 작업일자

    public Row() {}
    public Row(String manage_no, String borough, String wifi_name, String street_name_address, String address_detail,
               String installation_location, String installation_type, String installation_agency,
               String service_specification, String net_type, String install_year, String in_out,String wifi_connection,
               String x_coordinate, String y_coordinate, String work_date) {
        this.manage_no = manage_no;
        this.borough = borough;
        this.wifi_name = wifi_name;
        this.street_name_address = street_name_address;
        this.address_detail = address_detail;
        this.installation_location = installation_location;
        this.installation_type = installation_type;
        this.installation_agency = installation_agency;
        this.service_specification = service_specification;
        this.net_type = net_type;
        this.install_year = install_year;
        this.in_out = in_out;
        this.wifi_connection = wifi_connection;
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.work_date = work_date;
    }

    @Override
    public String toString() {
        return "Row{" +
                "manage_no='" + manage_no + '\'' +
                ", borough='" + borough + '\'' +
                ", wifi_name='" + wifi_name + '\'' +
                ", street_name_address='" + street_name_address + '\'' +
                ", address_detail='" + address_detail + '\'' +
                ", installation_location='" + installation_location + '\'' +
                ", installation_type='" + installation_type + '\'' +
                ", installation_agency='" + installation_agency + '\'' +
                ", service_specification='" + service_specification + '\'' +
                ", net_type='" + net_type + '\'' +
                ", install_year='" + install_year + '\'' +
                ", in_out='" + in_out + '\'' +
                ", wifi_connection='" + wifi_connection + '\'' +
                ", x_coordinate='" + x_coordinate + '\'' +
                ", y_coordinate='" + y_coordinate + '\'' +
                ", work_date='" + work_date + '\'' +
                '}';
    }

    public String getManage_no() {
        return manage_no;
    }

    public void setManage_no(String manage_no) {
        this.manage_no = manage_no;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getWifi_name() {
        return wifi_name;
    }

    public void setWifi_name(String wifi_name) {
        this.wifi_name = wifi_name;
    }

    public String getStreet_name_address() {
        return street_name_address;
    }

    public void setStreet_name_address(String street_name_address) {
        this.street_name_address = street_name_address;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getInstallation_location() {
        return installation_location;
    }

    public void setInstallation_location(String installation_location) {
        this.installation_location = installation_location;
    }

    public String getInstallation_type() {
        return installation_type;
    }

    public void setInstallation_type(String installation_type) {
        this.installation_type = installation_type;
    }

    public String getInstallation_agency() {
        return installation_agency;
    }

    public void setInstallation_agency(String installation_agency) {
        this.installation_agency = installation_agency;
    }

    public String getService_specification() {
        return service_specification;
    }

    public void setService_specification(String service_specification) {
        this.service_specification = service_specification;
    }

    public String getNet_type() {
        return net_type;
    }

    public void setNet_type(String net_type) {
        this.net_type = net_type;
    }

    public String getInstall_year() {
        return install_year;
    }

    public void setInstall_year(String install_year) {
        this.install_year = install_year;
    }

    public String getIn_out() {
        return in_out;
    }

    public void setIn_out(String in_out) {
        this.in_out = in_out;
    }

    public String getWifi_connection() {
        return wifi_connection;
    }

    public void setWifi_connection(String wifi_connection) {
        this.wifi_connection = wifi_connection;
    }

    public String getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(String x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public String getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(String y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    public String getWork_date() {
        return work_date;
    }

    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }
}