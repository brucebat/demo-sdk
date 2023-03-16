package com.brucebat;

import com.brucebat.demo.tools.HttpUtils;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 测试使用的主类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2023/3/13 14:09
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello, world!");

        // 测试HttpClient的使用
        try {
            String response = HttpUtils.get("http://www.baidu.com", null);
            System.out.println("同步获取网页信息 : " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 异步调用处理，在使用CompleteFuture之后会发现启动之后main线程不会停止
        CompletableFuture<HttpResponse<String>> responseFuture = HttpUtils.getAsync("http://www.baidu.com", null);
        try {
            HttpResponse<String> response = responseFuture.get(5000, TimeUnit.SECONDS);
            System.out.println("异步获取网页信息 : " + response.body() + ", 请求状态码 : " + response.statusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
