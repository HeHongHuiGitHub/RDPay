package com.yungouos.springboot.demo.service.order.impl;

import org.springframework.stereotype.Service;

import com.yungouos.springboot.demo.entity.Order;
import com.yungouos.springboot.demo.service.base.BaseService;
import com.yungouos.springboot.demo.service.order.OrderService;

import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class OrderServiceImpl extends BaseService<Order> implements OrderService {

	@Override
	public Order add(String body, String money) {
		Order order = null;
		try {
			order = new Order();
			order.setOrderNo(System.currentTimeMillis() + "");
			order.setBody(body);
			order.setMoney(money);
			order.setStatus(0);
			order.setAddTime(DateUtil.now());
			Integer i = super.save(order);
			if (i == null || i <= 0) {
				throw new Exception("订单保存失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public Order getOrderInfo(String orderNo) {
		Order order = null;
		try {
			Order orderWhere = new Order();
			orderWhere.setOrderNo(orderNo);
			order = super.selectOne(orderWhere);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return order;
	}

	@Override
	public boolean paySuccess(String orderNo, String payNo, String payTime) {
		try {
			Order order = getOrderInfo(orderNo);
			if (order == null) {
				throw new Exception("订单不存在");
			}
			if (order.getStatus().intValue() == 1) {
				return true;
			}

			Order orderData = new Order();
			orderData.setStatus(1);
			orderData.setPayNo(payNo);
			orderData.setPayTime(payTime);

			Example example = new Example(Order.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("orderNo", orderNo);
			super.updateSelective(orderData, example);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
