package com.myexam.controller.request.authlete;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOAuthRequest {

  public Map<String, String[]> createOAuthRequestMap() {
    Map<String, String[]> map = new HashMap<>();

    for (Field field : this.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      Object fieldValue;
      try {
        fieldValue = field.get(this);
      } catch (IllegalAccessException e) {
        // ToDo: issue the warning log
        continue;
      }
      if (fieldValue != null) {
        String[] value = {fieldValue.toString()};
        map.put(field.getName(), value);
      }
    }
    return map;
  }
}
