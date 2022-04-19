import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.List;


public class Main {

    public static ObjectMapper mapper = new ObjectMapper();
    public static final String REMOTE_URL =
            "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) throws IOException {

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        final HttpGet request = new HttpGet(REMOTE_URL);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            List<FactsAboutCats> facts = mapper.readValue(
                    response.getEntity().getContent(), new TypeReference<>() {
                    });

            facts.stream()
                    .filter(x -> x.getUpvotes() != 0)
                    .forEach(System.out::println);
        } finally {
            httpClient.close();
        }
    }
}
