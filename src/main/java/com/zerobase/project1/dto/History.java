package com.zerobase.project1.dto;

public class History {
    private String x_coordinate;
    private String y_coordinate;
    private String search_date;

    public History() {}
    public History(String x_coordinate, String y_coordinate, String search_date) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.search_date = search_date;
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

    public String getSearch_date() {
        return search_date;
    }

    public void setSearch_date(String search_date) {
        this.search_date = search_date;
    }
}
