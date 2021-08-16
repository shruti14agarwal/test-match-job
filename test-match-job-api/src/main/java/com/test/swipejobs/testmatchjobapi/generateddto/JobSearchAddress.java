
package com.test.swipejobs.testmatchjobapi.generateddto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class JobSearchAddress {

    @JsonProperty("unit")
    private String unit;
    @JsonProperty("maxJobDistance")
    private Integer maxJobDistance;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("latitude")
    private String latitude;


    @JsonProperty("unit")
    public String getUnit() {
        return unit;
    }

    @JsonProperty("unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @JsonProperty("maxJobDistance")
    public Integer getMaxJobDistance() {
        return maxJobDistance;
    }

    @JsonProperty("maxJobDistance")
    public void setMaxJobDistance(Integer maxJobDistance) {
        this.maxJobDistance = maxJobDistance;
    }

    @JsonProperty("longitude")
    public String getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("latitude")
    public String getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

   

}
