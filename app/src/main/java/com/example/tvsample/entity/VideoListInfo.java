package com.example.tvsample.entity;

import java.util.List;

/**
 * Created by JasonWu on 2017/12/30
 */

public class VideoListInfo {

    /**
     * code : 200
     * msg :
     * data : [{"name":"海賊王","description":"海賊王搞笑片段","playListId":"PLPp04LRZtWt257HjgAccxgeRRtfeGAFxs","imageUrl":"http://rs.sfacg.com/web/comic/images/Logo/de61cff8-8066-46c2-82e9-b1871a8a5c38.jpg"},{"name":"火影忍者","description":"火影忍者完整OP","playListId":"PLzMpXgW7tjL42ZbZ5nP_HgBOqvxkjxZra","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDLy_mInshxYbJn-FJBx2pF7QBHNxx3QUs07ZCjCapmYUgfEIn0w"},{"name":"死神","description":"死神特輯","playListId":"PLifVQVZ1QtGDxmDefOO6wOdjtqYVZ-7f7","imageUrl":"https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=c36594b10ef41bd5de53eff661db81a0/1f178a82b9014a9048783527a9773912b21beee2.jpg"},{"name":"銀魂","description":"銀魂全話アニメ","playListId":"PLIT1C_nkHVxOJhan6TEtavAgxTQ--rkTI","imageUrl":"http://wx4.sinaimg.cn/mw690/777d58c0gy1flofdluypej20cv07t3zp.jpg"},{"name":"家庭教師","description":"家庭教師里包恩Reborn OP+ED","playListId":"PLqYNJxCElBIw34NCAZIKbNFphTyXIW6ow","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCuIllXFx_xlPhtmgpiNUvjt1-wY1igv8BLe0A6NL1dfpMktDqRQ"},{"name":"死亡筆記","description":"死亡筆記粵語","playListId":"uIcwxAxWTPk&list=PL5ACE02FD345A5695","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6bE9CEVRuSCOz9GlMVjdahYiULYtujd2-AeBPjmW93UmCJu98bw"},{"name":"暗殺教師","description":"杀老师","playListId":"PLrK9LuzQn5QzAB6iW0lG2-9l1d9laXwFL","imageUrl":"https://pbs.twimg.com/media/CmSvjmLUcAMpYwW.jpg"},{"name":"叛逆的魯路修","description":"叛逆的魯路修第一季","playListId":"PLVQp3fIBGEDLvgVNEGkamo9WUYc7CFzfj","imageUrl":"https://i.ytimg.com/vi/oe_WSJriWeM/hqdefault.jpg"}]
     */

    private String code;
    private String msg;
    private List<PlayListEntity> data;

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

    public List<PlayListEntity> getData() {
        return data;
    }

    public void setData(List<PlayListEntity> data) {
        this.data = data;
    }

    public static class PlayListEntity {
        /**
         * name : 海賊王
         * description : 海賊王搞笑片段
         * playListId : PLPp04LRZtWt257HjgAccxgeRRtfeGAFxs
         * imageUrl : http://rs.sfacg.com/web/comic/images/Logo/de61cff8-8066-46c2-82e9-b1871a8a5c38.jpg
         */

        private String name;
        private String description;
        private String playListId;
        private String imageUrl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPlayListId() {
            return playListId;
        }

        public void setPlayListId(String playListId) {
            this.playListId = playListId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
