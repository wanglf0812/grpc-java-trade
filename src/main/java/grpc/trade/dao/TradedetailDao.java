package grpc.trade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import grpc.trade.dto.TradeDtlsDto;

@Component
@Mapper
public interface TradedetailDao {
	
	public TradeDtlsDto getTradeDtlsById(@Param("id") Long id);
	
	public List<TradeDtlsDto> getTradeDtlsByTradeId(List<Long> tradeIdList);

}
