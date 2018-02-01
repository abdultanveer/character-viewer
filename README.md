# character-viewer

##### A sample project of consuming network and image libraries, MVP architeture, listing and maintaining data.

###### For image loading, Glide library provides a powerful usage and by using Kotlin extensions, we can make the usage as a single line

```kotlin
//Extensions.kt
fun ImageView.loadImage(context: Context, url: String, @DrawableRes placeHolder: Int) {
    Glide.with(context)
            .load(url)
            .apply(RequestOptions()
                    .placeholder(placeHolder)
                    .error(placeHolder))
            .into(this)
}
//Usage
icon.loadImage(context, url, drawable_placeholder)
```

###### For networking, Retrofit library is one of the most known. By using Kotlin higher order functions, it is easy to consume the network response as such below

```kotlin
        WebServices.request(URL),
                { response -> /*ON SUCCESS*/ },
                { error -> /*ON FAIL*/})
```


###### There are several ways of detecting device type such tablet or phone. One way is creating a layout file that has same name but under sw600dp. This allows to interchange between these layouts through different type of devices.
###### By using the same idea we can also create two same named value resources but one will be under values-sw600dp folder. In this project I used both for different purposes. 
```xml
<!-- values-sw600dp/device.xml -->
 <bool name="is_tablet">true</bool>
 ```
 
 ###### There are 2 application flavors are provided. While they both serving for same purpose with different data, we can easily change between them and detect each's query parameter by requesting 'character_type' string resource.
 ```xml
  productFlavors {
        simsons {
            resValue "string", "character_type", "simpsons+characters"
        }
        thewire {
           resValue "string", "character_type", "the+wire+characters"
        }
    }
```
