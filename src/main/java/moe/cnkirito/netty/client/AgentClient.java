package moe.cnkirito.netty.client;

import io.netty.channel.Channel;
import moe.cnkirito.netty.model.AgentFuture;
import moe.cnkirito.netty.model.AgentRequest;
import moe.cnkirito.netty.model.AgentRequestHolder;

/**
 * @author 徐靖峰[OF2938]
 * company qianmi.com
 * Date 2018-05-17
 */
public class AgentClient {

    private AgentClientConnecManager connectManager;

    public AgentClient(){
        this.connectManager = new AgentClientConnecManager();
    }

    public Object invoke(String interfaceName, String method, String parameterTypesString, String parameter) throws Exception {
        Channel channel = connectManager.getChannel();
        AgentRequest agentRequest = new AgentRequest();
        agentRequest.setInterfaceName(interfaceName);
        agentRequest.setMethod(method);
        agentRequest.setParameterTypesString(parameterTypesString);
        agentRequest.setParameter(parameter);
        AgentFuture future = new AgentFuture();
        AgentRequestHolder.put(agentRequest.getId(), future);
        channel.writeAndFlush(agentRequest);
        Object result = null;
        try{
            result = future.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
