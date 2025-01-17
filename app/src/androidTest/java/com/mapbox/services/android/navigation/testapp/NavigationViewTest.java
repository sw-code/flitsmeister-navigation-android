package com.mapbox.services.android.navigation.testapp;

import static com.mapbox.services.android.navigation.testapp.action.NavigationViewAction.invoke;

import static org.junit.Assert.assertNotNull;

import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.services.android.navigation.testapp.test.TestNavigationActivity;
import com.mapbox.services.android.navigation.ui.v5.NavigationViewOptions;
import com.mapbox.services.android.navigation.ui.v5.map.NavigationMapboxMap;
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation;

import org.junit.Test;

import com.mapbox.services.android.navigation.testapp.activity.BaseNavigationActivityTest;

public class NavigationViewTest extends BaseNavigationActivityTest {

  @Override
  protected Class getActivityClass() {
    return TestNavigationActivity.class;
  }

  @Test
  public void onInitialization_navigationMapboxMapIsNotNull() {
    validateTestSetup();

    invoke(getNavigationView(), (uiController, navigationView) -> {
      DirectionsRoute testRoute = DirectionsRoute.fromJson(loadJsonFromAsset("lancaster-1.json"));
      NavigationViewOptions options = NavigationViewOptions.builder()
        .directionsRoute(testRoute)
        .build();

      navigationView.startNavigation(options);
    });
    NavigationMapboxMap navigationMapboxMap = getNavigationView().retrieveNavigationMapboxMap();

    assertNotNull(navigationMapboxMap);
  }

  @Test
  public void onNavigationStart_mapboxNavigationIsNotNull() {
    validateTestSetup();

    invoke(getNavigationView(), (uiController, navigationView) -> {
      DirectionsRoute testRoute = DirectionsRoute.fromJson(loadJsonFromAsset("lancaster-1.json"));
      NavigationViewOptions options = NavigationViewOptions.builder()
        .directionsRoute(testRoute)
        .build();

      navigationView.startNavigation(options);
    });
    MapboxNavigation mapboxNavigation = getNavigationView().retrieveMapboxNavigation();

    assertNotNull(mapboxNavigation);
  }

  //TODO: Test is running locally but not on firebase. Research the root cause
  /*@Test
  public void onNavigationStart_mapboxSendFeedback() {
    validateTestSetup();

    invoke(getNavigationView(), (uiController, navigationView) -> {
      navigationView.retrieveFeedbackButton().show();
      DirectionsRoute testRoute = DirectionsRoute.fromJson(loadJsonFromAsset("lancaster-1.json"));
      NavigationViewOptions options = NavigationViewOptions.builder()
              .directionsRoute(testRoute)
              .build();

      navigationView.startNavigation(options);
    });
    FeedbackRobot feedbackRobot = new FeedbackRobot().openFeedback();
    try {
      sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    FeedbackResultRobot result = feedbackRobot.clickFeedbackAtPos(0);
    result.isSuccess(getNavigationView().getResources().getString(R.string.feedback_submitted));
  }*/
}
