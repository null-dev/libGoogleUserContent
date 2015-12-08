# libGoogleUserContent
A very lightweight library to programmatically construct googleusercontent.com URLs.

### What does this library do? ###
libGoogleUserContent allows you to offload some of the load onto Google's servers if you need to sometimes apply transformations and resizing operations onto your images. Google's servers can do some of these operations via a hidden API. I have reverse engineered some of the API features and made them available in library form.

WARNING: This library uses an API that is mostly undocumented and unsupported publicly. <b>Transformations and features may come and go at any time</b> at the discretion of the great "Google". This library is not official either, it comes from a lot of trial and error and the reverse engineering of many URLs.

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

Get the url of an animated thumbnail of a video from a content key with a play button overlaid on it, apply a 30 pixel border and flip it upside down:
```
GUCVideo video = new GUCVideo("FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH", GUCVideoType._MP4_1280x720);
GUCVideoThumbnail thumb = video.getThumbnail();
thumb.applyTransformation(new GUCPhotoTransformation(GUCPhotoTransformation.Type.BORDER, "30"));
thumb.applyTransformation(new GUCPhotoTransformation(GUCPhotoTransformation.Type.VERTICAL_FLIP));
URL thumbURL = thumb.getURL();
```
(Returns: https://lh3.googleusercontent.com/FStqaBaXK7pteZ4jX5poKc0c-Ed2tqKcv2NyTAP7MwuH=s0-b30-fv)