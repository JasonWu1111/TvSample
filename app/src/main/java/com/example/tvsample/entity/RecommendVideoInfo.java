package com.example.tvsample.entity;

import java.util.List;

/**
 * Created by JasonWu on 02/01/2018
 */

public class RecommendVideoInfo {
    private List<HeadlineEntity> data;

    public List<HeadlineEntity> getData() {
        return data;
    }

    public void setData(List<HeadlineEntity> data) {
        this.data = data;
    }

    public static class HeadlineEntity {
        /**
         * title : 正在熱播
         */

        private String headline;

        public String getHeadline() {
            return headline;
        }

        public void setHeadlineHeadline(String title) {
            this.headline = title;
        }
    }
}
