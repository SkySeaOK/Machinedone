package com.example.develop.machinedone.bean;

import java.util.List;

/**
 * Created by develop on 2017/7/7.
 */

public class MainList
{

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

        private String value;
        private String onclick;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getOnclick() {
            return onclick;
        }

        public void setOnclick(String onclick) {
            this.onclick = onclick;
        }
    }
}
