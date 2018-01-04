package com.example.tvsample.entity;

import java.util.List;

/**
 * Created by JasonWu on 04/01/2018
 */

public class VideoDetailEntity  {

    /**
     * commentCount : 0
     * cover : http://i.ytimg.com/vi/4hMDEdCth4E/mqdefault.jpg
     * description : 一體雙魂，打破現實與「二次元」界限！
     一場換心手術讓少女 夏早安 獲得雙重人格❗️擁有推理能力的隱藏人格 愛迪生，牽引著夏早安進入了一系列連環殺人陰謀中。推理天才 米卡卡為幫助好友夏早安找出隱藏在愛迪生人格身後的秘密，於是組成了校園天才破案二人組..
     * detailList : [{"duration":1369,"index":1,"videoId":"4hMDEdCth4E"},{"duration":1368,"index":2,"videoId":"ZqUcplJ1yKI"},{"duration":1365,"index":3,"videoId":"Sp1qneIaDzU"},{"duration":1407,"index":4,"videoId":"vVf0TZkZD6c"},{"duration":1407,"index":5,"videoId":"TIA0ATKpYJg"},{"duration":1465,"index":6,"videoId":"xOY9LZjuUQk"},{"duration":1365,"index":7,"videoId":"mANFT7BEWrQ"},{"duration":1518,"index":8,"videoId":"_VNtcUjvKPk"},{"duration":1475,"index":9,"videoId":"Ij93tLUykzo"},{"duration":1419,"index":10,"videoId":"Tc-LwJVL7MI"},{"duration":1350,"index":11,"videoId":"B0DqSqO2YSE"},{"duration":1350,"index":12,"videoId":"SdjhD2P1Sp4"},{"duration":1337,"index":13,"videoId":"l8YcHs6A7wU"},{"duration":1340,"index":14,"videoId":"3HWG6NBizmg"},{"duration":1337,"index":15,"videoId":"Q3jkCnP-hhc"},{"duration":1337,"index":16,"videoId":"nBcwL5YGx0U"},{"duration":1395,"index":17,"videoId":"c0Q13BzM-mg"},{"duration":1372,"index":18,"videoId":"k7BJMeGMWGE"},{"duration":1424,"index":19,"videoId":"iRpD7oKm29w"},{"duration":1342,"index":20,"videoId":"PZR6-dsI0aY"}]
     * episode : 20
     * id : f762c0c91f79a3cdc600edaa9727cbf3
     * isFavorite : 0
     * itemTypes : ["today"]
     * tags : ["2017","大陸","懸疑"]
     * title : 推理筆記
     * viewCount : 1
     */

    private int commentCount;
    private String cover;
    private String description;
    private int episode;
    private String id;
    private int isFavorite;
    private String title;
    private int viewCount;
    private List<DetailListBean> detailList;
    private List<String> itemTypes;
    private List<String> tags;

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public List<DetailListBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailListBean> detailList) {
        this.detailList = detailList;
    }

    public List<String> getItemTypes() {
        return itemTypes;
    }

    public void setItemTypes(List<String> itemTypes) {
        this.itemTypes = itemTypes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static class DetailListBean {
        /**
         * duration : 1369
         * index : 1
         * videoId : 4hMDEdCth4E
         */

        private int duration;
        private int index;
        private String videoId;

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }
    }
}
