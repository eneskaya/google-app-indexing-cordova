## Google App Indexing for Cordova/Ionic apps (Android only)

Further information on App Indexing: https://developers.google.com/app-indexing

### Deeplinking first!

Be sure, that you have deeplinking enabled in your android app! I recommend you check out this plugin: https://github.com/EddyVerbruggen/Custom-URL-scheme

### Installation

In your Ionic/Cordova application do: `cordova plugin add https://github.com/eneskaya/google-app-indexing-cordova.git`

**Note**: At the moment this plugin is not yet submitted to the Cordova Plugins directory. I will submit it soon.

### Usage

When you add the plugin, there is a global `AppIndexing` object available, after cordova has loaded. This object has two methods: `startView` and `endView` with following signatures:

`AppIndexing.startView(title, description, webPath, androidPath, success, error)`

and

`AppIndexing.endView(title, description, webPath, androidPath, success, error)`

Whenever a user of your app starts viewing content in your app, just call the `startView` method,
and when they leave the page call `endView` with exactly the same arguments.

**Note**: I think, Google is measuring the time the customer stays on that page to prevent
that you simply push all of your content to the App Indexing. It can lead to your app not being
indexed at all, if they see such behaviour.

### `webPath` and `androidPath`

If you're confused about the two paths, please read on https://developers.google.com/app-indexing first.
The webPath is your content accessible via the web *(e.g. http://example.com/path/to/some-content)*. Whereas the androidPath is
the deeplink to your android app in the style of `android-app://{app package identifier}/{scheme}/{path}}`
*(e.g. android-app://de.example.com/https/path/to/some-content)*. Ideally the path should be the same for web and deeplink,
but that's up to you.

### Got questions?

Don't hesitate to open a issue or tweet me @eneskaya
