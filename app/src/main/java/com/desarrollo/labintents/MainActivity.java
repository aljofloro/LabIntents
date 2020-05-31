package com.desarrollo.labintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.MimeTypeFilter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int CAMERA_PIC_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_camera = (Button)findViewById(R.id.btn_camera);

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_PIC_REQUEST);
            }
        });

        /**
         * Envío de mensaje
         * */
        ((Button)findViewById(R.id.btn_send)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enviarIntent = new Intent();
                enviarIntent.setAction(Intent.ACTION_SEND);
                enviarIntent.putExtra(Intent.EXTRA_TEXT,
                        ((EditText)findViewById(R.id.edt_mensaje)).getText().toString());
                enviarIntent.setType("text/plain");
                if(enviarIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(Intent.createChooser(enviarIntent,getResources().getText(R.string.chooser_msn)));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_PIC_REQUEST){
            if(resultCode == RESULT_OK){
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                ImageView img_camera = (ImageView)findViewById(R.id.img_camera);
                img_camera.setImageBitmap(bitmap);
            }
        }
    }
}
