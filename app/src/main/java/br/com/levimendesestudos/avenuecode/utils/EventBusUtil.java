package br.com.levimendesestudos.avenuecode.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 809778 on 23/02/2016.
 */
public class EventBusUtil {

    private static EventBus eventBus;

    public static EventBus getInstance() {
        if (eventBus == null) {
            eventBus = new EventBus();
        }

        return eventBus;
    }

    public static void register(Object o) {
        if (!getInstance().isRegistered(o)) {
            getInstance().register(o);
        }
    }

    public static void unRegister(Object o) {
        if (getInstance().isRegistered(o)) {
            getInstance().unregister(o);
        }
    }

    private EventBusUtil() {}
}
