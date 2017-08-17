package apps.com.rxapiintegration.feature.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by USER on 31-03-2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class UserDetails {

    private String name, location;

    public UserDetails(@JsonProperty("name") String name,
                       @JsonProperty("location") String location) {
        this.name = name;
        this.location = location;
    }

    String getName() {
        return name;
    }

    String getLocation() {
        return location;
    }

}
