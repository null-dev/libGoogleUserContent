import gq.nulldev.libGUC.GUCGoogleCommunicator;
import gq.nulldev.libGUC.GUCObject;
import gq.nulldev.libGUC.photos.GUCPhoto;
import gq.nulldev.libGUC.photos.GUCPhotoSize;
import gq.nulldev.libGUC.videos.GUCVideo;
import gq.nulldev.libGUC.videos.GUCVideoThumbnail;
import gq.nulldev.libGUC.videos.GUCVideoType;

/**
 * Project: libGoogleUserContent
 * Created: 28/06/15
 * Author: nulldev
 */

public class Test {

    static final String EXAMPLE_CONTENT_KEY = "FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH";
    static final String EXAMPLE_INVALID_CONTENT_KEY = "0000000000000000000000000000000000000";

    /*
    Too lazy to write unit tests :/
     */
    public static void main(String[] args) {
        System.out.println("Starting tests...");
        System.out.println();
        System.out.println("Testing basic GUC object creation:");
        System.out.println(new GUCObject(EXAMPLE_CONTENT_KEY));
        System.out.println();
        System.out.println("Testing basic photo creation:");
        System.out.println(new GUCPhoto(EXAMPLE_CONTENT_KEY));
        System.out.println("Testing photo edge pixel limiting:");
        {
            GUCPhotoSize photoSize = new GUCPhotoSize(500);
            GUCPhoto photo = new GUCPhoto(EXAMPLE_CONTENT_KEY, photoSize);
            System.out.println(photo);
        }
        System.out.println("Testing photo height and width scaling:");
        {
            GUCPhotoSize photoSize = new GUCPhotoSize(100, 300);
            GUCPhoto photo = new GUCPhoto(EXAMPLE_CONTENT_KEY, photoSize);
            System.out.println(photo);
        }
        System.out.println();
        System.out.println("Testing video types:");
        {
            System.out.println("TINY-3GP:");
            System.out.println(new GUCVideo(EXAMPLE_CONTENT_KEY, GUCVideoType._3GP_320x180));
            System.out.println("SMALL-MP4:");
            System.out.println(new GUCVideo(EXAMPLE_CONTENT_KEY, GUCVideoType._MP4_640x360));
            System.out.println("BIG-MP4:");
            System.out.println(new GUCVideo(EXAMPLE_CONTENT_KEY, GUCVideoType._MP4_1280x720));
            //My example content is not big enough for the massive MP4 :(
        }
        System.out.println();
        System.out.println("Testing video thumbnails:");
        {
            GUCVideo video = new GUCVideo(EXAMPLE_CONTENT_KEY, GUCVideoType._MP4_640x360);
            System.out.println("Animated, no play button:");
            GUCVideoThumbnail thumb = video.getThumbnail();
            thumb.animate(true);
            thumb.showPlayButton(false);
            System.out.println(thumb);
            System.out.println("Non-animated, no play button:");
            thumb = video.getThumbnail();
            thumb.animate(false);
            thumb.showPlayButton(false);
            System.out.println(thumb);
            System.out.println("Animated, play button:");
            thumb = video.getThumbnail();
            thumb.animate(true);
            thumb.showPlayButton(true);
            System.out.println(thumb);
            System.out.println("Non-animated, play button:");
            thumb = video.getThumbnail();
            thumb.animate(false);
            thumb.showPlayButton(true);
            System.out.println(thumb);
        }
        System.out.println();
        System.out.println("Testing content key verifier:");
        System.out.println(GUCGoogleCommunicator.isContentKeyValid(EXAMPLE_CONTENT_KEY));
        System.out.println(GUCGoogleCommunicator.isContentKeyValid(EXAMPLE_INVALID_CONTENT_KEY));
        System.out.println();
        System.out.println("Testing getSupportedResolutions:");
        System.out.println(GUCGoogleCommunicator.getSupportedResolutions(EXAMPLE_CONTENT_KEY));
        System.out.println();
        System.out.println("All tests complete!");
    }
}
