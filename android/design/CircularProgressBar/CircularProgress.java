package com.tt.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thoughttechnology.apptps.R;

/**
 * Created by Mhamed on 15-03-02.
 */
public class CircularProgress extends LinearLayout {

    private int progress;
    private ProgressBar progressBar;
    TextView txProgress;

    public CircularProgress(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.compenent_circular_progress, this, true);
        progressBar = (ProgressBar) this.findViewById(R.id.progressBar_circular);
        txProgress = (TextView) findViewById(R.id.tx_progressCircular);
    }

    public CircularProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircularProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * @param progress value of the progress (between 0 and 100)
     */
    public void setProgress(int progress) {
        progressBar.setProgress(progress);
        txProgress.setText(Integer.toString(progress) + "%");
    }

    public int getProgress() {
        return progress;
    }

    /**
     * Change the color of the Progress bar. Using a pre-defined Drawable for the Circular progress like
     * {@link com.thoughttechnology.apptps.R.drawable#progress_bar_circular_black R.drawable.progress_bar_circular_lvl0}
     * {@link com.thoughttechnology.apptps.R.drawable#progress_bar_circular_orange R.drawable.progress_bar_circular_lvl1}
     * {@link com.thoughttechnology.apptps.R.drawable#progress_bar_circular_green R.drawable.progress_bar_circular_lvl2}
     *
     * @param d circular progress drawable
     */
    public void setProgressDrawable(Drawable d) {
        progressBar.setProgressDrawable(d);
    }


    /**
     * Change the color of the Progress bar. Using a pre-defined Drawable for the Circular progress like
     * {@link com.thoughttechnology.apptps.R.drawable#progress_bar_circular_black R.drawable.progress_bar_circular_lvl0}
     * {@link com.thoughttechnology.apptps.R.drawable#progress_bar_circular_orange R.drawable.progress_bar_circular_lvl1}
     * {@link com.thoughttechnology.apptps.R.drawable#progress_bar_circular_green R.drawable.progress_bar_circular_lvl2}
     *
     * @param id XML Drawable id
     */
    public void setProgressDrawable(int id) {
        progressBar.setProgressDrawable(getResources().getDrawable(id));
    }

    public void setTextSize(float size) {
        txProgress.setTextSize(size);
    }

    public void setTextColor(int color) {
        txProgress.setTextColor(color);
    }

}
