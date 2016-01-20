# CommonAdapter

[![](https://jitpack.io/v/twiceyuan/CommonAdapter.svg)](https://jitpack.io/#twiceyuan/CommonAdapter)

一个通用的 ListView / RecyclerView 适配器

# 引用

```groovy
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```
```groovy
dependencies {
  compile 'com.github.twiceyuan:CommonAdapter:0.1'
}
```

# 使用

1. 创建一个 ViewHolder 并继承 CommonHolder<T>，T 为实体数据类型

  ```java
  // 绑定布局资源
  @LayoutId(R.layout.item_person)
  public class PersonHolder extends CommonHolder<Person> {
  
      // 绑定 View 资源
      @ViewId(R.id.name) TextView name;
      @ViewId(R.id.email) TextView email;
  
      public PersonHolder(View itemView) {
          super(itemView);
      }
  
      // 绑定数据
      @Override public void bindData(Person person) {
          name.setText(person.name);
          email.setText(person.email);
      }
  }
  ```

2. 创建适配器并配置到 ListView 或者 RecyclerView 上

    RecyclerView Adapter
    
    ```java
    // build
    SimpleRecyclerAdapter<Person> recyclerAdapter = new SimpleRecyclerAdapter<>(this, PersonHolder.class);
    
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    // setup
    mRecyclerView.setAdapter(recyclerAdapter);
    ```
    
    ListView Adapter
    
    ```java
    // build
    SimpleListAdapter<Person> listAdapter = new SimpleListAdapter<>(this, PersonHolder.class);
    // setup
    mListView.setAdapter(listAdapter);
    ```

    配置监听器
    ```
    recyclerAdapter.setOnBindListener(new SimpleRecyclerAdapter.OnBindListener<Person, PersonHolder>() {
        @Override public void onBind(int position, final Person person, PersonHolder holder) {
            // holder 用于直接对 view 配置监听器
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    toast(person.name);
                }
            });
            holder.email.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    toast(person.email);
                }
            });
        }
    });
    ```

# 感谢

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
