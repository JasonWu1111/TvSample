package com.example.tvsample.utils;

/**
 * Created by JasonWu on 2017/9/13
 */

public class SignatureUtil {

    public static String loginInfoToString(String identifier, long ts, String name, String email, String gender
            , String avatar, int sourceType, String version) {
        return "identifier=" + identifier + "&ts=" + ts + "&name=" + name + (email == null ? "" : "&email=" + email)
                + (gender == null ? "" : "&gender=" + gender) + "&avatar=" + avatar + "&sourceType=" + sourceType + "&version=" + version;
    }

    public static String logoutIfoToString(String memberId, String token, long ts, String version) {
        return "memberId=" + memberId + "&token=" + token + "&ts=" + ts + "&version=" + version;
    }

    public static String favoriteInfoToString(String memberId, String token, long ts, String videoId, String version) {
        return "memberId=" + memberId + "&token=" + token + "&version=" + version + "&ts=" + ts
                + "&videoId=" + videoId + "&version=" + version;
    }

    public static String favoriteListInfoToString(String memberId, String token, long ts, int page, int size, String version) {
        return "memberId=" + memberId + "&token=" + token + "&version=" + version + "&ts=" + ts
                + "&page=" + page + "&size=" + size + "&version=" + version;
    }
}
