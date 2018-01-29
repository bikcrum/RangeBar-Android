# RangeBar-Android

#### Android doesn't provide rangebar officially because of its limited use. But don't worry you can find it here.

## Here is how to use it

### Add this to root build.gradle (Project)

allprojects {
    repositories {
        ...
        // add this line
        maven { url "https://jitpack.io" }
    }
}

### Add this to build.gradle (Module)


dependencies {
    ...
    // Also add this line
    compile 'com.github.bikcrum:RangeBar-Android:1.3'
}


### To use it your code

Simply add the View to your layout

<com.bikcrum.widget.RangeBar
        android:id="@+id/range_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="20dp"
        app:max="50" />


### Add OnRangeChange listener

RangeBar bar = findViewById(R.id.range_bar);

        bar.setOnRangeChangeListener(new OnRangeChangeListener() {
            @Override
            public void onRangeChanged(RangeBar bar, int startIndex, int endIndex, boolean fromUser) {
                Log.d(TAG, "start = " + startIndex + ", end = " + endIndex + ", from user = " + fromUser);
            }

            @Override
            public void onStartTrackingTouch(RangeBar bar) {
                Log.d(TAG, "on start tracking touch");

            }

            @Override
            public void onStopTrackingTouch(RangeBar bar) {
                Log.d(TAG, "on end tracking touch");
            }
        });


### Make it SeekBar

  <com.bikcrum.widget.RangeBar
        android:id="@+id/range_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="20dp"
        android:isRange="false"
        app:max="50" />

