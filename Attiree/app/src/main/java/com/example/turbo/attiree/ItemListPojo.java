package com.example.turbo.attiree;

public class ItemListPojo {
    String icon;

    public ItemListPojo(String title, String description, String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
