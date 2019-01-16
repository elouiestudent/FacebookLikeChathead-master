package com.example.chatheads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button startService,stopService;
	public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//		startActivity(myIntent);
		checkPermission();

		startService=(Button)findViewById(R.id.startService);
		stopService=(Button)findViewById(R.id.stopService);
		startService.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startService(new Intent(getApplication(), FloatingButton.class));
				
			}
		});
		stopService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stopService(new Intent(getApplication(), FloatingButton.class));

			}
		});
	}

	@TargetApi(Build.VERSION_CODES.M)
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
			if (!Settings.canDrawOverlays(this)) {
				// You don't have permission
				checkPermission();
			} else {
				// Do as per your logic
			}
		}
	}

	public void checkPermission() {
		System.out.println("CHECK PERMISSIONS:");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (!Settings.canDrawOverlays(this)) {
				Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
						Uri.parse("package:" + getPackageName()));
				startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
			}
		}
	}
}
