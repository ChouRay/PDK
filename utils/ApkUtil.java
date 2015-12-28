package com.bobo.splayer.util;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import com.bobo.splayer.common.entity.GameDetailEntity;

public class ApkUtil {

	public static void installApk(Context context, String fileName) {
		if (fileName == null) {
			return;
		}
		Intent startIntent = new Intent();

		startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startIntent.setAction("android.intent.action.VIEW");
		startIntent.addCategory("android.intent.category.DEFAULT");
		Uri uri = Uri.fromFile(new File(fileName));
		startIntent.setDataAndType(uri, "application/vnd.android.package-archive");
		context.startActivity(startIntent);
	}

	public static boolean isInstalled(Context context, String packagename) {
		boolean result = false;
		if (TextUtils.isEmpty(packagename)) {
			return false;
		}
		Intent intent = getStartIntent(context, packagename);
		if (intent != null) {
			result = true;
		}
		return result;
	}

	public static Intent getStartIntent(Context context, String packagename) {
		if (TextUtils.isEmpty(packagename)) {
			return null;
		}
		PackageManager pm = context.getPackageManager();
		Intent i = pm.getLaunchIntentForPackage(packagename);
		return i;
	}

	public static synchronized void open(Context context, String pkgName) {
		try {
			Intent intent = context.getPackageManager()
					.getLaunchIntentForPackage(pkgName);
			context.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized boolean isPkgInstalled(Context context, String pkgName) {
		PackageInfo packageInfo = null;
		try {
			packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(pkgName, 0);
		} catch (PackageManager.NameNotFoundException e) {
			packageInfo = null;
			//e.printStackTrace();
		}
		if (packageInfo == null) {
			return false;
		} else {
			return true;
		}
	}
}
