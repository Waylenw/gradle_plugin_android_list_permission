# gradle_plugin_android_list_permission

Android日常开发中在引入第三方SDK的时候会引入一些不必要的权限，那应该如何剔除呢?

以定位权限来说,在清单文件可以通过如下中标识移除相关权限需要移除

# 清单文件标识
```
<uses-permission
     android:name="android.permission.ACCESS_FINE_LOCATION"
     tools:node="remove" />
<uses-permission
    android:name="android.permission.ACCESS_COARSE_LOCATION"
    tools:node="remove" />
  
  权限在加入强制移除的标识后发现，在最终打包的清单文件中,有些权限确实是消失了,但有些权限还是和牛皮廯一样怎么也去不掉,
  这是为为什么呢？问题在于我们在引入一些Gradle第三方插件的时候会在最终生成清单文件中注入这些权限,所以在最后生成的时候发现
  这些权限最后还是保留了下来.
  
  知道问题了之后,那我们该如何处理问题,这个时候就可以考虑使用自定义插件解决这个问题了.
  
  
  未完................
    
   
