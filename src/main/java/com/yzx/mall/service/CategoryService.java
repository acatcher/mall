package com.yzx.mall.service;

import com.github.pagehelper.PageInfo;
import com.yzx.mall.model.request.AddCategoryReq;
import com.yzx.mall.model.request.UpdateCategoryReq;
import com.yzx.mall.model.vo.CategoryVO;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

public interface CategoryService {
    void addCategory(AddCategoryReq addCategoryReq);

    void updateCategory(UpdateCategoryReq updateCategoryReq);

    void deleteCategory(Integer id);

    PageInfo getCategoryListAdmin(Integer pageNum, Integer pageSize);


    @Cacheable("categoryList")
    List<CategoryVO> getListUser(Integer parentId);
}
