package com.bin.gateway.filter;



import com.bin.gateway.task.ConstantConfig;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

 
public class PreRequestLogFilter extends ZuulFilter {

		
	  @Override
	  public String filterType() {
	    return "pre";
	  }
	  @Override
	  public int filterOrder() {
	    return 6;
	  }
	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }
	  @Override
	  public Object run() {
		  
		  RequestContext ctx = RequestContext.getCurrentContext();
		//  URL kk=ctx.getRouteHost();
		  
		  if(ConstantConfig.LIMITER.tryAcquire()) {//控制qps
			    
			    
			    ctx.addZuulRequestHeader("Accept", "application/json;charset=UTF-8");
			    //   ctx.addZuulRequestHeader("x-forwarded-proto","http");
				//ctx.setSendZuulResponse(true);  
		        //ctx.setResponseStatusCode(200);  
		        //ctx.set("isSuccess", true);           			
			    return null;
	        } else {
	        	  //超过请求频率直接返回服务器繁忙
	              ctx.addZuulResponseHeader("Content-Type", "application/json;charset=UTF-8");
	        	  ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由  
	        	  ctx.setResponseStatusCode(505);// 返回错误码  
	        	  String text="{\"msg\":\"服务器繁忙!\",\"code\":505,\"status\":false}";
	        	  ctx.setResponseBody(text);// 返回错误内容  
	        	  ctx.set("isSuccess", false);  
	        	  return null;  
	        }
	  
	  }
	}