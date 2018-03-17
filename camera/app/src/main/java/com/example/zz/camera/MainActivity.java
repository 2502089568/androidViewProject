package com.example.zz.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {











































    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private  void camer(View view){
    myCamerView = (myCamerView)findViewById(R.id.camera);
        myCamerView.setJpegQuality(50);

    }


    @Override
        protected void onResume(){
            super.onResume();
            cameraView.start();
        }

        @Override
        protected void onPause(){
            cameraView.stop();
            super.onPause();
        }

}

