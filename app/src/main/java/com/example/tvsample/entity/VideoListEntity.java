package com.example.tvsample.entity;

import java.util.List;

/**
 * Created by JasonWu on 04/01/2018
 */

public class VideoListEntity {

    /**
     * cover : https://i.ytimg.com/vi/r_bobF8ZnHo/mqdefault.jpg
     * description : 侯武忠選擇故鄉澎湖作為他開始行醫的起點，因為他深切體認到，澎湖離島為台灣醫療體系最弱的一環。為建立病患對醫師的信心，他自我為求準時看診，自掏腰包，包下交通船定期到離島赴診
     * episode : 5
     * id : 5f1c26537e037ced5fe9c12cd18dbf33
     * tags : ["mainland"]
     * title : 菊島醫師情
     */

    private String cover;
    private String description;
    private int episode;
    private String id;
    private String title;
    private List<String> tags;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
