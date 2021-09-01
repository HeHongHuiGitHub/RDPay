import com.alibaba.fastjson.JSONObject;
import com.yungouos.pay.entity.WxOauthInfo;
import com.yungouos.pay.entity.WxWebLoginBiz;
import com.yungouos.pay.wxapi.WxApi;

/**
 * 
 * 微信开放API相关接口示例
 *
 * @author YunGouOS技术部-029
 *
 */
public class WxApiTest {

	public static void main(String[] args) {
		String mch_id = "1529637931";
		String key = "499F61DB734C4BF39792A098C44FA80A";
		String callback_url = "http://www.baidu.com";
		JSONObject params = new JSONObject();
		params.put("desc", "附加数据，授权结束后可以返回");
		params.put("test", "你可以组装任何你想临时存储的数据");

		/**
		 * 授权类型 mp-base：基础授权，不会有授权页面，用户无感知，可获取openid。
		 * 
		 * mp-info：详细授权，首次授权会弹出授权页面，可获取用户昵称、头像等信息。
		 * 
		 * open-url：微信PC端扫码登录url
		 */
		String type = "mp-base";
		String oauthUrl = WxApi.getWxOauthUrl(mch_id, callback_url, type, params, key);
		System.out.println(oauthUrl);

		String code = "9F6501CA055545259E20D2301EB3AFD9";
		WxOauthInfo wxOauthInfo = WxApi.getWxOauthInfo(mch_id, code, key);
		System.out.println(wxOauthInfo);

		WxWebLoginBiz wxWebLoginBiz = WxApi.getWebLogin(mch_id, callback_url, params, key);

		System.out.println(wxWebLoginBiz.toString());
	}
}
