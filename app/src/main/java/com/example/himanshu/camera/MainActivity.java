package com.example.himanshu.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static final int REEQUEST_IMAGE_CAPTURE=1;
    ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button capture=(Button)findViewById(R.id.button);
        imageview=(ImageView)findViewById(R.id.imageView);
    }
    public void launchCamera(View view)
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        /*File pictureDirectory= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "CameraApp");
        String pictureName=getPictureName();
        File imageFile=new File(pictureDirectory,pictureName);
        Uri pictureUri=Uri.fromFile(imageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
*/
        startActivityForResult(intent,REEQUEST_IMAGE_CAPTURE);
    }

    private String getPictureName() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp=sdf.format(new Date());
        return "Plane place image"+timestamp+".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REEQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK)
        {
            Bundle extras=data.getExtras();
            if(extras != null) {
                Bitmap photo = (Bitmap) extras.get("data");
                imageview.setImageBitmap(photo);
            }
        }
    }
}
