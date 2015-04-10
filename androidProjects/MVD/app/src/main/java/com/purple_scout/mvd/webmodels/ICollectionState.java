package com.purple_scout.mvd.webmodels;

import java.util.Map;

/**
 * Created by abc123 on 2015-04-06.
 */
public interface ICollectionState {
    String getUrl();
    Map<String, String> getDataMapping();
    String getAction();
}
