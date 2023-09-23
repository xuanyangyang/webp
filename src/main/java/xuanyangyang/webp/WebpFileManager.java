package xuanyangyang.webp;

public class WebpFileManager {
    private final String webpHome;

    public WebpFileManager(String webpHome) {
        this.webpHome = webpHome;
    }

    public String getFile(String fileName) {
        return webpHome + "/" + fileName;
    }
}
