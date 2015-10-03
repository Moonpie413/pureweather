package space.wxh.pureweather.util;

/**
 * Created by howord on 15-10-3.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
