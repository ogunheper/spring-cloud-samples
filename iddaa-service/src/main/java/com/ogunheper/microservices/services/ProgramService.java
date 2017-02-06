package com.ogunheper.microservices.services;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Random;

@Component
public class ProgramService {

    private static final Random random = new Random();

    @HystrixCommand(fallbackMethod = "emptyListOfEvents")
    public Collection<String> getListOfActiveEvents() {
        if (requestMustFail()) {
            throw new RuntimeException("Can not reach active event list");
        }

        return Lists.newArrayList(
                "Fenerbahçe - Galatasaray",
                "Beşiktaş - Trabzonspor"
        );
    }

    @SuppressWarnings("unused")
    private Collection<String> emptyListOfEvents() {
        return Lists.newArrayListWithCapacity(0);
    }

    private boolean requestMustFail() {
        return (random.nextDouble() < 0.9d);
    }
}
