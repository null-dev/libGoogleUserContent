import gq.nulldev.libGUC.GUCGoogleCommunicator;
import gq.nulldev.libGUC.GUCObject;
import gq.nulldev.libGUC.photos.GUCPhoto;
import gq.nulldev.libGUC.photos.GUCPhotoSize;
import gq.nulldev.libGUC.photos.GUCPhotoTransformation;
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
    //This invalid content key is not really reliable (what happens when a content with that key actually appears)?
    static final String EXAMPLE_INVALID_CONTENT_KEY = "0000000000000000000000000000000000000";

    /*
    Too lazy to use JUnit
     */
    public static void main(String[] args) {
        String result;
        boolean booleanResult;
        System.out.println("Starting tests...");
        System.out.println();
        System.out.println("Testing basic GUC object creation:");
        System.out.println(result = new GUCObject(EXAMPLE_CONTENT_KEY).toString());
        assert result.equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH");
        System.out.println();
        System.out.println("Testing basic photo creation:");
        System.out.println(result = new GUCPhoto(EXAMPLE_CONTENT_KEY).toString());
        assert result.equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=s0");
        System.out.println("Testing photo edge pixel limiting:");
        {
            GUCPhotoSize photoSize = new GUCPhotoSize(500);
            GUCPhoto photo = new GUCPhoto(EXAMPLE_CONTENT_KEY, photoSize);
            System.out.println(photo);
            assert photo.toString().equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=s500");
        }
        System.out.println("Testing photo height and width scaling:");
        {
            GUCPhotoSize photoSize = new GUCPhotoSize(100, 300);
            GUCPhoto photo = new GUCPhoto(EXAMPLE_CONTENT_KEY, photoSize);
            System.out.println(photo);
            assert photo.toString().equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=w300-h100");
        }
        System.out.println();
        System.out.println("Testing video types:");
        {
            System.out.println("TINY-3GP:");
            System.out.println(result = new GUCVideo(EXAMPLE_CONTENT_KEY, GUCVideoType._3GP_320x180).toString());
            assert result.equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=m36");
            System.out.println("SMALL-MP4:");
            System.out.println(result = new GUCVideo(EXAMPLE_CONTENT_KEY, GUCVideoType._MP4_640x360).toString());
            assert result.equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=m18");
            System.out.println("BIG-MP4:");
            System.out.println(result = new GUCVideo(EXAMPLE_CONTENT_KEY, GUCVideoType._MP4_1280x720).toString());
            assert result.equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=m22");
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
            assert thumb.toString().equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=s0-no");
            System.out.println("Non-animated, no play button:");
            thumb = video.getThumbnail();
            thumb.animate(false);
            thumb.showPlayButton(false);
            System.out.println(thumb);
            assert thumb.toString().equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=s0-k-no");
            System.out.println("Animated, play button:");
            thumb = video.getThumbnail();
            thumb.animate(true);
            thumb.showPlayButton(true);
            System.out.println(thumb);
            assert thumb.toString().equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=s0");
            System.out.println("Non-animated, play button:");
            thumb = video.getThumbnail();
            thumb.animate(false);
            thumb.showPlayButton(true);
            System.out.println(thumb);
            assert thumb.toString().equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=s0-k");
        }
        System.out.println();
        System.out.println("Testing content key verifier:");
        System.out.println(booleanResult = GUCGoogleCommunicator.isContentKeyValid(EXAMPLE_CONTENT_KEY));
        assert booleanResult;
        System.out.println(booleanResult = GUCGoogleCommunicator.isContentKeyValid(EXAMPLE_INVALID_CONTENT_KEY));
        assert !booleanResult;
        System.out.println();
        System.out.println("Testing getSupportedResolutions:");
        System.out.println(result = GUCGoogleCommunicator.getSupportedResolutions(EXAMPLE_CONTENT_KEY).toString());
        assert result.equals("[_MP4_1280x738, _MP4_1280x720, _MP4_640x360, _3GP_320x180]");
        System.out.println();
        {
            System.out.println("Testing transformations:");
            GUCVideoThumbnail thumbnail = new GUCVideo(EXAMPLE_CONTENT_KEY, GUCVideoType._MP4_640x360).getThumbnail();
            thumbnail.applyTransformation(new GUCPhotoTransformation(GUCPhotoTransformation.Type.BORDER, "30"));
            thumbnail.applyTransformation(new GUCPhotoTransformation(GUCPhotoTransformation.Type.VERTICAL_FLIP));
            System.out.println(result = thumbnail.getURL());
            assert result.equals("https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=s0-b30-fv");
        }
        System.out.println();
        System.out.println("All tests complete!");
    }
}
