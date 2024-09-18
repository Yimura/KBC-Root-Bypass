package sh.damon.kbcrootbypass.hooks;

import java.util.LinkedHashMap;

import de.robv.android.xposed.XC_MethodHook;

public class AnalyticsHook extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        final LinkedHashMap<String, String> returnValue = (LinkedHashMap<String, String>) param.getResult();
        returnValue.clear();
        returnValue.put("rooted", "false");
        param.setResult(returnValue);
    }
}
