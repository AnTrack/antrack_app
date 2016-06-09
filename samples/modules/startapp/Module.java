package org.antrack.app.modules.startapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

import org.antrack.app.ModuleInterface;
import org.antrack.app.modules.Template;

public class Module extends Template implements ModuleInterface {
    @Override
    public String version() { return "1.0"; }
    @Override
    public String desc() { return "Start application"; }
    @Override
    public String[] startWhen() { return new String[]{"command"}; }

    @Override
    public String command() { return "startapp <txt>"; }

    @Override
    public String onCommand(Context context, String[] args) {
        Intent mIntent = context.getPackageManager().getLaunchIntentForPackage(args[0]);
        if (mIntent != null) {
            try {
                context.startActivity(mIntent);
            } catch (ActivityNotFoundException err) {
                return "error: app not found";
            }
        }
        return "done";
    }
}
