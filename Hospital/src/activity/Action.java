/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Hospital;
import object.NhomMau;
import object.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author TIENNH
 */
public class Action {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @SuppressWarnings({"ConvertToTryWithResources", "UnusedAssignment"})
    public Hospital singIn(String request) {
        try (InputStream is = new URL(request).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonText);
            // System.out.println(json.toString());
            JSONArray jsonArray = (JSONArray) json.get("data");
            JSONObject jsonData = (JSONObject) jsonArray.get(0);
            // System.out.println(jsonData.toString());
            if (json.isEmpty()) {
                return null;
            } else {
                Hospital hospital = convertToHospital(jsonData);
                return hospital;
            }
        } catch (ParseException | IOException | java.text.ParseException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Hospital getHospital(String request) {

        try (InputStream is = new URL(request).openStream()) {

            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonText);
            JSONArray jsonArray = (JSONArray) json.get("data");
            JSONObject jsonData = (JSONObject) jsonArray.get(0);
            Hospital hospital = convertToHospital(jsonData);
            return hospital;
        } catch (ParseException | IOException | java.text.ParseException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getUser(String request) {
        try (InputStream is = new URL(request).openStream()) {
            ArrayList<User> arr = new ArrayList<>();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonText);
            JSONArray jsonArray = (JSONArray) json.get("data");
            int i;
            for (i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonData = (JSONObject) jsonArray.get(i);
                User user = convertToUser(jsonData);
                arr.add(user);
            }

            return arr;
        } catch (ParseException | IOException | java.text.ParseException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public boolean updateHospital(String request) {
        try (InputStream is = new URL(request).openStream()) {
            ArrayList<User> arr = new ArrayList<>();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonText);
            Object object = json.get("success");
            String result = String.valueOf(object);
            if (result.equals("1")) {
                return true;
            } else {
                return false;
            }

        } catch (IOException | ParseException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateUserFromHospital(String request) {
        try (InputStream is = new URL(request).openStream()) {
            ArrayList<User> arr = new ArrayList<>();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonText);
            Object object = json.get("success");
            String result = String.valueOf(object);
            if (result.equals("1")) {
                return true;
            } else {
                return false;
            }

        } catch (IOException | ParseException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @SuppressWarnings("UnusedAssignment")
    public User convertToUser(JSONObject json) throws java.text.ParseException {
        try {
            /*SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
               Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, 5); 
                String output = sdf.format(c.getTime());
                System.out.println(output);*/

            String id, userName, passWord, sdt, idBV, diaChi, thoiGianRanh, tinhTrang, ten, diemSucKhoe;
            Date ngayHienMau = null, ngayXetNghiem = null;
            NhomMau nhomMau = null;
            byte strByte[];
            id = convertNull((String) json.get("id"));
            userName = convertNull((String) json.get("userName"));
            passWord = convertNull((String) json.get("passWord"));
            nhomMau = convertStringToNhomMau(convertNull((String) json.get("nhomMau")));
            sdt = convertNull((String) json.get("sdt"));

            ngayHienMau = convertStringToDate(convertNull((String) json.get("ngayHienMau")));
            ngayXetNghiem = convertStringToDate(convertNull((String) json.get("ngayXetNghiem")));

            idBV = convertNull((String) json.get("idBV"));
            diaChi = convertNull((String) json.get("diaChi"));
            thoiGianRanh = convertNull((String) json.get("thoiGianRanh"));
            tinhTrang = convertNull((String) json.get("tinhTrang"));
            ten = convertNull((String) json.get("ten"));
            diemSucKhoe = convertNull((String) json.get("diemSucKhoe"));

            strByte = id.getBytes("ISO-8859-1");
            id = new String(strByte, "UTF-8");

            strByte = userName.getBytes("ISO-8859-1");
            userName = new String(strByte, "UTF-8");

            strByte = passWord.getBytes("ISO-8859-1");
            passWord = new String(strByte, "UTF-8");

            strByte = sdt.getBytes("ISO-8859-1");
            sdt = new String(strByte, "UTF-8");

            strByte = idBV.getBytes("ISO-8859-1");
            idBV = new String(strByte, "UTF-8");

            strByte = diaChi.getBytes("ISO-8859-1");
            diaChi = new String(strByte, "UTF-8");

            strByte = thoiGianRanh.getBytes("ISO-8859-1");
            thoiGianRanh = new String(strByte, "UTF-8");

            strByte = tinhTrang.getBytes("ISO-8859-1");
            tinhTrang = new String(strByte, "UTF-8");

            strByte = ten.getBytes("ISO-8859-1");
            ten = new String(strByte, "UTF-8");

            User user = new User(id, userName, passWord, nhomMau, sdt, ngayHienMau, ngayXetNghiem, idBV, diaChi, thoiGianRanh, tinhTrang, ten, diemSucKhoe);
            return user;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @SuppressWarnings("UnusedAssignment")
    public Hospital convertToHospital(JSONObject json) throws java.text.ParseException {
        try {
            /*SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
               Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, 5); 
                String output = sdf.format(c.getTime());
                System.out.println(output);*/

            String id, userName, passWord, sdt, diaChi, ten;
            byte strByte[];
            id = convertNull((String) json.get("id"));
            userName = convertNull((String) json.get("userName"));
            passWord = convertNull((String) json.get("passWord"));
            sdt = convertNull((String) json.get("sdt"));

            diaChi = convertNull((String) json.get("diaChi"));
            ten = convertNull((String) json.get("ten"));

            strByte = id.getBytes("ISO-8859-1");
            id = new String(strByte, "UTF-8");

            strByte = userName.getBytes("ISO-8859-1");
            userName = new String(strByte, "UTF-8");

            strByte = passWord.getBytes("ISO-8859-1");
            passWord = new String(strByte, "UTF-8");

            strByte = sdt.getBytes("ISO-8859-1");
            sdt = new String(strByte, "UTF-8");

            strByte = diaChi.getBytes("ISO-8859-1");
            diaChi = new String(strByte, "UTF-8");

            strByte = ten.getBytes("ISO-8859-1");
            ten = new String(strByte, "UTF-8");

            Hospital hospital = new Hospital(id, userName, passWord, sdt, diaChi, ten);
            return hospital;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Date convertStringToDate(String strDate) {
        try {
            if (strDate.equals("")) {
                return null;
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String dt[] = strDate.split("[-]");
                String str = "";
                int i;
                for (i = dt.length - 1; i >= 0; i--) {
                    if (i != 0) {
                        str += dt[i] + "-";
                    } else {
                        str += dt[i];
                    }
                }
                Date date = formatter.parse(str);
                return date;
            }
        } catch (java.text.ParseException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public NhomMau convertStringToNhomMau(String strNhomMau) {
        if (strNhomMau.equals("")) {
            return null;
        }
        NhomMau nhomMau = new NhomMau(strNhomMau, null);
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<String> arr = new ArrayList<>();
        if (strNhomMau.equalsIgnoreCase("A") || strNhomMau.equalsIgnoreCase("B")) {
            arr.add("O");
        } else if (strNhomMau.equalsIgnoreCase("AB")) {
            arr.add("A");
            arr.add("B");
            arr.add("O");
        }
        nhomMau.setNhomMauTT(arr);
        return nhomMau;
    }

    public String convertNull(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }
}
