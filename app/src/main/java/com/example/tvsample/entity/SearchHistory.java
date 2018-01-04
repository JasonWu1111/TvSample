package com.example.tvsample.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by JasonWu on 2018/1/4
 */

@Entity
public class SearchHistory {
    @Id
    private Long id;

    private String text;

    @Generated(hash = 865810149)
    public SearchHistory(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    @Generated(hash = 1905904755)
    public SearchHistory() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
