package com.reda.touk.myapp.app;


public class AppConfig {
    // Server user login url

    public static final String STREAM_URL = "rtsp://192.168.43.181:1935/live/android_test";
    public static final String PUBLISHER_USERNAME = "Hamza";
    public static final String PUBLISHER_PASSWORD = "chahdi94";

    public static String URL_BASE = "http://192.168.43.181:8888/";
    public static String URL_LOGIN = "http://192.168.43.181:8888/Projet_SVS/login.php";
    public static String URL_SAVEEVENT = "http://192.168.43.181:8888/Projet_SVS/locationws.php";
    public static String URL_GETUSER = "http://192.168.43.181:8888/Projet_SVS/webserviceapi.php";
    //public static String URL_LOGIN = "http://10.5.5.135/android_login_api/login.php";

    // Server user register url

    public static String URL_REGISTER = "http://192.168.43.181:8888/Projet_SVS/register.php";
   //public static String URL_REGISTER = "http://10.5.5.135/android_login_api/register.php";

    // Server user location url

   public static String URL_LOCATION = URL_BASE+"Projet_SVS/location.php";
   // public static String URL_LOCATION = "http://10.5.5.135/android_login_api/location.php";
}

