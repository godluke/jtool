package cn.com.llg.jtool.action;

/**
 *
 * 分面参数
 * Created by god.luke on 2016/1/23.
 */
public class Page {

    /**
     * 起始行号
     */
    int start = 0 ;

    /**
     * 起始页号
     */
    int page = 1 ;

    /**
     * 每面行数
     */
    int limit = 20 ;

    /**
     * 总条数
     */
    long count = -1 ;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
