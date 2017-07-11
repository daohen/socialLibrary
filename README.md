# socialLibrary
社交分享相关代码封装

[![wxsocial](https://jitpack.io/v/daohen/socialLibrary.svg)](https://jitpack.io/#daohen/socialLibrary)

gradle
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
    }
}

dependencies {
    compile 'com.github.daohen:socialLibrary:wxsocial-xx'
}
```


代码混淆
```
-keep class com.tencent.mm.opensdk.** {*;}
-keep class com.tencent.wxop.** {*;}
-keep class com.tencent.mm.sdk.** {*;}
```