package com.demo.simpleprotobuf.retrofit.api;

import com.demo.simpleprotobuf.model.PersonOuterClass.Person;
import com.demo.simpleprotobuf.model.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by sam on 18/3/22.
 */

public interface ProtobufService {
  @POST("/experiment/protobuf")
  Observable<Person> getProtobufPersonJson(@Body UserInfo userInfo);

  @POST("/experiment/protobuf")
  Observable<Person> getProtobufPerson(@Body Person person);
}
