package com.lx.community.dto;

import com.lx.community.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDto {
    private List<QuestionDto> questionDtoList ;
    private boolean showFirst;
    private boolean showPre;
    private boolean showNext;
    private boolean showEnd;
    private List<Integer> pages = new ArrayList<>();
    private Integer page ;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage;
        if(totalCount % size == 0)
            totalPage = totalCount / size;
        else
            totalPage = totalCount / size + 1 ;
        if(page < 1)
            page = 1 ;
        if(page > totalPage )
            page = totalPage;
        this.page = page;
        //固定展示5页
        for (int i = 0; i < 5; i++) {
            if((page + i) <= totalPage )
                pages.add(page + i);
        }
        //是否展示上一页选项
        if(page == 1)
            showPre = false ;
        else
            showPre = true;
        //是否展示下一页选项
        if(page == totalPage)
            showNext = false;
        else
            showNext = true;
        //是否展示返回首页选项
        if(pages.contains(1))
            showFirst = false ;
        else
            showFirst = true ;
        //是否展示尾页选项
        if(pages.contains(totalPage))
            showEnd = false;
        else
            showEnd = true;
    }
}
