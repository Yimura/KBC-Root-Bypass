package sh.damon.kbcrootbypass;

import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import sh.damon.kbcrootbypass.hooks.AnalyticsHook;
import sh.damon.kbcrootbypass.hooks.ReturnFalseHook;

public class KbcRootBypass implements IXposedHookLoadPackage {
    final String TAG = this.getClass().getName();

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        final String packageName = lpparam.packageName;

        if (!packageName.equals("com.kbc.mobile.android.phone.kbc")) {
            return;
        }

        XposedBridge.log("Hooking KBC Application!");
        hookMethods(lpparam.classLoader);
    }

    private void hookMethods(ClassLoader classLoader) throws Throwable {
        XposedBridge.log("Attempting to hook methods.");

        // search for "rootDetails" or "rootHiders" strings
        XposedHelpers.findAndHookMethod("o.CapturePresenteruploadDocumentMedia12", classLoader, "read", android.content.Context.class, new AnalyticsHook());

        // search "/system/app/Superuser.apk" or "test-keys"
        XposedHelpers.findAndHookMethod("o.isShowAppointment", classLoader, "write", new ReturnFalseHook());

        // new String[]{"/system/xbin/which", "su"};
        XposedHelpers.findAndHookMethod("o.GameDetailActivityCompanion", classLoader, "read", new ReturnFalseHook());

        XposedBridge.log("Finished hooking methods.");
    }
}