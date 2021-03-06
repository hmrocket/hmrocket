package com.example.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class ListArticleFragment extends ListFragment {

	private OnHeadlineSelectedListener mCallback;
	
	 // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }
    
    @Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	// This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Send the event to the host activity
        mCallback.onArticleSelected(position);
    }
}
