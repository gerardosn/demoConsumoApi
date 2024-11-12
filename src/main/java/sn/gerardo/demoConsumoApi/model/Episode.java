package sn.gerardo.demoConsumoApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Episode")
    private String episode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    @Override
    public String toString() {
        return "Episodio " + episode + ": " + title;
    }
}
