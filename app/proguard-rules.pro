# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/leeeyou/androidSDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep public class com.xyz.leeeyou.zhihuribao.**{*;}

## -----------
## Bugly
## -----------
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

## ----------------------------------
## android default
## ----------------------------------
-dontusemixedcaseclassnames #包名不混合大小写
-dontskipnonpubliclibraryclasses #不跳过非公共的库的类
-verbose #混淆时记录日志，混淆后生产映射文件 map 类名->转化后类名的映射
-keepattributes SourceFile,LineNumberTable #保留行号

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize #不优化输入的类文件
-dontpreverify #关闭预校验，preverify是proguard的四个步骤之一,Android不需要preverify,去掉这一步可以加快混淆速度
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation* #保护注解

-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

-dontwarn android.support.v4.** #屏蔽掉所有关于android.support.v4.**的警告
-dontwarn android.webkit.WebView #屏蔽掉所有关于android.webkit.WebView的警告

# 保持所有拥有本地方法的类名及本地方法名
# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# 不混淆构造方法
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

# 不混淆构造方法
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# 不混淆构造方法
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保持自定义View的get和set相关方法
# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# 保持Activity中View及其子类入参的方法
# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# 枚举
# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Parcelable
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# R文件的静态成员
-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.

-dontwarn android.support.** #屏蔽掉所有关于android.support.**的警告
-keep class android.support.** { *;} #不混淆android.support.**下的所有类
-keep interface android.support.** { *;} #不混淆android.support.**下的所有接口

## -----------
## Retrofit
## -----------
-dontwarn okio.**
-dontwarn javax.annotation.**

-dontwarn org.codehaus.**
-dontwarn java.nio.**
-dontwarn java.lang.invoke.**

## ----------------------------------
## OkHttp
## ----------------------------------
-keepattributes Signature
-keepattributes Annotation
-keepattributes *Annotation*
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.* { *; }
-dontwarn okio.**
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keep class okio.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep interface okhttp3.** { *; }

## ----------------------------------
## okio
## ----------------------------------
-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn okio.**

# see https://github.com/square/okhttp/blob/master/okhttp/src/main/resources/META-INF/proguard/okhttp3.pro

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform

## -----------
## Glide
## -----------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

## -----------
## BaseRecyclerViewAdapterHelper
## -----------
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}

## -----------
## RxJava
## -----------
-dontwarn rx.internal.util.unsafe.*


#解决在6.0系统出现java.lang.InternalError
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

## -----------
## Banner
## -----------
-keep class com.youth.banner.** {
    *;
}

## -----------
## PersistentCookieJar
## -----------
-dontwarn com.franmontiel.persistentcookiejar.**
-keep class com.franmontiel.persistentcookiejar.**

-keepclassmembers class * implements java.io.Serializable {
 static final long serialVersionUID;
 private static final java.io.ObjectStreamField[] serialPersistentFields;
 !static !transient <fields>;
 private void writeObject(java.io.ObjectOutputStream);
 private void readObject(java.io.ObjectInputStream);
 java.lang.Object writeReplace();
 java.lang.Object readResolve();
}

## ----------------------------------
## EventBus
## ----------------------------------
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

## ----------------------------------
## gson bean
## ----------------------------------
-dontwarn **.bean.**
-keep class **.bean.** { *;}
-keep class com.leeeyou.service.entity.** { *; }