# Android ViewPager Indicator
Lightweight library to display Android ViewPager indicator with custome images.

#How to use
In your layout
```
<com.hembitski.hmviewpagerindicator.VPIndicator
        android:id="@+id/viewPagerIndicator"
        android:layout_width="wrap_content"
        android:layout_height="20dp" />
```
In your onCreate
```
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        Adapter adapter = new Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        VPIndicator indicator = (VPIndicator) findViewById(R.id.viewPagerIndicator);
        indicator.setParams(viewPager);
```

![default](default.gif)

or you can put in your drawable folder images and use them. For example:

pic.png

![exPic.png](exPic.png)

 and picb.png
 
 ![exPicb.png](exPicb.png)
 
In your onCreate

```
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        Adapter adapter = new Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        VPIndicator indicator = (VPIndicator) findViewById(R.id.viewPagerIndicator);
        indicator.setParams(viewPager, R.drawable.picb, R.drawable.pic);
```

 ![custome1.gif](custome1.gif)
 
 You can use any image
 
 ![custome2.gif](custome2.gif)
 
# What new

You can disable touch mode

```
        indicator.setTouchMode(false);
```

# Download
Maven:
```
<dependency>
  <groupId>com.github.hmikalai</groupId>
  <artifactId>hmvpindicator</artifactId>
  <version>1.1.1</version>
  <type>pom</type>
</dependency>
<dependency>
  <groupId>com.google.android</groupId>
  <artifactId>support-v4</artifactId>
  <version>r7</version>
</dependency>
```

Or use Gradle:
```
dependencies {
    compile 'com.github.hmikalai:hmvpindicator:1.1.1'
    compile 'com.android.support:appcompat-v7:24.2.0'
}
```

# Author
Mikalai Hembitski
