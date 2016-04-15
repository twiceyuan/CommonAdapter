# CommonAdapter

[![Build Status](https://travis-ci.org/twiceyuan/CommonAdapter.svg?branch=master)](https://travis-ci.org/twiceyuan/CommonAdapter)
[![Jitpack](https://jitpack.io/v/twiceyuan/CommonAdapter.svg)](https://jitpack.io/#twiceyuan/CommonAdapter)
[![Apache 2.0](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)
<a href="http://www.methodscount.com/?lib=com.github.twiceyuan%3ACommonAdapter%3A0.4.4"><img src="https://img.shields.io/badge/Size-31 KB-e91e63.svg"></img></a>
<a href="http://www.methodscount.com/?lib=com.github.twiceyuan%3ACommonAdapter%3A0.3"><img src="https://img.shields.io/badge/Methods count-core: 105 | deps: 16456-e91e63.svg"></img></a>

[中文介绍](README.zh.md)

* [x] A common ListView / RecyclerView adapter.
* [x] Support RecyclerView multiple view type. This is a [demo](https://github.com/twiceyuan/RecyclerViewType)

![Intro](art/banner.png)


# Setup

```groovy
allprojects {
  repositories {
    // ...
    maven { url "https://jitpack.io" }
  }
}
```
```groovy
dependencies {
  compile 'com.github.twiceyuan:CommonAdapter:0.4.4'
}
```

# Usage

1. create a ViewHolder extends CommonHolder\<T\>，T is a model class

  ```java
  // bind layout id
  @LayoutId(R.layout.item_person)
  public class PersonHolder extends CommonHolder<Person> {
  
      // bind View id
      @ViewId(R.id.name) TextView name;
      @ViewId(R.id.email) TextView email;
  
      public PersonHolder(View itemView) {
          super(itemView);
      }
  
      // bind data
      @Override public void bindData(Person person) {
          name.setText(person.name);
          email.setText(person.email);
      }
  }
  ```

2. Create a common adapter and call setAdapter method of list view or recycler view.

    **RecyclerView Adapter**
    
    ```java
    // build adapter
    CommonAdapter<Person, PersonHolder> recyclerAdapter = new CommonAdapter<>(this, PersonHolder.class);

    // set adapter
    mRecyclerView.setAdapter(recyclerAdapter);
    ```
    
    **ListView Adapter**
    
    ```java
    // build adapter
    CommonListAdapter<Person, PersonHolder> listAdapter = new CommonListAdapter<>(this, PersonHolder.class);
    mListView.setAdapter(listAdapter);
    ```

    **Setup listener (by holder)** 
    
    ```java
    recyclerAdapter.setOnBindListener((position, person, holder) -> {
        // holder is used to bind event listener
        holder.name.setOnClickListener((v) -> toast(person.name));
        holder.email.setOnClickListener((v) -> toast(person.email));
    });
    ```

# Proguard

```
-keepattributes *Annotation*
-keepclassmembers class * extends com.twiceyuan.commonadapter.library.holder.CommonHolder {
    public <init>(...);
}
```

# Thanks

* EasyAdapter https://github.com/ribot/easy-adapter

# License
```
Copyright 2016 twiceYuan.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
