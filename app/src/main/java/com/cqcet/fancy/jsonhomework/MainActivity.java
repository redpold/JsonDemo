package com.cqcet.fancy.jsonhomework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Code By Fancy 方小樱 罗茜
 *
 *
 * */

public class MainActivity extends AppCompatActivity {

    Gson gson=new Gson();
    ListView lt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Map<String, Object>> listems=new ArrayList<>();
        try {
            InputStream is=getAssets().open("chinacitylist.json");
            byte[] buffer=new byte[is.available()];
            is.read(buffer);
            String json=new String(buffer,"UTF-8");
            is.close();
            CityBean city=gson.fromJson(json,CityBean.class);
            List<CityBean> appinfos = new ArrayList<CityBean>();
            CityBean cityBean=new CityBean();
            appinfos.add(city);
            for (int i = 0; i < city.getCity_info().size(); i++) {
                Map<String, Object> listem = new HashMap<String, Object>();
                listem.put("city", city.getCity_info().get(i).getCity());
                listem.put("cnty", city.getCity_info().get(i).getCnty());
                listem.put("id", city.getCity_info().get(i).getId());
                listem.put("lon", city.getCity_info().get(i).getLon());
                listem.put("lat", city.getCity_info().get(i).getLat());
                listem.put("prov", city.getCity_info().get(i).getProv());
                listems.add(listem);
            }
            SimpleAdapter simplead = new SimpleAdapter(this, listems, R.layout.listitem, new String[] { "city", "cnty","id", "lon","lat","prov" },
                    new int[]{R.id.city,R.id.cnty,R.id.id,R.id.lon,R.id.lat,R.id.prov});
            lt1=(ListView)findViewById(R.id.Ls);
            lt1.setAdapter(simplead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



