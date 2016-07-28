package de.eneskaya.appindexing;

import android.net.Uri;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class AppIndexing extends CordovaPlugin {

  private GoogleApiClient mClient;

  private static final String TAG = "GoogleAppIndexingCordovaAndroid";
  private static final String VIEW_STARTED = "startView";
  private static final String VIEW_ENDED = "endView";

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView)
  {
    super.initialize(cordova, webView);
    mClient = new GoogleApiClient.Builder(this.cordova.getActivity()).addApi(AppIndex.API).build();
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException
  {
    if (VIEW_STARTED.equals(action)) {
      this.startView(args.getString(0), args.getString(1), args.getString(2), args.getString(3));
      callbackContext.success();
      return true;
    }

    if (VIEW_ENDED.equals(action)) {
      this.endView(args.getString(0), args.getString(1), args.getString(2), args.getString(3));
      callbackContext.success();
      return true;
    }

    return false;  // Returning false results in a "MethodNotFound" error.
  }

  private void startView(String title, String description, String webPath, String androidPath)
  {
    mClient.connect();

    Action viewAction = Action.newAction(
         Action.TYPE_VIEW,
         title,
         Uri.parse(webPath),
         Uri.parse(androidPath)
    );

    AppIndex.AppIndexApi.start(mClient, viewAction);
  }

  private void endView(String title, String description, String webPath, String androidPath)
  {
    Action viewAction = Action.newAction(
         Action.TYPE_VIEW,
         title,
         Uri.parse(webPath),
         Uri.parse(androidPath)
    );

    AppIndex.AppIndexApi.end(mClient, viewAction);

    mClient.disconnect();
  }

}
