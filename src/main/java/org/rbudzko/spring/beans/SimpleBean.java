package org.rbudzko.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;

/**
 * Normal bean created by @Component, wired by @Autowire constructor and finally initialized by @PostConstruct.
 */
@Component
public class SimpleBean {
    private final Collection<DefaultPlayer> dynamics;

    /**
     * This bean autowire using standard ways. It's a proof that we example wires dynamics with statics.
     */
    @Autowired
    public SimpleBean(final Collection<DefaultPlayer> dynamics) {
        this.dynamics = Collections.unmodifiableCollection(dynamics);
    }

    /**
     * Call dynamic beans resolved as dependencies.
     */
    @PostConstruct
    public void init() {
        dynamics.forEach(DefaultPlayer::print);
    }
}
