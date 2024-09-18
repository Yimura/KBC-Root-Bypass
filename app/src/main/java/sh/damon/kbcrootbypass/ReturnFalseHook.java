package sh.damon.kbcrootbypass;

import de.robv.android.xposed.XC_MethodHook;

public class ReturnFalseHook extends XC_MethodHook {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        param.setResult(false);
    }
}
