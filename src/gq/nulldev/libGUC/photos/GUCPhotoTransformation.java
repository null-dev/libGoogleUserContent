package gq.nulldev.libGUC.photos;

/**
 * Project: libGoogleUserContent
 * Created: 07/12/15
 * Author: nulldev
 */
public class GUCPhotoTransformation {
    Type type;
    String argument = "";

    /**
     * Construct a transformation from a type that does not require arguments
     * @param type The type of transformation
     */
    public GUCPhotoTransformation(Type type) {
        this.type = type;
    }

    /**
     * Construct a transformation with a type and an argument
     * @param type The type of transformation to apply
     * @param argument Arguments to the transformation
     */
    public GUCPhotoTransformation(Type type, String argument) {
        this.type = type;
        this.argument = argument;
    }

    /**
     * Get this transformation as a URL parameter
     * @return The URL parameter
     */
    public String toUrlParameter() {
        return type.getUrlParameter() + argument;
    }

    /**
     * Get any arguments supplied to this transformation
     * @return The arguments
     */
    public String getArgument() {
        return argument;
    }

    /**
     * Get thet type of this transformation
     * @return The type of transformation
     */
    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GUCPhotoTransformation that = (GUCPhotoTransformation) o;

        return argument.equals(that.argument) && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = argument.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GUCPhotoTransformation{" +
                "type=" + type +
                ", argument='" + argument + '\'' +
                '}';
    }

    //TODO Investigate parameters d,l,q,t,v
    //Parameter l seems to only apply on videos only affecting loading times
    public enum Type {
        /**
         * If the image is a GIF image, this will return one frame from it.
         * GIF images generated as video thumbnails usually have about 50 frames.
         *
         * Argument: The frame number to get.
         *
         */
        FRAME("a"),
        /**
         * Adds a border onto the edge of the image.
         * If the border is thicker than 1 pixel it will be added onto the outside of the shortest edge and the inside of the longest edge.
         *
         * Argument: The thickness of the border in pixels.
         *
         */
        BORDER("b"),
        /**
         * Horizontally flips the image
         *
         * Argument: None
         *
         */
        HORIZONTAL_FLIP("fh"),
        /**
         * Vertically flips the image
         *
         * Argument: None
         *
         */
        VERTICAL_FLIP("fv");
        private String urlParameter;

        Type(String urlParameter) {
            this.urlParameter = urlParameter;
        }

        /**
         * Get the URL parameter required to execute this transformation
         *
         * @return The URL parameter attached to this transformation
         */
        public String getUrlParameter() {
            return urlParameter;
        }
    }
}
