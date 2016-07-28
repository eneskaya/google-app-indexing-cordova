var exec = require('cordova/exec');

function AppIndexing() {}

AppIndexing.prototype.startView = function(title, description, webPath, androidPath, success, error) {
  var arg0 = [
    title,
    description,
    webPath, // The path for the web content
    androidPath // The path for the android app deeplink
  ];

  exec(success, error, "AppIndexing", "startView", arg0);
};

AppIndexing.prototype.endView = function(title, description, webPath, androidPath, success, error) {
  var arg0 = [
    title,
    description,
    webPath, // The path for the web content
    androidPath // The path for the android app deeplink
  ];

  exec(success, error, "AppIndexing", "endView", arg0);
};

module.exports = new AppIndexing();
