package com.example.priyanshu.newsreport;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.app.LoaderManager.LoaderCallbacks;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BadmintonFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {
    private NewsAdapter madapter;
    private RecyclerView recyclerView;
    private TextView mEmptyStateTextView;
    private List<News> newsList=new ArrayList<>();
    public static final String Log_TAG=BadmintonFragment.class.getName();
    private static final int NEWS_LOADER_ID=3;

    private static final  String News_URL="http://content.guardianapis.com/search?q=badminton&api-key=3acf648e-60c0-49e6-8a7b-269b2c63ddac";


    public BadmintonFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.news_list,container,false);

        recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_view);
        mEmptyStateTextView = (TextView)rootview.findViewById(R.id.empty_view);

        madapter=new NewsAdapter(newsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(madapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        News currentNews = newsList.get(position);

                        // Convert the String URL into a URI object (to pass into the Intent constructor)
                        Uri NewsUri = Uri.parse(currentNews.getmUrl());

                        // Create a new intent to view the earthquake URI
                        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsUri);

                        // Send the intent to launch a new activity
                        startActivity(websiteIntent);
                    }
                })
        );
        ConnectivityManager connMgr =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager =getActivity().getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.restartLoader(NEWS_LOADER_ID, null, this).forceLoad();
            mEmptyStateTextView.setVisibility(View.GONE);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible

            // Update empty state with no connection error message
            View loadingIndicator = rootview.findViewById(R.id.loading_indicator);

            loadingIndicator.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("no_internet_connection");

        }
        return rootview;
    }


    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(getActivity(),News_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) throws NullPointerException {
        mEmptyStateTextView.setVisibility(View.GONE);

        try {


            View loadingIndicator = getView().findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
        }
        catch (NullPointerException e)
        {

        }


        if (data!=null&&!data.isEmpty()){
            newsList.addAll(data);
        madapter.notifyDataSetChanged();
        }
        else
        {
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("no data");
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
newsList.clear();
        madapter.notifyDataSetChanged();
    }
}



