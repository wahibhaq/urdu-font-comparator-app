package com.androidistan.urdufontcomparator.tracking;

import android.os.Bundle;

/**
 * Act as a contract for all tracking implemented by providers
 */
public interface AppTracker {

    /**
     * Handles tracking of all events
     */
    void trackEvent(String eventName, Bundle bundle);
}
