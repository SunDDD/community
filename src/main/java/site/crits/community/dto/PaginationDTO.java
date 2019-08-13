package site.crits.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos
 * @description 页码
 * @Date 2019/8/12
 */

@Data
public class PaginationDTO {

    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showLastPage;

    private Integer page;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {

        int totalPage;

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }


        //1.是否有翻到上一页
        showPrevious = page != 1;

        //2.是否有翻到下一页
        showNext = !page.equals(totalPage);

        //3.显示的页数
        for (int i = -3; i < 4; i++) {
            int l = (page + i);
            if (l > 0 && l <= totalPage) {
                pages.add(l);
            }
        }

        //3.是否展示跳到第一页
        showFirstPage = !pages.contains(1);

        //4.是否展示跳到最后一页
        showLastPage = !pages.contains(totalPage);

        //5.当前页数
        this.page = page;



    }
}
