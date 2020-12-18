package grpc.trade.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import grpc.trade.dao.TradedetailDao;
import grpc.trade.dto.TradeDtlsDto;
import grpc.trade.proto.ReplyTradeDetail;
import grpc.trade.proto.ReplyTradeDetailList;
import grpc.trade.proto.RequestId;
import grpc.trade.proto.RequestTradeId;
import grpc.trade.proto.TradeDetailGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TradeDetailServer extends TradeDetailGrpc.TradeDetailImplBase {

	@Autowired
	TradedetailDao tradedetailDao;

	@Override
	public void getTradeDetail(RequestId request, StreamObserver<ReplyTradeDetail> responseObserver) {

		TradeDtlsDto tradeDtls = tradedetailDao.getTradeDtlsById(request.getId());
		grpc.trade.proto.ReplyTradeDetail.Builder builder = ReplyTradeDetail.newBuilder();
		BeanUtils.copyProperties(tradeDtls, builder);
		ReplyTradeDetail replyTradeDetail = builder.build();
		responseObserver.onNext(replyTradeDetail);
		responseObserver.onCompleted();

	}

	@Override
	public void getTradeDetailByTradeIds(RequestTradeId request,
			StreamObserver<ReplyTradeDetailList> responseObserver) {
		List<TradeDtlsDto> tradeDtlsList = tradedetailDao.getTradeDtlsByTradeId(request.getTradeIdList());
		grpc.trade.proto.ReplyTradeDetailList.Builder builderList = ReplyTradeDetailList.newBuilder();
		for(TradeDtlsDto tradeDtls : tradeDtlsList){
			grpc.trade.proto.ReplyTradeDetail.Builder builder = ReplyTradeDetail.newBuilder();
			BeanUtils.copyProperties(tradeDtls, builder);
			ReplyTradeDetail replyTradeDetail = builder.build();
			builderList.addTradeDetailList(replyTradeDetail);
		}
		ReplyTradeDetailList replyTradeDetailList = builderList.build();
		responseObserver.onNext(replyTradeDetailList);
		responseObserver.onCompleted();
	}

}
