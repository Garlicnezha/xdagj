//package io.xdag.rpc.modules.xdag.subscribe;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import io.netty.channel.ChannelHandlerContext;
//import io.xdag.rpc.jsonrpc.JsonRpcResultOrError;
//import io.xdag.rpc.jsonrpc.JsonRpcVersion;
//import io.xdag.rpc.modules.XdagJsonRpcMethod;
//import io.xdag.rpc.modules.XdagJsonRpcRequest;
//import io.xdag.rpc.modules.XdagJsonRpcRequestVisitor;
//
//import java.util.Objects;
//
//public class XDAGSubscribeRequest extends XdagJsonRpcRequest {
//
//    private final XdagSubscribeParams params;
//
//    @JsonCreator
//    public XDAGSubscribeRequest(
//            @JsonProperty("jsonrpc") JsonRpcVersion version,
//            @JsonProperty("method") XdagJsonRpcMethod method,
//            @JsonProperty("id") Integer id,
//            @JsonProperty("params") XdagSubscribeParams params) {
//        super(version, verifyMethod(method), Objects.requireNonNull(id));
//        this.params = Objects.requireNonNull(params);
//    }
//
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public XdagSubscribeParams getParams() {
//        return params;
//    }
//
//    @Override
//    public JsonRpcResultOrError accept(XdagJsonRpcRequestVisitor visitor, ChannelHandlerContext ctx) {
//        return visitor.visit(this, ctx);
//    }
//
//    private static XdagJsonRpcMethod verifyMethod(XdagJsonRpcMethod method) {
//        if (method != XdagJsonRpcMethod.ETH_SUBSCRIBE) {
//            throw new IllegalArgumentException(
//                    "Wrong method mapped to eth_subscribe. Check JSON mapping configuration in JsonRpcRequest."
//            );
//        }
//        return method;
//    }
//}
