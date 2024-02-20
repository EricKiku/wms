package com.shiqi.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiqi.wms.pojo.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface InventoryM extends BaseMapper<Inventory> {

    // 返回所有库存
     @Select("SELECT * FROM inventory,product,warehouse WHERE inventory.p_id = product.p_id AND inventory.w_id = warehouse.w_id")
     List<Map<String,Object>> selectInventoryList();

     // 查询库存，根据p_id和w_id
    @Select("SELECT * FROM inventory WHERE p_id = #{p_id} AND w_id = #{w_id}")
    Inventory selectListByPIdAndWId(int p_id, int w_id);
    // 更新i_amount，根据p_id和w_id
    @Update("UPDATE inventory SET i_amount = #{i_amount} WHERE p_id = #{p_id} AND w_id = #{w_id}")
    int updateIAmountByPIdAndWId(int i_amount, int p_id, int w_id);
}
