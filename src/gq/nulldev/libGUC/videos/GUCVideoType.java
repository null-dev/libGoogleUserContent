package gq.nulldev.libGUC.videos;

/**
 * Project: libGoogleUserContent
 * Created: 28/06/15
 * Author: nulldev
 */

/**
 * Different variants of the size of the video Google can serve.
 *
 * Please note that the video size does not affect the thumbnail
 *
 * WARNING: Do not just use the largest size possible. It will just give you an invalid URL (which doesn't fall back).
 *          Make sure that the original video is at least equal or larger to the size you want served.
 *          To do this, you can use the "getSupportedResolutions(GUCObject video)" function in the GUCGoogleCommunicator class.
 */
public enum GUCVideoType {
    _MP4_1920x1080(37),
    _MP4_1280x720(22),
    _MP4_640x360(18),
    _3GP_320x180(36);

    private int code;

    GUCVideoType(int code) {
        this.code = code;
    }

    /**
     * Get the secret video codes that google uses to identify the different types.
     * @return The video code.
     */
    public int getCode() {
        return code;
    }
}
