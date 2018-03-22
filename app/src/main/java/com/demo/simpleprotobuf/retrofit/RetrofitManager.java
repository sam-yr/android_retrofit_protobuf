package com.demo.simpleprotobuf.retrofit;

import com.demo.simpleprotobuf.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.protobuf.ProtoConverterFactory;
import retrofit2.converter.wire.WireConverterFactory;

/**
 * Created by sam on 17/10/7.
 */

public class RetrofitManager {
  private OkHttpClient mOkHttpClient;
  private Retrofit mRetrofit;
  private Retrofit.Builder mBuilder;
  private IAppConfig mConfig;

  private static class Holder {
    private static final RetrofitManager sInstance = new RetrofitManager();
  }

  private RetrofitManager() {
    init();
  }

  private void init() {
    initConfig();
    initOkHttpClient();
    initRetrofit();
  }

  private void initConfig() {
    mConfig = new DefaultConfig();
  }

  private void initOkHttpClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.connectTimeout(20, TimeUnit.SECONDS);
    builder.readTimeout(20, TimeUnit.SECONDS);
    builder.writeTimeout(20, TimeUnit.SECONDS);
    if (mConfig.getInterceptors() != null) {
      for (Interceptor interceptor : mConfig.getInterceptors()) {
        builder.addInterceptor(interceptor);
      }
    }
    // builder.addNetworkInterceptor(null);

    if (mConfig.getHostnameVerifier() != null) {
      builder.hostnameVerifier(mConfig.getHostnameVerifier());
    }

    if (mConfig.getSSLSocketFactory() != null) {
      builder.sslSocketFactory(mConfig.getSSLSocketFactory());
    }

    mOkHttpClient = builder.build();
  }

  public static Retrofit self() {
    return Holder.sInstance.mRetrofit;
  }

  private void initRetrofit() {
    mBuilder = new Retrofit.Builder();
    mBuilder.callFactory(mOkHttpClient)
        .baseUrl(mConfig.getApiBaseUrl());
    if (BuildConfig.USE_GOOGLE_PROTOBUF) {
      mBuilder.addConverterFactory(ProtoConverterFactory.create());
    } else {
      mBuilder.addConverterFactory(WireConverterFactory.create());
    }
    mBuilder.addConverterFactory(GsonConverterFactory.create());
    mBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    mRetrofit = mBuilder.build();
  }

}
