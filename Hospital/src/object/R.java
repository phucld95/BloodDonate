/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author TIENNH
 */
public class R {

    public static String windowsClassName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

    public static String urlGetUserWidthBlood = "http://phucld95.pe.hu/selectNhomMau.php?";
    public static String urlGetHospital = "http://phucld95.pe.hu/selectNameHospital.php?";
    public static String urlCheckUser = "http://phucld95.pe.hu/selectUserPass.php?";
    public static String urlCheckHospital = "http://phucld95.pe.hu/selectUserPassHospital.php?";
    public static String urlUpdateUserWithHospital = "http://phucld95.pe.hu/updateUser2.php?";
    public static String urlUpdateHospital = "http://phucld95.pe.hu/updateHospital.php?";
    public static String urlGetUser = "http://phucld95.pe.hu/selectUser.php?";

    public static String title = "\\icon\\title.jpg";
    public static String backGround = "\\icon\\background.jpg";
    public static String icon = "\\icon\\icon.jpg";
    public static ArrayList<User> arrayUser;

    public static int boundsX = 10;
    public static int heightFrame = 10;
    public static int heightLabel = 40;
    public static int widthLabel = 150;
    public static int widthTextField = 300;
    public static int widthButton = 100;
    public static Color color = new Color(209, 17, 65);
    public static Color colorBackGround = new Color(255, 240, 255);

}
