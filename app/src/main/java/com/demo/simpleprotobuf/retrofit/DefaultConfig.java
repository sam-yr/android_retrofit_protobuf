package com.demo.simpleprotobuf.retrofit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by sam on 18/3/22.
 */

public class DefaultConfig implements IAppConfig {
  @Override
  public String getApiBaseUrl() {
    return "http://elyeproject.x10host.com";
  }

  @Override
  public SSLSocketFactory getSSLSocketFactory() {
    return null;
  }

  @Override
  public HostnameVerifier getHostnameVerifier() {
    return null;
  }

  @Override
  public Interceptor[] getInterceptors() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return new Interceptor[] {
        interceptor
    };
  }
}
