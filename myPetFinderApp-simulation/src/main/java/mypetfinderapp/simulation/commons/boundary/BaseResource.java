package simulation.commons.boundary;

import org.apache.commons.io.IOUtils;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class BaseResource {

    public static final String CONSUME_DEFAULT = MediaType.APPLICATION_JSON + "; charset=utf-8";
    public static final String PRODUCE_DEFAULT = MediaType.APPLICATION_JSON + "; charset=utf-8";
    public static final String PRODUCE_TEXT_PLAIN = MediaType.TEXT_PLAIN + "; charset=utf-8";

    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String EXPIRES = "Expires";
    public static final String CACHE_CONTROL_NO_CACHE = "must-revalidate, private, no-cache, no-store, max-age=0";
    public static final Integer EXPIRES_ALWAYS = -1;

    protected String getFileAsString(final String file) {
        try (final InputStream inputStream = BaseResource.class.getResourceAsStream("/" + file)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading file");
        }
    }

    protected boolean checkIfContains(String response, String property, String part) {
        int indexOfProp = response.indexOf("\"" + property + "\"");
        String line = response.substring(indexOfProp, response.indexOf(",", indexOfProp));
        return line.contains(part);
    }
}
