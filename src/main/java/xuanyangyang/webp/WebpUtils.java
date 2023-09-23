package xuanyangyang.webp;

/**
 * @author XYY
 * @since 1.0
 */
public abstract class WebpUtils {
    private static final WebpService webpService;

    static {
        String webpHome = System.getProperty("webpHome");
        if (webpHome == null || webpHome.isBlank()) {
            webpHome = "webp";
        }
        webpService = DefaultWebpService.newDefaultWebpService(webpHome);
    }

    public static boolean support(String imgType) {
        return webpService.support(imgType);
    }

    public static byte[] img2Webp(byte[] imageBytes, String imgType) throws Exception {
        return webpService.img2Webp(imageBytes, imgType);
    }

    public static void addConverter(WebpConverter converter) {
        webpService.addConverter(converter);
    }
}
