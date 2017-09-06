package com.example.priyanshu.newsreport;

import android.content.Context;
import android.net.ParseException;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Priyanshu on 12-Feb-17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
private List<News> newsList;

    private TextView titleview,dateView,timeView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        public MyViewHolder(View view)
        {
            super(view);
         titleview = (TextView) view.findViewById(R.id.news_title);
         dateView = (TextView) view.findViewById(R.id.dateview);

         timeView = (TextView) view.findViewById(R.id.timeview);

        }



    }
public NewsAdapter(List<News> newslist)
{
    newsList=newslist;
    setHasStableIds(true);

}
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_item, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        News currentNews = newsList.get(position);
        String time=currentNews.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy ");
        SimpleDateFormat sdf3=new SimpleDateFormat("h:mm a");
        String[] parts=time.split("T");
        parts[1]=parts[1].replace("Z","");
        try {
            Date d = sdf.parse(time);
            dateView.setText(sdf2.format(d));
            timeView.setText(sdf3.format(d));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }



        titleview.setText(currentNews.getTitle());




    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
}
