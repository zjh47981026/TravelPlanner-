package com.laioffer.travelplanner.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.travelplanner.exception.MapException;
import com.laioffer.travelplanner.model.Place;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONObject;
import java.util.List;
import org.apache.http.util.EntityUtils;

@Service
public class PlaceService {
    private static final String MAP_API_KEY = "";

    private static final String URL =
            "https://maps.googleapis.com/maps/api/place/textsearch/json?query=new%20york%20tourists%20attractions&key=AIzaSyC2OtNgKjM1aMy8ur8ltA9LTS19fT-Ndzc";

    private static final String SEARCH_BASE_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=%s&key=%s";

    private static final String PHOTO_URL_TEMPLATE =
            "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photo_reference=%s&key=%s";

    private static final String NEXT_PAGE_URL_TEMPLATE =
            "https://maps.googleapis.com/maps/api/place/textsearch/json?key=%s&pagetoken=%s";

    private static String nextPageToken =
            "AeJbb3fZmAf1rADeQ4u_WY28q14QyA9hQkjEDRRMNEtoX0St_24LGq8h58Mm0rAPTjvS-9TRmwIj1YBWeu3V-u0SqI_8YcvUNkroVRB_mOE7fwYjPQ7zo0mYxlWJd_2tovUoMpr6cTykdAUgy_MYfWybG5jPqk2T2E6PQpehGVDuQy-gKYXwx-CRBlUYFMjFm0a0kGJRROaXXJxepor7m8f8gle_yZaz_1shfsAr-CLxeGztTg-V4n1XjVvh_vb3G2Vtn95VzFFuwS7MkBcHObdpYWSh7XWUwDnflTgqmtilNbYx3cYGSz_GdMhjGdcE_Sy7JxZe1TQ6ety_FRsse3ZzD-e_Ya6idvOVut1U6-7sp0PBW5DxnYmEmUXAtnMuM3BVf8CB_6f8Lxa0EC7JaOoPsEaJdQ-5ZeNmamVo";

      private String buildPlaceURL(String url, String text, String apiKey ) {
           return String.format(url, text, apiKey);
      }

      private String buildPhotoURL(String url, String ref, String apiKey) {
          return String.format(url, ref, apiKey);
      }

      private String buildNextPageURL(String url, String apiKey, String pageToken) {
          return String.format(url, apiKey, pageToken);
      }

    private String searchMap(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        ResponseHandler<String> responseHandler = response -> {
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                System.out.println("Response status: " + response.getStatusLine().getReasonPhrase());
                throw new MapException("Failed to get result from Map API");
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                throw new MapException("Failed to get result from Map API");
            }

            JSONObject obj = new JSONObject(EntityUtils.toString(entity));
            return obj.getJSONArray("results").toString();
        };

        try {
            // Define the HTTP request
            HttpGet request = new HttpGet(url);
            return httpclient.execute(request, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MapException("Failed to get result from Map API");
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Place> getPlaceList(String data) throws MapException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(data, Place[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new MapException("Failed to parse place data from Map API");
        }
    }
    public List<Place> searchPlace () throws MapException {
        String text = "new york tourists attractions";
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Place> list = getPlaceList(searchMap(buildPlaceURL(SEARCH_BASE_URL, text, MAP_API_KEY)));
        List<Place> resultList = new ArrayList<>(list);
        List<Place> extraList = null;
        if (nextPageToken != null) {
            extraList = getPlaceList(searchMap(buildNextPageURL(NEXT_PAGE_URL_TEMPLATE, MAP_API_KEY, nextPageToken)));
            for (Place p : extraList) {
                resultList.add(p);
            }
        }

       for (Place p : resultList) {
           String[] photoLinks = new String[p.getPhotos().length];
           for (int i = 0; i < photoLinks.length; i++) {
               String photoRef = p.getPhotos()[i].getPhotoRef();
               String url = buildPhotoURL(PHOTO_URL_TEMPLATE, photoRef, MAP_API_KEY);
               photoLinks[i] = url;
           }
           p.setPhotoLinks(photoLinks);
       }
       return resultList;
    }
}
