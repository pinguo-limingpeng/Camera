package com.camera360.camera;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	public static String CAMERA_RESULE_PHOTO = "camera_result_photo";
	private FragmentManager mManager;
	private FragmentTransaction mTransaction;
	private CameraBGFragment mBgFragment;
	private CameraResultShowFragment mShowFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mManager = getSupportFragmentManager();

		mBgFragment = new CameraBGFragment();
		mBgFragment.setPictureCallback(new PictureCallback() {

			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				mTransaction = mManager.beginTransaction();
				mShowFragment = new CameraResultShowFragment();
				Bundle bundle = new Bundle();
				bundle.putByteArray(CAMERA_RESULE_PHOTO, data);
				mShowFragment.setArguments(bundle);
				mTransaction.replace(R.id.container, mShowFragment).commit();
			}
		});

		mTransaction = mManager.beginTransaction();
		mTransaction.add(R.id.container, mBgFragment, "mBgFragment").commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
