package cn.com.llg.jtool.clazz;

import java.util.Date;

/**
 * Created by god.luke on 2016/1/23.
 */
public class TU {

    public TU() {
    }

    public TU(String strA, Long lngB, Date dtC) {
        this.strA = strA;
        this.lngB = lngB;
        this.dtC = dtC;
    }

    private String strA ;
    private Long lngB ;
    private Date dtC ;

    public String getStrA() {
        return strA;
    }

    public void setStrA(String strA) {
        this.strA = strA;
    }

    public Long getLngB() {
        return lngB;
    }

    public void setLngB(Long lngB) {
        this.lngB = lngB;
    }

    public Date getDtC() {
        return dtC;
    }

    public void setDtC(Date dtC) {
        this.dtC = dtC;
    }
}
