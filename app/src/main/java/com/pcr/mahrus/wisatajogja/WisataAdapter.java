package com.pcr.mahrus.wisatajogja;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
 * Created by PC-01-320 on 8/11/2017.
 */

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.WisataHolder> {

    private ArrayList<wisata> mData;
    private Activity mACtivity;
    RecyclerView.OnItemTouchListener mItemClickListener;
    public WisataAdapter(ArrayList<wisata> data, Activity activity) {
        this.mData = data;
        this.mACtivity = activity;


    }

    @Override
    public WisataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_wisata_jogja, parent, false);
        return new WisataHolder(view);
    }

    @Override
    public void onBindViewHolder(WisataHolder holder, final int position) {
        wisata wisatas = mData.get(position);

        holder.setNama(wisatas.getNama_pariwisata());
        holder.setAlamat(wisatas.getAlamat_pariwisata());
        holder.setDetail(wisatas.getDetail_pariwisata().substring(0,100)+" ...");

        Glide.with(mACtivity)
                .load(wisatas.getGambar_pariwisata())
                .into(holder.wisataImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=position;
                Log.e("EEE:",mData.get(position).toString());
                Intent intent = new Intent(mACtivity,DetailWisata.class);
                intent.putExtra("id", ""+pos);
                mACtivity.startActivity(intent);
               /* String nama_pariwisata="";
                String alamat_pariwisata="";
                String detail_pariwisata="";
                String gambar_pariwisata="";
                try {
                    JSONObject json = readJsonFromUrl("http://www.erporate.com/bootcamp/jsonBootcamp.php");
                    Log.e("JSON",json.toString());
                    JSONArray wisatasArray = json.getJSONArray("data");
                    //list = new ArrayList<>();
                        JSONObject jWisata = (JSONObject) wisatasArray.get(1);
                        nama_pariwisata=jWisata.getString("nama_pariwisata").toString();
                        alamat_pariwisata=jWisata.getString("alamat_pariwisata").toString();
                        detail_pariwisata=jWisata.getString("detail_pariwisata").toString();
                        gambar_pariwisata=jWisata.getString("gambar_pariwisata").toString();
                        wisata wisatas=new wisata();
                        wisatas.setAlamat_pariwisata(alamat_pariwisata);
                        wisatas.setNama_pariwisata(nama_pariwisata);
                        wisatas.setDetail_pariwisata(detail_pariwisata);
                        wisatas.setGambar_pariwisata(gambar_pariwisata);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(mACtivity,DetailWisata.class);
                intent.putExtra("id", pos);
                intent.putExtra("nama_pariwisata", nama_pariwisata);
                intent.putExtra("alamat_pariwisata", alamat_pariwisata);
                intent.putExtra("gambar_pariwisata", gambar_pariwisata);
                intent.putExtra("detail_pariwisata", detail_pariwisata);*/
                //mACtivity.startActivity(intent);
                Log.e("Eid",""+pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    public RecyclerView.OnItemTouchListener getmItemClickListener() {
        return mItemClickListener;
    }


    public class WisataHolder extends RecyclerView.ViewHolder {

        ImageView wisataImageView;
        TextView namaPariwisataTextView;
        TextView alamatPariwisataTextView;
        TextView detailPariwisataTextView;

        public WisataHolder(View itemView) {
            super(itemView);

            wisataImageView = (ImageView) itemView.findViewById(R.id.imageview_wisata);
            namaPariwisataTextView = (TextView) itemView.findViewById(R.id.nama_wisata_textview);
            alamatPariwisataTextView = (TextView) itemView.findViewById(R.id.alamat_textview);
            detailPariwisataTextView = (TextView) itemView.findViewById(R.id.detail_textview);

        }


        public void setNama(String nama) {
            namaPariwisataTextView.setText(nama);
        }

        public void setAlamat(String alamat) {
            alamatPariwisataTextView.setText(alamat);
        }

        public void setDetail(String detail) {
            detailPariwisataTextView.setText(detail);
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
