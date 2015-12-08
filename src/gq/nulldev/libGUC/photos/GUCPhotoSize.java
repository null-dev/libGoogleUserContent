package gq.nulldev.libGUC.photos;
/**
 * Project: libGoogleUserContent
 * Created: 28/06/15
 * Author: nulldev
 */

/**
 * A simple size argument for photos.
 */

/**
 * An easy way to specify sizes for GUC photos.
 */
public class GUCPhotoSize {
    int longestEdgeLimit = -1;
    boolean crop = false;

    int height = -1;
    int width = -1;

    /**
     * Limits the size of the longest edge of the photo to the specified number of pixels.
     * @param longestEdgeLimit The maximum length of the longest edge of the photo.
     * @param crop Whether or not to force this limit by cropping the photo.
     *             This will scale the shorter edge up/down to the edge limit and crop the longest edge down to the edge limit. (The photo will be square)
     */
    public GUCPhotoSize(int longestEdgeLimit, boolean crop) {
        this.longestEdgeLimit = longestEdgeLimit;
        this.crop = crop;
    }

    /**
     * Scale the photo to the dimensions specified.
     *
     * WARNING: Google scales the photo keeping the aspect ratio, it will limit prioritize the longest side to the param specified here.
     *
     * @param height The height of the photo.
     * @param width The width of the photo.
     * @param crop Whether or not to force this limit by cropping the photo.
     *             This will scale the shorter edge up/down to the limit and crop the longest edge down to the limit.
     */
    public GUCPhotoSize(boolean crop, int height, int width) {
        this.crop = crop;
        this.height = height;
        this.width = width;
    }

    /**
     * Limits the size of the longest edge of the photo to the specified number of pixels.
     * @param longestEdgeLimit The maximum length of the longest edge of the photo.
     */
    public GUCPhotoSize(int longestEdgeLimit) {
        if(longestEdgeLimit < 0) {
            throw new IllegalArgumentException("The pixel limit cannot be negative!");
        }
        this.longestEdgeLimit = longestEdgeLimit;
    }

    /**
     * Scale the photo to the dimensions specified.
     *
     * WARNING: Google scales the photo keeping the aspect ratio, it will limit prioritize the longest side to the param specified here.
     *
     * @param height The height of the photo.
     * @param width The width of the photo.
     */
    public GUCPhotoSize(int height, int width) {
        if(height < 0 || width < 0) {
            throw new IllegalArgumentException("The height/width cannot be negative!");
        }
        this.height = height;
        this.width = width;
    }

    /**
     * Get the maximum number of pixels the longest edge of the photo may contain.
     *
     * Use getLongestEdgeLimit as this method has been renamed!
     *
     * @return The maximum length of the longest edge of the photo.
     */
    @Deprecated
    public int getPixelLimit() {
        return longestEdgeLimit;
    }

    /**
     * Get the maximum number of pixels the longest edge of the photo may contain.
     * @return The maximum length of the longest edge of the photo.
     */
    public int getLongestEdgeLimit() {
        return longestEdgeLimit;
    }

    /**
     * Get whether or not to crop the image.
     * @return Whether or not to force this limit by cropping the photo.
     *         This will scale the shorter edge up/down to the limit and crop the longest edge down to the limit.
     */
    public boolean isCrop() {
        return crop;
    }

    /**
     * Get the height of the photo.
     * @return The height of the photo.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the width of the photo.
     * @return The width of the photo.
     */
    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GUCPhotoSize that = (GUCPhotoSize) o;

        if (longestEdgeLimit != that.longestEdgeLimit) return false;
        if (crop != that.crop) return false;
        if (height != that.height) return false;
        return width == that.width;

    }

    @Override
    public int hashCode() {
        int result = longestEdgeLimit;
        result = 31 * result + (crop ? 1 : 0);
        result = 31 * result + height;
        result = 31 * result + width;
        return result;
    }

    @Override
    public String toString() {
        return "GUCPhotoSize{" +
                "longestEdgeLimit=" + longestEdgeLimit +
                ", crop=" + crop +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
