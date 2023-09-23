package xuanyangyang.webp;

/**
 * cwebp参数
 *
 * @author XYY
 * @since 1.0
 */
public class EncodeParam {
    private String inputFile;
    private String outputFile;
    private boolean enableLossy;

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public boolean isEnableLossy() {
        return enableLossy;
    }

    public void setEnableLossy(boolean enableLossy) {
        this.enableLossy = enableLossy;
    }
}
