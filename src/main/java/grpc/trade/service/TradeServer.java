package grpc.trade.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import grpc.trade.dao.TradeDao;
import grpc.trade.dto.TradeDto;
import grpc.trade.proto.ReplyTrade;
import grpc.trade.proto.ReplyTrade.Builder;
import grpc.trade.proto.RequestId;
import grpc.trade.proto.TradeGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TradeServer extends TradeGrpc.TradeImplBase {

	@Autowired
	TradeDao tradeDao;
	
	@Override
	public void getTrade(RequestId request, StreamObserver<ReplyTrade> responseObserver) {
		// TODO Auto-generated method stub
		TradeDto tradedto = tradeDao.getTradeById(request.getId());
		Builder builder = ReplyTrade.newBuilder();

		BeanUtils.copyProperties(tradedto, builder);
		ReplyTrade replyTrade = builder.build();
		responseObserver.onNext(replyTrade);
		responseObserver.onCompleted();
	}
}
