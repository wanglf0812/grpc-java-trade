package grpc.trade.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import grpc.trade.dto.TradeDto;

@Component
@Mapper
public interface TradeDao {

	public TradeDto getTradeById(@Param("id") Long id);
	
}
