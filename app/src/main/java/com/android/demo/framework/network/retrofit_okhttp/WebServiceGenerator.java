package com.android.demo.framework.network.retrofit_okhttp;

import com.android.demo.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * The @WebServiceGenerator is API/HTTP client heart.
 * In its current state, it only defines one public method
 * to create a basic REST adapter for a given class/interface
 */
public class WebServiceGenerator {

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {

        Retrofit retrofit = builder
                .client(getHttpClient())
                .build();

        return retrofit.create(serviceClass);

    }

    /**
     * @return @OkHttpClient with all configurations
     */
    private static OkHttpClient getHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient()
                .newBuilder()
//                .cache(new Cache(new File(context.getCacheDir(), "response"), Constant.CACHE_SIZE)) // not doing cache
                .connectTimeout(BuildConfig.API_TIME_OUT_MILLI_SEC, TimeUnit.MILLISECONDS)
                .readTimeout(BuildConfig.API_TIME_OUT_MILLI_SEC, TimeUnit.MILLISECONDS)
                .addInterceptor(new UserAgentInterceptor());
//                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)); // used for debugging

        return builder.build();
    }

}