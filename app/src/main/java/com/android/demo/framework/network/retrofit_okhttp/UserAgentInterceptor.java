package com.android.demo.framework.network.retrofit_okhttp;

import android.text.TextUtils;
import android.util.Log;

import com.android.demo.BuildConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class UserAgentInterceptor implements Interceptor {

    final private static String TAG = "UserAgentInterceptor";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();


        /*
         *
         * Sample code if some extra headers to be added
         *
         * Headers header = originalRequest.headers();
         *
         *         can set custom headers over here
         *         Headers newHeader = setHeadersForRequest(header.toMultimap());
         *
         *
         *         Request requestWithUserAgent = originalRequest.newBuilder()
         *                 .headers(newHeader)
         *                 .build();
         *
         */


        Log.i(TAG, "URL : " + originalRequest.url());
        Log.i(TAG, "Request : " + originalRequest.body());


        Response response = chain.proceed(originalRequest);


        //we can also implement custom logger over here
        if (BuildConfig.DEBUG) {
            String responseBodyString = response.body().string();

            int maxLogSize = 2024;

            if (responseBodyString.length() < maxLogSize) {
                Log.i(TAG, responseBodyString);
            } else {
                for (int i = 0; i <= responseBodyString.length() / maxLogSize; i++) {
                    int start = i * maxLogSize;
                    int end = (i + 1) * maxLogSize;
                    end = end > responseBodyString.length() ? responseBodyString.length() : end;
                    Log.i(TAG, responseBodyString.substring(start, end));
                }
            }

            // now we have extracted the response body but in the process
            // we have consumed the original response and can't read it again
            // so we need to build a new one to return from this method
            MediaType contentType = response.body().contentType();
            ResponseBody responseBody = ResponseBody.create(responseBodyString, contentType);
            response = response.newBuilder().body(responseBody).build();
        }


        return response;
    }

    /**
     * These headers are sample headers for caching
     * <p>
     * Setting up 3 headers in request as follows -
     * Cache-Control Value : public, max-age=86400
     * Content-Type Value : application/x-www-form-urlencoded;charset=UTF-8;application/json
     * Accept Value : application/json
     */
    private Headers setHeadersForRequest(Map<String, List<String>> headerHashMap) {

        //String contentTypeString = "charset=UTF-8;application/json";
        String contentTypeString = "charset=UTF-8;application/x-www-form-urlencoded";

        List<String> contentType = new ArrayList<>();
        contentType.add(contentTypeString);

        List<String> acceptType = new ArrayList<>();
        acceptType.add("application/json");

        headerHashMap.put("Content-Type", contentType);

        Headers.Builder headerBuilder = new Headers.Builder();

        Iterator entries = headerHashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) entries.next();
            String key = thisEntry.getKey().toString();
            List<String> values = (List<String>) thisEntry.getValue();
            String valueString = TextUtils.join(";", values);
            Log.i(TAG, "Header : Key : " + key + " value : " + valueString);
            headerBuilder.add(key, valueString);
        }

        return headerBuilder.build();

    }

}