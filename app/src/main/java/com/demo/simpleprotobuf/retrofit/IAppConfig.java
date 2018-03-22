package com.demo.simpleprotobuf.retrofit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;

/**
 * Created by sam on 17/10/7.
 */

public interface IAppConfig {
  String getApiBaseUrl();
  SSLSocketFactory getSSLSocketFactory();
  HostnameVerifier getHostnameVerifier();
  Interceptor[] getInterceptors();
}
