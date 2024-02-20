package com.shiqi.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiqi.wms.pojo.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InM extends BaseMapper<In> {
    @Delete("delete from inku where in_id = #{id}")
    int deleteInById(int id);

    @Select("SELECT * FROM inku WHERE in_id = #{id}")
    List<In> selectListById(int id);
}
