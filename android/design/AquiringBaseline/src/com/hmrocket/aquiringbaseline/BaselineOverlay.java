/**
 * 
 */
package com.hmrocket.aquiringbaseline;

import android.graphics.drawable.TransitionDrawable;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * @author		mhamed
 * @since   	Jan 6, 2014
 * @version		v1.0
 */
public class BaselineOverlay {

	private final String       AQUIRING_BASELINE;
	private final String       SESSION_STARTING;

	private TextView           mBaselineCountdown;
	private String             mDisplayedText;
	private CountDownTimer     mCountDownTimer;
	private TransitionDrawable mTranstionDrawable;
	
	public BaselineOverlay(TextView txBaseline) {
		this.mBaselineCountdown = txBaseline;
		SESSION_STARTING  = mBaselineCountdown.getResources().getString(R.string.session_starting_);
		AQUIRING_BASELINE = mBaselineCountdown.getResources().getString(R.string.aquiring_baseline_);
	}

	public void startBaselineAnimation() {
		this.mBaselineCountdown.setText(AQUIRING_BASELINE);
		mDisplayedText = AQUIRING_BASELINE;
		this.mBaselineCountdown.setVisibility(TextView.VISIBLE);
		
		if (mCountDownTimer == null) {
			startAnimation();
		}
	}
	
	public void stopAndHide() {
		this.mBaselineCountdown.setVisibility(TextView.GONE);
		if (mCountDownTimer != null) {
			stopAnimation();
		}
	}
	
	public void startingSession() {
		this.mBaselineCountdown.setText(SESSION_STARTING);
		mDisplayedText = SESSION_STARTING;
		this.mBaselineCountdown.setVisibility(TextView.VISIBLE);
		if (mCountDownTimer == null) {
			startAnimation();
		}
	}
	
	public void setSessionTime(long sessionTime) {
		if (sessionTime > 0) {
			stopAndHide();
		} else if(sessionTime > -5*1000) {
			mDisplayedText = SESSION_STARTING;
		}else {
			mDisplayedText = AQUIRING_BASELINE;
		}
	}
	
	
	private void stopAnimation() {
		mCountDownTimer.cancel();
		mCountDownTimer = null;
	}
	
	private void startAnimation() {
		mTranstionDrawable = (TransitionDrawable) mBaselineCountdown.getBackground();
		mTranstionDrawable.setCrossFadeEnabled(true);
		mCountDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
			int count = 0;
			@Override
			public void onTick(long millisUntilFinished) {
				//the text is animated every 1000 milliseconds
				String text = mDisplayedText;
				switch (count%4) {
				case 0:
					break;
				case 1:
					text = " " + text;
					text = text.concat(".");
					break;
				case 2:
					text = "  " + text;
					text = text.concat("..");
					break;
				case 3:
					text = "   " + text;
					text = text.concat("...");
					break;
				}
				mBaselineCountdown.setText(text);
				
				if (count % 3 == 0) {
					//the background is animated every 3000 milliseconds with an offset of 500
					if (count % 2 ==0  ) {
						mTranstionDrawable.startTransition(2500);
					} else {
						mTranstionDrawable.reverseTransition(2500);
					}
				}
				count++;
			}

			@Override
			public void onFinish() {
				mCountDownTimer = null;
			}
		};
		mCountDownTimer.start();
	}
	
//	private void setCountdown( long newValue )
//	{
//		mSessionTime = newValue;
//
//		String S;
//		if( mSessionTime < 0 )
//		{
//			S = "-" + mSimpleDateFormat.format( -mSessionTime );
//		}
//		else if( mSessionTime > 0 )
//		{
//			S = "+" + mSimpleDateFormat.format( mSessionTime );
//		}
//		else
//		{
//			S = " " + mSimpleDateFormat.format( mSessionTime );
//		}
//		
//		vSessionTime.setText(S);
//	}
//	
//	public void setEpochId( int epochId )
//	{
//		mEpochId = epochId;
//		
//		if (mEpNum == null) {
////			if(D)Log.e(getTag(), "null Pointer exception");
////			if(D)Log.w(getTag(), "setEpochId() called and " + "mEpNum is " + mEpNum);
//			return;
//		}
//		
//		final String S;
//		if( epochId == Globals.BASELINE_EPOCH_INDEX )
//		{
//			S = "Baseline";
//		}
//		else
//		{
//			if( Globals.INFINITE_SESSION_DURATION == mCurrentSessionConfiguration.getSessionDuration() )
//			{
//				// Note that the leading space char is to match the
//				// 'space','-','+' in the time value column				
//				S = String.format(" %d", mEpochId );
//			}
//			else
//			{
//				// Technically we might have entered session duration + 1 epoch but really
//				// we don't want to display that one, so force displaying of session duration
//				long displayedEpochId = ( mEpochId <= mCurrentSessionConfiguration.getSessionDuration() )
//						? mEpochId
//						: mCurrentSessionConfiguration.getSessionDuration()
//						;
//				
//				// Note that the leading space char is to match the
//				// 'space','-','+' in the time value column				
//				S = String.format(" %d / %d"
//					, displayedEpochId
//					, mCurrentSessionConfiguration.getSessionDuration()
//					);
//			}
//		}
//		getActivity().runOnUiThread(new Runnable() {
//			
//			@Override
//			public void run() {
//				mEpNum.setText(S);
//			}
//		});
//	}

}
