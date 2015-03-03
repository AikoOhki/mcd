package com.example.aiko.mcd;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;


public class Closeet extends ActionBarActivity {

    private static final int REQUEST_GALLERY = 1;
    ImageView outer;
    ImageView tops;
    ImageView bottoms;
    ImageView dress;
    ImageView shoes;
    ImageView accessories;
    ImageView pushedButton;
    Bitmap bp;

    File dateFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closeet);
        outer = (ImageView) findViewById(R.id.outer);
        tops = (ImageView) findViewById(R.id.tops);
        bottoms = (ImageView) findViewById(R.id.bottoms);
        dress = (ImageView) findViewById(R.id.dress);
        shoes = (ImageView) findViewById(R.id.shoes);
        accessories = (ImageView) findViewById(R.id.accessories);

        outer.setOnTouchListener(new MyButtonOnTouchListener());
        tops.setOnTouchListener(new MyButtonOnTouchListener());
        bottoms.setOnTouchListener(new MyButtonOnTouchListener());
        dress.setOnTouchListener(new MyButtonOnTouchListener());
        shoes.setOnTouchListener(new MyButtonOnTouchListener());
        accessories.setOnTouchListener(new MyButtonOnTouchListener());
    }

    public void outer(View v) {
        pushedButton = outer;

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);

    }

    public void tops(View v) {
        pushedButton = tops;

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    public void bottoms(View v) {
        pushedButton = bottoms;

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    public void dress(View v) {
        pushedButton = dress;

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    public void shoes(View v) {
        pushedButton = shoes;

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    public void accessories(View v) {
        pushedButton = accessories;

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            try {
                InputStream input = getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(input);
                input.close();

                pushedButton.setImageBitmap(image);
            } catch (Exception e) {

            }

        }
    }

    public Bitmap getViewBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap cache = view.getDrawingCache();
        if (cache == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cache);
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public void ss(View v) {

        bp = getViewBitmap(findViewById(R.id.cdRoot));

        dateFile = new File(Environment.getExternalStorageDirectory(),
                "MCD-" + System.currentTimeMillis() + ".png");

        try {
            FileOutputStream outStream = new FileOutputStream(dateFile);
            bp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void instagram(View v) {
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.instagram.android");
        startActivity(intent);

    }

    class MyButtonOnTouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d("a", "a");
                    ((ImageView) v).setColorFilter(new LightingColorFilter(Color.LTGRAY, 0));
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_OUTSIDE:
                    ((ImageView) v).clearColorFilter();
                    break;
            }
            return false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_closeet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
