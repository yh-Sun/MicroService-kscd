package com.kscd.decorator.sun;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClogInputStream extends FilterInputStream {
    public ClogInputStream(InputStream in) {
        super( in );
        System.out.println( "Clog.read()----这里输出的是中文" );
    }

    public int read() throws IOException {

        return in.read();
    }


}
