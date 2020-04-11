package datvtp.utils;

import java.io.Serializable;

public class Constants implements Serializable {

    public static String GOOGLE_CLIENT_ID = "96412228183-n832q7o703me5esfep37rsn7c5nijjvb.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "kR5YUQrpZi0dF-oXlj6Vqyqf";
    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/HanaShop_VanThanhPhuongDat_SE130449/loginGoogle";
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
