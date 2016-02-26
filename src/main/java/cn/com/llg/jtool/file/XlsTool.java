package cn.com.llg.jtool.file;

import cn.com.llg.jtool.exception.RunTime;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 在列头需要一个列结束标志
 * Created by god.luke on 2016/1/23.
 */
public class XlsTool {

    public static String ColumnOver = "OVER" ;


    /**
     * 生成只带列头和列结束标志的xls文件
     * @param os
     * @param data
     */
    public static void createXlsOSHead(OutputStream os,List<String> data){
        List<List<String>> page = new ArrayList<List<String>>(1) ;
        data.add(ColumnOver) ;
        page.add(data) ;
        createXlsOS(os,page) ;
    }


    /**
     * 建立xls文件到指定的输出流中<br>
     * 并在xls中写入传入的指定数据<br>
     * 数据指定格式List<List<String>> 内层List<String>是每一行中显示的内容,也就是说list每个数据是一个格，先被list add的写在前面<br>
     * @param os
     * @param data
     */
    public static void createXlsOS(OutputStream os,List<List<String>> data ){

        try {
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(os) ;
            WritableSheet writableSheet = writableWorkbook.createSheet("Sheet0", 0) ;

            Label cell = null ;
            List<String> colLst = null ;
            for(int row = 0 ;row<data.size() ;row++){
                colLst = data.get(row) ;
                for(int col = 0 ;col<colLst.size() ;col++){
                    cell = new Label(col, row, colLst.get(col)) ;
                    writableSheet.addCell(cell);
                }
            }
            writableWorkbook.write();
            writableWorkbook.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            throw RunTime.create(e.getMessage()) ;
        }
    }

    /**
     * 读取xls文件，并返回 List<List<String>> 类型结果，xls文件中每一行对应一个List<String>
     * @param inputStream
     * @return
     */
    public static List<List<String>> readXls(InputStream inputStream){
        Workbook book = null ;

        try {
            book = Workbook.getWorkbook(inputStream) ;//
        } catch (Exception e) {
            throw RunTime.create(e.getMessage()) ;
        }

        Sheet sheet = book.getSheet(0);
        int columns = getColumns(sheet);
        List<List<String>> lstSheet = new ArrayList<List<String>>(sheet.getRows()) ;
        List<String> row = null ;

        for(int r =0; r< sheet.getRows() ;r++){
            row = new ArrayList<String>(columns) ;
            for(int c = 0 ;c<columns;c++ ){
                row.add(sheet.getCell(c, r).getContents().trim()) ;
            }
            lstSheet.add(row) ;
        }
        return lstSheet ;
    }

    /**
     * 返回有效列数
     * @param sheet
     * @return
     */
    private static int getColumns(Sheet sheet){
        Cell[]  row = sheet.getRow(0) ;
        for(int i = 0 ;i<row.length ;i++){
            if(ColumnOver.equals(row[i].getContents().trim())){
                return i ;
            }
        }
        throw RunTime.create( "xls文件没有列结束标志") ;
    }
}
