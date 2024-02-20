package com.shiqi.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiqi.wms.pojo.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductM extends BaseMapper<Product> {

    // 返回所有产品
    @Select("select * from product")
    List<Product> selectAll();

    // 删除一个产品
    @Delete("delete from product where p_id = #{id}")
    int deleteProductById(int id);

    // 根据pId查询产品
    @Select("select * from product where p_id = #{pId}")
    List<Product> selectProductById(int pId);
}
