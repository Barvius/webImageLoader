package com.barvius.httpimage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoadDialog extends AsyncTask<Void, Void, ArrayList<User>> {
    private MainActivity activity;
    private ProgressDialog progressDialog;

    public LoadDialog(MainActivity activity) {
        this.activity=activity;
        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
    }

    protected void onPreExecute() {
        progressDialog.setMessage("Загружаю");
        progressDialog.show();
    }

    protected ArrayList<User> doInBackground(Void... urls) {
        try {
            URL url = new URL("https://android.garnizon.xyz/skype/?getAll");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(15000);
            InputStream stream = urlConnection.getInputStream();


            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }

            ArrayList<User> result = new ArrayList<>();

            try {
                JSONArray tmp = new JSONArray(stringBuilder.toString());
                for (int i = 0; i < tmp.length(); i++) {
                    JSONObject obj = tmp.getJSONObject(i);
                    result.add(new User(obj.getString("un"),obj.getLong("id")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("TAG", stringBuilder.toString());
            stream.close();

            return result;
        } catch (Exception e) {
            return null;
        }
    }

    protected void onPostExecute(ArrayList<User> returnData) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        ListAdapter listAdapter = new ListAdapter(activity, returnData);
        ListView listView = (ListView) activity.findViewById(R.id.lw_people);
        listView.setAdapter(listAdapter);

    }


}
