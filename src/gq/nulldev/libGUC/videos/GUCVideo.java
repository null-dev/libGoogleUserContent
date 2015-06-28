package gq.nulldev.libGUC.videos;

import gq.nulldev.libGUC.GUCObject;

/**
 * Project: libGoogleUserContent
 * Created: 28/06/15
 * Author: nulldev
 */
public class GUCVideo extends GUCObject {
    GUCVideoType videoType;

    /**
     * Construct a video from the content key and a video type.
     * @param contentKey The content key.
     * @param videoType The video type.
     */
    public GUCVideo(String contentKey, GUCVideoType videoType) {
        super(contentKey);
        this.videoType = videoType;
    }

    /**
     * Get the thumbnail of the video.
     * @return The thumbnail of the video.
     */
    public GUCVideoThumbnail getThumbnail() {
        return new GUCVideoThumbnail(this);
    }

    /**
     * Set the video type.
     *
     * The video type cannot be 'null'.
     * @param videoType The video type.
     */
    public void setVideoType(GUCVideoType videoType) {
        if(videoType == null) {
            throw new IllegalArgumentException("Video type cannot be 'null'!");
        }
        this.videoType = videoType;
    }

    /**
     * Get the video type.
     * @return The video type.
     */
    public GUCVideoType getVideoType() {
        return videoType;
    }

    @Override
    public String getURL() {
        return super.getURL() + "=m" + videoType.getCode();
    }

    @Override
    public String toString() {
        return this.getURL();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GUCVideo gucVideo = (GUCVideo) o;

        return videoType == gucVideo.videoType;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + videoType.hashCode();
        return result;
    }
}
