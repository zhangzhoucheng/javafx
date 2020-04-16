package com.zz.test.javafxmvn.common.entity.mapper;

import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.common.entity.PyProcessExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;


@Mapper
public interface PyProcessMapper {
    long countByExample(PyProcessExample example);

    int deleteByExample(PyProcessExample example);

    int deleteByPrimaryKey(Integer processId);

    int insert(PyProcess record);

    int insertSelective(PyProcess record);

    List<PyProcess> selectByExample(PyProcessExample example);

    PyProcess selectByPrimaryKey(Integer processId);

    int updateByExampleSelective(@Param("record") PyProcess record, @Param("example") PyProcessExample example);

    int updateByExample(@Param("record") PyProcess record, @Param("example") PyProcessExample example);

    int updateByPrimaryKeySelective(PyProcess record);

    int updateByPrimaryKey(PyProcess record);
}