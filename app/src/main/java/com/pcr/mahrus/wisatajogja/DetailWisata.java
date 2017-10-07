package com.pcr.mahrus.wisatajogja;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by PC-01-320 on 8/11/2017.
 */

public class DetailWisata extends AppCompatActivity {
    String id="";
    ImageView gambar;
    TextView nama;
    TextView alamat;
    TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_wisata);
        nama=(TextView)findViewById(R.id.nama_wisata_textview_detail);
        alamat=(TextView)findViewById(R.id.alamat_textview_detail);
        detail=(TextView)findViewById(R.id.detail_textview_detail);

        new GetData().execute();
    }

    private class GetData extends AsyncTask<String, Void, String> {



        String nama_pariwisata;
        String alamat_pariwisata;
        String detail_pariwisata;
        String gambar_pariwisata;

        @Override
        protected String doInBackground(String... strings) {
            try {
                JSONObject json = readJsonFromUrl("http://www.erporate.com/bootcamp/jsonBootcamp.php");

                JSONArray wisatasArray = json.getJSONArray("data");
                //list = new ArrayList<>();
                    Intent i=   getIntent();
                    id = i.getStringExtra("id");
                    JSONObject jWisata = (JSONObject) wisatasArray.get(Integer.parseInt(id));
                    Log.e("JSON",jWisata.toString());
                    nama_pariwisata=jWisata.getString("nama_pariwisata").toString();
                    alamat_pariwisata=jWisata.getString("alamat_pariwisata").toString();
                    detail_pariwisata=jWisata.getString("detail_pariwisata").toString();
                    gambar_pariwisata=jWisata.getString("gambar_pariwisata").toString();



            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String aVoid) {
        nama.setText(nama_pariwisata);
            alamat.setText(alamat_pariwisata);
            detail.setText(detail_pariwisata);
            new DownloadImageTask((ImageView) findViewById(R.id.imageview_wisata_detail))
                    .execute(gambar_pariwisata);

        }
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
