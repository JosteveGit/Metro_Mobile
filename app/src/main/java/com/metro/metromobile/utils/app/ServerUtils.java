package com.metro.metromobile.utils.app;

import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;

import static android.content.Context.DOWNLOAD_SERVICE;

public class ServerUtils {

    // Properties-----------------------------------------------------------------------------------
    public static final String BASE_URL = "https://api.mmobileservices.com/";


    // Server Error Messages
    public static final String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    public static final String REQUEST_PROCESS_ERROR_MESSAGE = "Something went wrong! Error processing request.";
    public static final String NETWORK_ERROR_MESSAGE = "Error communicating with the server. Please try again.";


    // Methods--------------------------------------------------------------------------------------
    public static boolean isNetworkUnavailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null || !activeNetworkInfo.isConnected();
    }

    public static void sendFileToDownloadManager(Context context, String url, String fileName) {
        Uri downloadUri = Uri.parse(url);

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("Black Knights Resource Download");
        request.setDescription("Downloading " + fileName);
        request.setVisibleInDownloadsUi(true);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                fileName);
        if (downloadManager != null) {
            downloadManager.enqueue(request);
        }
    }
}