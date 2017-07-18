package com.example.develop.machinedone.bean;

import java.util.List;

/**
 * Created by develop on 2017/7/17.
 */

public class ProblemBean {


    private List<MenuitemBean> menuitem;

    public List<MenuitemBean> getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(List<MenuitemBean> menuitem) {
        this.menuitem = menuitem;
    }

    public static class MenuitemBean {
        /**
         * title : New
         * detail : When the boy came back, the old man was asleep in the chair and the sun was down.
         * user : AoBama
         */

        private String title;
        private String detail;
        private String user;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }
}
