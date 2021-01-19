package github.davids13.mypetfinderapp.commons.cookies;

public class Cookie {

    private final String name;
    private final String value;
    private final String path;
    private final String domain;
    private final int version;

    public Cookie(String name, String value, String path, String domain, int version) {
        this.name = name;
        this.value = value;
        this.path = path;
        this.domain = domain;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getPath() {
        return path;
    }

    public String getDomain() {
        return domain;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return String.format("Cookie{name='%s', value='%s', path='%s', domain='%s', version=%d}", name, value, path, domain, version);
    }
}
