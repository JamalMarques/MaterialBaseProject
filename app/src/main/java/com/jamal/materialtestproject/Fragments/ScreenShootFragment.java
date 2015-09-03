package com.jamal.materialtestproject.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jamal.materialtestproject.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by yamil.marques on 9/3/15.
 */
public class ScreenShootFragment extends BaseFragment{

    public static final String TAG = "ScreenShootFragment";

    private ImageView imageView,imageView2,imageView3,imageView4,imageView5;
    private Button shootItButton;
    private RelativeLayout shootLayout;

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    public static ScreenShootFragment getInstance(){
        ScreenShootFragment frag = new ScreenShootFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragmet_screen_shoot, container, false);

        shootLayout = (RelativeLayout)rootview.findViewById(R.id.photo_layout);
        imageView = (ImageView)rootview.findViewById(R.id.imageView);
        imageView2 = (ImageView)rootview.findViewById(R.id.imageView2);
        imageView3 = (ImageView)rootview.findViewById(R.id.imageView3);
        imageView4 = (ImageView)rootview.findViewById(R.id.imageView4);
        imageView5 = (ImageView)rootview.findViewById(R.id.imageView5);
        shootItButton = (Button)rootview.findViewById(R.id.b_shoot);
        shootItButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenShootBehavior(shootLayout);
            }
        });

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.img1);
        imageView.setImageBitmap(Bitmap.createScaledBitmap(bm, 2000, 1300, true));
        bm.recycle();
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.img2);
        imageView2.setImageBitmap(Bitmap.createScaledBitmap(bm, 2000, 1300, true));
        bm.recycle();
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.img3);
        imageView3.setImageBitmap(Bitmap.createScaledBitmap(bm, 2000, 1300, true));
        bm.recycle();
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.img4);
        imageView4.setImageBitmap(Bitmap.createScaledBitmap(bm, 2000, 1300, true));
        bm.recycle();
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.img5);
        imageView5.setImageBitmap(Bitmap.createScaledBitmap(bm, 2000, 1300, true));
        bm.recycle();

        /*Matrix matrix = new Matrix();
        imageView.setScaleType(ImageView.ScaleType.MATRIX);   //required
        matrix.postRotate((float) 80, imageView.getWidth()/2, imageView.getHeight()/2);
        imageView.setImageMatrix(matrix);*/


        return rootview;
    }


    private void screenShootBehavior(RelativeLayout shootLayout){
        /*rootview.setDrawingCacheEnabled(true);
                rootview.buildDrawingCache(true);
                Bitmap b = Bitmap.createBitmap(rootview.getDrawingCache());
                rootview.setDrawingCacheEnabled(false);
                recyclerView.setVisibility(View.GONE);
                img.setImageBitmap(b);*/

        //New
        View window = shootLayout;
        Canvas bitmapCanvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(window.getWidth()*2, window.getHeight()*2, Bitmap.Config.ARGB_8888);

        bitmapCanvas.setBitmap(bitmap);
        bitmapCanvas.scale(2.0f, 2.0f);
        window.draw(bitmapCanvas);

        //bitmap.compress(Bitmap.CompressFormat.PNG, 0, myOutputStream);


        //Save on the device memory
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/my_yam_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".png"; //.jpg
        File file = new File(myDir, fname);
        Log.i(TAG, "" + file);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, out); //b //JPEG //90
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_SHORT).show();
    }
}
