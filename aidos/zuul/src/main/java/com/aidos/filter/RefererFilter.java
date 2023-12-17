package com.aidos.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

@Component
public class RefererFilter extends ZuulFilter {

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    //RequestContext ctx = RequestContext.getCurrentContext();
    //HttpServletRequest request = ctx.getRequest();
    //HttpServletResponse response = ctx.getResponse();

    //TODO: maybe log or something useful, else scrap the filter 

    return null;
  }

}