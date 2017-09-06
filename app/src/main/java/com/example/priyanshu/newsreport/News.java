package com.example.priyanshu.newsreport;

/**
 * Created by Priyanshu on 12-Feb-17.
 */

public class News {
    private String mTitle;
    private String mUrl;
    private String mtime;
    public News(String Title,String url,String time)
    {

        mTitle=Title;
        mUrl=url;
        mtime=time;

    }

    public String getTitle()
    {
        return mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }
    public  String getTime()
    {
        return mtime;
    }
}
