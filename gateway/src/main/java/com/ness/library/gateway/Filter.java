package com.ness.library.gateway;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;


public class Filter extends ZuulFilter {


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("x-location", "India");
        return null;
    }
}