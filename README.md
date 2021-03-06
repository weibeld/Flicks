# Project 1 - *Flicks*

**Flicks** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: **12** hours spent in total


## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current movies** from the Movie Database API
* [x] Layout is optimized with the [ViewHolder](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#improving-performance-with-the-viewholder-pattern) pattern.
* [x] For each movie displayed, user can see the following details:
  * [x] Title, Poster Image, Overview (Portrait mode)
  * [x] Title, Backdrop Image, Overview (Landscape mode)

The following **optional** features are implemented:

* [x] Display a nice default [placeholder graphic](http://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#configuring-picasso) for each image during loading.
* [x] Improved the user interface through styling and coloring.

The following **bonus** features are implemented:

* [x] Allow user to view details of the movie including ratings and popularity within a separate activity or dialog fragment.
* [ ] When viewing a popular movie (i.e. a movie voted for more than 5 stars) the video should show the full backdrop image as the layout.  Uses [Heterogenous ListViews](http://guides.codepath.com/android/Implementing-a-Heterogenous-ListView) or [Heterogenous RecyclerView](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) to show different layouts.
* [ ] Allow video trailers to be played in full-screen using the YouTubePlayerView *(started implementing this feature, but couldn't finish it due to time reasons, I will implement it after the deadline)*
    * [ ] Overlay a play icon for videos that can be played.
    * [ ] More popular movies should start a separate activity that plays the video immediately.
    * [ ] Less popular videos rely on the detail page should show ratings and a You Tube preview.
* [ ] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.
* [ ] Apply rounded corners for the poster or background images using [Picasso transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#other-transformations)
* [x] Replaced android-async-http network client with the popular [OkHttp](http://guides.codepath.com/android/Using-OkHttp) networking libraries.

The following **additional** features are implemented:

* [x] Pull-to-refresh with [SwipeRefreshLayout](https://developer.android.com/reference/android/support/v4/widget/SwipeRefreshLayout.html)
* [x] Use of the [Data Binding Library](https://developer.android.com/topic/libraries/data-binding/index.html) to reduce boilerplate code and eliminate `findViewById()` calls


## Video Walkthrough

Here's a walkthrough of implemented user stories:

![Video Walkthrough](assets/walkthrough.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).


## Notes

- Wanted to display all the trailers in a ListView inside the container ScrollView of DetailActivity. However, it's not possible to have a ListView inside a ScrollView. Need to find another way to display a variable-length list of items inside a ScrollView.
- Figuring out how to use Retrofit took some time, especially because of changes in the APIs of different versions (e.g. Retrofit 1 vs. 2, or OkHttp 2 vs. 3). However, once it works, Retrofit seems to be a very powerful library.


## Official Android libraries used

- [Data Binding Library](https://developer.android.com/topic/libraries/data-binding/index.html) - Easier binding of XML layouts to application logic


## Open-source third-party libraries used

- [Glide](https://github.com/bumptech/glide) - Image loading and caching library (alternative to [Picasso](http://square.github.io/picasso/))
- [Retrofit 2](https://square.github.io/retrofit/) - HTTP client (alternative to [android-async-http](http://loopj.com/android-async-http/))
- [Gson](https://github.com/google/gson) - Java serialization/deserialization library that can convert Java Objects into JSON and back (used in conjunction with Retrofit)
- [OkHttp 3](http://square.github.io/okhttp/) - HTTP & HTTP/2 client for Android (used in conjunction with Retrofit)


## License

    Copyright 2017 Daniel Weibel

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
