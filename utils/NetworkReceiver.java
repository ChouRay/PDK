package com.bobo.splayer.downloadframework.filedownload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



/**
 * 动态的网络状态变化广播 接收器
 * Created by zhoulei on 2016/7/18.
 */
public final class NetworkReceiver extends BroadcastReceiver {
    private static final int NETWORK_NONE = -1;

    private NetworkListener mNetworkListener;
    private IntentFilter filter;

    private NetworkReceiver(){
        filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    private static final NetworkReceiver INSTANCE = new NetworkReceiver();

    public static NetworkReceiver getInstance() {
        return INSTANCE;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (mNetworkListener == null ) {
                return;
            }

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            int networkType = NETWORK_NONE;
            boolean isConnected = false;
            if (networkInfo != null ) {
                networkType = networkInfo.getType();
                isConnected = networkInfo.isConnected();
            }

            if (networkType == ConnectivityManager.TYPE_WIFI && isConnected) {
                mNetworkListener.onNetworkWifi();
            } else if (networkType == ConnectivityManager.TYPE_MOBILE && isConnected) {
                mNetworkListener.onNetworkMobile();
            } else if (networkType == NETWORK_NONE){
                mNetworkListener.onNetworkInvalid();
            }
        }
    }

    public interface NetworkListener {
        void onNetworkInvalid();
        void onNetworkWifi () ;
        void onNetworkMobile () ;
    }
}
