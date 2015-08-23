package gq.nulldev.libGUC.videos;

/**
 * Project: libGoogleUserContent
 * Created: 28/06/15
 * Author: nulldev
 */

/**
 * Different variants of the size of the video Google can serve.
 *
 * Please note that the video size does not affect the thumbnail.
 *
 * Also please note that the videos do not have to be in the MP4 format, they can also be found regularly in WEBM format but
 * the MP4 format is the most common.
 *
 * WARNING: Do not just use the largest size possible. It will just give you an invalid URL (which doesn't fall back).
 *          Make sure that the original video is at least equal or larger to the size you want served.
 *          To do this, you can use the "getSupportedResolutions(GUCObject video)" function in the GUCGoogleCommunicator class.
 */
public enum GUCVideoType {
    /* +--------------------------------------------------+ */
    /* | PLEASE NOTE ABOUT VIDEOS IN 4K                   | */
    /* |                                                  | */
    /* | The videos appear to have a split video          | */
    /* | and audio track at this point!                   | */
    /* |                                                  | */
    /* | Also note that the video track is not DCI        | */
    /* | 4K it is actually UHD 4K.                        | */
    /* | */ _MP4_3840x2160(272, "Ultra HD 4K"),/* (VIDEO) | */
    /* |    _AUDIO_3840x2160(251),                (AUDIO) | */
    /* +--------------------------------------------------+ */
    _MP4_2560x1440(271, "Quad HD"),
    _MP4_1920x1080(37, "Full HD"),
    _MP4_1280x738(15, "HD"),
    _MP4_1280x720(22, "HD"),
    _MP4_640x360(18, "SD"),
    _3GP_320x180(36, "Quarter VGA");

    private int code;
    private String friendlyName;

    GUCVideoType(int code, String name) {
        this.code = code;
        this.friendlyName = name;
    }

    /**
     * Get the secret video codes that Google uses to identify the different types.
     * @return The video code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Get a more human friendly name of the video format.
     * @return A human friendly name of the video format.
     */
    public String getFriendlyName() { return friendlyName; }
}
