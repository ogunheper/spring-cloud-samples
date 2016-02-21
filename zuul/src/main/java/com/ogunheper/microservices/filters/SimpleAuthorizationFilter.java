package com.ogunheper.microservices.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ProxyRouteLocator.ProxyRouteSpec;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Component
public class SimpleAuthorizationFilter extends ZuulFilter {

    @Autowired
    private ProxyRouteLocator routeLocator;

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

        final RequestContext requestContext = RequestContext.getCurrentContext();
        final String requestURI = requestContext.getRequest().getRequestURI();
        final ProxyRouteSpec route = routeLocator.getMatchingRoute(requestURI);

        System.out.println("requestContext: " + requestContext.toString());
        System.out.println("requestURI    : " + requestURI.toString());
        System.out.println("route         : " + route.toString());

        if (route != null) {
            final String authorization = requestContext.getRequest().getHeader("Authorization");

            if (authorization != null && authorization.contains("token")) {
                requestContext.addZuulRequestHeader("X-UserId", String.valueOf(new Random().nextLong()));
            } else {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpServletResponse.SC_FORBIDDEN);
                requestContext.set("error.status_code", HttpServletResponse.SC_FORBIDDEN);
            }
        }
        return null;
    }
}
