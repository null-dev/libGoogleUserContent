package gq.nulldev.libGUC.photos;

import gq.nulldev.libGUC.GUCObject;

import java.util.ArrayList;

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
    ArrayList<GUCPhotoTransformation> transformations = null;

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
     * Apply a transformation on the image
     * @param transformation The transformation to apply
     */
    public void applyTransformation(GUCPhotoTransformation transformation) {
        if(transformations == null) transformations = new ArrayList<>();
        transformations.add(transformation);
    }

    /**
     * Get all transformations applied on the image
     * @return An arraylist of the applied transformations
     */
    public ArrayList<GUCPhotoTransformation> getTransformations() {
        if(transformations == null) transformations = new ArrayList<>();
        return transformations;
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
        StringBuilder url = new StringBuilder(super.getURL());
        if(photoSize == null) {
            url.append("=s0");
        } else {
            //Size specified
            if(photoSize.getLongestEdgeLimit() != -1) {
                url.append("=s")
                        .append(photoSize.getLongestEdgeLimit());
            } else {
                url.append("=w")
                        .append(photoSize.getWidth())
                        .append("-h")
                        .append(photoSize.getHeight());
            }
            //Add crop
            if(photoSize.isCrop()) {
                url.append("-c");
            }
        }
        //Apply transformations
        if(transformations != null && transformations.size() > 0) {
            for(GUCPhotoTransformation transformation : transformations) {
                url.append('-').append(transformation.toUrlParameter());
            }
        }
        return url.toString();
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
