package org.rbudzko.spring.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to be used as dynamic bean.
 */
public class DefaultPlayer {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultPlayer.class);
    private final String name;

    /**
     * This method is added to increase complexity, because often it is not possible to create beans by constructors.
     * For example eternal libraries force us to use builders or other creations not aligned with Spring's ways.
     */
    public static DefaultPlayer forName(final String name) {
        return new DefaultPlayer(name);
    }

    /**
     * Intentionally private, so default constructor or public constructor is not available for Spring.
     */
    private DefaultPlayer(final String name) {
        this.name = name;
    }

    /**
     * It's our test method :-)!
     */
    public void print() {
        LOGGER.info("I'm dynamic player named [{}].", name);
    }
}
