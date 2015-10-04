package com.example.jola.zadanie;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockListFragment;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment_2 extends SherlockListFragment {

    private DbHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbHelper = OpenHelperManager.getHelper(getActivity(), DbHelper.class);
        Dao<DbItem, Integer> dao = dbHelper.getDao();
        List<DbItem> links = null;
        DbItem dbItem;

        try {
            links = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<HashMap<String, String>> listOfLinks = new ArrayList<HashMap<String, String>>();
        for(int i = 0; i < links.size(); i++){
            dbItem = links.get(i);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("longUrl", dbItem.getLongUrl());
            map.put("shortUrl", dbItem.getShortUrl());
            listOfLinks.add(map);
            Log.d("data2", String.valueOf(listOfLinks));
        }

        String[] from = {"longUrl", "shortUrl"};
        int[] to = { R.id.longLink, R.id.shortLink};

        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), listOfLinks, R.layout.fragment_2, from, to);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
