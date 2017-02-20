[![SU](https://raw.githubusercontent.com/Ericsongyl/SuperUtils/master/banner.png)](https://github.com/Ericsongyl/SuperUtils)

# SuperUtils
AndroidLibrary：superutil is a library,include string format;check email,psw;time,mobile phone number,bank card num format……

# How to import in your project
```java
    compile 'com.github.Ericsongyl:superutil:1.0.1'
```

# Contents and how to use
1. AppLogUtil
```java
    AppLogUtil.i(tag, log info);
    AppLogUtil.d(tag, log info);
    AppLogUtil.w(tag, log info);
    AppLogUtil.v(tag, log info);
    AppLogUtil.e(tag, log info);
    AppLogUtil.printStackTrace(exception);
```

2. StringUtils
```java
    StringUitls.isContainChinese(string);
    StringUitls.MD5(string);
    StringUitls.toHexString(byte[]);
    StringUitls.toByteArray(string);
    StringUitls.isMobile(string);
    StringUitls.isEmail(string);
    StringUitls.toDate(string);
    StringUitls.isToday(string);
    StringUitls.isEmpty(string);
    StringUitls.isNull(json, string);
    StringUitls.friendlyTimeShow(string);
    StringUitls.formatDuring(long);
```

3. FormatUtils
```java
    FormatUtils.formatF2Y(string);
    FormatUtils.formatF2Y(int);
    FormatUtils.formatY2F(string);
    FormatUtils.formatY2F(int);
    FormatUtils.getDecimalFormat(string);
    FormatUtils.customDataTime(String style, String string);
```

4. MyCrashHandler
```java
    ... ... ...
    private MyCrashHandler mCrashHandler;
    mCrashHandler = MyCrashHandler.getInstance();
    mCrashHandler.init(context);
    Thread.setDefaultUncaughtExceptionHandler(mCrashHandler);
    ... ... ...
```

5. ScreenUtils
```java
    ScreenUtils.getScreenWidth(context);
    ScreenUtils.getScreenHeight(context);
    ScreenUtils.getStatusHeight(context);
    ScreenUtils.snapShotWithStatusBar(activity);
    ScreenUtils.snapShotWithoutStatusBar(activity);
```

6. ImageLoadUtil
```java
    ImageLoadUtil.getImage(String url, final ImageView imageView, DisplayImageOptions options);
    ImageLoadUtil.getImageForRoundedCorner(String url, final ImageView imageView, DisplayImageOptions options);
    ImageLoadUtil.GetRoundedCornerBitmap(bitmap);
```

7. CheckUtil
```java
    CheckUtil.isEqual(str1, str2);
    CheckUtil.isLegalBankCardNum(context, cardStr);
    CheckUtil.isLegalCardId(context, noStr);
    CheckUtil.strMaskFourForamt(cardStr);
    CheckUtil.getBankEnd4No(cardStr);
    CheckUtil.strPassForamt(phoneStr);
    CheckUtil.formatIdCardCode(idCardStr);
```

# Contact author
Email:nicksong1009@foxmail.com

blog:http://blog.csdn.net/weiren1101
