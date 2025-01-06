package sh.damon.kbcrootbypass.hooks;

import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class AnalyticsHook extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        final Object returnObj = param.getResult();
        if (!(returnObj instanceof LinkedHashMap)) {
            XposedBridge.log("Return object is not the right type, aborting.");
            return;
        }
        final LinkedHashMap<String, String> rootIndicators = (LinkedHashMap<String, String>) returnObj;

        if (!rootIndicators.containsKey("rooted")) {
            XposedBridge.log("We're not in the correct function, aborting.");
            return;
        }

        for (Map.Entry<String, String> rootData : rootIndicators.entrySet()) {
            XposedBridge.log(String.format("{%s, %s}", rootData.getKey(), rootData.getValue()));
        }

        rootIndicators.clear();
        rootIndicators.put("rooted", "false");
        param.setResult(rootIndicators);
    }
}
