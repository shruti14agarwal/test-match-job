
package com.test.swipejobs.testmatchjobapi.generateddto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Availability {

    @JsonProperty("title")
    private String title;
    @JsonProperty("dayIndex")
    private Integer dayIndex;
  

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("dayIndex")
    public Integer getDayIndex() {
        return dayIndex;
    }

    @JsonProperty("dayIndex")
    public void setDayIndex(Integer dayIndex) {
        this.dayIndex = dayIndex;
    }

  

}
