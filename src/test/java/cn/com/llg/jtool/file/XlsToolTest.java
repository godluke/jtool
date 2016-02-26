package cn.com.llg.jtool.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by god.luke on 2016/1/23.
 */
public class XlsToolTest {

    @Test
    public void createXlsOSHead(){
        String path = this.getClass().getClassLoader().getResource(".").getPath() ;
        File f = new File(path+"test.xls") ;
        System.out.println(path+"test.xls");
        List<String> head = null ;
        head = new ArrayList<String>(10) ;
        for(int colN = 0 ;colN<10 ;colN++){
            head.add("col--"+0+"--"+colN) ;
        }
        try {
            XlsTool.createXlsOSHead(new FileOutputStream(f), head);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createXlsOS(){
        String path = this.getClass().getClassLoader().getResource(".").getPath() ;
        File f = new File(path+"testPage.xls") ;
        System.out.println(path+"testPage.xls");
        List<List<String>> page = new ArrayList<List<String>>(10) ;
        List<String> row = null ;
        for(int rowN = 0 ;rowN<10 ;rowN++){
            row = new ArrayList<String>(10) ;
            for(int colN = 0 ;colN<10 ;colN++){
                row.add("col"+rowN+"--"+colN) ;
            }
            page.add(row) ;
        }
        try {
            XlsTool.createXlsOS(new FileOutputStream(f),page);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void readXls() throws Exception{
        createXlsOSHead() ;
        String path = this.getClass().getClassLoader().getResource(".").getPath() ;
        File f = new File(path+"test.xls") ;
        List<List<String >> lst = XlsTool.readXls(new FileInputStream(f)) ;
        Assert.assertEquals(lst.get(0).get(0),"col--0--0");
        Assert.assertEquals(lst.get(0).get(9),"col--0--9");

    }

}
