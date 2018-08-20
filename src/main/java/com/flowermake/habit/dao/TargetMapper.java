package com.flowermake.habit.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flowermake.habit.domain.Target;

public interface TargetMapper {
    int deleteByPrimaryKey(Long iId);

    int insert(Target record);

    int insertSelective(Target record);

    Target selectByPrimaryKey(Long iId);

    int updateByPrimaryKeySelective(Target record);

    int updateByPrimaryKey(Target record);
    
    List<Target> selectByCdt(@Param("uid")long uid, @Param("startCdt")Date startCdt,@Param("endCdt")Date endCdt,@Param("start")int start,@Param("end")int end);
    
    List<Target> selectByIndex(@Param("uid")long uid, @Param("index")byte index,@Param("flag")byte flag,@Param("start")int start,@Param("end")int end);
}