package com.example.tvsample.entity;

import java.util.List;

/**
 * Created by JasonWu on 04/01/2018
 */

public class VideoDetailInfo {
    /**
     * code : 200
     * data : {"commentCount":0,"cover":"http://i.ytimg.com/vi/4hMDEdCth4E/mqdefault.jpg","description":"一體雙魂，打破現實與「二次元」界限！\n一場換心手術讓少女 夏早安 獲得雙重人格❗️擁有推理能力的隱藏人格 愛迪生，牽引著夏早安進入了一系列連環殺人陰謀中。推理天才 米卡卡為幫助好友夏早安找出隱藏在愛迪生人格身後的秘密，於是組成了校園天才破案二人組..","detailList":[{"duration":1369,"index":1,"videoId":"4hMDEdCth4E"},{"duration":1368,"index":2,"videoId":"ZqUcplJ1yKI"},{"duration":1365,"index":3,"videoId":"Sp1qneIaDzU"},{"duration":1407,"index":4,"videoId":"vVf0TZkZD6c"},{"duration":1407,"index":5,"videoId":"TIA0ATKpYJg"},{"duration":1465,"index":6,"videoId":"xOY9LZjuUQk"},{"duration":1365,"index":7,"videoId":"mANFT7BEWrQ"},{"duration":1518,"index":8,"videoId":"_VNtcUjvKPk"},{"duration":1475,"index":9,"videoId":"Ij93tLUykzo"},{"duration":1419,"index":10,"videoId":"Tc-LwJVL7MI"},{"duration":1350,"index":11,"videoId":"B0DqSqO2YSE"},{"duration":1350,"index":12,"videoId":"SdjhD2P1Sp4"},{"duration":1337,"index":13,"videoId":"l8YcHs6A7wU"},{"duration":1340,"index":14,"videoId":"3HWG6NBizmg"},{"duration":1337,"index":15,"videoId":"Q3jkCnP-hhc"},{"duration":1337,"index":16,"videoId":"nBcwL5YGx0U"},{"duration":1395,"index":17,"videoId":"c0Q13BzM-mg"},{"duration":1372,"index":18,"videoId":"k7BJMeGMWGE"},{"duration":1424,"index":19,"videoId":"iRpD7oKm29w"},{"duration":1342,"index":20,"videoId":"PZR6-dsI0aY"}],"episode":20,"id":"f762c0c91f79a3cdc600edaa9727cbf3","isFavorite":0,"itemTypes":["today"],"tags":["2017","大陸","懸疑"],"title":"推理筆記","viewCount":1}
     * msg : success
     */

    private String code;
    private VideoDetailEntity data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public VideoDetailEntity getData() {
        return data;
    }

    public void setData(VideoDetailEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
