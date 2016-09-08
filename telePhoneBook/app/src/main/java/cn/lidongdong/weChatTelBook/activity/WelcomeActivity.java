package cn.lidongdong.weChatTelBook.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WelcomeActivity extends AppCompatActivity {
    private String theUrl = "http://img4.duitang.com/uploads/item/201502/01/20150201163209_NNruz.jpeg";
    private ImageView iv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        iv= (ImageView) findViewById(R.id.welcome_lv);
        tv= (TextView) findViewById(R.id.welcome_tv);
        new ImageTask().execute(theUrl);
        //去掉顶部的通知栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tv.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
         }
     });
    }

    private class ImageTask extends AsyncTask<String,Integer,Bitmap>{
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(WelcomeActivity.this);
            progressDialog.setMessage("加载中....");
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //先请求网络图片,在倒计时
            Bitmap bitmap=null;
            HttpURLConnection connection=null;
            InputStream inputStream=null;
            try {
                URL url=new URL(params[0]);
                connection= (HttpURLConnection) url.openConnection();
                if (connection.getResponseCode()== HttpURLConnection.HTTP_OK) {
                    inputStream=connection.getInputStream();
                    bitmap= BitmapFactory.decodeStream(inputStream);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (connection!=null&&inputStream!=null) {
                    try {
                        inputStream.close();
                        connection.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //开始倒计时
            int all=5;
            int index=0;
            while (all>=index){
                publishProgress(all);
                all--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (values[0]==0) {
                tv.setText("跳转");
                Log.d("ImageTask", "跳转");
            }else {
                tv.setText(String.valueOf(values[0]));
            }

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap!=null) {
                progressDialog.dismiss();
                iv.setImageBitmap(bitmap);
            }
        }
    }
}
