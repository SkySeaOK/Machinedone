package com.example.develop.machinedone.bean;

import java.util.List;

/**
 * Created by develop on 2017/7/28.
 */

public class LogListViewItem {
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

        private String user;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

    }
}
