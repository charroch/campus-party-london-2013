package com.cp.london.gplus;

public class PlusActivity {
    String url, title, content;

    public static PlusActivity from(String url, String title, String content) {
        PlusActivity a = new PlusActivity();
        a.url = url;
        a.title = title;
        a.content = content;
        return a;
    }
}
