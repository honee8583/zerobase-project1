package com.zerobase.project1.service;


import com.zerobase.project1.dto.Row;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiService {

    public static long getTotalCount() throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        urlBuilder.append("/" +  URLEncoder.encode("6f58766956686f6e313138415458487a","UTF-8") ); // 인증키
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); // 요청파일타입 (xml,xmlf,xls,json)
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); // 서비스명 (대소문자 구분 필수입니다.)
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
//        urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/
//        System.out.println(urlBuilder.toString());

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        String result = sb.toString();
        System.out.println("result: " + result);

        Long totalCount = 0L;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = null;
            jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject tbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
            totalCount = (Long) tbPublicWifiInfo.get("list_total_count");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ApiService.getTotalCount()");
        Long a = totalCount / 1000; // 반복횟수
        if (a % 10 > 0) {
            return a + 1;
        }

        return a;
    }
    public static List<Row> getData(long start, long end) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        urlBuilder.append("/" +  URLEncoder.encode("6f58766956686f6e313138415458487a","UTF-8") ); // 인증키
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); // 요청파일타입 (xml,xmlf,xls,json)
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); // 서비스명 (대소문자 구분 필수입니다.)
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start),"UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end),"UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
        urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/
        System.out.println(urlBuilder.toString());

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        String result = sb.toString();
//        System.out.println("result: " + result);

        List<Row> rowList = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = null;
            jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject tbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");

            if (tbPublicWifiInfo != null) {
                Long totalCount = (Long) tbPublicWifiInfo.get("list_total_count");

                JSONObject subResult = (JSONObject) tbPublicWifiInfo.get("RESULT");
                String code = (String) subResult.get("CODE");
                String message = (String) subResult.get("MESSAGE");
                JSONArray infoArr = (JSONArray) tbPublicWifiInfo.get("row");

                for (int i = 0; i < infoArr.size(); i++) {
                    JSONObject tmp = (JSONObject) infoArr.get(i);
//                    System.out.println(rowList.size());
                    Row row = new Row(
                            (String) tmp.get("X_SWIFI_MGR_NO"),     // 관리번호
                            (String) tmp.get("X_SWIFI_WRDOFC"),     // 자치구
                            (String) tmp.get("X_SWIFI_MAIN_NM"),    // 와이파이명
                            (String) tmp.get("X_SWIFI_ADRES1"),     // 도로명주소
                            (String) tmp.get("X_SWIFI_ADRES2"),     // 상세주소
                            (String) tmp.get("X_SWIFI_INSTL_FLOOR"),// 설치위치(층)
                            (String) tmp.get("X_SWIFI_INSTL_TY"),   // 설치유형
                            (String) tmp.get("X_SWIFI_INSTL_MBY"),  // 설치기관
                            (String) tmp.get("X_SWIFI_SVC_SE"),     // 서비스구분
                            (String) tmp.get("X_SWIFI_CMCWR"),      // 망종류
                            (String) tmp.get("X_SWIFI_CNSTC_YEAR"), // 설치년도
                            (String) tmp.get("X_SWIFI_INOUT_DOOR"), // 실내외구분
                            (String) tmp.get("X_SWIFI_REMARS3"),    // 와이파이 접속환경
                            (String) tmp.get("LAT"),                // 좌표1
                            (String) tmp.get("LNT"),                // 좌표2
                            (String) tmp.get("WORK_DTTM")           // 작업일자
                    );

//                System.out.println(row.toString());
                    rowList.add(row);
                }
            } else {
                return null;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

//        System.out.println("ApiService.getData()");

        return rowList;
    }
}