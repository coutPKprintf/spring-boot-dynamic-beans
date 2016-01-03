package org.rbudzko.spring.beans;

import org.rbudzko.spring.beans.inner.Player;
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
    private final Collection<Player> dynamics;

    /**
     * This bean autowire using standard ways. It's a proof that we example wires dynamics with statics.
     */
    @Autowired
    public SimpleBean(final Collection<Player> dynamics) {
        this.dynamics = Collections.unmodifiableCollection(dynamics);
    }

    /**
     * Call dynamic beans resolved as dependencies.
     */
    @PostConstruct
    public void init() {
        dynamics.forEach(Player::print);
    }
}
