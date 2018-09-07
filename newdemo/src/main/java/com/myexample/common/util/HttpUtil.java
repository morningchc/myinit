//package com.myexample.util;
//
//import com.myexample.exception.BusinessException;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.config.SocketConfig;
//import org.apache.http.entity.ByteArrayEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.client.IdleConnectionEvictor;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by David on 2017/8/28.
// */
//public class HttpUtil {
//
//    private final static CloseableHttpClient httpclient;
//    static {
//        // 启用连接线程池，最大150个连接
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//        cm.setMaxTotal(150);
//        cm.setDefaultMaxPerRoute(cm.getMaxTotal());
//        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(5000).build();
//        cm.setDefaultSocketConfig(socketConfig);
//        // 默认超时    ConnectionRequestTimeout连接池超时     ConnectTimeout建立连接超时     SocketTimeout读取数据超时
//        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).setCookieSpec(null).build();
//        httpclient = HttpClients.custom().setDefaultRequestConfig(config).setConnectionManager(cm).build();
//
//        //空闲连接清理
//        IdleConnectionEvictor idleConnectionEvictor = new IdleConnectionEvictor(cm,60, TimeUnit.SECONDS,30,TimeUnit.SECONDS);
//        idleConnectionEvictor.start();
//    }
//
//    /**
//     * HTTP发送
//     * @param url 地址
//     * @param postcontent 发送报文
//     * @param charset 编码集
//     * @param headerMap 请求头信息
//     * @param timeout 超时时间
//     * @return 返回报文
//     */
//    public static String post(String url, String postcontent, String charset, Map<String, String> headerMap, int timeout) {
//        HttpPost httpPost = new HttpPost(url);
//        CloseableHttpResponse response = null;
//        String result = "";
//        try {
//            byte[] data = postcontent.getBytes(charset);
//
//            if (data != null) {
//                ByteArrayEntity byteEntity = new ByteArrayEntity(data);
//                httpPost.setEntity(byteEntity);
//            }
//            if (headerMap != null) {
//                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
//                    httpPost.addHeader(entry.getKey(), entry.getValue());
//                }
//            }
//
//            RequestConfig localConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(timeout).setCookieSpec(null).build();
//            httpPost.setConfig(localConfig);
//
//            response = httpclient.execute(httpPost);
//
//            if(response.getStatusLine().getStatusCode() != 200) {
//                throw new BusinessException("HTTP请求异常，异常代码为" + response.getStatusLine().getStatusCode());
//            }
//
//            byte[] rtnData = EntityUtils.toByteArray(response.getEntity());
//
//            result = new String(rtnData, charset);
//        } catch (Exception e) {
//            throw new BusinessException("发送HTTP请求异常"+e.getMessage());
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//}
