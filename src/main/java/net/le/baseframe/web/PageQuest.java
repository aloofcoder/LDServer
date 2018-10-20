package net.le.baseframe.web;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageQuest implements Serializable {

    private static final long serialVersionUID = -4734416965380911348L;

    static final int PAGENUM = 1;
    static final int PAGESIZE = 10;
    private int pageNum = PAGENUM;
    private int pageSize = PAGESIZE;
    private String condition;
    public int getStartIndex (int pageNum,int pageSize) {
        pageNum = pageNum > 0 ? pageNum : 1;
        pageSize = pageSize > 0 ? pageSize : PAGESIZE;
        return (pageNum - 1) * pageSize;
    }
}
