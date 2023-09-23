package xuanyangyang.webp;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author XYY
 * @since 1.0
 */
public class WebpTests {
    DefaultWebpService webpService = DefaultWebpService.newDefaultWebpService("C:/Users/10539/Desktop/utils/libwebp-1.3.1-windows-x64");

    @Test
    public void testSupport() {
        System.out.println(webpService.support("jpg"));
        System.out.println(webpService.support("jpeg"));
        System.out.println(webpService.support("png"));
        System.out.println(webpService.support("gif"));
    }

    @Test
    public void testEncode() throws Exception {
        String dir = "rawImages";
        Files.walk(Path.of(dir))
                .map(path -> path.toFile())
                .filter(file -> file.isFile())
                .forEach(file -> {
                    String[] split = file.getName().split("\\.");
                    String name = split[0];
                    String imgType = split[1];
                    if (!webpService.support(imgType)) {
                        return;
                    }
                    try {
                        byte[] imgBytes = Files.readAllBytes(file.toPath());
                        byte[] webpBytes = webpService.img2Webp(imgBytes, imgType);
                        Files.write(Path.of(dir + "/" + name + ".webp"), webpBytes);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
