package com.purple_scout.mvd.web;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Created by abc123 on 2015-04-06.
 */
public interface IJsonCollectionConvertable {
    public void createCollection(JSONArray jsonArr);
    public String getUrl();
    public Map<String, String> getDefaultMap();
    public String getAction();
}
