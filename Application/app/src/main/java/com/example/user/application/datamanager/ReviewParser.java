package com.example.user.application.datamanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 보운 on 2015-09-07.
 */
public class ReviewParser {
    final String json = "[{\"yadmNm\":\"홍성숙헤어갤러리\",\"review\":\"서비스가 완전 짱\"}," +
            "{\"yadmNm\":\"김미란헤어샾\", \"review\":\"머리 망친거 같애....\"}," +
            "{\"yadmNm\":\"이승희헤어클리닉\",\"review\":\"재방문 의사 300%!!\"}," +
            "{\"yadmNm\":\"오월의신부\", \"review\":\"여기 영 꽝이냉...ㅠㅠ\"}," +
            "{\"yadmNm\":\"비달싹뚝\", \"review\":\"내 머리 돌려내\"}," +
            "{\"yadmNm\":\"민수연헤어\", \"review\":\"여기서 머리 자르고 여친생김\"}," +
            "{\"yadmNm\":\"미들수헤어\", \"review\":\"으아아아아아아아악\"}," +
            "{\"yadmNm\":\"이공삼(203)헤어클럽\", \"review\":\"여긴 어디 난 누구?\"}," +
            "{\"yadmNm\":\"조수희미용실\", \"review\":\"제2의 블루클럽인듯\"}," +
            "{\"yadmNm\":\"금비헤어샵\", \"review\":\"거울보고 원빈인줄\"}," +
            "{\"yadmNm\":\"가마솥회관\",\"review\":\"맛있어1\"}," +
            "{\"yadmNm\":\"해원복집\",\"review\":\"맛있어2\"}," +
            "{\"yadmNm\":\"높은기와집\",\"review\":\"맛있어3\"}," +
            "{\"yadmNm\":\"관악산가든\",\"review\":\"맛있어4\"}," +
            "{\"yadmNm\":\"월량\",\"review\":\"맛있어5\"}," +
            "{\"yadmNm\":\"롯데리아서울랜드\",\"review\":\"맛있어6\"}," +
            "{\"yadmNm\":\"놀부부대찌개\",\"review\":\"맛있어7\"}," +
            "{\"yadmNm\":\"스위티하우스\",\"review\":\"맛있어8\"}," +
            "{\"yadmNm\":\"서라벌\",\"review\":\"맛있어9\"}," +
            "{\"yadmNm\":\"청도일식\",\"review\":\"맛있어10\"}," +
            "{\"yadmNm\":\"(학교법인)인제대학교부산백병원\",\"review\":\"치료 잘하내1\"}," +
            "{\"yadmNm\":\"가톨릭대학교인천성모병원\",\"review\":\"치료 잘하내2\"}," +
            "{\"yadmNm\":\"강북삼성병원\",\"review\":\"치료 잘하내3\"}," +
            "{\"yadmNm\":\"건국대학교병원\",\"review\":\"치료 잘하내4\"}," +
            "{\"yadmNm\":\"경북대학교병원\",\"review\":\"치료 잘하내5\"}," +
            "{\"yadmNm\":\"경상대학교병원\",\"review\":\"치료 잘하내6\"}," +
            "{\"yadmNm\":\"경희대학교병원\",\"review\":\"치료 잘하내7\"}," +
            "{\"yadmNm\":\"계명대학교동산병원\",\"review\":\"치료 잘하내8\"}," +
            "{\"yadmNm\":\"고려대의과대학부속구로병원\",\"review\":\"치료 잘하내9\"}," +
            "{\"yadmNm\":\"고려대학교의과대학부속안산병원\",\"review\":\"치료 잘하내10\"}," +
            "{\"yadmNm\":\"태성여관\",\"review\":\"방 넓다1\"}," +
            "{\"yadmNm\":\"경기여관\",\"review\":\"방 넓다2\"}," +
            "{\"yadmNm\":\"청자여인숙\",\"review\":\"방 넓다3\"}," +
            "{\"yadmNm\":\"한일여인숙\",\"review\":\"방 넓다4\"}," +
            "{\"yadmNm\":\"서래장여관\",\"review\":\"방 넓다5\"}," +
            "{\"yadmNm\":\"현대여관\",\"review\":\"방 넓다6\"}," +
            "{\"yadmNm\":\"신정여관\",\"review\":\"방 넓다7\"}," +
            "{\"yadmNm\":\"대성여관\",\"review\":\"방 넓다8\"}," +
            "{\"yadmNm\":\"에이스여관\",\"review\":\"방 넓다9\"}," +
            "{\"yadmNm\":\"광산장여관\",\"review\":\"방 넓다10\"}," +
            "{\"yadmNm\":\"한국문화예술위원회(기초공연활성화추진단)\",\"review\":\"공연 재밌어1\"}," +
            "{\"yadmNm\":\"한국문화예술위원회(기초공연활성화추진단)\",\"review\":\"공연 재밌어2\"}," +
            "{\"yadmNm\":\"한국공연예술센터(문화사업부)\",\"review\":\"공연 재밌어3\"}," +
            "{\"yadmNm\":\"한국공연예술센터(문화사업부)\",\"review\":\"공연 재밌어4\"}," +
            "{\"yadmNm\":\"한국공연예술센터(문화사업부)\",\"review\":\"공연 재밌어5\"}," +
            "{\"yadmNm\":\"한국공연예술센터(문화사업부)\",\"review\":\"공연 재밌어6\"}," +
            "{\"yadmNm\":\"한국공연예술센터, 극단 청년단\",\"review\":\"공연 재밌어7\"}," +
            "{\"yadmNm\":\"국립현대무용단\",\"review\":\"공연 재밌어8\"}," +
            "{\"yadmNm\":\"국립현대무용단\",\"review\":\"공연 재밌어9\"}," +
            "{\"yadmNm\":\"(사)트러스트무용단\",\"review\":\"공연 재밌어10\"}]";

    public ArrayList<Review> jsonParser() {
        ArrayList<Review> list = new ArrayList<Review>();
        try {
            //JSON String으로 부터 JSONArray 생성. [](대괄호)
            JSONArray jArr = new JSONArray(json);
            for (int i = 0; i < jArr.length(); i++) {
                //JSONArray에서 i번째 해당하는 JSONObject를 추출.
                JSONObject jObj = jArr.getJSONObject(i);
                Review review = new Review();
                //각 이름("id"/"tel")에 해당하는 값을 추출.
                review.setName(jObj.getString("yadmNm"));
                review.setReview(jObj.getString("review"));
                list.add(review);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
