package com.example.tvsample.utils;

/**
 * Created by JasonWu on 2017/9/13
 */

public class SignatureUtil {

    public static String loginInfoToString(String identifier, long ts, String name, String email, String gender
            , String avatar, int sourceType, String version) {
        return "identifier=" + identifier + "&ts=" + ts + "&name=" + name + "&email=" + email + (gender == null ? "" : "&gender=" + gender)
                + "&avatar=" + avatar + "&sourceType=" + sourceType + "&version=" + version;
    }

    public static String logoutIfoToString(String memberId, long ts, String token, String version) {
        return "memberId=" + memberId + "&ts=" + ts + "&token=" + token + "&version=" + version;
    }
}
