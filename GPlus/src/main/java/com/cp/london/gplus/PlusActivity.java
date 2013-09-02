package com.cp.london.gplus;

public class PlusActivity {
    String url, title, content, itemUrl;

    public static PlusActivity from(String url, String title, String content, String itemUrl) {
        PlusActivity a = new PlusActivity();
        a.url = url;
        a.title = title;
        a.content = content;
        a.itemUrl = itemUrl;
        return a;
    }

}
