package com.ogunheper.microservices.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ProxyRouteLocator.ProxyRouteSpec;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Slf4j
@Component
public class SimpleAuthorizationFilter extends ZuulFilter {

    @Autowired
    private ProxyRouteLocator proxyRouteLocator;

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
        final ProxyRouteSpec route = proxyRouteLocator.getMatchingRoute(requestURI);

        log.info("requestContext: " + requestContext.toString());
        log.info("requestURI    : " + requestURI.toString());
        log.info("route         : " + route.toString());

        if (route != null) {
            final String authorization = requestContext.getRequest().getHeader("Authorization");

            if (authorization != null && authorization.contains("token")) {
                requestContext.addZuulRequestHeader("X-UserId", String.valueOf(new Random().nextLong()));
            } else {
                requestContext.setSendZuulResponse(false);
                requestContext.set("error.status_code", HttpServletResponse.SC_FORBIDDEN);
            }
        }
        return null;
    }
}
