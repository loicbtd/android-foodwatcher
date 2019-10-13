package ca.qc.cgmatane.foodwatcher.vue;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Build;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.IOException;

import ca.qc.cgmatane.foodwatcher.R;


public class ActivitePrisePhoto extends ActiviteMaitresse {

    private static final String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int REQUEST_PERMISSIONS = 101;

    private boolean isCameraInitialized = false;

    private Camera mCamera = null;
    private static SurfaceHolder myHolder;

    private static CameraPreview mPreview;

    private FrameLayout preview;

    private int rotation;

    private static boolean frontCamera = true;
    private static Camera.Parameters p;

    private static OrientationEventListener orientationEventListener = null;

    private static boolean fM;

    public static Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.vue_prise_photo);

        navigationView.getMenu().findItem(R.id.activity_master_drawer_action_find_store).setChecked(true); //TODO R.id.'...' A changer

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && arePermissionsDenied()) {

            requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
            return;

        }

        if (!isCameraInitialized){

            mCamera = Camera.open();

            mPreview = new CameraPreview(this, mCamera);
            preview = (FrameLayout)findViewById(R.id.camera_preview);
            preview.addView(mPreview);

            rotateCamera();

            final Button takePictureCameraButton = (Button)findViewById(R.id.take_picture);
            takePictureCameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getApplicationContext(),"Take Picture button hit",Toast.LENGTH_SHORT).show();
                    mCamera.takePicture(null, null, mPicture);

                }
            });

            orientationEventListener = new OrientationEventListener(this) {
                @Override
                public void onOrientationChanged(int i) {
                    rotateCamera();
                }
            };
            orientationEventListener.enable();

            preview.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    if (frontCamera){

                        if (fM){
                            p.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                        }else {
                            p.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                        }

                        mCamera.setParameters(p);

                        fM = !fM;

                    }

                    return true;

                }
            });


        }

    }


    @Override
    protected void onPause() {
        super.onPause();

        releaseCamera();

    }

    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        if (requestCode == REQUEST_PERMISSIONS && grantResults.length>0){

            if (arePermissionsDenied()){
                ((ActivityManager) (this.getSystemService(ACTIVITY_SERVICE))).clearApplicationUserData();
                recreate();
            }else {
                onResume();
            }

        }

    }

    @SuppressLint("NewApi")
    private boolean arePermissionsDenied(){

        for (int i=0; i<PERMISSIONS.length; i++){
            if (checkSelfPermission(PERMISSIONS[i]) != PackageManager.PERMISSION_GRANTED){
                return true;
            }
        }
        return false;

    }

    public PictureCallback mPicture = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            bitmap = BitmapFactory.decodeByteArray(data,0,data.length);

            //TODO Intent comme j'avais fais mais je pense que ça ne marchera pas donc plutôt un finish()
//            Intent intent = new Intent(ActivitePrisePhoto.this,ActiviteAjouterProduit.class);
//            startActivity(intent);

            finish();

        }
    };

    private void rotateCamera(){

        if (mCamera != null){

            rotation = this.getWindowManager().getDefaultDisplay().getRotation();
            if (rotation == 0){
                rotation = 90;
            }else if (rotation == 1){
                rotation = 0;
            }else if (rotation == 2){
                rotation = 270;
            }else {
                rotation = 180;
            }

            mCamera.setDisplayOrientation(rotation);


            p = mCamera.getParameters();
            p.setRotation(rotation);
            mCamera.setParameters(p);

        }

    }

    private void releaseCamera(){

        if (mCamera != null){
            preview.removeView(mPreview);
            mCamera.release();
            orientationEventListener.disable();
            mCamera = null;
            frontCamera = !frontCamera;
        }

    }







    private static class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

        private static SurfaceHolder mHolder;
        private static Camera mCamera;

        public CameraPreview(Context context, Camera camera){

            super(context);
            mCamera = camera;
            mHolder = getHolder();
            mHolder.addCallback(this);
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {

            myHolder = surfaceHolder;
            try {
                mCamera.setPreviewDisplay(surfaceHolder);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }
    }




}
