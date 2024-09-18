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

    private void hookMethods(ClassLoader classLoader) {
        XposedBridge.log("Attempting to hook methods.");

        XposedHelpers.findAndHookMethod("o.setAnalytics", classLoader, "read", Context.class, new AnalyticsHook());

        XposedHelpers.findAndHookMethod("o.setSmsNotificationAllowed", classLoader, "write", new ReturnFalseHook());

        final String weirdClass = "o.r8lambdafUieMBRcNBmlgxIKf_w_5HybxO8";
        XposedHelpers.findAndHookMethod(weirdClass, classLoader, "AudioAttributesCompatParcelizer", new ReturnFalseHook());
        XposedHelpers.findAndHookMethod(weirdClass, classLoader, "read", new ReturnFalseHook());
        XposedHelpers.findAndHookMethod(weirdClass, classLoader, "RemoteActionCompatParcelizer", new ReturnFalseHook());

        XposedBridge.log("Finished hooking methods.");
    }
}