# gradle_plugin_android_list_permission

Android日常开发中在引入第三方SDK的时候会引入一些不必要的权限，那应该如何剔除呢?

以定位权限来说,在清单文件可以通过如下中标识移除相关权限.

# 清单文件标识
```
<uses-permission
     android:name="android.permission.ACCESS_FINE_LOCATION"
     tools:node="remove" />
<uses-permission
    android:name="android.permission.ACCESS_COARSE_LOCATION"
    tools:node="remove" />
 ```   
    
  
  在声明权限强制移除的标识后发现，在最终打包的清单文件中,有些权限确实是消失了,但有些权限还是和牛皮廯一样怎么也去不掉,
  这是为什么呢？问题在于我们在引入一些Gradle第三方插件的时候会在最终生成清单文件中注入这些权限,所以在最后生成的时候发现
  这些权限最后还是保留了下来.
  
  知道问题了之后,那我们该如何处理问题,这个时候就可以考虑使用自定义插件解决这个问题了.
  
 ## android-permission插件的使用
 在项目根目录build.grade中补充如下代码
 ```
 buildscript {
    repositories {
         //添加如下代码
        maven { url "https://dl.bintray.com/waylenw/maven" }
    }
    dependencies {
     //添加如下代码
        classpath 'com.android.list.permission:gradle-plugin:1.0.0'
    }
}
 ```

在主项目build.gradle添加的引用插件
```
 //添加如下代码
apply plugin: 'android-permission'
```
在主项目的创建一下permissionList.txt文件，内容放置你需要剔除的权限,格式必须是一行一个`android.permission.xxxxxx`
```
android.permission.CHANGE_NETWORK_STATE
#android.permission.CHANGE_WIFI_STATE
android.permission.VIBRATE
android.permission.FLASHLIGHT
android.permission.WAKE_LOCK
```
如需要补充注释,或者注释掉某个权限的剔除列表,可在开头加上`#`使用
# License
```
Copyright 2019 waylenw, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

```

    
   
