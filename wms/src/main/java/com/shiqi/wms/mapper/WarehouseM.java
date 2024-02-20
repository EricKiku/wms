package com.shiqi.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiqi.wms.pojo.Warehouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WarehouseM extends BaseMapper<Warehouse> {
    //返回所有仓库
    @Select("select * from warehouse")
    List<Warehouse> selectAll();
    @Select("select * from warehouse where w_id = #{id}")
    List<Warehouse> selectWarehouseById(int id);
}
