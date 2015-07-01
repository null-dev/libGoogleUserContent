package gq.nulldev.libGUC.photos;

import gq.nulldev.libGUC.GUCObject;

/**
 * Project: libGoogleUserContent
 * Created: 28/06/15
 * Author: nulldev
 */

/**
 * A GUC photo.
 */
public class GUCPhoto extends GUCObject {
    GUCPhotoSize photoSize = null;

    /**
     * Construct a new photo stored in GoogleUserContent from a content key.
     * @param contentKey The content key.
     */
    public GUCPhoto(String contentKey) {
        super(contentKey);
    }
    /**
     * Construct a new photo stored in GoogleUserContent from a content key and scale it to a size.
     * @param contentKey The content key.
     * @param photoSize The size of the photo.
     */
    public GUCPhoto(String contentKey, GUCPhotoSize photoSize) {
        super(contentKey);
        this.photoSize = photoSize;
    }

    /**
     * Get the photo size if it is to be scaled.
     *
     * Can be null if the original photo size is requested.
     * @return The photo size.
     */
    public GUCPhotoSize getPhotoSize() {
        return photoSize;
    }

    /**
     * Set the photo size for scaling.
     *
     * Set it to null for the original photo.
     * @param photoSize The new photo size.
     */
    public void setPhotoSize(GUCPhotoSize photoSize) {
        this.photoSize = photoSize;
    }

    @Override
    public String getURL() {
        if(photoSize == null) {
            return super.getURL() + "=s0";
        } else {
            //Size specified
            if(photoSize.getPixelLimit() != -1) {
                return super.getURL() + "=s" + photoSize.getPixelLimit();
            } else {
                return super.getURL() + "=w" + photoSize.getWidth() + "-h" + photoSize.getHeight();
            }
        }
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

        GUCPhoto gucPhoto = (GUCPhoto) o;

        return !(photoSize != null ? !photoSize.equals(gucPhoto.photoSize) : gucPhoto.photoSize != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (photoSize != null ? photoSize.hashCode() : 0);
        return result;
    }
}
