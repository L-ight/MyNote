package com.example.lightory.mynote;

/**
 * Created by Lightory on 2016/8/4.
 */
public class BaseItem {
    String li_title,li_content,li_date;
    BaseItem(String li_title,String li_content,String li_date){
        this.li_title = li_title;
        this.li_content = li_content;
        this.li_date = li_date;
    }
    public String getTitle(){
        return li_title;
    }
    public String getContent(){
        return li_content;
    }
    public String getDate(){
        return li_date;
    }
}
