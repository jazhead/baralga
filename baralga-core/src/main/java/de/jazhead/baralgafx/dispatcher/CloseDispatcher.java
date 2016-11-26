package de.jazhead.baralgafx.dispatcher;

import de.jazhead.baralgafx.event.CloseEvent;
import de.jazhead.baralgafx.listener.CloseListener;

import java.util.ArrayList;

public class CloseDispatcher {

    private static final ArrayList<CloseListener> listeners = new ArrayList<>();

    public static void addListener(final CloseListener closeListener) {
        listeners.add(closeListener);
    }

    public static void removeListener(final CloseListener closeListener) {
        listeners.remove(closeListener);
    }

    public static void notifyListeners(final CloseEvent closeEvent) {

        listeners.forEach(listener -> listener.onClose(closeEvent));
    }
}
