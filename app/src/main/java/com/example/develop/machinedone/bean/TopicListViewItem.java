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
         * value : New
         * onclick : CreateNewDoc(asdasdasda)
         */

        private String topic;

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        private String agreecount;

        public String getAgreecount() {
            return agreecount;
        }

        public void setAgreecount(String agreecount) {
            this.agreecount = agreecount;
        }

        private String thankcount;

        public String getThankcount() {
            return thankcount;
        }

        public void setThankcount(String thankcount) {
            this.thankcount = thankcount;
        }

        private int imageid;

        public int getImageid() {
            return imageid;
        }

        public void setImageid(int imageid) {
            this.imageid = imageid;
        }
    }
}
