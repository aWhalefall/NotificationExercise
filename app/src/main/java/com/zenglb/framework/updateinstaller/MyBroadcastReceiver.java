package com.zenglb.framework.updateinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.RemoteInput;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("ACTION_SNOOZE".equals(intent.getAction())) {
            Toast.makeText(context, "reciver", Toast.LENGTH_SHORT).show();
        }
        try {
            Intent demoIntent = new Intent(context, Main2Activity.class);
            demoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(demoIntent);
        } catch (Exception e) {
            Log.e("dd", e.getMessage());
        }

    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence("key_text_reply");
        }
        return null;
    }
}
