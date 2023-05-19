package com.jf.map;

import com.jf.dto.PayTypeNew;
import com.jf.entity.PayType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;

/**
 * @author 86136
 * @version 1.0
 * @description TODO
 * @date 2023/5/19 17:29
 */
@Mapper
public interface PayTypeMapper {

    PayTypeMapper INSTANCE = Mappers.getMapper(PayTypeMapper.class);
    @ValueMappings({
            @ValueMapping(source = "ALIPAY", target = "NETWORK"),
            @ValueMapping(source = "WEPAY", target = "NETWORK"),
            @ValueMapping(source = "DIGITAL_CASH", target = "CASH"),
            // MappingConstants.ANY_REMAINING表示剩下其它的源枚举和目标枚举对应不上的全部映射为指定的枚举对象
            @ValueMapping(source = MappingConstants.ANY_REMAINING, target = "CARD")
    })
    PayTypeNew payTypeToPayTypeNew(PayType payType);
}
