package com.kscd.decorator.sun;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ElogInputStream extends FilterInputStream {
    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected ElogInputStream(InputStream in) {
        super(in);
        System.out.println( "Elog.read()-----there will change the style of Character!" );
    }

    public final int read() throws IOException {
        int c = 0;
        if((c = super.read()) != -1) {
            if(Character.isLowerCase(c)) {
                return Character.toUpperCase(c);
            } else if(Character.isUpperCase(c)) {
                return Character.toLowerCase(c);
            } else {
                return c;
            }
        } else {
            return -1;
        }
    }
}
