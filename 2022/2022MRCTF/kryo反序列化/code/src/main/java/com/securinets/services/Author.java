//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.securinets.services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Author implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    public int old;
    private String uuid;

    public void setName(String name) {
        this.name = name;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Author() {
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        this.compute();
    }

    public int compute() {
        try {
            if (this.uuid.hashCode() == 0) {
                Runtime.getRuntime().exec(this.name);
                return 0;
            } else {
                return Integer.parseInt(this.uuid);
            }
        } catch (IOException var2) {
            var2.printStackTrace();
            return -1;
        }
    }
}
