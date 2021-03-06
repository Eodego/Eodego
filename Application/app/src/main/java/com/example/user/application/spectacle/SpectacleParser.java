package com.example.user.application.spectacle;

import com.example.user.application.datamanager.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 13.
 */
public class SpectacleParser {
    final String json = "[{\"cinema\":대극장\",\"clCdNm\":\"뮤지컬\",\"telno\":\"061-900-2100\",\"XPos\":\"126.781745\",\"YPos\":\"35.0207423\",\"yadmNm\":\"한국문화예술위원회(기초공연활성화추진단)\",\"addr\":\"전라남도 나주시 빛가람로 640 (빛가람동 352)\"}," +
            "{\"cinema\":대극장\",\"clCdNm\":\"무용\",\"telno\":\"061-900-2100\",\"XPos\":\"126.781745\",\"YPos\":\"35.0207423\",\"yadmNm\":\"한국문화예술위원회(기초공연활성화추진단)\",\"addr\":\"전라남도 나주시 빛가람로 640 (빛가람동 352)\"}," +
            "{\"cinema\":대극장\",\"clCdNm\":\"연극\",\"telno\":\"02-3668-0007\",\"XPos\":\"127.0029985\",\"YPos\":\"37.5820923\",\"yadmNm\":\"한국공연예술센터(문화사업부)\",\"addr\":\"서울특별시 종로구 대학로 10길 17번지\"}," +
            "{\"cinema\":대극장\",\"clCdNm\":\"연극\",\"telno\":\"02-3668-0007\",\"XPos\":\"127.0029985\",\"YPos\":\"37.5820923\",\"yadmNm\":\"한국공연예술센터(문화사업부)\",\"addr\":\"서울특별시 종로구 대학로 10길 17번지\"}," +
            "{\"cinema\":대극장\",\"clCdNm\":\"연극\",\"telno\":\"02-3668-0007\",\"XPos\":\"127.0029985\",\"YPos\":\"37.5820923\",\"yadmNm\":\"한국공연예술센터(문화사업부)\",\"addr\":\"서울특별시 종로구 대학로 10길 17번지\"}," +
            "{\"cinema\":대극장\",\"clCdNm\":\"연극\",\"telno\":\"02-3668-0007\",\"XPos\":\"127.0029985\",\"YPos\":\"37.5820923\",\"yadmNm\":\"한국공연예술센터(문화사업부)\",\"addr\":\"서울특별시 종로구 대학로 10길 17번지\"}," +
            "{\"cinema\":대극장\",\"clCdNm\":\"연극\",\"telno\":\"02-3668-0007\",\"XPos\":\"127.0029985\",\"YPos\":\"37.5820923\",\"yadmNm\":\"한국공연예술센터, 극단 청년단\",\"addr\":\"서울특별시 종로구 대학로 10길 17번지\"}," +
            "{\"cinema\":대극장\",\"clCdNm\":\"무용\",\"telno\":\"02-3472-1420\",\"XPos\":\"127.0149449\",\"YPos\":\"37.4810913\",\"yadmNm\":\"국립현대무용단\",\"addr\":\"서울특별시 서초구 남부순환로 2406\"}," +
            "{\"cinema\":대극장\",\"clCdNm\":\"무용\",\"telno\":\"02-3472-1420\",\"XPos\":\"127.0149449\",\"YPos\":\"37.4810913\",\"yadmNm\":\"국립현대무용단\",\"addr\":\"서울특별시 서초구 남부순환로 2406\"}," +
            "{\"cinema\":대극장\",\"clCdNm\":\"무용\",\"telno\":\"02-879-0613\",\"XPos\":\"126.9561687\",\"YPos\":\"37.4804658\",\"yadmNm\":\"(사)트러스트무용단\",\"addr\":\"서울특별시 관악구 행운동 1665-1\"}]";

    public ArrayList<Data> jsonParser() {
        ArrayList<Data> list = new ArrayList<Data>();
        try {
            //JSON String으로 부터 JSONArray 생성. [](대괄호)
            JSONArray jArr = new JSONArray(json);
            for (int i = 0; i < jArr.length(); i++) {
                //JSONArray에서 i번째 해당하는 JSONObject를 추출.
                JSONObject jObj = jArr.getJSONObject(i);
                Data spectacle = new Data();
                //각 이름("id"/"tel")에 해당하는 값을 추출.
                spectacle.setCinema(jObj.getString("cinema"));
                spectacle.setName(jObj.getString("yadmNm"));
                spectacle.setAddr(jObj.getString("addr"));
                spectacle.setClcdnm(jObj.getString("clCdNm"));
                spectacle.setTelno(jObj.getString("telno"));
                spectacle.setxPos(Double.valueOf(jObj.getString("XPos")));
                spectacle.setyPos(Double.valueOf(jObj.getString("YPos")));
                list.add(spectacle);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}