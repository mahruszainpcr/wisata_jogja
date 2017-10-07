package com.pcr.mahrus.wisatajogja;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import java.util.ArrayList;

/**
 * Created by PC-01-320 on 8/9/2017.
 */

public class WisataJogja extends AppCompatActivity{
    private RecyclerView mWisataRecyclerView;
    private WisataAdapter mAdapter;
    private ArrayList<wisata> mWisataCollection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wisata_jogja    );
        init();
        new GetData().execute();
    }
    private void init() {
        mWisataRecyclerView = (RecyclerView) findViewById(R.id.restaurant_recycler);
        mWisataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWisataRecyclerView.setHasFixedSize(true);
        mWisataCollection = new ArrayList<>();
        mAdapter = new WisataAdapter(mWisataCollection, this);
        mWisataRecyclerView.setAdapter(mAdapter);
    }
    private class GetData extends AsyncTask<String, Void, String> {




        @Override
        protected String doInBackground(String... strings) {
            try {
                JSONObject json = readJsonFromUrl("http://www.erporate.com/bootcamp/jsonBootcamp.php");
                Log.e("JSON",json.toString());
                JSONArray wisatasArray = json.getJSONArray("data");
                //list = new ArrayList<>();
                for (int i = 0; i < wisatasArray.length(); i++) {
                    String nama_pariwisata;
                    String alamat_pariwisata;
                    String detail_pariwisata;
                    String gambar_pariwisata;
                    JSONObject jWisata = (JSONObject) wisatasArray.get(i);
                    nama_pariwisata=jWisata.getString("nama_pariwisata").toString();
                    alamat_pariwisata=jWisata.getString("alamat_pariwisata").toString();
                    detail_pariwisata=jWisata.getString("detail_pariwisata").toString();
                    gambar_pariwisata=jWisata.getString("gambar_pariwisata").toString();
                    wisata wisatas=new wisata();
                    wisatas.setAlamat_pariwisata(alamat_pariwisata);
                    wisatas.setNama_pariwisata(nama_pariwisata);
                    wisatas.setDetail_pariwisata(detail_pariwisata);
                    wisatas.setGambar_pariwisata(gambar_pariwisata);
                    //Log.e("Nama",""+jWisata.getString("nama_pariwisata").toString());
                    mWisataCollection.add(wisatas);

                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            mAdapter.notifyDataSetChanged();

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
