package com.yee.demo.spider;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author yee
 * 2024/6/26 14:48
 */
@Data
public class HtmlDetailDTO {
    @ExcelProperty("Clave de Registro")
    private String Clave_de_Registro;
    @ExcelProperty("Denominacion Social")
    private String Denominacion_Social;
    @ExcelProperty("Nombre Corto o comercial")
    private String NombreCorto_o_comercial;
    @ExcelProperty("Estatus")
    private String Estatus;
    @ExcelProperty("Sector")
    private String Sector;
    @ExcelProperty("Estado")
    private String Estado;
    @ExcelProperty("Última Sección Actualizada")
    private String Ultima_Seccion_Actualizada;
    @ExcelProperty("No Localizable")
    private String NoLocalizable;
    @ExcelProperty("Actualizados el")
    private String Actualizados_el;
    @ExcelProperty("Nombre corto")
    private String Nombre_corto;
    @ExcelProperty("Estatus")
    private String detaialEstatus;
    @ExcelProperty("RFC")
    private String RFC;
    @ExcelProperty("Clave de Registro")
    private String detaial_Clave_de_Registro;
    @ExcelProperty("Sector")
    private String detaialSector;
    @ExcelProperty("Supervisora")
    private String Supervisora;
    @ExcelProperty("Entidad")
    private String Entidad;
    @ExcelProperty("Inicio de operaciones")
    private String Inicio_de_operaciones;
    @ExcelProperty("Página_WEB")
    private String Pagina_WEB;
    @ExcelProperty("Correo electrónico")
    private String Correo_electronico;
    @ExcelProperty("Alta en SIPRES")
    private String Alta_en_SIPRES;
    @ExcelProperty("Relaciones con otras instituciones")
    private String Relaciones_con_otras_instituciones;
    @ExcelProperty("Datos de contrato con SIC")
    private String Datos_de_contrato_con_SIC;
    @ExcelProperty("logo")
    private String logo;
}
