package sn.gerardo.demoConsumoApi.service;

//import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sn.gerardo.demoConsumoApi.model.SeasonResponse;

@Service //@Component
public class OmdbService {
    @Value("${OMDB_API_KEY}") private  String API_KEY; // Reemplaza con tu API key de OMDB

//    private static String API_KEY;
//    @PostConstruct
//    public void init(){
//        API_KEY=API_KEY_FromValue;
//    }

    private static final String BASE_URL = "https://www.omdbapi.com/";

    @Autowired
    private RestTemplate restTemplate;

    public SeasonResponse getSeasonEpisodes(String seriesName, int season) {
        String url = String.format("%s?apikey=%s&t=%s&Season=%d", BASE_URL, API_KEY, seriesName, season);
//        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
//                .queryParam("apikey", apiKey)
//                .queryParam("t", seriesName)
//                .queryParam("Season", season)
//                .build()
//                .toUriString();
        return restTemplate.getForObject(url, SeasonResponse.class);
    }
}
