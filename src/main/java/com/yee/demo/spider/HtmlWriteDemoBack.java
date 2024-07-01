package com.yee.demo.spider;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yee
 * 2024/6/27 10:47
 */
public class HtmlWriteDemoBack {
    final static String localUri = "/Users/linyijun/Desktop/spider/";
    public static void main(String[] args) {
//        writeWebJSp();
        String htmlContent = read(null);
        processHtml(htmlContent);

    }

    private static HtmlDetailDTO processDetailUrl(Element td, HtmlDetailDTO dto){
        if (td.select("a").size() > 0) {
            Element a = td.select("a").first();
            String onclick = a.attr("onclick");
            String httpUrl = StrUtil.split(StrUtil.replace(onclick,"\"","'"),"'").get(1);
            String httpGetUrl = httpUrl.replace("../../jsp", "https://webapps.condusef.gob.mx/SIPRES/jsp");
            if(httpGetUrl.length() == 49){
                return dto;
            }
            return processDetail(httpGetUrl, dto);
//            }
        }
        return dto;
    }

    private static void processHtml(String htmlContent){
        List<HtmlDetailDTO> list = Lists.newArrayList();

        Document doc = Jsoup.parse(htmlContent);
        Elements trs = doc.select("table tr");
        trs.stream().forEach(tr ->{
            HtmlDetailDTO dto = new HtmlDetailDTO();
            Elements tds = tr.select("td");
            for (int i = 0; i < tds.size(); i++) {
                Element td = tds.get(i);
                String text = td.text();
                if(StringUtils.isEmpty(text)){
                    text = td.firstElementSibling().wholeText();
                }
                if(StringUtils.isEmpty(text)){
                    text = td.nextElementSibling().wholeText();
                }
                if (i==0){
                    dto.setClave_de_Registro(text);
                }
                else if (i==1){
                    dto.setDenominacion_Social(text);
                    dto = processDetailUrl(td, dto);
                }
                else if (i==2){
                    dto.setNombreCorto_o_comercial(text);
                    dto = processDetailUrl(td, dto);
                }
                else if (i==3){
                    dto.setEstatus(text);
                    dto = processDetailUrl(td, dto);
                }
                else if (i==4){
                    dto.setSector(text);
                    dto = processDetailUrl(td, dto);
                }
                else if (i==5){
                    dto = processDetailUrl(td, dto);
                    dto.setEstado(text);
                }
                else if (i==6){
                    dto = processDetailUrl(td, dto);
                    dto.setUltima_Seccion_Actualizada(text);
                }
            }
            list.add(dto);
            System.out.println(list.size());
//            if (list.size() == 80) {
//                EasyExcel.write("/Users/linyijun/Desktop/test.xlsx").sheet("模板").head(HtmlDetailDTO.class).doWrite(list);
                return;
//            }
        });
//        for (Element tr : trs) {
//            System.out.println("size:"+list.size());
//        }
//        System.out.println("========= 开始写excel =========");
//        EasyExcel.write("/Users/linyijun/Desktop/data_export.xlsx").sheet("模板").head(HtmlDetailDTO.class).doWrite(list);
    }



    private static HtmlDetailDTO processDetail(String url, HtmlDetailDTO dto) {
        try {
            // 使用Jsoup获取HTML文档
            Document document = Jsoup.connect(url).get();
            Elements rows = document.select("#div_generales .row");
            for (Element row : rows) {
                // 遍历row元素下的col-sm-*类的div元素
                Elements cols = row.select("div[class^='col-sm-']");
                for (Element col : cols) {
                    // 查找<b>标签并获取其文本内容
                    Element bTag = col.select("b").first();
                    String title = bTag.wholeText();
                    String wholeText = col.wholeText();
                    String[] split = title.split(" ");
                    String splitKey = title;
                    if (split.length > 1) {
                        splitKey = split[split.length-1];
                    }
                    String[] split1 = wholeText.split(splitKey);
//                    System.out.println("split:"+ split1[1]);
//
//                    Element hrTag = col.select("hr").first();
//                    String text="";
//                    if (hrTag != null) {
//                        StringBuilder textAfterHr = new StringBuilder();
//                        for (Node node : hrTag.siblingNodes()) {
//                            if (node instanceof org.jsoup.nodes.TextNode) {
//                                String value = ((TextNode) node).getWholeText().trim();
//                                // 如果节点是TextNode，则获取其文本并去除前后的空白字符
//                                textAfterHr.append(value);
//                            }
//                        }
//                    }
//
//                    if (StringUtils.isEmpty(text)&& hrTag!=null){
//                        StringBuilder textAfterHr = new StringBuilder();
//                        Element nextSibling = hrTag.nextElementSibling();
//                        while (nextSibling != null && !(nextSibling.tagName().equals("b") || nextSibling.tagName().equals("hr"))) {
//                            // 累加文本内容，包括子元素的文本
//                            textAfterHr.append(nextSibling.wholeText()).append(" ");
//                            nextSibling = nextSibling.nextElementSibling();
//                        }
//                        text = textAfterHr.toString().trim();
//                    }
//                    if (StringUtils.isEmpty(text) && hrTag!=null && hrTag.nextElementSibling()!=null){
//                        text = hrTag.nextElementSibling().wholeText();
//                    }
                    String text = split1[1];
                    dto = setValue(title.trim(), text.trim(), dto);

                    // 提取图片链接
                    Element imgElement = document.select("#div_generales #divlogo #logoins").first();
                    String imgSrc = imgElement != null ? imgElement.absUrl("src") : null;
                    if (imgSrc != null) {
                        dto.setLogo(imgSrc);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }

    private static HtmlDetailDTO setValue(String title, String text,HtmlDetailDTO dto){

        if (title.contains("Actualizados")){
            dto.setActualizados_el(text);
        }else if (title.equals("Nombre corto")){
            dto.setNombre_corto(text);
        }else if (title.equals("Estatus")){
            dto.setDetaialEstatus(text);
        }else if (title.equalsIgnoreCase("RFC")){
            dto.setRFC(text);
        }else if (title.contains("Registro")){
            dto.setDetaial_Clave_de_Registro(text);
        }else if (title.equals("Sector")){
            dto.setDetaialSector(text);
        }else if (title.equals("Supervisora")){
            dto.setSupervisora(text);
        }else if (title.equals("Entidad")){
            dto.setEntidad(text);
        }else if (title.contains("Inicio")){
            dto.setInicio_de_operaciones(text);
        }else if (title.contains("WEB")){
            dto.setPagina_WEB(text);
        }else if (title.contains("Correo")){
            dto.setCorreo_electronico(text);
        }else if (title.equals("Alta en SIPRES")){
            dto.setAlta_en_SIPRES(text);
        }else if (title.contains("instituciones")){
            dto.setRelaciones_con_otras_instituciones(text);
        }else if (title.contains("SIC")){
            dto.setDatos_de_contrato_con_SIC(text);
        }
        return dto;
    }
    private static String read(String fileName){
        try {
            if (StringUtils.isEmpty(fileName)) {
                fileName = "web.txt";
            }else {
                fileName = fileName+".html";
            }
            File file = new File(localUri + fileName);
            FileInputStream fin=new FileInputStream(file);
            InputStreamReader in=new InputStreamReader(fin,"utf-8"); //获取输入流
            BufferedReader br = new BufferedReader(in);
            char[] buf=new char[fin.available()];
            br.read(buf); //br的效率更高，带缓冲
            in.close();
            fin.close();
            return String.valueOf(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void writeWebJSp(){
        String url = "https://webapps.condusef.gob.mx/SIPRES/jsp/pub/resulbusq.jsp";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("tipo", "1"));
        params.add(new BasicNameValuePair("pnom", "SOFOM"));

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                String htmlContent = EntityUtils.toString(entity, "UTF-8");
                String filePath = localUri+"/web1.txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    writer.write(htmlContent);
                    System.out.println("文件写入成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("文件写入失败：" + e.getMessage());
                }
            }
        }catch (Exception e){

        }
    }
}
