package gq.nulldev.libGUC.videos;

import gq.nulldev.libGUC.photos.GUCPhoto;

/**
 * Project: libGoogleUserContent
 * Created: 28/06/15
 * Author: nulldev
 */
public class GUCVideoThumbnail extends GUCPhoto {
    boolean animate = true;
    boolean showPlayButton = true;

    /**
     * Get the thumbnail from an existing video object.
     * @param video The video to get the thumbnail from.
     */
    public GUCVideoThumbnail(GUCVideo video) {
        super(video.getContentKey());
    }

    /**
     * Get the thumbnail from a video's content key.
     * @param contentKey The content key.
     */
    public GUCVideoThumbnail(String contentKey) {
        super(contentKey);
    }

    /**
     * Set whether to animate the thumbnail as a gif or not.
     * @param animate Animate the thumbnail?
     */
    public void animate(boolean animate) {
        this.animate = animate;
    }

    /**
     * Set whether to show the play button or not.
     * @param showPlayButton Show the play button?
     */
    public void showPlayButton(boolean showPlayButton) {
        this.showPlayButton = showPlayButton;
    }

    /**
     * Get the original video this thumbnail was constructed from.
     * @param videoType The type of the video.
     * @return The video.
     */
    public GUCVideo getVideo(GUCVideoType videoType) {
        return new GUCVideo(super.getContentKey(), videoType);
    }

    /**
     * Get whether the thumbnail is animated or not.
     * @return Is the thumbnail animated?
     */
    public boolean isAnimated() {
        return animate;
    }

    /**
     * Get whether we are showing the play button or not.
     * @return Is the play button shown?
     */
    public boolean isPlayButtonShown() {
        return showPlayButton;
    }

    @Override
    public String getURL() {
        String url = super.getURL();
        if(!animate) {
            url += "-k";
        }
        if(!showPlayButton) {
            url += "-no";
        }
        return url;
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

        GUCVideoThumbnail that = (GUCVideoThumbnail) o;

        return animate == that.animate && showPlayButton == that.showPlayButton;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (animate ? 1 : 0);
        result = 31 * result + (showPlayButton ? 1 : 0);
        return result;
    }
}
