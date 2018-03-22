package com.demo.simpleprotobuf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.demo.simpleprotobuf.model.PersonOuterClass.Person;
import com.demo.simpleprotobuf.retrofit.RetrofitManager;
import com.demo.simpleprotobuf.retrofit.RxSchedulers;
import com.demo.simpleprotobuf.retrofit.api.ProtobufService;

import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {

//  private OkHttpClient okHttpClient = new OkHttpClient();
//
//  private Observable<Person> observable =
//      Observable.just("http://elyeproject.x10host.com/experiment/protobuf")
//          .map(new Function<String, Person>() {
//            @Override
//            public Person apply(String url) throws Exception {
//
//              Request request = new Request.Builder().url(url).build();
//              Call call = okHttpClient.newCall(request);
//              Response response = call.execute();
//
//              if (response.isSuccessful()) {
//                ResponseBody responseBody = response.body();
//                if (responseBody != null) {
//                   return Person.parseFrom(responseBody.byteStream());
//                   return Person.ADAPTER.decode(responseBody.source());
//                }
//              }
//
//              return null;
//            }
//          }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

  TextView mResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mResult = findViewById(R.id.txt_main);
    mResult.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mResult.setText("Start request...");
        requestProtobuf();
      }
    });

  }

  private void requestProtobuf() {
    Person person = Person.newBuilder()
        .setName("person003")
        .setEmail("sam-yr@gmail.com")
        .setId(123455)
        .build();
    RetrofitManager.self().create(ProtobufService.class).getProtobufPerson(person)
        .compose(RxSchedulers.<Person>io_main())
        .subscribe(new Consumer<Person>() {
          @Override
          public void accept(Person person) throws Exception {
            showResult(person);
          }
        });

//    RetrofitManager.self().create(ProtobufService.class).getProtobufPersonJson(new UserInfo())
//        .compose(RxSchedulers.<Person>io_main())
//        .subscribe(new Consumer<Person>() {
//          @Override
//          public void accept(Person person) throws Exception {
//            showResult(person);
//          }
//        });
  }

  private void showResult(Person result) {
    mResult.setText(result.toString());
  }
}
