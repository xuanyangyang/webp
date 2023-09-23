package xuanyangyang.webp;

public class Gif2WebpConverter implements WebpConverter {
    private final WebpFileManager fileManager;

    public Gif2WebpConverter(WebpFileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public boolean encode(EncodeParam param) throws Exception {
        String cwebp = fileManager.getFile("gif2webp");
        StringBuilder cmdBuilder = new StringBuilder(cwebp);
        if (param.isEnableLossy()) {
            cmdBuilder.append(" ").append("-lossy");
        }
        cmdBuilder.append(" ").append(param.getInputFile()).append(" -o ").append(param.getOutputFile());
        String command = cmdBuilder.toString();
        Process exec = Runtime.getRuntime().exec(command);
        int exitCode = exec.waitFor();
        return exitCode == 0;
    }

    @Override
    public boolean decode(DecodeParam param) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decode'");
    }

    @Override
    public boolean support(String imgType) {
        return "gif".equals(imgType);
    }

}
