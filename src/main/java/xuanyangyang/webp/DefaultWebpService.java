package xuanyangyang.webp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DefaultWebpService implements WebpService {
    private final List<WebpConverter> converterList = new ArrayList<>();
    private static final String IMAGES_DIR = "images";

    public DefaultWebpService() {
        File dir = new File(IMAGES_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    @Override
    public boolean support(String imgType) {
        for (WebpConverter converter : converterList) {
            if (converter.support(imgType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public byte[] img2Webp(byte[] imageBytes, String imgType) throws Exception {
        for (WebpConverter converter : converterList) {
            if (converter.support(imgType)) {
                String fileId = UUID.randomUUID().toString();
                String inputFile = IMAGES_DIR + "/" + fileId + "." + imgType;
                String outputFile = IMAGES_DIR + "/" + fileId + "." + "webp";
                Path inputPath = Path.of(inputFile);
                Path outputPath = Path.of(outputFile);
                Files.write(inputPath, imageBytes);
                try {
                    EncodeParam param = new EncodeParam();
                    param.setInputFile(inputFile);
                    param.setOutputFile(outputFile);
                    param.setEnableLossy(true);
                    if (!converter.encode(param)) {
                        throw new RuntimeException("无法转换成webp");
                    }
                    byte[] result = Files.readAllBytes(outputPath);
                    return result;
                } finally {
                    Files.deleteIfExists(inputPath);
                    Files.deleteIfExists(outputPath);
                }
            }
        }
        throw new RuntimeException("找不到" + imgType + "转换器");
    }

    @Override
    public void addConverter(WebpConverter converter) {
        converterList.add(converter);
    }

    public static DefaultWebpService newDefaultWebpService(String webpHome) {
        WebpFileManager webpFileManager = new WebpFileManager(webpHome);
        DefaultWebpService defaultWebpService = new DefaultWebpService();
        defaultWebpService.addConverter(new Img2WebpConverter(webpFileManager));
        defaultWebpService.addConverter(new Gif2WebpConverter(webpFileManager));
        return defaultWebpService;
    }
}
