package com.yee.demo.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2020
 */
public class BaseRespository<M extends BaseMapper<T>, T> {

    @Autowired
    protected M baseMapper;

    public int insert(T entity) {
        return baseMapper.insert(entity);
    }

    public int updateById(T entity) {
        return baseMapper.updateById(entity);
    }

    public T selectById(Serializable id) {
        return (T) baseMapper.selectById(id);
    }

    public T selectOne(Wrapper<T> queryWrapper) {
        return (T) baseMapper.selectOne(queryWrapper);
    }

    public List<T> selectList(Wrapper<T> queryWrapper){
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 新增 并检测数据（PK、UK）是否重复
     *
     * @param entity
     * @return true:数据冲突Insert失败，false:数据Insert正常
     */
    public boolean insertForCheckDuplicate(T entity) {
        try {
            insert(entity);
        } catch (Exception e) {
            if (!DBUtil.isDuplicateEntry(e)) {
                throw e;
            }
            return true;
        }
        return false;
    }


    public T insertForCheckDuplicateReturn(T entity) {
        try {
            insert(entity);
        } catch (Exception e) {
            if (!DBUtil.isDuplicateEntry(e)) {
                throw e;
            }
        }
        return entity;
    }
}


