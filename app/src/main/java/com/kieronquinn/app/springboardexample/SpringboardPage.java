package com.kieronquinn.app.springboardexample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import clc.sliteplugin.flowboard.AbstractPlugin;
import clc.sliteplugin.flowboard.ISpringBoardHostStub;

public class SpringboardPage extends AbstractPlugin {

    /*

        Example Springboard Page for the Amazfit Pace. This class requires the library JAR as well to work

        A springboard page has two modes: App Mode and Springboard Mode (my names, not Huami's)

        App Mode is when the page is disabled in the launcher, and launched instead from the app list.
        This behaves like a normal app, but with limited functionality. Swiping from the right to left should not be used in this mode as it is used to close the app

        Springboard Mode is when the page is shown in the launcher, note that you should not use swipe left or right in this mode, to allow the user to swipe between pages

     */

    //Tag for logging purposes. Change this to something suitable
    private static final String TAG = "SpringboardPage";
    //As AbstractPlugin is not an Activity or Service, we can't just use "this" as a context or getApplicationContext, so Context is global to allow easier access
    private Context mContext;
    //These get set up later
    private View mView;
    private boolean mHasActive = false;
    private ISpringBoardHostStub mHost = null;

    //Much like a fragment, getView returns the content view of the page. You can set up your layout here
    @Override
    public View getView(Context paramContext) {
        Log.d(TAG, "getView()" + paramContext.getPackageName());
        //Keep context
        this.mContext = paramContext;
        //Inflate layout as required. The layout here being inflated is "widget_blank"
        this.mView = LayoutInflater.from(paramContext).inflate(R.layout.widget_blank, null);
        //Container is the root of the layout, we're just using it to set a click listener here (you can remove this if you wish)
        View container = this.mView.findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                //Simply show a toast. Launching an activity is commented out but works as shown
                Toast.makeText(mContext, "Clicked!", Toast.LENGTH_LONG).show();
                /*Intent localIntent = new Intent(paramContext, MainActivity.class);
                localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                paramContext.startActivity(localIntent);*/
            }
        });
        return this.mView;
    }

    //Return the icon for this page, used when the page is disabled in the app list. In this case, the launcher icon is used
    @Override
    public Bitmap getWidgetIcon(Context paramContext) {
        return ((BitmapDrawable) this.mContext.getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
    }

    //Return the launcher intent for this page. This might be used for the launcher as well when the page is disabled?
    @Override
    public Intent getWidgetIntent() {
        Intent localIntent = new Intent();
        /*localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        localIntent.setAction("android.intent.action.MAIN");
        localIntent.addCategory("android.intent.category.LAUNCHER");
        localIntent.setComponent(new ComponentName(this.mContext.getPackageName(), "com.huami.watch.deskclock.countdown.CountdownListActivity"));*/
        return localIntent;
    }

    //Return the title for this page, used when the page is disabled in the app list. In this case, the app name is used
    @Override
    public String getWidgetTitle(Context paramContext) {
        return this.mContext.getResources().getString(R.string.app_name);
    }

    //Called when the page is shown
    @Override
    public void onActive(Bundle paramBundle) {
        super.onActive(paramBundle);
        //Check if the view is already inflated (reloading)
        if ((!this.mHasActive) && (this.mView != null)) {
            //It is, simply refresh
            refreshView();
        }
        //Store active state
        this.mHasActive = true;
    }

    private void refreshView() {
        //Called when the page reloads, check for updates here if you need to
    }

    //Returns the springboard host
    public ISpringBoardHostStub getHost() {
        return this.mHost;
    }

    //Called when the page is loading and being bound to the host
    @Override
    public void onBindHost(ISpringBoardHostStub paramISpringBoardHostStub) {
        Log.d(TAG, "onBindHost");
        //Store host
        this.mHost = paramISpringBoardHostStub;
    }

    //Called when the page is destroyed completely (in app mode). Same as the onDestroy method of an activity
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //Called when the page becomes inactive (the user has scrolled away)
    @Override
    public void onInactive(Bundle paramBundle) {
        super.onInactive(paramBundle);
        //Store active state
        this.mHasActive = false;
    }

    //Called when the page is paused (in app mode)
    @Override
    public void onPause() {
        super.onPause();
        this.mHasActive = false;
    }

    //Not sure what this does, can't find it being used anywhere. Best leave it alone
    @Override
    public void onReceiveDataFromProvider(int paramInt, Bundle paramBundle) {
        super.onReceiveDataFromProvider(paramInt, paramBundle);
    }

    //Called when the page is shown again (in app mode)
    @Override
    public void onResume() {
        super.onResume();
        //Check if view already loaded
        if ((!this.mHasActive) && (this.mView != null)) {
            //It is, simply refresh
            this.mHasActive = true;
            refreshView();
        }
        //Store active state
        this.mHasActive = true;
    }

    //Called when the page is stopped (in app mode)
    @Override
    public void onStop() {
        super.onStop();
        this.mHasActive = false;
    }
}
