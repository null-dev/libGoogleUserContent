package gq.nulldev.libGUC;

/**
 * Project: libGoogleUserContent
 * Created: 28/06/15
 * Author: nulldev
 */

/**
 * The base class for GoogleUserContent objects.
 */
public class GUCObject {

    static final String baseURL = "https://lh3.googleusercontent.com/";

    /**
     * The content key is this part of the original URL:
     *
     * https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=k-no
     *                                   ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     * All the characters of the content key have been marked with a '^'
     */
    String contentKey;

    /**
     * Construct a new GoogleUserContent object from a content key.
     * @param contentKey The content key of the object.
     */
    public GUCObject(String contentKey) {
        this.contentKey = contentKey;
    }

    /**
     * Get the URL of the GoogleUserContent object.
     */
    public String getURL() {
        return baseURL + contentKey;
    }

    /**
     * Get the content key of the object.
     * @return The content key.
     */
    public String getContentKey() {
        return contentKey;
    }

    @Override
    public String toString() {
        return this.getURL();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GUCObject gucObject = (GUCObject) o;

        return contentKey.equals(gucObject.contentKey);

    }

    @Override
    public int hashCode() {
        return contentKey.hashCode();
    }
}
