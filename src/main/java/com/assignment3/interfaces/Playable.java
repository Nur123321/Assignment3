package com.assignment3.interfaces;

public interface Playable {
    void play();

    static boolean supportsOffline(boolean hasDownload) {
        return hasDownload;
    }
}
