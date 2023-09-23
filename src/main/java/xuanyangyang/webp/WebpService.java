package xuanyangyang.webp;

public interface WebpService {
    boolean support(String imgType);

    byte[] img2Webp(byte[] imageBytes, String imgType) throws Exception;

    void addConverter(WebpConverter converter);
}
