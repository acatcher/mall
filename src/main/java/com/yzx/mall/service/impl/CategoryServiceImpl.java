package com.yzx.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzx.mall.exception.MallException;
import com.yzx.mall.exception.MallExceptionCode;
import com.yzx.mall.model.dao.CategoryMapper;
import com.yzx.mall.model.pojo.Category;
import com.yzx.mall.model.request.AddCategoryReq;
import com.yzx.mall.model.request.UpdateCategoryReq;
import com.yzx.mall.model.vo.CategoryVO;
import com.yzx.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public void addCategory(AddCategoryReq addCategoryReq){
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryReq, category);

        // check same name
        Category old = categoryMapper.selectByName(category.getName());
        if(old != null){
            throw new MallException(MallExceptionCode.CATEGORY_EXISTS);
        }

        int count = categoryMapper.insertSelective(category);

        if(count == 0){
            throw new MallException(MallExceptionCode.FAIL_INSERT);
        }

    }


    @Override
    public void updateCategory(UpdateCategoryReq updateCategoryReq){
        Category category = new Category();
        BeanUtils.copyProperties(updateCategoryReq, category);
        Category old = categoryMapper.selectByName(category.getName());

        if(old!=null && !old.getId().equals(category.getId())){
            throw new MallException(MallExceptionCode.CATEGORY_EXISTS);
        }

        int count = categoryMapper.updateByPrimaryKeySelective(category);

        if(count == 0){
            throw new MallException(MallExceptionCode.FAIL_INSERT);
        }

    }

    @Override
    public void deleteCategory(Integer id){

        int i = categoryMapper.deleteByPrimaryKey(id);
        if(i == 0){
            throw new MallException(MallExceptionCode.DELETE_FAILED);
        }
    }

    @Override
    public PageInfo getCategoryListAdmin(Integer pageNum, Integer pageSize){

        PageHelper.startPage(pageNum, pageSize, "type, order_num");
        List<Category> categories = categoryMapper.selectList();
        PageInfo pageInfo = new PageInfo(categories);

        return pageInfo;
    }

    @Override
    @Cacheable(value = "categoryList", key="#parentId + 'seeker'")
    public List<CategoryVO> getListUser(Integer parentId){

        return getCategoryListUserHelper(parentId);
    }

    public List<CategoryVO> getCategoryListUserHelper(Integer parentId){
        List<Category> categories = categoryMapper.selectListByPID(parentId);
        // no child
        if(categories == null || categories.size() == 0){
            return null;
        }

        // find child
        List<CategoryVO> list = new ArrayList<>();
        for(Category category: categories){
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category, categoryVO);
            categoryVO.setList(getCategoryListUserHelper(categoryVO.getId()));
            list.add(categoryVO);
        }
        return list;

    }


}
