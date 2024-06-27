//package com.yee.demo.spider;
//
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.core.map.MapUtil;
//import cn.hutool.poi.excel.ExcelUtil;
//import cn.hutool.poi.excel.ExcelWriter;
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Cookie;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.interactions.Actions;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Author Lobo
// * @Date 2024-06-25
// */
//
//public class AppStore {
//
//    private static String cookiePath = "/data/cookie.file";
//    private static WebDriver webDriver = null;
//
//    public static void main(String[] args) {
//
//
//        try {
//            init();
////            setCookie();
////            getDetail("https://app.diandian.com/app/ygguvu2lxlzr8a6/ios?market=1&country=18&id=1621211149");
////            getDetail("https://app.diandian.com/app/np2uguz8lxeokh7/ios?market=1&country=18");
////            getDetail("https://app.diandian.com/app/4xoupudl2007wul/ios?market=1&country=18");
//            fix();
//
////            List<Map<String, String>> appList = getAllApp();
//            List<Map<String, String>> appList = fix();
//
//            appList.stream().forEach(detailMap -> {
//                String link = MapUtil.getStr(detailMap, "href");
//                try {
//                    System.out.println("rank:" + MapUtil.getStr(detailMap, "rank"));
//                    detailMap.putAll(getDetail(link));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//
//            System.out.println(JSON.toJSONString(appList));
//
//            writeExcel(appList);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            webDriver.close();
//            webDriver.quit();
//        }
//
//    }
//
//    private static List<Map<String, String>> fix() {
//        List<String> detailList = Lists.newArrayList(
//                "https://app.diandian.com/app/6jupurjq55o7qfw/ios?market=1&country=18",
//                "https://app.diandian.com/app/ygguvuk9q0wk6b6/ios?market=1&country=18",
//                "https://app.diandian.com/app/yo8uvu61d0glla9/ios?market=1&country=18",
//                "https://app.diandian.com/app/n31uru36kw5kzhg/ios?market=1&country=18",
//                "https://app.diandian.com/app/492uduzm6p6josj/ios?market=1&country=18",
//                "https://app.diandian.com/app/njzumuz6z06rgcp/ios?market=1&country=18"
//        );
//
//        List<Map<String, String>> appList = Lists.newArrayList();
//        for (String detail : detailList) {
//            Map<String, String> map = Maps.newHashMap();
//            map.put("href", detail);
//            appList.add(map);
//        }
//        return appList;
//
//    }
//
//    private static void writeExcel(List<Map<String, String>> appList) {
//
//        ExcelWriter writer = ExcelUtil.getWriter("D://" + "AppStore" + DateUtil.format(new Date(), "yyyyMMddSSS") + ".xlsx");
//        writer.addHeaderAlias("href", "点点URL");
//        writer.addHeaderAlias("rank", "排名");
//        writer.addHeaderAlias("appName", "APP名称");
//        writer.addHeaderAlias("packageName", "Bundle ID");
//        writer.addHeaderAlias("score", "评分");
//        writer.addHeaderAlias("scoreNum", "评论数");
//        writer.addHeaderAlias("dev", "开发者");
//        writer.addHeaderAlias("company", "供应商");
//        writer.addHeaderAlias("", "联系方式");
//        writer.addHeaderAlias("website", "网址");
//        writer.addHeaderAlias("desc", "描述");
//        writer.setColumnWidth(0, 50);
//        writer.setColumnWidth(1, 50);
//        writer.setColumnWidth(2, 50);
//        writer.setColumnWidth(3, 50);
//        writer.setColumnWidth(4, 50);
//        writer.setColumnWidth(5, 50);
//        writer.setColumnWidth(6, 50);
//        writer.setColumnWidth(7, 50);
//        writer.setColumnWidth(8, 50);
//        writer.setColumnWidth(9, 50);
//        writer.setColumnWidth(10, 50);
//
//        writer.write(appList, true);
//        writer.flush();
//        writer.close();
//
//
//    }
//
//    private static void init() {
//        System.getProperties().setProperty("webdriver.chrome.driver", "/data/chromedriver.exe");
//        ChromeOptions chromeOptions = new ChromeOptions();
//
//        chromeOptions.addArguments("--no-sandbox");//禁用沙箱
//        chromeOptions.addArguments("--disable-dev-shm-usage");//禁用开发者shm
////        chromeOptions.addArguments("--headless"); //无头浏览器，这样不会打开浏览器窗口
////        chromeOptions.addArguments("--disable-gpu");
////        chromeOptions.addArguments("--disable-features=NetworkService");
////        chromeOptions.addArguments("--window-size=1920x1080");
////        chromeOptions.addArguments("--disable-features=VizDisplayCompositor");
//        webDriver = new ChromeDriver();
//        webDriver = new ChromeDriver(chromeOptions);
//        webDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
//        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//
//    }
//
//    private static List<Cookie> login() {
//        try {
//            String url = "https://app.diandian.com/login";
//            webDriver.get(url);
//            Thread.sleep(10000);
//            Set<Cookie> cookies = webDriver.manage().getCookies();
//            System.out.println(cookies);
//            FileUtil.writeLines(cookies, cookiePath, "UTF-8");
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(cookiePath));
//            objectOutputStream.writeObject(cookies);
//            return Lists.newArrayList(cookies);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    private static List<Cookie> getCookie() throws Exception {
//        if (!FileUtil.exist(cookiePath)) {
//            return login();
//        }
//
//        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(cookiePath));
//        HashSet<Cookie> cookies = (HashSet<Cookie>) objectInputStream.readObject();
//        System.out.println(cookies);
//        return Lists.newArrayList(cookies);
//    }
//
//    private static void setCookie() throws Exception {
//        List<Cookie> cookies = getCookie();
//        String url = "https://app.diandian.com/login";
//        webDriver.get(url);
//        for (Cookie c : cookies) {
//            webDriver.manage().addCookie(c);
//        }
//
//    }
//
//    private static List<Map<String, String>> getAllApp() throws Exception {
//        String link = "https://app.diandian.com/rank/ios/1-1-144-18-2?time=1719304822000&device=1&timetype=";
//        List<Map<String, String>> appList = Lists.newArrayList();
//        System.out.println("开始请求...link:" + link);
//        webDriver.get(link);
//        WebElement lastElement = webDriver.findElement(By.cssSelector(".el-row:last-child"));
//        Actions actions = new Actions(webDriver);
//        actions.moveToElement(lastElement).perform();
//        Thread.sleep(20000L);
//        WebElement webElements = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div"));
//        List<WebElement> appElements = webElements.findElements(By.className("dd-app"));
//
//        int rank = 1;
//        for (WebElement appElement : appElements) {
//            WebElement ahref = appElement.findElements(By.tagName("a")).get(0);
//            String rankStr = String.valueOf(rank++);
//            String href = ahref.getAttribute("href");
//            System.out.println("rank::::" + rank + "==========" + href);
//
//            Map<String, String> deatilMap = Maps.newHashMap();
//            deatilMap.put("href", href);
//            deatilMap.put("rank", rankStr);
//            appList.add(deatilMap);
//        }
//
//
//        return appList;
//    }
//
//
//    private static Map<String, String> getDetail(String link) throws Exception {
//        Map<String, String> detailMap = Maps.newHashMap();
//        System.out.println("开始请求...link:" + link);
//        webDriver.get(link);
//        Thread.sleep(3000L);
//        WebElement element = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[1]/div/div[6]/div[1]/div[2]/div[1]/div[1]/div/div"));
//        String appName = element.getText();
//        detailMap.put("appName", appName);
//
//
//        try {
//            element = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[1]/div/div[6]/div[2]/div[2]/div[1]/a"));
//            String score = element.getText();
//            detailMap.put("score", score);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            element = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[1]/div/div[6]/div[2]/div[2]/a"));
//            String scoreNum = element.getText();
//            detailMap.put("scoreNum", scoreNum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            element = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div/div/div/div[1]/div[3]/p"));
//            String desc = element.getText();
//            detailMap.put("desc", desc);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String companyPath = "/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div/div/div/div[2]/div[2]/div[3]/div[2]/p";
//        String websitexPath = "/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div/div/div/div[2]/div[2]/div[16]/div[2]/a";
//        String packageNamexPath = "/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div/div/div/div[2]/div[2]/div[6]/div[2]/p";
//        try {
//            element = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div/div/div/div[2]/div[2]/div[2]/div[2]/a"));
//            String dev = element.getText();
//            detailMap.put("dev", dev);
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                element = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div/div/div/div[2]/div[3]/div[2]/div[2]/a"));
//                String dev = element.getText();
//                detailMap.put("dev", dev);
//                companyPath = "/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div/div/div/div[2]/div[3]/div[3]/div[2]/p";
//                websitexPath = "/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div/div/div/div[2]/div[3]/div[16]/div[2]/a/span";
//                packageNamexPath = "/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div/div/div/div[2]/div[3]/div[6]/div[2]/p";
//            } catch (Exception e1) {
//                e1.printStackTrace();
//
//            }
//        }
//        try {
//            element = webDriver.findElement(By.xpath(companyPath));
//            String company = element.getText();
//            detailMap.put("company", company);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            element = webDriver.findElement(By.xpath(websitexPath));
//            String website = element.getText();
//            detailMap.put("website", website);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            element = webDriver.findElement(By.xpath(packageNamexPath));
//            String packageName = element.getText();
//            detailMap.put("packageName", packageName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("detailMap:" + JSON.toJSONString(detailMap));
//        return detailMap;
//    }
//}
