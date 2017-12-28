package com.example.tvsample.entity;

import java.util.List;

/**
 * Created by JasonWu on 28/12/2017
 */

public class HotSearchInfo {

    /**
     * code : 200
     * msg :
     * data : [{"rank":1,"title":"將軍在上"},{"rank":2,"title":"秦時明月之君臨之下"},{"rank":3,"title":"小豬佩奇 第四季"},{"rank":4,"title":"虎嘯龍吟"},{"rank":5,"title":"微微一下很傾城"},{"rank":6,"title":"熊出沒之探險日記"},{"rank":7,"title":"一起通過窗"},{"rank":8,"title":"九州海上牧云記"},{"rank":9,"title":"火影忍者"},{"rank":10,"title":"我的！體育老師"}]
     */

    private String code;
    private String msg;
    private List<HotSearchEntity> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<HotSearchEntity> getData() {
        return data;
    }

    public void setData(List<HotSearchEntity> data) {
        this.data = data;
    }

    public static class HotSearchEntity {
        /**
         * rank : 1
         * title : 將軍在上
         */

        private int rank;
        private String title;

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
