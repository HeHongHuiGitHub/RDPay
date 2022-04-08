import com.alibaba.fastjson.JSONObject;
import com.yungouos.pay.entity.*;
import com.yungouos.pay.wxpay.WxPay;

/**
 * 微信支付调用演示
 *
 * @author YunGouOS技术部-029
 */
public class WxPayTest {

    public static void main(String[] args) {

        String result;
        String mchId = "1529637931";
        String key = "499F61DB734C4BF39792A098C44FA80A";
        // 收银台支付结束后返回地址
        String returnUrl = "http://www.baidu.com";
        try {

            /**
             * 付款码支付（被扫）
             */
            CodePayBiz codePayBiz = WxPay.codePay(System.currentTimeMillis() + "", "0.01", mchId, "测试", "134681285892396042", null, null, null, null, null, null, null, key);
            System.out.println("付款码支付结果：" + codePayBiz.toString());

            /**
             * 扫码支付 返回二维码连接
             */
            result = WxPay.nativePay(System.currentTimeMillis() + "", "1", mchId, "测试", null, null, null, null, null, null, null, null, key);
            System.out.println("扫码支付 结果：" + result);

            /**
             * 公众号支付 返回JSSDK需要的jspackage
             */
            String jspackage = WxPay.jsapiPay(System.currentTimeMillis() + "", "1", mchId, "测试", "o-_-itxeWVTRnl-iGT_JJ-t3kpxU", null, null, null, null, null, null, null, key);
            System.out.println("公众号支付结果：" + jspackage);

            /**
             * 收银台支付 返回收银台支付地址，跳转到该地址即可
             */
            String cashierPayUrl = WxPay.cashierPay(System.currentTimeMillis() + "", "1", mchId, "测试收银台支付", null, null, null, null, null, null, null, key);
            System.out.println("收银台支付结果：" + cashierPayUrl);

            /**
             * 小程序支付 不是真正的下单，组装参数。拿到参数后使用小程序的前端将参数传递给支付收银小程序
             */
            JSONObject minAppPay = WxPay.minAppPay(System.currentTimeMillis() + "", "0.01", mchId, "小程序支付演示", "海底捞", null, null, null, null, null, null, key);
            System.out.println("小程序支付结果：" + minAppPay.toJSONString());

            /**
             * 小程序支付，真正的下单，返回小程序支付所需参数
             */
            minAppPay = WxPay.minAppPaySend("o-_-itxeWVTRnl-iGT_JJ-t3kpxU", System.currentTimeMillis() + "", "0.01", mchId, "小程序支付演示", null, null, null, null, null, null, key);
            System.out.println("小程序支付结果：" + minAppPay.toJSONString());

            /**
             * 微信刷脸支付
             */
            FacePayBiz facePayBiz = WxPay.facePay(System.currentTimeMillis() + "", "0.01", mchId, "人脸支付测试", "o-_-itxeWVTRnl-iGT_JJ-t3kpxU", "人脸特征码", null, null, null, null, null, null, key);
            System.out.println("刷脸支付结果：" + facePayBiz);

            /**
             * 微信刷脸支付SDK模式凭证
             */
            FacePayAuthInfoBiz facePayAuthInfo = WxPay.getFacePayAuthInfo(mchId, "门店ID", "门店名称", "刷脸支付信息", "设备ID", null, null, key);
            System.out.println("刷脸支付凭证：" + facePayAuthInfo);

            /**
             * 微信h5支付
             */
            String h5payResult = WxPay.H5Pay(System.currentTimeMillis() + "", "0.01", mchId, "H5支付测试，仅限企业", null, null, null, null, null, null, null, key);
            System.out.println("微信H5支付结果：" + h5payResult);

            /**
             * 微信APP支付
             */
            JSONObject appPayParams = WxPay.appPay("wx465856913462378a", System.currentTimeMillis() + "", "0.01", mchId, "APP支付测试，仅限企业", null, null, null, null, null, null, key);
            System.out.println("微信APP支付结果：" + appPayParams.toJSONString());


            /**
             * QQ小程序支付
             */
            String url = WxPay.qqPay("appId", "accessToken", System.currentTimeMillis() + "", "0.01", mchId, "H5支付测试，仅限企业", null, null, null, null, null, null, null, key);
            System.out.println("QQ小程序支付结果：" + url);

            /**
             * 查询刷卡支付结果
             */
            CodePayBiz codePayBiz2 = WxPay.getCodePayResult("1556267522899", mchId, key);
            System.out.println("微信刷卡支付结果：" + codePayBiz2.toString());

            /**
             * 订单退款
             */
            RefundOrder refundOrder = WxPay.orderRefund("1556267522899", mchId, "0.1", null, "退款描述", null, key);
            System.out.println("订单退款结果：" + refundOrder.toString());

            /**
             * 查询退款结果
             */
            RefundSearch refundSearch = WxPay.getRefundResult("R17200911248111", mchId, key);
            System.out.println("查询退款结果：" + refundSearch.toString());

            /**
             * 下载对账单 正常直接通过getUrl获取到excel地址到浏览器访问下载即可 也可以通过getList获取到对账单的数据流集成到业务系统中
             */
            WxDownloadBillBiz downloadBillBiz = WxPay.downloadBill(mchId, "2020-01-29", null, null, key);
            System.out.println("对账单excel地址：" + downloadBillBiz.getUrl());
            System.out.println("对账单数据：" + downloadBillBiz.getList().toString());
            System.out.println("对账单统计数据：" + downloadBillBiz.getTotal().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
