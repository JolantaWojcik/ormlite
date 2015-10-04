package com.example.jola.zadanie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import cz.msebera.android.httpclient.*;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

import java.io.IOException;
import java.sql.SQLException;

public class Fragment_1 extends SherlockFragment {

    private EditText editText;
    private String fullUrl;
    private String correctUrl;
    private DbHelper dbHelper;
    private ImageButton button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
         editText = (EditText) view.findViewById(R.id.editText);

        button = (ImageButton) view.findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addLink(v);
            }
        });

        return view;
    }

    public void addLink(View view) {
        fullUrl = editText.getText().toString();

        if(fullUrl.length() > 0){
            correctUrl = "^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
            if(fullUrl.matches(correctUrl)){
                new LoadUrl().execute(fullUrl);
            }else{
                Toast.makeText(getActivity(), R.string.blad, Toast.LENGTH_LONG).show();
            }
        }
    }

    private class LoadUrl extends AsyncTask<String, Void, Boolean>{
        @Override
        protected Boolean doInBackground(String... params) {
            String userUrl = params[0];
            String staticUrl = "http://to.ly//api.php?longurl=";
            String url = staticUrl + userUrl;
            String shortUrl = null;

            try {
                HttpGet httpget = new HttpGet(url);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();
                shortUrl = EntityUtils.toString(entity);

                dbHelper = OpenHelperManager.getHelper(getActivity(), DbHelper.class);
                Dao <DbItem, Integer> dao = dbHelper.getDao();
                dao.create(new DbItem(userUrl, shortUrl));

                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean == true){
                Toast.makeText(getActivity(), R.string.sukces, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getActivity(), R.string.bladB, Toast.LENGTH_LONG).show();
            }
        }
    }
}
