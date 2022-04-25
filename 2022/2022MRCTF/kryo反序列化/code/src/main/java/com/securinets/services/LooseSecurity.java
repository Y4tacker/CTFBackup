//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.securinets.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.regex.Pattern;

public class LooseSecurity extends ObjectInputStream {
    public LooseSecurity(InputStream inputStream) throws IOException {

        super(inputStream);
    }

    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        if (!Pattern.matches("(com\\.securinets\\.(.*))|(java\\.(.*))|(java\\.time\\.(.*))", desc.getName())) {
            throw new InvalidClassException("Unauthorized deserialization attempt", desc.getName());
        } else {
            return super.resolveClass(desc);
        }
    }
}
