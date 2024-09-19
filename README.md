# KBC Root Bypass

## Frida Script

```js
Java.perform(_ => {
    let LinkedHashMap = Java.use("java.util.LinkedHashMap");

    let setAnalytics = Java.use("o.setAnalytics");
    setAnalytics["read"].implementation = function (context) {
        let result = this["read"](context);

        console.log("Analytic root detector called, clearing map and spoofing root value.");
        result.clear();
        result.put("rooted", "false");

        return result;
    };

    let setSmsNotificationAllowed = Java.use("o.setSmsNotificationAllowed");
    setSmsNotificationAllowed["write"].overload().implementation = function () {
        console.log("fake setSmsNotificationAllowed root check called!");

        return false;
    };

    let r8lambdafUieMBRcNBmlgxIKf_w_5HybxO8 = Java.use("o.r8lambdafUieMBRcNBmlgxIKf_w_5HybxO8");
    r8lambdafUieMBRcNBmlgxIKf_w_5HybxO8["AudioAttributesCompatParcelizer"].implementation = function () {
        return false;
    };
    r8lambdafUieMBRcNBmlgxIKf_w_5HybxO8["read"].implementation = function () {
        return false;
    };
    r8lambdafUieMBRcNBmlgxIKf_w_5HybxO8["RemoteActionCompatParcelizer"].implementation = function () {
        return false;
    };
});

```