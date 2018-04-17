package com.chongu.weixin;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by cheng on 2016-5-26.
 */
public class WeixinApiResultImpl {
    private Map<String, String> map = null;
    private Map<String, String> errorMap = null;

    /**
     * 鎺ュ彛杩斿洖鐨処nputStream娴侊紙XML娴侊級
     * @param inputStream
     */
    public WeixinApiResultImpl(InputStream inputStream) throws DocumentException {
        super();
            this.map = getResultsMap(inputStream);
        this.errorMap = new HashMap<String, String>();
        //閿欒鐮�
        this.errorMap.put("NOAUTH", "鍟嗘埛鏃犳鎺ュ彛鏉冮檺");
        this.errorMap.put("NOTENOUGH", " 浣欓涓嶈冻");
        this.errorMap.put("ORDERPAID", " 鍟嗘埛璁㈠崟宸叉敮浠�");
        this.errorMap.put("ORDERCLOSED", "璁㈠崟宸插叧闂�");
        this.errorMap.put("SYSTEMERROR", "绯荤粺閿欒");
        this.errorMap.put("APPID_NOT_EXIST", "APPID涓嶅瓨鍦�");
        this.errorMap.put("MCHID_NOT_EXIST", "MCHID涓嶅瓨鍦�");
        this.errorMap.put("APPID_MCHID_NOT_MATCH", "appid鍜宮ch_id涓嶅尮閰�");
        this.errorMap.put("LACK_PARAMS", "缂哄皯鍙傛暟");
        this.errorMap.put("OUT_TRADE_NO_USED", "鍟嗘埛璁㈠崟鍙烽噸澶�");
        this.errorMap.put("SIGNERROR", "绛惧悕閿欒");
        this.errorMap.put("XML_FORMAT_ERROR", "XML鏍煎紡閿欒");
        this.errorMap.put("REQUIRE_POST_METHOD", "璇蜂娇鐢╬ost鏂规硶");
        this.errorMap.put("POST_DATA_EMPTY", "post鏁版嵁涓虹┖");
        this.errorMap.put("NOT_UTF8", "缂栫爜鏍煎紡閿欒 ");
    }


    /**
     * 鍙拡瀵规病鏈夐噸澶嶈妭鐐圭殑xml
     * @param in
     * @return
     */
    private Map<String, String> getResultsMap(InputStream in) throws DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        if (in == null) {
            return map;
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        for (Element child : childElements) {
        	System.out.println("child.getName()-->"+child.getName());
        	System.out.println("child.getStringValue()-->"+child.getStringValue());
            map.put(child.getName(), child.getStringValue());
        }
        return map;
    }


    private String return_code;//杩斿洖鐘舵�佺爜
    private String return_msg;//杩斿洖淇℃伅
    private String appid;//鍏紬璐﹀彿ID
    private String mch_id;//鍟嗘埛鍙�
    private String sub_appid;//瀛愬晢鎴峰叕浼楄处鍙稩D
    private String sub_mch_id;//瀛愬晢鎴峰彿
    private String device_info;//璁惧鍙�
    private String nonce_str;//闅忔満瀛楃涓�
    private String sign;//绛惧悕
    private String result_code;//涓氬姟缁撴灉
    private String err_code;//閿欒浠ｇ爜
    private String err_code_des;//閿欒浠ｇ爜鎻忚堪
    private String trade_type;//浜ゆ槗绫诲瀷
    private String prepay_id;//棰勬敮浠樹氦鏄撲細璇濇爣璇�
    private String code_url;//浜岀淮鐮侀摼鎺�

    /**
     * 杩斿洖鐘舵�佺爜
     *
     * @return
     */
    public String getReturn_code() {
        return this.map.get("return_code").toString();
    }

    /**
     * 杩斿洖淇℃伅
     *
     * @return
     */
    public String getReturn_msg() {
        return this.map.get("return_msg").toString();
    }

    /**
     * 褰搑eturn_code涓篠UCCESS鏃惰繑鍥烇細鍏紬璐﹀彿ID
     *
     * @return
     */
    public String getAppid() {
        return this.map.get("appid").toString();
    }

    /**
     * 褰搑eturn_code涓篠UCCESS鏃惰繑鍥烇細鍟嗘埛鍙�
     *
     * @return
     */
    public String getMch_id() {
        return this.map.get("mch_id").toString();
    }

    /**
     * 褰搑eturn_code涓篠UCCESS鏃惰繑鍥烇細瀛愬晢鎴峰叕浼楄处鍙稩D
     *
     * @return
     */
    public String getSub_appid() {
        return this.map.get("sub_appid").toString();
    }

    /**
     * 褰搑eturn_code涓篠UCCESS鏃惰繑鍥烇細瀛愬晢鎴峰彿
     *
     * @return
     */
    public String getSub_mch_id() {
        return this.map.get("sub_mch_id").toString();
    }

    /**
     * 褰搑eturn_code涓篠UCCESS鏃惰繑鍥烇細璁惧鍙�
     *
     * @return
     */
    public String getDevice_info() {
        return this.map.get("device_info").toString();
    }

    /**
     * 褰搑eturn_code涓篠UCCESS鏃惰繑鍥烇細闅忔満瀛楃涓�
     *
     * @return
     */
    public String getNonce_str() {
        return this.map.get("nonce_str").toString();
    }

    /**
     * 褰搑eturn_code涓篠UCCESS鏃惰繑鍥烇細绛惧悕
     *
     * @return
     */
    public String getSign() {
        return this.map.get("sign").toString();
    }

    /**
     * 褰搑eturn_code涓篠UCCESS鏃惰繑鍥烇細涓氬姟缁撴灉
     *
     * @return
     */
    public String getResult_code() {
        return this.map.get("result_code").toString();
    }

    /**
     * 褰搑eturn_code涓篠UCCESS鏃惰繑鍥烇細閿欒浠ｇ爜
     * 鍙互鏍规嵁getErrorMsg(String errCode)  鏉ヨ幏鍙栭敊璇俊鎭��
     *
     * @return
     */
    public String getErr_code() {
        return this.map.get("err_code").toString();
    }

    /**
     * 褰搑eturn_code涓篠UCCESS鏃惰繑鍥烇細閿欒浠ｇ爜鎻忚堪
     *
     * @return
     */
    public String getErr_code_des() {
        return this.map.get("err_code_des").toString();
    }

    /**
     * 褰搑eturn_code 鍜宺esult_code閮戒负SUCCESS鏃惰繑鍥烇細浜ゆ槗绫诲瀷
     *
     * @return
     */
    public String getTrade_type() {
        return this.map.get("trade_type").toString();
    }

    /**
     * 褰搑eturn_code 鍜宺esult_code閮戒负SUCCESS鏃惰繑鍥烇細棰勬敮浠樹氦鏄撲細璇濇爣璇�
     *
     * @return
     */
    public String getPrepay_id() {
        return this.map.get("prepay_id").toString();
    }

    /**
     * 褰搑eturn_code 鍜宺esult_code閮戒负SUCCESS鏃惰繑鍥烇細浜岀淮鐮侀摼鎺�
     *
     * @return
     */
    public String getCode_url() {
        return this.map.get("code_url").toString();
    }

    /**
     * 鏍规嵁閿欒缂栫爜杩斿洖閿欒淇℃伅
     *
     * @param errCode
     * @return
     */
    public String getErrorMsg(String errCode) {
        return this.errorMap.get(errCode);
    }

    //閿欒淇℃伅
    public final String NOAUTH = "鍟嗘埛鏃犳鎺ュ彛鏉冮檺";
    public final String NOTENOUGH = " 浣欓涓嶈冻";
    public final String ORDERPAID = " 鍟嗘埛璁㈠崟宸叉敮浠�";
    public final String ORDERCLOSED = "璁㈠崟宸插叧闂�";
    public final String SYSTEMERROR = "绯荤粺閿欒";
    public final String APPID_NOT_EXIST = "APPID涓嶅瓨鍦�";
    public final String MCHID_NOT_EXIST = "MCHID涓嶅瓨鍦�";
    public final String APPID_MCHID_NOT_MATCH = "appid鍜宮ch_id涓嶅尮閰�";
    public final String LACK_PARAMS = "缂哄皯鍙傛暟";
    public final String OUT_TRADE_NO_USED = "鍟嗘埛璁㈠崟鍙烽噸澶�";
    public final String SIGNERROR = "绛惧悕閿欒";
    public final String XML_FORMAT_ERROR = "XML鏍煎紡閿欒";
    public final String REQUIRE_POST_METHOD = "璇蜂娇鐢╬ost鏂规硶";
    public final String POST_DATA_EMPTY = "post鏁版嵁涓虹┖";
    public final String NOT_UTF8 = "缂栫爜鏍煎紡閿欒 ";


    /**
     * 杩斿洖鎺ュ彛鑾峰彇鍒癱ode_url鐨勪簩缁寸爜鍥剧墖image娴�
     * @param width 瀹藉害
     * @param height 楂樺害
     * @return
     * @throws IOException
     * @throws WriterException
     */
    public BufferedImage getBufImg(int width,int height)
            throws IOException, WriterException {
        if(null==this.getCode_url()||"".equals(this.getCode_url())) {
            throw new NullPointerException("璇锋眰寰俊鎺ュ彛鍑洪敊锛屾病鏈夎幏鍙栧埌code_url銆�");
        }
            // 浜岀淮鐮佺殑鍥剧墖鏍煎紡
            String format = "png";
            Hashtable<EncodeHintType, String> hints =
                    new Hashtable<EncodeHintType, String>();
            // 鍐呭鎵�浣跨敤缂栫爜
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(this.getCode_url(), BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
       /* if (!ImageIO.write(image, format, new File("d:" + "csyor.com.png"))) {
            throw new IOException("Could not write an image of format " +
                    format + " to " );
        }*/
            return image;
    }

}
