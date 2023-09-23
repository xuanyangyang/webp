package xuanyangyang.webp;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Img2WebpConverter implements WebpConverter {
    private static final Set<String> IMG_TYPE_SET;
    static {
        IMG_TYPE_SET = new HashSet<>();
        IMG_TYPE_SET.add("jpeg");
        IMG_TYPE_SET.add("jpg");
        IMG_TYPE_SET.add("png");
        IMG_TYPE_SET.add("tiff");
        IMG_TYPE_SET.add("tif");
    }

    private final WebpFileManager fileManager;

    public Img2WebpConverter(WebpFileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public boolean encode(EncodeParam param) throws IOException, InterruptedException {
        String cwebp = fileManager.getFile("cwebp");
        String command = cwebp + " -m 6 " + param.getInputFile() + " -o " + param.getOutputFile();
        System.out.println(command);
        Process exec = Runtime.getRuntime().exec(command);
        int exitCode = exec.waitFor();
        return exitCode == 0;
    }

    @Override
    public boolean decode(DecodeParam param) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decode'");
    }

    @Override
    public boolean support(String imgType) {
        return IMG_TYPE_SET.contains(imgType);
    }

}
