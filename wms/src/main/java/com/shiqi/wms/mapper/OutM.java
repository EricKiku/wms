package com.shiqi.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiqi.wms.pojo.In;
import com.shiqi.wms.pojo.Out;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OutM extends BaseMapper<Out> {
    @Delete("delete from outku where out_id = #{id}")
    int deleteInById(int id);

    @Select("SELECT * FROM outku WHERE out_id = #{id}")
    List<Out> selectListById(int id);
}
