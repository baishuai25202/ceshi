package com.example.demo.controller;

import com.example.demo.util.UnionPayClient;
import com.example.demo.wangYin.AcpService;
import com.example.demo.wangYin.DemoBase;
import com.unionpay.acp.sdk.LogUtil;
import com.unionpay.acp.sdk.SDKConstants;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ProjectName: demo06292
 * @Package: com.example.demo.controller
 * @Author: ${白帅}
 * @Description: ${description}
 * @Date: 2019/7/2 19:42
 * @Version: 1.0
 */

@RestController
@RequestMapping("/union")
public class UnionpayController {

    /**
     * 支付
     *
     * @param orderNumber 商户订单号
     * @param txnAmt      金额（分）
     * @return
     * @throws Exception
     */
    @RequestMapping("/orderpay")
    public Map pay(HttpServletResponse response) throws Exception {

        String payhtml = UnionPayClient.pay("dyf2019070201", "100", "鞋");
        //生成自动跳转的form表单，直接返给前端，让前端做页面的跳转
        response.getWriter().write(payhtml);
        Map<Object, Object> map = new HashMap<>();
        map.put(200,"success");
        return map;
        }


    /**
     * 订单状态查询
     *
     * @param orderNumber
     * @param txnTime
     * @return
     * @throws Exception
     */
    @RequestMapping("/orderquery")
    public Map query(@RequestParam String orderNumber,
                                  @RequestParam String txnTime) throws Exception {

        //参数限制逻辑
        Map<Object, Object> map = new HashMap<>();
        map.put(200,"success");
        Map<String, String> rspData = UnionPayClient.query(orderNumber, txnTime);

        //返回参数处理
        if (!rspData.isEmpty()) {
            //验证签名
            if (AcpService.validate(rspData, DemoBase.encoding_UTF8)) {
                LogUtil.writeLog("验证签名成功");
                if ("00".equals(rspData.get("respCode"))) {//如果查询交易成功
                    //处理被查询交易的应答码逻辑
                    String origRespCode = rspData.get("origRespCode");
                    if ("00".equals(origRespCode)) {
                        System.out.println("交易成功了！！！！！！！！");

                        //交易成功，更新商户订单状态
                        //数据库修改成功后告诉前端，用户支付成功
                        return map;

                    } else if ("03".equals(origRespCode) ||
                            "04".equals(origRespCode) ||
                            "05".equals(origRespCode)) {
                        //需再次发起交易状态查询交易
                    } else {
                        //其他应答码为失败请排查原因
                    }
                } else {
                    //查询交易本身失败，或者未查到原交易，检查查询交易报文要素
                }
            } else {
                LogUtil.writeErrorLog("验证签名失败");
                //检查验证签名失败的原因
            }
        } else {
            //未返回正确的http状态
            LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
        }
        return map;
    }

    @RequestMapping("/orderrefund")
    public Map refund(@RequestParam String refundOrderId,
                                   @RequestParam String txnAmt,
                                   @RequestParam String queryId) {
        Map<Object, Object> map = new HashMap<>();
        map.put(200,"success");

        Map<String, String> rspData = UnionPayClient.refund(refundOrderId, txnAmt, queryId);

        /**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
        //应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
        if (!rspData.isEmpty()) {
            if (AcpService.validate(rspData, DemoBase.encoding_UTF8)) {
                LogUtil.writeLog("验证签名成功");
                String respCode = rspData.get("respCode");
                if ("00".equals(respCode)) {

                    //交易已受理，等待接收后台通知更新订单状态,也可以主动发起 查询交易确定交易状态。
                   return map;

                } else if ("03".equals(respCode) ||
                        "04".equals(respCode) ||
                        "05".equals(respCode)) {
                    //后续需发起交易状态查询交易确定交易状态
                } else {
                    //其他应答码为失败请排查原因
                }
            } else {
                LogUtil.writeErrorLog("验证签名失败");
            }
        } else {
            //未返回正确的http状态
            LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
        }

        return map;
    }

    /**
     * 后台异步通知路径
     * @param req
     * @return
     */
    @RequestMapping("/notifyurl")
    public ResponseEntity<Object> back(HttpServletRequest req){

        LogUtil.writeLog("BackRcvResponse接收后台通知开始");
        String encoding = req.getParameter(SDKConstants.param_encoding);

        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(req);

        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {

            Iterator<Map.Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap<String, String>(reqParam.size());

            while (it.hasNext()) {

                Map.Entry<String, String> e = it.next();
                String key = (String) e.getKey();
                String value = (String) e.getValue();
                valideData.put(key, value);
            }
        }

        //重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!AcpService.validate(valideData, encoding)) {
            LogUtil.writeLog("验证签名结果[失败].");
            //验签失败，需解决验签问题
        } else {
            LogUtil.writeLog("验证签名结果[成功].");

            //【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

            String orderId =valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
            String respCode = valideData.get("respCode");
            System.out.println(orderId+respCode);
        }

        LogUtil.writeLog("BackRcvResponse接收后台通知结束");

        //返回给银联服务器http 200  状态码
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    /**
     * 获取参数集合
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(
            final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                // 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                if (res.get(en) == null || "".equals(res.get(en))) {
                    // System.out.println("======为空的字段名===="+en);
                    res.remove(en);
                }
            }
        }
        return res;
    }



}



