package net.le.baseframe.web;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageBean implements Serializable {

    private static final long serialVersionUID = -8621490041493135800L;
    // 当前页
    private int pageNum = 1;
    // 每页数据
    private int pageSize = 10;
    // 总页数
    private int totalPage;
    // 总条数
    private int totalSize;
    // 数据
    private List<?> list;

    /**
     *
     * @param pageSize
     * @param totalSize
     * @return
     */
    public static int getTotalPage(int pageSize, int totalSize) {
        return totalSize % pageSize == 0 ? totalSize/pageSize : totalSize/pageSize + 1;
    }
}
