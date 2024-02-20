package com.shiqi.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TjM extends BaseMapper {
    @Select("SELECT in_id, w_name,in_type,in_amount,p_price FROM warehouse,inku,product WHERE warehouse.`w_id` = inku.`in_warehouse` AND inku.`p_id`=product.`p_id`")
    List<Map<String, Object>> inSteam();

    @Select("SELECT out_id, w_name,out_type,out_amount,p_price FROM warehouse,outku,product WHERE warehouse.`w_id` = outku.`out_warehouse` AND outku.`p_id`=product.`p_id`")
    List<Map<String, Object>> outSteam();

}
