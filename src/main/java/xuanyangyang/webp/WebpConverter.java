package xuanyangyang.webp;

/**
 * @author XYY
 * @since 1.0
 */
public interface WebpConverter {

    boolean encode(EncodeParam param) throws Exception;

    boolean decode(DecodeParam param) throws Exception;

    boolean support(String imgType);
}
