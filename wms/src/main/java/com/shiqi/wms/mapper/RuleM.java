package com.shiqi.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiqi.wms.pojo.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RuleM extends BaseMapper<Rule> {
    //根据uId获取一个角色
    @Select("select * from rule where u_id = #{uId}")
    List<Rule> selectById(int uId);
}
