# Status
[![Build Status](https://travis-ci.org/talenguyen/PrettyBundle.svg?branch=development)](https://travis-ci.org/talenguyen/PrettyBundle)
# PrettyBundle
Android library which uses annotation processor to help Android Developer easy to deal with Bundle to communicate between Activity, Fragment and Service.

PrettyBundle is inspired by [Dart](https://github.com/f2prateek/dart)

## Getting start
### Activity
Inject extras in Activity. (Borrow the idea of ButterKnife and Dart).
```java
public class TargetActivity extends Activity {
    @Extra String name;
    @Extra int age;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrettyBundle.inject(this); // call to inject the extra.
        // TODO: uses the extras properties
    }
}
```
To set extras and start the TargetActivity
```java
Intent intent = Activities.createTargetActivityIntent("Giang", 26);
startActivity(intent);
```
***Activities*** is the utility class which is generated by the library processor. 

***Activityes.createTargetActivityIntent()*** is the corresponding method to create new instance of the ***Intent*** class which will be used to start the ***TargetActivity***

### Fragment
Inject extras in Fragment. 
```java
public class TargetFragment extends Fragment {
    @Extra String name;
    @Extra int age;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrettyBundle.inject(this); // call to inject the extra.
        // TODO: uses the extras properties
    }
}
```
Set extras and create an instance of TargetFragment.
```java
TargetFragment targetFragment = Fragments.createTargetFragment("Giang", 26);
```
***Fragments*** is the utility class which is generated by the library processor. 

***Fragments.createTargetFragment()*** is the corresponding method to create new instance of the ***TargetFragment***.

### Service
Inject extras in Service.
```java
public class TargetService extends Service {
    @Extra String name;
    @Extra int age;

    @Override public IBinder onBind(Intent intent) {
        return null;
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        PrettyBundle.inject(this, intent);
        return super.onStartCommand(intent, flags, startId);
    }
}
```
To set extras and start the TargetService
```java
Intent intent = Services.createTargetServiceIntent("Giang", 26);
startService(intent);
```

## Download
Latest version is **1.0.0-SNAPSHOT**

### Gradle
```gradle
compile 'com.github.talenguyen:prettybundle:1.0.0-SNAPSHOT'
compile 'com.github.talenguyen:prettybundle-processor:1.0.0-SNAPSHOT'
```

### Maven
```maven
<dependency>
  <groupId>com.github.talenguyen</groupId>
  <artifactId>prettybundle</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>

<dependency>
  <groupId>com.github.talenguyen</groupId>
  <artifactId>prettybundle-processor</artifactId>
  <version>1.0.0-SNAPSHOT</version>
  <optional>true</optional>
</dependency>
```

### JARs
Download [latest JARs](https://github.com/talenguyen/PrettyBundle/releases)

### License

    Copyright 2015 Giang Nguyen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
