package com.chongu.weixin;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.security.MessageDigest;
import java.util.*;

/**
 * 寰俊鏀粯鎺ュ彛鍙傛暟灏佽瀹炵幇绫伙紱
 * 鏍规嵁锛歨ttps://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
 *  鎴� 锛歨ttps://pay.weixin.qq.com/wiki/doc/api/native_sl.php?chapter=9_1
 *  鏉ュ喅瀹氬摢浜涘弬鏁颁负蹇呭～銆�
 * @author sunzhicheng
 * Created by cheng on 2016-5-25.
 */
public class WeixinPayApiImpl {
    private Map<String, Object> map = null;
    private List<String> requiredList = null;
    private final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private String key = "";
    /**
     * 鑾峰彇寰俊鎺ュ彛鍦板潃
     * @return
     */
    public String getUrl() {
        return url;
    }


    //姝ゅ彉閲忔病鏈夊疄闄呯殑浣跨敤鍒帮紝鍗曠函涓轰簡缂栫爜鏃舵柟渚垮畾浣嶅拰鏄惁婕忔帀鍙傛暟鑰屽瓨鍦�
    private String appid = null;//1   鍏紬璐﹀彿ID
    private String mch_id = null;//1   鍟嗘埛鍙�
    private String sub_appid = null;//瀛愬晢鎴峰叕浼楄处鍙稩D
    private String sub_mch_id = null;//1   瀛愬晢鎴峰彿
    private String device_info = null;//璁惧鍙�
    private String nonce_str = null;//1   闅忔満瀛楃涓�
    private String sign = null;//1   绛惧悕
    private String body = null;//1   鍟嗗搧鎻忚堪
    private String detail = null;//鍟嗗搧璇︽儏
    private String attach = null;//闄勫姞鏁版嵁
    private String out_trade_no = null;//1鍟嗘埛璁㈠崟鍙�
    private String fee_type = null;//璐у竵绫诲瀷
    private Integer total_fee = null;//1鎬婚噾棰�
    private String spbill_create_ip = null;//1缁堢IP
    private String time_start = null;//浜ゆ槗璧峰鏃堕棿
    private String time_expire = null;//浜ゆ槗缁撴潫鏃堕棿
    private String goods_tag = null;//鍟嗗搧鏍囪
    private String notify_url = null;//1閫氱煡鍦板潃
    private String trade_type = null;//1浜ゆ槗绫诲瀷
    private String product_id = null;//鍟嗗搧ID
    private String limit_pay = null;//鎸囧畾鏀粯鏂瑰紡
    private String openid = null;//鐢ㄦ埛鏍囪瘑
    private String sub_openid = null;//鐢ㄦ埛瀛愭爣璇�

    /**
     * 蹇呭～ 鍏紬璐﹀彿ID
     * 渚嬶細wxd678efh567hg6787
     * 璇存槑锛氬井淇″垎閰嶇殑鍏紬璐﹀彿ID锛堜紒涓氬彿corpid鍗充负姝ppId锛�
     *
     * @param appid
     */
    public void setAppid(String appid) {
        if(null!=appid){
            this.map.put("appid", appid);
        }
        this.appid = appid;
    }
    /**
     * 蹇呭～ 鍟嗘埛鍙�
     * 渚嬶細1230000109
     * 璇存槑锛氬井淇℃敮浠樺垎閰嶇殑鍟嗘埛鍙�
     *
     * @param mch_id
     */
    public void setMch_id(String mch_id) {
        if(null!=mch_id){
            this.map.put("mch_id", mch_id);
        }
        this.mch_id = mch_id;
    }
    /**
     * 瀛愬晢鎴峰叕浼楄处鍙稩D
     * 渚嬶細wxd678efh567hg6787
     * 璇存槑锛氬井淇″垎閰嶇殑瀛愬晢鎴峰叕浼楄处鍙稩D锛屽闇�鍦ㄦ敮浠樺畬鎴愬悗鑾峰彇sub_openid鍒欐鍙傛暟蹇呬紶銆�
     *
     * @param sub_appid
     */
    public void setSub_appid(String sub_appid) {
        if(null!=sub_appid){
            this.map.put("sub_appid", sub_appid);
        }
        this.sub_appid = sub_appid;
    }
    /**
     * 蹇呭～ 瀛愬晢鎴峰彿
     * 渚嬶細1230000109
     * 璇存槑锛氬井淇℃敮浠樺垎閰嶇殑瀛愬晢鎴峰彿
     *
     * @param sub_mch_id
     */
    public void setSub_mch_id(String sub_mch_id) {
        if(null!=sub_mch_id){
            this.map.put("sub_mch_id", sub_mch_id);
        }
        this.sub_mch_id = sub_mch_id;
    }
    /**
     * 璁惧鍙�
     * 渚嬶細013467007045764
     * 璇存槑锛氱粓绔澶囧彿(闂ㄥ簵鍙锋垨鏀堕摱璁惧ID)锛屾敞鎰忥細PC缃戦〉鎴栧叕浼楀彿鍐呮敮浠樿浼�"WEB"
     *
     * @param device_info
     */
    public void setDevice_info(String device_info) {
        if(null!=device_info){
            this.map.put("device_info", device_info);
        }
        this.device_info = device_info;
    }
    /**
     * 蹇呭～ 闅忔満瀛楃涓�
     * 渚嬶細5K8264ILTKCH16CQ2502SI8ZNMTM67VS
     * 璇存槑锛氶殢鏈哄瓧绗︿覆锛屼笉闀夸簬32浣嶃�俬ttps://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_3
     * 榛樿鍊硷細30浣嶉暱搴︾敱A-Z,0-9缁勬垚鐨勯殢鏈轰覆
     * @param nonce_str
     */
    public void setNonce_str(String nonce_str) {
        if(null!=nonce_str){
            this.map.put("nonce_str", nonce_str);
        }
        this.nonce_str = nonce_str;
    }
    /**
     * 蹇呭～ 绛惧悕銆愭棤闇�鎵嬪姩璁惧�硷紝璋冪敤鏃朵細鑷姩璁＄畻骞惰祴鍊笺��
     * 渚嬶細C380BEC2BFD727A4B6845133519F3AD6
     * 璇存槑锛氱鍚嶏紝璇﹁ https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_3
     * 榛樿鍊硷細璋冪敤鏃舵牴鎹浉搴旂殑绠楁硶鑷姩杩涜璁＄畻鎴栧鍒�
     * @param sign
     */
    public void setSign(String sign) {
        if(null!=sign){
            this.map.put("sign", sign);
        }
        this.sign = sign;
    }
    /**
     * 蹇呭～ 鍟嗗搧鎻忚堪
     * 渚嬶細Ipad mini  16G  鐧借壊
     * 璇存槑:鍟嗗搧鎴栨敮浠樺崟绠�瑕佹弿杩�
     *
     * @param body
     */
    public void setBody(String body) {
        if(null!=body){
            this.map.put("body", body);
        }
        this.body = body;
    }
    /**
     * 鍟嗗搧璇︽儏
     * 渚嬶細Ipad mini  16G  鐧借壊
     * 璇存槑:鍟嗗搧鍚嶇О鏄庣粏鍒楄〃
     *
     * @param detail
     */
    public void setDetail(String detail) {
        if(null!=detail){
            this.map.put("detail", detail);
        }
        this.detail = detail;
    }
    /**
     * 闄勫姞鏁版嵁
     * 渚嬶細娣卞湷鍒嗗簵
     * 璇存槑锛氶檮鍔犳暟鎹紝鍦ㄦ煡璇PI鍜屾敮浠橀�氱煡涓師鏍疯繑鍥烇紝璇ュ瓧娈典富瑕佺敤浜庡晢鎴锋惡甯﹁鍗曠殑鑷畾涔夋暟鎹�
     *
     * @param attach
     */
    public void setAttach(String attach) {
        if(null!=attach){
            this.map.put("attach", attach);
        }
        this.attach = attach;
    }
    /**
     * 蹇呭～ 鍟嗘埛璁㈠崟鍙�
     * 渚嬶細20150806125346
     * 璇存槑锛氬晢鎴风郴缁熷唴閮ㄧ殑璁㈠崟鍙�,32涓瓧绗﹀唴銆佸彲鍖呭惈瀛楁瘝,銆�
     * 鍏朵粬璇存槑 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     *
     * @param out_trade_no
     */
    public void setOut_trade_no(String out_trade_no) {
        if(null!=out_trade_no){
            this.map.put("out_trade_no", out_trade_no);
        }
        this.out_trade_no = out_trade_no;
    }
    /**
     * 璐у竵绫诲瀷  榛樿涓猴細CNY 浜烘皯甯�
     * 渚嬶細CNY
     * 璇存槑锛氱鍚圛SO 4217鏍囧噯鐨勪笁浣嶅瓧姣嶄唬鐮侊紝榛樿浜烘皯甯侊細CNY
     * 鍏朵粬璇﹁ https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     *
     * @param fee_type
     */
    public void setFee_type(String fee_type) {
        if(null!=fee_type){
            this.map.put("fee_type", fee_type);
        }
        this.fee_type = fee_type;
    }
    /**
     * 蹇呭～ 鎬婚噾棰濓紝蹇呴』澶т簬0 鍗曚綅锛氬垎
     * 渚嬶細888銆�8鍏�8瑙�8鍒嗐��
     * 璇存槑锛氳鍗曟�婚噾棰濓紝鍗曚綅涓哄垎
     *
     * @param total_fee
     */
    public void setTotal_fee(Integer total_fee) {
        if(null!=total_fee&&total_fee>0){
            this.map.put("total_fee", total_fee);
        }
        this.total_fee = total_fee;
    }
    /**
     * 蹇呭～ 缁堢IP
     * 渚嬶細	123.12.12.123
     * 璇存槑锛欰PP鍜岀綉椤垫敮浠樻彁浜ょ敤鎴风ip锛孨ative鏀粯濉皟鐢ㄥ井淇℃敮浠楢PI鐨勬満鍣↖P銆�
     * 榛樿鍊硷細鏈満鍐呯綉IP
     * @param spbill_create_ip
     */
    public void setSpbill_create_ip(String spbill_create_ip) {
        if(null!=spbill_create_ip){
            this.map.put("spbill_create_ip", spbill_create_ip);
        }
        this.spbill_create_ip = spbill_create_ip;
    }
    /**
     * 浜ゆ槗璧峰鏃堕棿
     * 渚嬶細20091225091010
     * 璇存槑锛氳鍗曠敓鎴愭椂闂达紝鏍煎紡涓簓yyyMMddHHmmss锛屽2009骞�12鏈�25鏃�9鐐�10鍒�10绉掕〃绀轰负20091225091010銆�
     * 璇︾粏锛歨ttps://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     *
     * @param time_start
     */
    public void setTime_start(String time_start) {
        if(null!=time_start){
            this.map.put("time_start", time_start);
        }
        this.time_start = time_start;
    }
    /**
     * 浜ゆ槗缁撴潫鏃堕棿
     * 渚嬶細20091225091010
     * 璇存槑锛氳鍗曠敓鎴愭椂闂达紝鏍煎紡涓簓yyyMMddHHmmss锛屽2009骞�12鏈�25鏃�9鐐�10鍒�10绉掕〃绀轰负20091225091010銆�
     * 璇︾粏锛歨ttps://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     *
     * @param time_expire
     */
    public void setTime_expire(String time_expire) {
        if(null!=time_expire){
            this.map.put("time_expire", time_expire);
        }
        this.time_expire = time_expire;
    }
    /**
     * 鍟嗗搧鏍囪
     * 渚嬶細WXG
     * 璇存槑锛氬晢鍝佹爣璁帮紝浠ｉ噾鍒告垨绔嬪噺浼樻儬鍔熻兘鐨勫弬鏁�
     * 璇存槑璇﹁锛歨ttps://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_1
     *
     * @param goods_tag
     */
    public void setGoods_tag(String goods_tag) {
        if(null!=goods_tag){
            this.map.put("goods_tag", goods_tag);
        }
        this.goods_tag = goods_tag;
    }
    /**
     * 蹇呭～ 閫氱煡鍦板潃锛堝洖璋冨湴鍧�锛�
     * 渚嬶細http://www.weixin.qq.com/wxpay/pay.php
     * 璇存槑锛氭帴鏀跺井淇℃敮浠樺紓姝ラ�氱煡鍥炶皟鍦板潃锛岄�氱煡url蹇呴』涓虹洿鎺ュ彲璁块棶鐨剈rl锛屼笉鑳芥惡甯﹀弬鏁般��
     *
     * @param notify_url
     */
    public void setNotify_url(String notify_url) {
        if(null!=notify_url){
            this.map.put("notify_url", notify_url);
        }
        this.notify_url = notify_url;
    }
    /**
     * 蹇呭～ 浜ゆ槗绫诲瀷
     * 渚嬶細NATIVE
     * 璇存槑锛氬彇鍊煎涓嬶細JSAPI锛孨ATIVE锛孉PP
     * 璇︾粏璇存槑锛歨ttps://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     *
     * @param trade_type
     */
    public void setTrade_type(String trade_type) {
        if(null!=trade_type){
            this.map.put("trade_type", trade_type);
        }
        this.trade_type = trade_type;
    }
    /**
     * 鍟嗗搧ID
     * 渚嬶細12235413214070356458058
     * 璇存槑锛歵rade_type=NATIVE锛屾鍙傛暟蹇呬紶銆傛id涓轰簩缁寸爜涓寘鍚殑鍟嗗搧ID锛屽晢鎴疯嚜琛屽畾涔夈��
     *
     * @param product_id
     */
    public void setProduct_id(String product_id) {
        if(null!=product_id){
            this.map.put("product_id", product_id);
        }
        this.product_id = product_id;
    }
    /**
     * 鎸囧畾鏀粯鏂瑰紡
     * 渚嬶細no_credit
     * 璇存槑锛歯o_credit--鎸囧畾涓嶈兘浣跨敤淇＄敤鍗℃敮浠�
     *
     * @param limit_pay
     */
    public void setLimit_pay(String limit_pay) {
        if(null!=limit_pay){
            this.map.put("limit_pay", limit_pay);
        }
        this.limit_pay = limit_pay;
    }
    /**
     * 鐢ㄦ埛鏍囪瘑
     * 渚嬶細oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * 璇存槑锛歵rade_type=JSAPI锛屾鍙傛暟蹇呬紶锛岀敤鎴峰湪鍟嗘埛appid涓嬬殑鍞竴鏍囪瘑銆俹penid濡備綍鑾峰彇锛屽彲鍙傝�僪ttps://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_4
     * 浼佷笟鍙疯浣跨敤http://qydev.weixin.qq.com/wiki/index.php?title=OAuth%E9%AA%8C%E8%AF%81%E6%8E%A5%E5%8F%A3
     * 鑾峰彇浼佷笟鍙峰唴鎴愬憳userid锛屽啀璋冪敤http://qydev.weixin.qq.com/wiki/index.php?title=Userid%E4%B8%8Eopenid%E4%BA%92%E6%8D%A2%E6%8E%A5%E5%8F%A3
     * 杩涜杞崲
     *
     * @param openid
     */
    public void setOpenid(String openid) {
        if(null!=openid){
            this.map.put("openid", openid);
        }
        this.openid = openid;
    }
    /**
     * 鐢ㄦ埛瀛愭爣璇�
     * 渚嬶細oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * 璇存槑锛歵rade_type=JSAPI锛屾鍙傛暟蹇呬紶锛岀敤鎴峰湪鍟嗘埛appid涓嬬殑鍞竴鏍囪瘑銆俹penid濡備綍鑾峰彇锛屽彲鍙傝�僪ttps://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_4
     * 浼佷笟鍙疯浣跨敤http://qydev.weixin.qq.com/wiki/index.php?title=OAuth%E9%AA%8C%E8%AF%81%E6%8E%A5%E5%8F%A3
     * 鑾峰彇浼佷笟鍙峰唴鎴愬憳userid锛屽啀璋冪敤http://qydev.weixin.qq.com/wiki/index.php?title=Userid%E4%B8%8Eopenid%E4%BA%92%E6%8D%A2%E6%8E%A5%E5%8F%A3
     * 杩涜杞崲
     *
     * @param sub_openid
     */
    public void setSub_openid(String sub_openid) {
        if(null!=sub_openid) {
            this.map.put("sub_openid", sub_openid);
        }
        this.sub_openid = sub_openid;
    }

    /**
     * 灏嗕笉鑳戒负绌虹殑鍒楁寚瀹氬埌list涓苟杩斿洖杩欎釜list
     * @return
     */
    private List<String> requiredParam() {
        //瀹氫箟涓嶈兘涓虹┖鐨勫瓧娈�
        List<String> list = new ArrayList<String>();
        list.add("appid");
        list.add("mch_id");
        list.add("sub_mch_id");
        list.add("nonce_str");
        list.add("sign");
        list.add("body");
        list.add("out_trade_no");
        list.add("total_fee");
        list.add("spbill_create_ip");
        list.add("notify_url");
        list.add("trade_type");
        return list;
    }

    /**
     * appid,mch_id,nonce_str,sign,body,out_trade_no,total_fee,spbill_create_ip,notify_url,trade_type
     * 涓嶈兘涓虹┖鎴朜ULL锛岃瑙乻et鏂规硶璇存槑
     * 鎵�鏈夊睘鎬ч粯璁や负null
     * 鏂囨。鍦板潃锛歨ttps://pay.weixin.qq.com/wiki/doc/api/native_sl.php?chapter=9_1
     * @param key  涓哄晢鎴风殑key銆怉PI瀵嗗寵銆�
     */
    public WeixinPayApiImpl(String key) {
        super();
        this.key=key;
        map = new HashMap<String, Object>();
       List<String> tmpList = requiredParam();
        if (tmpList.size() > 0) {
            this.requiredList = tmpList;
        } else {
            this.requiredList = null;
        }
    }


    /**
     * 灏嗘墍鏈変笉涓簄ull鐨勫睘鎬у皝瑁呮垚map骞惰繑鍥�
     */
    public Map<String, Object> getMap() throws Exception {
        this.setDefaultValue();
        if(null!=this.requiredList) {
                for (String s : this.requiredList) {//妫�鏌ヤ负绌虹殑鍊兼槸鍚﹀繀濉�
                    if (!this.map.containsKey(s)) {//鍑虹幇鏈寘鍚殑蹇呭～椤�
                        throw new Exception(s + "--涓哄繀濉」銆傝妫�鏌ュ繀濉」锛�" + this.requiredList + " 鏄惁濉啓姝ｇ‘銆�");
                    }
                    if ("total_fee".equals(s)) {//閲戦
                        Object o =  this.map.get(s);
                        int total = (Integer) o;
                        if (total <= 0) {
                            throw new Exception(s + "--涓哄繀濉」銆備笖蹇呴』澶т簬0銆�");
                        }
                    }
                }
        }
        return this.map;
    }

    /**
     * 鑻mlRootElementName涓簄ill鍒欐病鏈塕oot鑺傜偣
     *
     * @param xmlRootElementName 鍙互涓簄ull
     * @return
     */
    public String getXml(String xmlRootElementName) throws Exception{
       Map<String, Object> tmap = getMap();
        StringBuffer sbf = null;
        sbf = new StringBuffer();
        if (null != xmlRootElementName) {
            sbf.append("<" + xmlRootElementName + ">");
        }
            for (String s : tmap.keySet()) {
                Object tmp = tmap.get(s);
                    sbf.append("<" + s + ">" + tmp + "</" + s + ">");
            }
        if (null != xmlRootElementName) {
            sbf.append("</" + xmlRootElementName + ">");
        }
        try {
            return new String(sbf.toString().getBytes(),"ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /***
     * MD5鍔犵爜 鐢熸垚32浣峬d5鐮�
     */
    private String string2MD5(String str){
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf).toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 寰俊鏀粯绛惧悕绠楁硶sign
     */
    private String getSign() {
        StringBuffer sb = new StringBuffer();
        String[] keyArr = (String[]) this.map.keySet().toArray(new String[this.map.keySet().size()]);//鑾峰彇map涓殑key杞负array
        Arrays.sort(keyArr);//瀵筧rray鎺掑簭
        for (int i = 0, size = keyArr.length; i < size; ++i) {
            if ("sign".equals(keyArr[i])) {
                continue;
            }
            sb.append(keyArr[i] + "=" + this.map.get(keyArr[i]) + "&");
        }
        sb.append("key=" + key);
        String sign = string2MD5(sb.toString());
        return sign;
    }

    /**
     * 鐢熸垚鐢盵A-Z,0-9]鐢熸垚鐨勯殢鏈哄瓧绗︿覆
     * @param length  娆茬敓鎴愮殑瀛楃涓查暱搴�
     * @return
     */
    private String getRandomString(int length){
        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i){
            int number = random.nextInt(2);
            long result = 0;

            switch(number){
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char)result));
                    break;
                case 1:

                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 鑾峰彇鏈満IP鍦板潃锛堝唴缃戯級
     * @return IP
     */
    private static String getLocalIp() {
        String ip = null;
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
                for (InterfaceAddress add : InterfaceAddress) {
                    InetAddress Ip = add.getAddress();
                    if (Ip != null && Ip instanceof Inet4Address) {
                        ip = Ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return ip;
    }


    /**
     * 璁剧疆榛樿鍊�
     */
    private void setDefaultValue(){
        if(null == this.map.get("nonce_str")){//闅忔満涓�
            this.setNonce_str(this.getRandomString(30));
        }
        if(null == this.map.get("spbill_create_ip")){//缁堢IP
            this.setSpbill_create_ip(this.getLocalIp());
        }
        if(null == this.map.get("sign")){//绛惧悕
            this.setSign(this.getSign());
        }
    }
}
