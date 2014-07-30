package com.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class ArticleFragment extends Fragment {

	public static final String ARG_POSITION = "arg_position";
	
	private int mArticlePosition = -1;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			mArticlePosition = savedInstanceState.getInt(ARG_POSITION);
		}
	}
	
	public void setArticle(int position) {
//		here you set the article 
	}
}
