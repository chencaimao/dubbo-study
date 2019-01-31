package com.ccm.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by chencm on 2018/11/15
 */
public interface BaseMapper<T,DbIdKey> {
    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 更新数据
     *
     * @param record
     * @return
     */
    int update(T record);

    /**
     * 查询基本信息
     *
     * @param id
     * @return
     */
    T find(@Param("id") DbIdKey id);

    /**
     * 查询全部
     *
     * @return
     */
    List<T> findAll(Map<String, Object> map);

    /**
     * 查询全部
     *
     * @return
     */
    List<T> findAllByCondition(Map<String, Object> map);

    /**
     * 查询分页列表
     *
     * @param map
     * @return
     */
    List<T> findList(Map<String, Object> map);

    /**
     * 查询符合条件的一条数据
     *
     * @param map
     * @return
     */
    T findFirst(Map<String, Object> map);

    /**
     * 查看数量
     *
     * @param map
     * @return
     */
    Integer findCount(Map<String, Object> map);

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    int delete(@Param("id") DbIdKey id);

    /**
     * 批量逻辑删除
     *
     * @param idList
     * @return
     */
    int deleteByIds(List<DbIdKey> idList);

    /**
     * 物理删除
     *
     * @param id
     * @return
     */
    int deletePhysically(@Param("id") DbIdKey id);

    /**
     * 批量物理删除
     *
     * @param idList
     * @return
     */
    int deletePhysicallyByIds(List<DbIdKey> idList);
}
