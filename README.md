# libGoogleUserContent
A very lightweight but pointless library to programmatically construct googleusercontent.com URLs.

It currently consists of only 5 classes! There really is no point in using this except because it is a lot more "Object Oriented" than hardcoding in URLS (and it looks a lot nicer).

You can download the JAR in the releases section, just import and go.

### What's a content key? ###
Most of the functions in this library require a content key, and many people seem to be confused by what it is. The content key is the part of the original URL from the <code>/</code> to the <code>=</code> as shown below.
```
https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=k-no
                                  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
```

### Examples ###
Get the url of a 1080x720p MP4 video from a content key:

```
URL videoURL = new GUCVideo("FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH", GUCVideoType._MP4_1280x720).getURL();
```
(Returns: https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=m22)

Get the url of an animated thumbnail of a video from a content key with a play button overlaid on it:
```
GUCVideo video = new GUCVideo("FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH", GUCVideoType._MP4_1280x720);
GUCVideoThumbnail thumb = video.getThumbnail();
thumb.animate(true);
thumb.showPlayButton(true);
URL thumbURL = thumb.getURL();
```
(Returns: https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=s0)

Get the thumbnail of a video and scale the longest side to 100px preserving aspect ratio:
```
GUCVideo video = new GUCVideo("FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH", GUCVideoType._MP4_1280x720);
GUCVideoThumbnail thumb = video.getThumbnail();
thumb.animate(false);
thumb.showPlayButton(false);
thumb.setPhotoSize(new GUCPhotoSize(100));
System.out.println(thumb.getURL());
```
(Returns: https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=s100-k-no)
