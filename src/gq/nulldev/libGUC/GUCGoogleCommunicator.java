package gq.nulldev.libGUC;

import gq.nulldev.libGUC.videos.GUCVideo;
import gq.nulldev.libGUC.videos.GUCVideoType;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Project: libGoogleUserContent
 * Created: 30/06/15
 * Author: nulldev
 */

/**
 * Manages communications to Google.
 */
public class GUCGoogleCommunicator {
    //The list of invalid response codes
    static ArrayList<Integer> INVALID_RESPONSE_CODES = new ArrayList<>(Arrays.asList(new Integer[]{
            404,
            403,
            500
    }));

    /**
     * Get all the supported resolutions of a video.
     * @param video The video.
     * @return An ArrayList of the supported resolutions in no particular order.
     */
    public static ArrayList<GUCVideoType> getSupportedResolutions(GUCVideo video) {
        return getSupportedResolutions(video.getContentKey());
    }

    /**
     * Get all the supported resolutions of a video.
     * @param video The video.
     * @return An ArrayList of the supported resolutions in no particular order.
     */
    public static ArrayList<GUCVideoType> getSupportedResolutions(GUCObject video) {
        return getSupportedResolutions(video.getContentKey());
    }

    /**
     * Get all the supported resolutions of a video.
     * @param contentKey The content key of the video.
     * @return An ArrayList of the supported resolutions in no particular order.
     */
    public static ArrayList<GUCVideoType> getSupportedResolutions(String contentKey) {
        ArrayList<GUCVideoType> supportedResolutions = new ArrayList<>();
        for(GUCVideoType type : GUCVideoType.values()) {
            try {
                if(isResponseValid(new URL(new GUCVideo(contentKey, type).getURL()))) {
                    supportedResolutions.add(type);
                }
            } catch (MalformedURLException ignored) {}
        }
        return supportedResolutions;
    }

    /**
     * Check if a content key is valid.
     * @param key The content key to check.
     * @return A boolean if the content key is valid.
     */
    public static boolean isContentKeyValid(String key) {
        try {
            return isResponseValid(new URL(new GUCObject(key).getURL()));
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Check if the response is valid.
     * @return The response to check.
     */
    static boolean isResponseValid(URL url) {
        try {
            return !INVALID_RESPONSE_CODES.contains(getResponseCode(url));
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Get the response code of a URL.
     * @param url The URL.
     * @return The response code.
     * @throws IOException If we failed to connect to the URL.
     */
    static int getResponseCode(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        return conn.getResponseCode();
    }
}
