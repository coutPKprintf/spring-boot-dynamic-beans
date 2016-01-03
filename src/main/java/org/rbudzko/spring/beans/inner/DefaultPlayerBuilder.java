package org.rbudzko.spring.beans.inner;

/**
 * Example builder.
 */
public class DefaultPlayerBuilder {
    /**
     * This method is added to increase complexity, because often it is not possible to create beans by constructors.
     * For example eternal libraries force us to use builders or other creations not aligned with Spring's ways.
     */
    public static DefaultPlayer forName(String name) {
        return new DefaultPlayer(name);
    }
}
