package com.design.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView[] images = new ImageView[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		int[] ids = new int[] { R.id.imageView1, R.id.imageView2, R.id.imageView3 };
		for (int i = 0; i < ids.length; i++)
			images[i] = (ImageView) findViewById(ids[i]);

		// make switch with top image if any of the left or the right image is
		// clicked
		images[0].setOnClickListener(translateOnClick);
		images[2].setOnClickListener(translateOnClick);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.action_anim_alpha:
			images[0].setOnClickListener(alphaOnClick);
			images[2].setOnClickListener(alphaOnClick);
			return true;

		case R.id.action_anim_rotation:
			images[0].setOnClickListener(translateOnClick);
			images[2].setOnClickListener(translateOnClick);
			return true;

		case R.id.action_anim_scale:
			images[0].setOnClickListener(scaleOnClick);
			images[2].setOnClickListener(scaleOnClick);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	View.OnClickListener alphaOnClick = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v instanceof ImageView) {
				final ImageView iv = (ImageView) v;
				switchCenterImage(android.R.anim.fade_out, android.R.anim.fade_in, iv);
			}

		}
	};
	View.OnClickListener translateOnClick = new View.OnClickListener() {

		@TargetApi(Build.VERSION_CODES.LOLLIPOP)
		@Override
		public void onClick(View v) {
			if (v instanceof ImageView && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				final ImageView iv = (ImageView) v;
				Path path = new Path();
				// center (Image 1) co-ordinates
				final float x1 = images[1].getX();
				final float y1 = images[1].getY();
				// Left/Right (Image 2) co-ordinates
				final float x3 = iv.getX();
				final float y3 = iv.getY();

				path.moveTo(x1, y1);
				final float x2 = (x1 + x3) / 2;
				float y2 = y1;
				path.quadTo(x2, y2, x3, y3);
				ObjectAnimator mAnimator = ObjectAnimator.ofFloat(iv, View.X, View.Y, path);
				int duration = getResources().getInteger(android.R.integer.config_longAnimTime);
				mAnimator.setDuration(duration);
				final Drawable drawableStart = images[1].getDrawable();
				final Drawable drawableEnd = iv.getDrawable();
				mAnimator.addListener(getOnAnimListener(iv, drawableStart, drawableEnd));
				mAnimator.start();
				
				path = new Path();
				path.moveTo(x1, y1);
				y2 = y3;
				path.quadTo(x2, y2, x3, y3);
				ObjectAnimator mAnimator2 = ObjectAnimator.ofFloat(images[1], View.X, View.Y, path);
				mAnimator2.setDuration(duration);
				mAnimator2.addListener(getOnAnimListener(images[1], drawableEnd, drawableStart));
				//just show-off
				mAnimator2.reverse();
			}

		}

		private AnimatorListener getOnAnimListener(final ImageView iv, final Drawable drawableStart, final Drawable drawableEnd) {
			return new Animator.AnimatorListener() {
				
				final float x = iv.getX();
				final float y = iv.getY();
				
				@Override
				public void onAnimationStart(Animator animation) {
					iv.setImageDrawable(drawableStart);
				}
				
				@Override
				public void onAnimationRepeat(Animator animation) {
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					iv.setX(x);
					iv.setY(y);
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
				}
			};
		}

	};
	View.OnClickListener scaleOnClick = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			final ImageView iv = (ImageView) v;
			switchCenterImage(R.anim.shrink_out, R.anim.shrink_in, iv);
		}
	};

	private void switchCenterImage(int animOutRes, int animInRes, final ImageView iv) {
		Animation outAnimation = AnimationUtils.loadAnimation(getApplicationContext(), animOutRes);
		final Animation inAnimation = AnimationUtils.loadAnimation(getApplicationContext(), animInRes);
		outAnimation.setAnimationListener(new Animation.AnimationListener() {
			int i = 0;

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				if (i++ % 2 == 0) {
					Drawable drawableCenter = images[1].getDrawable();
					images[1].setImageDrawable(iv.getDrawable());
					iv.setImageDrawable(drawableCenter);
					iv.startAnimation(inAnimation);
					images[1].startAnimation(inAnimation);
				}

			}
		});

		iv.startAnimation(outAnimation);
		images[1].startAnimation(outAnimation);
	}
}
