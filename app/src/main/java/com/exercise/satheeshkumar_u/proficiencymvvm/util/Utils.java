package com.exercise.satheeshkumar_u.proficiencymvvm.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

import com.exercise.satheeshkumar_u.proficiencymvvm.R;

public class Utils {

    private static ProgressDialog progressdialog;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static void showError(String message,Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle(R.string.warning_title);

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(R.string.alert_ok,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public static void showProgress(Context context) {
        progressdialog = new ProgressDialog(context);
        progressdialog.setMessage(context.getString(R.string.progress_loading));
        progressdialog.show();
    }

    public static void dismissProgress() {
        if(progressdialog!=null){
            progressdialog.dismiss();
        }
    }
}
