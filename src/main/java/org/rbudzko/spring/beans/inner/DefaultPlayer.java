package org.rbudzko.spring.beans.inner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to be used as dynamic bean.
 */
class DefaultPlayer implements Player{
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultPlayer.class);
    private final String name;

    /**
     * Intentionally package, so default constructor or public constructor is not available for Spring.
     */
    DefaultPlayer(final String name) {
        this.name = name;
    }

    /**
     * It's our test method :-)!
     */
    @Override
    public void print() {
        LOGGER.info("I'm dynamic player named [{}].", name);
    }
}
