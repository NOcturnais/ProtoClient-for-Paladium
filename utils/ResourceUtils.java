package fr.noctu.haxx.proto.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

public class ResourceUtils {

    public static ByteBuffer loadIcon(URL url) throws IOException {

        try (InputStream is = url.openStream()) {

            PNGDecoder decoder = new PNGDecoder(is);
            ByteBuffer bb = ByteBuffer.allocateDirect(decoder.getWidth() * decoder.getHeight() * 4);
            decoder.decode(bb, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            bb.flip();

            return bb;

        }

    }

}
