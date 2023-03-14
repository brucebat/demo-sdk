package com.brucebat;

import com.brucebat.demo.tools.HttpUtils;

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
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
