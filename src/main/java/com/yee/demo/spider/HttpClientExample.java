package com.yee.demo.spider;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yee
 * 2024/6/26 10:45
 */
public class HttpClientExample {

    final static String localUri = "/Users/linyijun/Desktop/spider/";

    public static void main(String[] args) {
//        processHtml("");
        String httpUrl = "https://webapps.condusef.gob.mx/SIPRES/jsp/home_publico.jsp?idins=13519";
        HtmlDetailDTO dto = new HtmlDetailDTO();
        processDetail(httpUrl, dto);
    }

    private void processData(Element td){
        String text = td.text();
        if(StringUtils.isEmpty(text)){
            text = td.firstElementSibling().wholeText();
        }
        if(StringUtils.isEmpty(text)){
            text = td.nextElementSibling().wholeText();
        }
    }

    private void processTd(Elements tds) {
        HtmlDetailDTO dto = new HtmlDetailDTO();
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
            else if (i==1 && td.select("a").size() > 0){
                dto.setDenominacion_Social(text);
                Element a = td.select("a").first();
                String onclick = a.attr("onclick");
                int startIndex = onclick.indexOf("window.open('") + "window.open('".length();
                int endIndex = onclick.indexOf("'", startIndex);
                if (startIndex != -1 && endIndex != -1) {
                    String httpUrl = onclick.substring(startIndex, endIndex);
                    String httpGetUrl = httpUrl.replace("../../jsp", "https://webapps.condusef.gob.mx/SIPRES/jsp");
                    dto = processDetail(httpGetUrl, dto);
                }
            }
            else if (i==2){
                dto.setNombreCorto_o_comercial(text);
            }
            else if (i==3){
                dto.setEstatus(text);
            }
            else if (i==4){
                dto.setSector(text);
            }
            else if (i==5){
                dto.setEstado(text);
            }
            else if (i==6){
                dto.setUltima_Seccion_Actualizada(text);
            }
        }
    }
    private static void processHtml(String htmlContent){
        List<HtmlDetailDTO> list = Lists.newArrayList();
        LinkedList<LinkedList<Object>> dataList = new LinkedList<>();
        // 使用Jsoup解析HTML内容
        Document doc = Jsoup.parse(htmlContent);
        Elements trs = doc.select("table tr");
        for (Element tr : trs) {
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
                else if (i==1 && td.select("a").size() > 0){
                    dto.setDenominacion_Social(text);
                    Element a = td.select("a").first();
                    String onclick = a.attr("onclick");
                    int startIndex = onclick.indexOf("window.open('") + "window.open('".length();
                    int endIndex = onclick.indexOf("'", startIndex);
                    if (startIndex != -1 && endIndex != -1) {
                        String httpUrl = onclick.substring(startIndex, endIndex);
                        String httpGetUrl = httpUrl.replace("../../jsp", "https://webapps.condusef.gob.mx/SIPRES/jsp");
                        dto = processDetail(httpGetUrl, dto);
                    }
                }
                else if (i==2){
                    dto.setNombreCorto_o_comercial(text);
                }
                else if (i==3){
                    dto.setEstatus(text);
                }
                else if (i==4){
                    dto.setSector(text);
                }
                else if (i==5){
                    dto.setEstado(text);
                }
                else if (i==6){
                    dto.setUltima_Seccion_Actualizada(text);
                }
            }
            list.add(dto);
        }
//        EasyExcel.write("/Users/linyijun/Desktop/data.xlsx").sheet("模板").head(HtmlDetailDTO.class).doWrite(list);
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
                    Element hrTag = col.select("hr").first();
                    String text="";
                    if (hrTag != null) {
                        StringBuilder textAfterHr = new StringBuilder();
                        for (Node node : hrTag.siblingNodes()) {
                            if (node instanceof org.jsoup.nodes.TextNode) {
                                String value = ((TextNode) node).getWholeText().trim();
                                // 如果节点是TextNode，则获取其文本并去除前后的空白字符
                                text = textAfterHr.append(value).toString();
                            }
                        }
                    }
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
                    if (StringUtils.isEmpty(text) && hrTag!=null && hrTag.nextElementSibling()!=null){
                        text = hrTag.nextElementSibling().wholeText();
                    }
                    dto = setValue(title, text, dto);

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
        }else if (title.trim().equals("Estatus")){
            dto.setDetaialEstatus(text);
        }else if (title.trim().equalsIgnoreCase("RFC")){
            dto.setRFC(text);
        }else if (title.contains("Registro")){
            dto.setDetaial_Clave_de_Registro(text);
        }else if (title.trim().equals("Sector")){
            dto.setDetaialSector(text);
        }else if (title.trim().equals("Supervisora")){
            dto.setSupervisora(text);
        }else if (title.trim().equals("Entidad")){
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
                fileName = "web.text";
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
}
