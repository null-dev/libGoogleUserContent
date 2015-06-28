package gq.nulldev.libGUC.photos;
/**
 * Project: libGoogleUserContent
 * Created: 28/06/15
 * Author: nulldev
 */

/**
 * A simple size argument for photos.
 */
public class GUCPhotoSize {
    int pixelLimit = -1;

    int height = -1;
    int width = -1;
    /**
     * Limits the size of the longest edge of the photo to the specified number of pixels.
     * @param pixelLimit The maximum number of pixels the longest edge of the photo may contain.
     */
    public GUCPhotoSize(int pixelLimit) {
        if(pixelLimit < 0) {
            throw new IllegalArgumentException("The pixel limit cannot be negative!");
        }
        this.pixelLimit = pixelLimit;
    }

    /**
     * Scale the photo to the dimensions specified.
     *
     * WARNING: Google scales the photo keeping the aspect ratio, it will prioritize height over width.
     *          If the width does not match the height's aspect ratio, it will just ignore the width.
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
     * @return The maximum number of pixels the longest edge of the photo may contain.
     */
    public int getPixelLimit() {
        return pixelLimit;
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

        return pixelLimit == that.pixelLimit && height == that.height && width == that.width;

    }

    @Override
    public int hashCode() {
        int result = pixelLimit;
        result = 31 * result + height;
        result = 31 * result + width;
        return result;
    }

    @Override
    public String toString() {
        if(pixelLimit != -1) {
            return "GUCPhotoSize{" +
                    "pixelLimit=" + pixelLimit +
                    '}';
        } else {
            return "GUCPhotoSize{" +
                    "height=" + height +
                    ", width=" + width +
                    '}';
        }
    }
}
