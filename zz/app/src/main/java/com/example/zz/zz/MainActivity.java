package com.example.zz.zz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.


        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


 /*   private Camera photo() {
        int cameraCount = 0;
        Camera cam = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();

        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {
                    cam = Camera.open(camIdx);
                    mCurrentCamIndex = camIdx;
                } catch (RuntimeException e) {
                    Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
                }
            }
        }

        Starting from API level 14, this method can be called when preview is active.
        private static void setCameraDisplayOrientation(Activity activity,int cameraId, Camera camera)
        {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(cameraId, info);
            int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();

            //degrees  the angle that the picture will be rotated clockwise. Valid values are 0, 90, 180, and 270.
            //The starting position is 0 (landscape).
            int degrees = 0;
            switch (rotation)
            {
                case Surface.ROTATION_0: degrees = 0; break;
                case Surface.ROTATION_90: degrees = 90; break;
                case Surface.ROTATION_180: degrees = 180; break;
                case Surface.ROTATION_270: degrees = 270; break;
            }
            int result;
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT)
            {
                result = (info.orientation + degrees) % 360;
                result = (360 - result) % 360;  // compensate the mirror
            }
            else
            {
                // back-facing
                result = (info.orientation - degrees + 360) % 360;
            }
            camera.setDisplayOrientation(result);
        }

        return cam;
    }

*/



   public void photo(View v) {
       Intent intent2 = new Intent(MainActivity.this,Show.class);
       startActivity(intent2);




       /* Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent1.putExtra("camerasensortype", 2); // 调用前置摄像头
        intent1.putExtra("autofocus", true); // 自动对焦
        intent1.putExtra("fullScreen", false); // 全屏
        intent1.putExtra("showActionIcons", false);
        startActivityForResult(intent1,2);*/





   }
}


