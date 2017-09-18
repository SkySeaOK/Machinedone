package com.example.develop.machinedone.bean;

import java.util.List;

/**
 * Created by develop on 2017/9/18.
 */

public class TopicListViewItem {

    private List<MenuitemBean> menuitem;

    public List<MenuitemBean> getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(List<MenuitemBean> menuitem) {
        this.menuitem = menuitem;
    }

    public static class MenuitemBean {
        /**
         * topic : New
         * agreecount : 1
         * thankcount : 2
         * imageid : R.mipmap.title_img
         */

        private String topic;
        private String agreecount;
        private String thankcount;
        private String imageid;

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getAgreecount() {
            return agreecount;
        }

        public void setAgreecount(String agreecount) {
            this.agreecount = agreecount;
        }

        public String getThankcount() {
            return thankcount;
        }

        public void setThankcount(String thankcount) {
            this.thankcount = thankcount;
        }

        public String getImageid() {
            return imageid;
        }

        public void setImageid(String imageid) {
            this.imageid = imageid;
        }
    }
}
