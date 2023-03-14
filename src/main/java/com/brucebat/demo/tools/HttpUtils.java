package com.brucebat.demo.tools;

import com.brucebat.demo.dto.exception.HttpException;
import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

/**
 * 使用jdk自带的httpClient来完成对于http接口的调用
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2023/3/13 17:13
 */
public class HttpUtils {

    private static final HttpClient httpClient;

    private static final int SC_OK = 200;

    static {
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10)).build();
    }

    /**
     * 获取请求参数
     *
     * @param url     待处理请求路径
     * @param headers 待处理请求头
     * @return 处理完成结果
     * @throws IOException          IO异常
     * @throws InterruptedException 中断异常
     */
    public static String get(String url, Map<String, String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(URI.create(url)).GET();
        return sendWithHeaders(headers, requestBuilder);
    }

    /**
     * 发起post请求
     *
     * @param url        待处理请求地址
     * @param headers    待处理请求头
     * @param bodyString 带请求body体
     * @return 调用完成返回结果
     * @throws IOException          IO异常
     * @throws InterruptedException 中断异常
     */
    public static String post(String url, Map<String, String> headers, String bodyString) throws IOException, InterruptedException {
        HttpRequest.Builder reqeustBuilder = HttpRequest.newBuilder(URI.create(url)).POST(HttpRequest.BodyPublishers.ofString(bodyString));
        return sendWithHeaders(headers, reqeustBuilder);
    }

    /**
     * 包含header的http请求发送（同步）
     *
     * @param headers        待处理请求头
     * @param requestBuilder 请求创建器
     * @return 调用完成返回结果
     * @throws IOException          IO异常
     * @throws InterruptedException 中断异常
     */
    private static String sendWithHeaders(Map<String, String> headers, HttpRequest.Builder requestBuilder) throws IOException, InterruptedException {
        if (MapUtils.isNotEmpty(headers)) {
            for (String key : headers.keySet()) {
                requestBuilder.header(key, headers.get(key));
            }
        }
        HttpRequest request = requestBuilder.build();
        // 可以看到这里的响应体是可以有开发人员自定义实现的
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (SC_OK != response.statusCode()) {
            throw new HttpException("http_" + response.statusCode(), "http调用发生异常");
        }
        return response.body();
    }
}
