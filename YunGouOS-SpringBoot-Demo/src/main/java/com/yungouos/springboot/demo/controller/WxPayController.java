package com.yungouos.springboot.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yungouos.springboot.demo.common.ApiResponse;
import com.yungouos.springboot.demo.service.wxpay.WxPayService;

import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/api/wxpay")
public class WxPayController {

	@Resource
	private WxPayService wxPayService;

	@RequestMapping("/nativePay")
	@ResponseBody
	public JSONObject nativePay(@RequestParam Map<String, String> data) {
		JSONObject response = ApiResponse.init();
		try {
			String body = data.get("body");
			String money = data.get("money");

			if (StrUtil.isBlank(body)) {
				response = ApiResponse.fail("body is not null");
				return response;
			}
			if (StrUtil.isBlank(money)) {
				response = ApiResponse.fail("money is not null");
				return response;
			}
			Map<String, Object> map = wxPayService.nativePay(body, money);

			response = ApiResponse.success("下单成功", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
